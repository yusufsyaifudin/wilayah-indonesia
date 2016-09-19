<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateProvinceGeojsonTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('province_geojson', function (Blueprint $table)
        {
            $table->increments('id');
            $table->char('province_id', 2)->notNull();
            $table->foreign('province_id')->references('id')->on('provinces')->onDelete('cascade');
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
        Schema::dropIfExists('province_geojson');
    }
}
