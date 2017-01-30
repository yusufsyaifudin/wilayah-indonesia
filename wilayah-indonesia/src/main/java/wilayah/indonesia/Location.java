package wilayah.indonesia;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import wilayah.indonesia.model.District;
import wilayah.indonesia.model.Province;
import wilayah.indonesia.model.Regency;
import wilayah.indonesia.model.Village;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yusuf on 24/10/16.
 */
public class Location {

    public enum TREE_LEVEL {
        PROVINCE, REGENCY, DISTRICT, VILLAGE
    }

    final static Logger logger = Logger.getLogger(Location.class);

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

    public static List<Province> ProvinceTrees() throws Exception {
        String treeStr = Loader.read("./resources/list_of_area/indonesia-region.min.json");
        List<Province> tree = gson.fromJson(treeStr, PROVINCES);
        return tree;
    }

    public static List<Province> ProvinceTrees(TREE_LEVEL tree_level) throws Exception {
        String provincesStr = "[{}]";
        String regenciesStr = "[{}]";
        String districtsStr = "[{}]";
        String villagesStr = "[{}]";

        switch (tree_level) {
            case PROVINCE:
                provincesStr = Loader.read("./resources/list_of_area/provinces.json");
                break;

            case REGENCY:
                provincesStr = Loader.read("./resources/list_of_area/provinces.json");
                regenciesStr = Loader.read("./resources/list_of_area/regencies.json");
                break;

            case DISTRICT:
                provincesStr = Loader.read("./resources/list_of_area/provinces.json");
                regenciesStr = Loader.read("./resources/list_of_area/regencies.json");
                districtsStr = Loader.read("./resources/list_of_area/districts.json");
                break;

            case VILLAGE:
                provincesStr = Loader.read("./resources/list_of_area/provinces.json");
                regenciesStr = Loader.read("./resources/list_of_area/regencies.json");
                districtsStr = Loader.read("./resources/list_of_area/districts.json");
                villagesStr  = Loader.read("./resources/list_of_area/villages.json");
                break;

            default:
                provincesStr = Loader.read("./resources/list_of_area/provinces.json");
                break;
        }


        List<Province> provinces = gson.fromJson(provincesStr, PROVINCES);
        List<Regency> regencies = gson.fromJson(regenciesStr, REGENCIES);
        List<District> districts = gson.fromJson(districtsStr, DISTRICT);
        List<Village> villages = gson.fromJson(villagesStr, VILLAGE);

        final long start = System.currentTimeMillis();
        List<Province> newProvinces = new ArrayList<>();
        provinces.forEach(p -> {

            List<Regency> newRegencies = new ArrayList<>();
            regencies.forEach(r -> {
                if(p.getId().equals( r.getProvince_id() )) {

                    List<District> newDistricts = new ArrayList<>();
                    districts.forEach(d -> {
                        if(r.getId().equals( d.getRegency_id() )) {

                            List<Village> newVillages = new ArrayList<>();
                            villages.forEach(v -> {
                                if(d.getId().equals( v.getDistrict_id() )) {
                                    newVillages.add(v);
                                }
                            });

                            d.setVillages(newVillages);
                            newDistricts.add(d);
                        }
                    });

                    r.setDistricts(newDistricts);
                    newRegencies.add(r);
                }
            });

            p.setRegencies(newRegencies);
            newProvinces.add(p);
        });

        logger.debug("Mapping location tree dependency took " + (System.currentTimeMillis() - start) + " ms");
        return newProvinces;
    }

}
