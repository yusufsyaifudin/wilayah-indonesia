# Wilayah Indonesia

Travis status: [![Build Status](https://travis-ci.org/yusufsyaifudin/wilayah-indonesia.svg?branch=master)](https://travis-ci.org/yusufsyaifudin/wilayah-indonesia)


## Migrating only data into PostgreSQL

Use https://github.com/rubenv/sql-migrate install with:

```
go get -v github.com/rubenv/sql-migrate/...
```

Create database in postgres, for example database `id_area`, 
and change your database configuration in file `rubenv-migrate.yml`, then run:

```
sql-migrate up -config rubenv-migrate.yml
```

## Using REST API

You can use Rest API with just one binary. 

* First, migrate the data using `sql-migrate`.
* Second, build using Golang by `go mod download` and `go build -o artifacts/wilayah_indonesia main.go`
  Then set config on `.env` file and run the program `./artifacts/wilayah_indonesia`
  
### Endpoints

Just 4 simple Rest API:

* http://127.0.0.1:9000/provinces -> List of Provinces
* http://127.0.0.1:9000/provinces/:province_id/regencies -> List of Regencies in :province_id
* http://127.0.0.1:9000/provinces/:province_id/regencies/:regency_id/districts -> List of District in :province_id and :regency_id
* http://127.0.0.1:9000/provinces/:province_id/regencies/:regency_id/districts/:district_id/villages -> List of Villages in :province_id and :regency_id and :district_id

## Using JAVA API

Please move all inside `/data` directory to `/resources` in your root project:

```
- your-project
  - resources
    - list_of_area
    - geojson
  - src
    - main
      - java
  - target
```

then add this to your maven `pom.xml`

```
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

then add the dependency:

```
<dependency>
  <groupId>com.github.yusufsyaifudin</groupId>
  <artifactId>wilayah-indonesia</artifactId>
  <version>0.0.4</version>
</dependency>
```

If you're using gradle, add this in your root build.gradle at the end of repositories:

```
allprojects {
  repositories {
    maven { url "https://jitpack.io" }
  }
}
```

and add the dependency

```
dependencies {
  compile 'com.github.yusufsyaifudin:wilayah-indonesia:0.0.4'
}
```

### Calling the data
If you have move the data to resources directory, you can now call this method to load the data (without database):

```
import wilayah.indonesia.*;
import wilayah.indonesia.model.*;

Location.Provinces() // will return List<Province>
Location.Regencies() // will return List<Regency>  
Location.Districts() // will return List<District>
Location.Villages() // will return List<Village>
```


### Note
Data in this repo come from many source:

* Nominatim OpenstreetMap
* Google geolocation API
* [edwardsamuel/Wilayah-Administratif-Indonesia repo](https://github.com/edwardsamuel/Wilayah-Administratif-Indonesia/tree/f71622fdc2c87f422307954ee23db5e6ed283835)
* Locationiq.org
* [Overpass API]() [http://overpass-api.de/api/interpreter?data=(relation({{osm_id}});>;);out;](http://overpass-api.de/api/interpreter?data=(relation(2390836);>;);out;)

## License
* The data are under: [ODBL v1.0.](/odbl-10.md)
* The source data is attributed to list mentioned above.
