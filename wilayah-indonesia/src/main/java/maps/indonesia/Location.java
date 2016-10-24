package maps.indonesia;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import maps.indonesia.model.District;
import maps.indonesia.model.Province;
import maps.indonesia.model.Regency;
import maps.indonesia.model.Village;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by yusuf on 24/10/16.
 */
public class Location {
    private static final Type PROVINCES = new TypeToken<List<Province>>() {}.getType();
    private static final Type REGENCIES = new TypeToken<List<Regency>>() {}.getType();
    private static final Type DISTRICT = new TypeToken<List<District>>() {}.getType();
    private static final Type VILLAGE = new TypeToken<List<Village>>() {}.getType();

    public static final Gson gson = new Gson();

    public static List<Province> Provinces() throws Exception {
        String provinces = Loader.read("./resources/list_of_area/provinces.json");
        return gson.fromJson(provinces, PROVINCES);
    }

    public static List<Regency> Regencies() throws Exception {
        String regencies = Loader.read("./resources/list_of_area/regencies.json");
        return gson.fromJson(regencies, REGENCIES);
    }

    public static List<District> Districts() throws Exception {
        String districts = Loader.read("./resources/list_of_area/districts.json");
        return gson.fromJson(districts, DISTRICT);
    }

    public static List<Village> Villages() throws Exception {
        String villages = Loader.read("./resources/list_of_area/villages.json");
        return gson.fromJson(villages, VILLAGE);
    }
}
