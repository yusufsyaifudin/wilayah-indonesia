<?php

use Illuminate\Database\Seeder;

class DistrictSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $district_path = base_path('../data/list_of_area/districts.json');
        $districts = \File::get($district_path);
        $districts = json_decode($districts);
        
        DB::transaction(function () use ($districts) {
          DB::table('districts')->delete();
          foreach ($districts as $district) {
            $data = [
              'id' => $district->id,
              'regency_id' => $district->regency_id,
              'name' => $district->name,
              'longitude' => $district->longitude,
              'latitude' => $district->latitude
            ];
            DB::table('districts')->insert($data);
          }
        });
    }
}
