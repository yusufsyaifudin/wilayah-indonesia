<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateVillageGeojsonTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('village_geojson', function (Blueprint $table)
        {
            $table->increments('id');
            $table->char('village_id', 10)->notNull();
            $table->foreign('village_id')->references('id')->on('villages')->onDelete('cascade');
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
        Schema::dropIfExists('village_geojson');
    }
}
