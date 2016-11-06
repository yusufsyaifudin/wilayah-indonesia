package wilayah.indonesia.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yusuf on 24/10/16.
 */
public class Regency {

    private String id;
    private String province_id;
    private String name;
    private String alt_name;
    private Double latitude;
    private Double longitude;
    private List<District> districts;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getProvince_id() { return province_id; }

    public void setProvince_id(String province_id) { this.province_id = province_id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAlt_name() { return alt_name; }

    public void setAlt_name(String alt_name) { this.alt_name = alt_name; }

    public Double getLatitude() {
        return (latitude == null) ? 0 : latitude;
    }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() {
        return (longitude == null) ? 0 : longitude;
    }

    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public List<District> getDistricts() {
        return (districts != null) ? districts : new ArrayList<>();
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }


    public String toString() {
        return "{id: " + getId().toString() +
                ", province_id: " + getProvince_id().toString() +
                ", name: " + getName() +
                ", alt_name: " + getAlt_name() +
                ", latitude: " + getLatitude().toString() +
                ", longitute: " + getLongitude().toString() +
                "}";
    }
}
