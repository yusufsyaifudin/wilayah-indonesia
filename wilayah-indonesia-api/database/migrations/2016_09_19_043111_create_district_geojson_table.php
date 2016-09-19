<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateDistrictGeojsonTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('district_geojson', function (Blueprint $table)
        {
            $table->increments('id');
            $table->char('district_id', 7)->notNull();
            $table->foreign('district_id')->references('id')->on('districts')->onDelete('cascade');
            $table->longText('geojson')->notNull();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('district_geojson');
    }
}
