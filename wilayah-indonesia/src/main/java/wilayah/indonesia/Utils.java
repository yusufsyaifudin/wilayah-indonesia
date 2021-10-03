package wilayah.indonesia;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import wilayah.indonesia.model.District;
import wilayah.indonesia.model.Province;
import wilayah.indonesia.model.Regency;
import wilayah.indonesia.model.Village;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static District setChild(final District district, final List<Village> villages) {

        district.setVillages(
            villages.stream()
                .filter(village -> district.getId()
                    .equals(village.getDistrict_id()))
                .collect(Collectors.toList()));
        return district;
    }

    public static Regency setChild(
        final Regency regency,
        final List<District> districts,
        final List<Village> villages) {

        regency.setDistricts(
            districts.stream()
                .filter(
                    district -> regency.getId()
                        .equals(district.getRegency_id()))
                .map(district -> setChild(district, villages))
                .collect(Collectors.toList()));
        return regency;
    }

    public static Province setChild(
        final Province province,
        final List<Regency> regencies,
        final List<District> districts,
        final List<Village> villages) {

        province.setRegencies(
            regencies.stream()
                .filter(regency -> province.getId()
                    .equals(regency.getProvince_id()))
                .map(regency -> setChild(regency, districts, villages))
                .collect(Collectors.toList()));
        return province;
    }
}
