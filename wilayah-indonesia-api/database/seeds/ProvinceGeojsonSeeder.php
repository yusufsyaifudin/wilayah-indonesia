<?php

use Illuminate\Database\Seeder;
use t1st3\JSONMin\JSONMin;

class ProvinceGeojsonSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $provinces = \App\Province::select('id')->get();
        DB::table('province_geojson')->delete();
        foreach ($provinces as $province) {
          $filename = base_path('../data/geojson/province/' . $province->id . '.geojson' );
          $exist = \File::exists($filename);
          if ($exist) {
            $contents = \File::get($filename);
            $geojson = JSONMin::minify($contents);

            DB::transaction(function () use ($province, $geojson) {
              $provinceGeojson = new \App\ProvinceGeojson();
              $provinceGeojson->province_id = $province->id;
              $provinceGeojson->geojson = $geojson;
              $provinceGeojson->save();
            });
          }
        }
    }
}
