package main

import (
	"fmt"
	"log"
	"net/http"
	"os"
	"strconv"
	"time"

	"github.com/go-pg/pg/v10"
	"github.com/go-pg/pg/v10/orm"
	"github.com/gofiber/fiber/v2"
	"github.com/spf13/viper"
	"gopkg.in/yaml.v2"
)

type Server struct {
	Version string `mapstructure:"version" yaml:"version"`
	Port    int    `mapstructure:"port" yaml:"port"`
}

type Postgres struct {
	Host     string `mapstructure:"host" yaml:"host"`
	Port     int    `mapstructure:"port" yaml:"port"`
	Username string `mapstructure:"username" yaml:"username"`
	Password string `mapstructure:"password" yaml:"password"`
	DB       string `mapstructure:"db" yaml:"db"`
}

type Config struct {
	StartTime   time.Time `yaml:"start_time"`
	ServiceName string    `yaml:"service_name"`
	Server      Server    `mapstructure:"server" yaml:"server"`
	Postgres    Postgres  `mapstructure:"postgres" yaml:"postgres"`
}

const (
	serviceName  = "wilayah-indonesia"
	limitPerPage = 10
)

func main() {
	config := &Config{}
	config.StartTime = time.Now()
	config.ServiceName = serviceName

	v := viper.New()
	v.AutomaticEnv()
	v.SetConfigType("dotenv")
	v.SetConfigFile(".env")

	err := v.ReadInConfig()
	if err != nil {
		log.Fatal(err)
	}

	config.Postgres.Host = v.GetString("PG_HOST")
	config.Postgres.Port = v.GetInt("PG_PORT")
	config.Postgres.Username = v.GetString("PG_USER")
	config.Postgres.Password = v.GetString("PG_PASSWORD")
	config.Postgres.DB = v.GetString("PG_DB")
	config.Server.Version = v.GetString("SERVER_VERSION")
	config.Server.Port = v.GetInt("SERVER_PORT")

	bs, _ := yaml.Marshal(config)
	_, _ = fmt.Fprintf(os.Stdout, "%v\n", string(bs))

	db := pg.Connect(&pg.Options{
		Addr:            fmt.Sprintf("%s:%d", config.Postgres.Host, config.Postgres.Port),
		User:            config.Postgres.Username,
		Password:        config.Postgres.Password,
		Database:        config.Postgres.DB,
		ApplicationName: serviceName,
	})
	defer func() {
		if err := db.Close(); err != nil {
			log.Fatal(err)
		}
	}()

	app := fiber.New()

	app.Get("/provinces", getProvinces(config.Server.Version, db))
	app.Get("/provinces/:province_id/regencies", getRegency(config.Server.Version, db))
	app.Get("/provinces/:province_id/regencies/:regency_id/districts", getDistrict(config.Server.Version, db))
	app.Get("/provinces/:province_id/regencies/:regency_id/districts/:district_id/villages", getVillage(config.Server.Version, db))

	log.Fatal(app.Listen(fmt.Sprintf(":%d", config.Server.Port)))
}

type Error struct {
	Code    int    `json:"code"`
	Message string `json:"message"`
}

// RespError follows https://google.github.io/styleguide/jsoncstyleguide.xml
type RespError struct {
	ApiVersion string `json:"apiVersion"`
	Error      Error  `json:"error"`
}

// RespSuccess follows https://google.github.io/styleguide/jsoncstyleguide.xml?showone=Paging_Example#Paging_Example
// For parameter page, we can use `?start={offset}`
// where offset =  startIndex + currentItemCount - 1
type RespSuccess struct {
	ApiVersion string      `json:"apiVersion"`
	Query      string      `json:"query"` // query of this search
	Time       float64     `json:"time"`  // query execution time
	TotalItem  int         `json:"totalItem"`
	Items      interface{} `json:"items"`
}

type Province struct {
	ID        int     `json:"id"`
	Name      string  `json:"name"`
	AltName   string  `json:"altName"`
	Latitude  float64 `json:"latitude"`
	Longitude float64 `json:"longitude"`
}

type Regency struct {
	ID        int     `json:"id"`
	Name      string  `json:"name"`
	AltName   string  `json:"altName"`
	Latitude  float64 `json:"latitude"`
	Longitude float64 `json:"longitude"`
}

type District struct {
	ID        int     `json:"id"`
	Name      string  `json:"name"`
	AltName   string  `json:"altName"`
	Latitude  float64 `json:"latitude"`
	Longitude float64 `json:"longitude"`
}

type Village struct {
	ID        int     `json:"id"`
	Name      string  `json:"name"`
	AltName   string  `json:"altName"`
	Latitude  float64 `json:"latitude"`
	Longitude float64 `json:"longitude"`
}

func getProvinces(apiVer string, db orm.DB) func(fCtx *fiber.Ctx) error {
	return func(fCtx *fiber.Ctx) error {
		var provinces []Province
		startQuery := time.Now()
		totalItem, err := db.Model(&provinces).SelectAndCount()
		if err != nil {
			return fCtx.Status(http.StatusOK).JSON(RespError{
				ApiVersion: apiVer,
				Error: Error{
					Code:    http.StatusUnprocessableEntity,
					Message: fmt.Sprintf("database error: %s", err.Error()),
				},
			})
		}

		return fCtx.Status(http.StatusOK).JSON(RespSuccess{
			ApiVersion: apiVer,
			Query:      "/provinces",
			Time:       time.Now().Sub(startQuery).Seconds(),
			TotalItem:  totalItem,
			Items:      provinces,
		})
	}
}

