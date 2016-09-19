<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        // $this->call(UsersTableSeeder::class);
        $this->call(ProvinceSeeder::class);
        $this->call(RegencySeeder::class);
        $this->call(DistrictSeeder::class);
        $this->call(VillageSeeder::class);
        $this->call(ProvinceGeojsonSeeder::class);
        $this->call(RegencyGeojsonSeeder::class);
        $this->call(DistrictGeojsonSeeder::class);
        $this->call(VillageGeojsonSeeder::class);
    }
}
