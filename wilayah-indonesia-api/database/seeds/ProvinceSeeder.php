<?php

use Illuminate\Database\Seeder;

class ProvinceSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $province_path = base_path('../data/list_of_area/provinces.json');
        $provinces = \File::get($province_path);
        $provinces = json_decode($provinces);
        
        DB::transaction(function () use ($provinces) {
          DB::table('provinces')->delete();
          foreach ($provinces as $province) {
            $data = [
              'id' => $province->id,
              'name' => $province->name,
              'longitude' => $province->longitude,
              'latitude' => $province->latitude
            ];
            DB::table('provinces')->insert($data);
          }
        });
    }
}
