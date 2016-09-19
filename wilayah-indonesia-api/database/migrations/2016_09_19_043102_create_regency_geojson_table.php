<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateRegencyGeojsonTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('regency_geojson', function (Blueprint $table)
        {
            $table->increments('id');
            $table->char('regency_id', 4)->notNull();
            $table->foreign('regency_id')->references('id')->on('regencies')->onDelete('cascade');
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
        Schema::dropIfExists('regency_geojson');
    }
}
