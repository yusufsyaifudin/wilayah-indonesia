package wilayah.indonesia;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wilayah.indonesia.model.District;
import wilayah.indonesia.model.Province;
import wilayah.indonesia.model.Regency;
import wilayah.indonesia.model.Village;

/**
 * Created by yusuf on 24/10/16.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Location {

    public enum TREE_LEVEL {
        PROVINCE, REGENCY, DISTRICT, VILLAGE
    }

    private static final String EMPTY_JSON = "[{}]";
    private static final Type PROVINCES = TypeToken.getParameterized(List.class, Province.class)
        .getType();
    private static final Type REGENCIES = TypeToken.getParameterized(List.class, Regency.class)
        .getType();
    private static final Type DISTRICT = TypeToken.getParameterized(List.class, District.class)
        .getType();
    private static final Type VILLAGE = TypeToken.getParameterized(List.class, Village.class)
        .getType();

    public static final Gson gson = new Gson();
    public static final String PROVINCE_TREE_PATH = "./resources/list_of_area/indonesia-region.min.json";
    public static final String PROVINCE_PATH = "./resources/list_of_area/provinces.json";
    public static final String REGENCY_PATH = "./resources/list_of_area/regencies.json";
    public static final String DISTRICT_PATH = "./resources/list_of_area/districts.json";
    public static final String VILLAGE_PATH = "./resources/list_of_area/villages.json";

    public static List<Province> provinces() throws IOException, LocationException {
        return gson.fromJson(Loader.read(PROVINCE_PATH), PROVINCES);
    }

    @Deprecated
    public static List<Province> Provinces() throws IOException, LocationException {
        return provinces();
    }

    public static List<Regency> regencies() throws IOException, LocationException {
        return gson.fromJson(Loader.read(REGENCY_PATH), REGENCIES);
    }

    @Deprecated
    public static List<Regency> Regencies() throws IOException, LocationException {
        return regencies();
    }

    public static List<District> districts() throws IOException, LocationException {
        return gson.fromJson(Loader.read(DISTRICT_PATH), DISTRICT);
    }

    @Deprecated
    public static List<District> Districts() throws IOException, LocationException {
        return districts();
    }

    public static List<Village> villages() throws IOException, LocationException {
        return gson.fromJson(Loader.read(VILLAGE_PATH), VILLAGE);
    }

    @Deprecated
    public static List<Village> Villages() throws IOException, LocationException {
        return villages();
    }

    public static List<Province> provinceTrees() throws IOException, LocationException {
        return gson.fromJson(Loader.read(PROVINCE_TREE_PATH), PROVINCES);
    }

    public static List<Province> provinceTrees(final TREE_LEVEL treeLevel)
        throws IOException, LocationException {

        if (treeLevel == null) {
            throw new LocationException();
        }

        final String provincesStr = Loader.read(PROVINCE_PATH);
        String regenciesStr = EMPTY_JSON;
        String districtsStr = EMPTY_JSON;
        String villagesStr = EMPTY_JSON;

        switch (treeLevel) {
            case VILLAGE:
                villagesStr = Loader.read(VILLAGE_PATH);
                // fallthrough
            case DISTRICT:
                districtsStr = Loader.read(DISTRICT_PATH);
                // fallthrough
            case REGENCY:
                regenciesStr = Loader.read(REGENCY_PATH);
                // fallthrough
            case PROVINCE:
                break;

        }

        List<Province> provinces = gson.fromJson(provincesStr, PROVINCES);
        List<Regency> regencies = gson.fromJson(regenciesStr, REGENCIES);
        List<District> districts = gson.fromJson(districtsStr, DISTRICT);
        List<Village> villages = gson.fromJson(villagesStr, VILLAGE);

        final long start = System.currentTimeMillis();

        final List<Province> newProvinces = provinces.stream()
            .map(province -> Utils.setChild(province, regencies, districts, villages))
            .collect(Collectors.toList());

        log.debug("Mapping location tree dependency took {} ms",
            (System.currentTimeMillis() - start));
        return newProvinces;
    }

    @Deprecated
    public static List<Province> ProvinceTrees() throws IOException, LocationException {
        return provinceTrees();
    }

    @Deprecated
    public static List<Province> ProvinceTrees(final TREE_LEVEL treeLevel)
        throws IOException, LocationException {
        return provinceTrees(treeLevel);
    }

}
