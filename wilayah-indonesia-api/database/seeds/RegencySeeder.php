<?php

use Illuminate\Database\Seeder;

class RegencySeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $regency_path = base_path('../data/list_of_area/regencies.json');
        $regencies = \File::get($regency_path);
        $regencies = json_decode($regencies);
        
        DB::transaction(function () use ($regencies) {
          DB::table('regencies')->delete();
          foreach ($regencies as $regency) {
            $data = [
              'id' => $regency->id,
              'province_id' => $regency->province_id,
              'name' => $regency->name,
              'longitude' => $regency->longitude,
              'latitude' => $regency->latitude
            ];
            DB::table('regencies')->insert($data);
          }
        });
    }
}