func getRegency(apiVer string, db orm.DB) func(fCtx *fiber.Ctx) error {
	return func(fCtx *fiber.Ctx) error {
		provinceIDStr := fCtx.Params("province_id")
		provinceID, _ := strconv.Atoi(provinceIDStr)

		startQuery := time.Now()
		var regencies []Regency
		totalItem, err := db.Model(&regencies).Where("province_id = ?", provinceID).SelectAndCount()
		if err != nil {
			return fCtx.Status(http.StatusOK).JSON(RespError{
				ApiVersion: apiVer,
				Error: Error{
					Code:    http.StatusUnprocessableEntity,
					Message: fmt.Sprintf("database error: %s", err.Error()),
				},
			})
		}

		return fCtx.Status(http.StatusOK).JSON(RespSuccess{
			ApiVersion: apiVer,
			Query:      fmt.Sprintf("/provinces/%d/regencies", provinceID),
			Time:       time.Now().Sub(startQuery).Seconds(),
			TotalItem:  totalItem,
			Items:      regencies,
		})
	}
}

func getDistrict(apiVer string, db orm.DB) func(fCtx *fiber.Ctx) error {
	return func(fCtx *fiber.Ctx) error {
		provinceIDStr := fCtx.Params("province_id")
		provinceID, _ := strconv.Atoi(provinceIDStr)

		regencyIDStr := fCtx.Params("regency_id")
		regencyID, _ := strconv.Atoi(regencyIDStr)

		startQuery := time.Now()
		var regency Regency
		err := db.Model(&regency).Where("id = ? AND province_id = ?", regencyID, provinceID).First()
		if err != nil {
			return fCtx.Status(http.StatusOK).JSON(RespError{
				ApiVersion: apiVer,
				Error: Error{
					Code:    http.StatusUnprocessableEntity,
					Message: fmt.Sprintf("database error: %s", err.Error()),
				},
			})
		}

		var districts []District
		totalItem, err := db.Model(&districts).Where("regency_id = ?", regency.ID).SelectAndCount()
		if err != nil {
			return fCtx.Status(http.StatusOK).JSON(RespError{
				ApiVersion: apiVer,
				Error: Error{
					Code:    http.StatusUnprocessableEntity,
					Message: fmt.Sprintf("database error: %s", err.Error()),
				},
			})
		}

		return fCtx.Status(http.StatusOK).JSON(RespSuccess{
			ApiVersion: apiVer,
			Query:      fmt.Sprintf("/provinces/%d/regencies/%d/districts", provinceID, regencyID),
			Time:       time.Now().Sub(startQuery).Seconds(),
			TotalItem:  totalItem,
			Items:      districts,
		})
	}
}

func getVillage(apiVer string, db orm.DB) func(fCtx *fiber.Ctx) error {
	return func(fCtx *fiber.Ctx) error {
		provinceIDStr := fCtx.Params("province_id")
		provinceID, _ := strconv.Atoi(provinceIDStr)

		regencyIDStr := fCtx.Params("regency_id")
		regencyID, _ := strconv.Atoi(regencyIDStr)

		districtIDStr := fCtx.Params("district_id")
		districtID, _ := strconv.Atoi(districtIDStr)

		startQuery := time.Now()
		var regency Regency
		err := db.Model(&regency).Where("id = ? AND province_id = ?", regencyID, provinceID).First()
		if err != nil {
			return fCtx.Status(http.StatusOK).JSON(RespError{
				ApiVersion: apiVer,
				Error: Error{
					Code:    http.StatusUnprocessableEntity,
					Message: fmt.Sprintf("database error: %s", err.Error()),
				},
			})
		}

		var district District
		err = db.Model(&district).Where("id = ? AND regency_id = ?", districtID, regency.ID).First()
		if err != nil {
			return fCtx.Status(http.StatusOK).JSON(RespError{
				ApiVersion: apiVer,
				Error: Error{
					Code:    http.StatusUnprocessableEntity,
					Message: fmt.Sprintf("database error: %s", err.Error()),
				},
			})
		}

		var villages []Village
		totalItem, err := db.Model(&villages).Where("district_id = ?", district.ID).SelectAndCount()
		if err != nil {
			return fCtx.Status(http.StatusOK).JSON(RespError{
				ApiVersion: apiVer,
				Error: Error{
					Code:    http.StatusUnprocessableEntity,
					Message: fmt.Sprintf("database error: %s", err.Error()),
				},
			})
		}

		return fCtx.Status(http.StatusOK).JSON(RespSuccess{
			ApiVersion: apiVer,
			Query:      fmt.Sprintf("/provinces/%d/regencies/%d/districts/%d/villages", provinceID, regencyID, districtID),
			Time:       time.Now().Sub(startQuery).Seconds(),
			TotalItem:  totalItem,
			Items:      villages,
		})
	}
}
