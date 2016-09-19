<?php

use Illuminate\Database\Seeder;
use t1st3\JSONMin\JSONMin;

class VillageGeojsonSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $villages = \App\Village::select('id')->get();
        DB::table('village_geojson')->delete();
        foreach ($villages as $village) {
          $filename = base_path('../data/geojson/village/' . $village->id . '.geojson' );
          $exist = \File::exists($filename);
          if ($exist) {
            $contents = \File::get($filename);
            $geojson = JSONMin::minify($contents);

            DB::transaction(function () use ($village, $geojson) {
              $villageGeojson = new \App\VillageGeojson();
              $villageGeojson->village_id = $village->id;
              $villageGeojson->geojson = $geojson;
              $villageGeojson->save();
            });
          }
        }
    }
}
