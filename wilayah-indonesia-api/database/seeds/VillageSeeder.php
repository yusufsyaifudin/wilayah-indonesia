<?php

use Illuminate\Database\Seeder;

class VillageSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $village_path = base_path('../data/list_of_area/villages.json');
        $villages = \File::get($village_path);
        $villages = json_decode($villages);
        
        DB::transaction(function () use ($villages) {
          DB::table('villages')->delete();
          foreach ($villages as $village) {
            $data = [
              'id' => $village->id,
              'district_id' => $village->district_id,
              'name' => $village->name,
              'longitude' => $village->longitude,
              'latitude' => $village->latitude
            ];
            DB::table('villages')->insert($data);
          }
        });
    }
}
