<?php

use Illuminate\Database\Seeder;
use t1st3\JSONMin\JSONMin;

class RegencyGeojsonSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $regencies = \App\Regency::select('id')->get();
        DB::table('regency_geojson')->delete();
        foreach ($regencies as $regency) {
          $filename = base_path('../data/geojson/regency/' . $regency->id . '.geojson' );
          $exist = \File::exists($filename);
          if ($exist) {
            $contents = \File::get($filename);
            $geojson = JSONMin::minify($contents);

            DB::transaction(function () use ($regency, $geojson) {
              $regencyGeojson = new \App\RegencyGeojson();
              $regencyGeojson->regency_id = $regency->id;
              $regencyGeojson->geojson = $geojson;
              $regencyGeojson->save();
            });
          }
        }
    }
}
