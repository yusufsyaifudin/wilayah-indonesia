<?php

use Illuminate\Database\Seeder;
use t1st3\JSONMin\JSONMin;

class DistrictGeojsonSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $districts = \App\District::select('id')->get();
        DB::table('district_geojson')->delete();
        foreach ($districts as $district) {
          $filename = base_path('../data/geojson/district/' . $district->id . '.geojson' );
          $exist = \File::exists($filename);
          if ($exist) {
            $contents = \File::get($filename);
            $geojson = JSONMin::minify($contents);

            DB::transaction(function () use ($district, $geojson) {
              $districtGeojson = new \App\DistrictGeojson();
              $districtGeojson->district_id = $district->id;
              $districtGeojson->geojson = $geojson;
              $districtGeojson->save();
            });
          }
        }
    }
}
