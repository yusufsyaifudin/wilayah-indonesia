package wilayah.indonesia.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yusuf on 24/10/16.
 */
public class District {
    private String id;
    private String regency_id;
    private String name;
    private String alt_name;
    private Double latitude;
    private Double longitude;
    private List<Village> villages;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getRegency_id() { return regency_id; }

    public void setRegency_id(String regency_id) { this.regency_id = regency_id; }

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

    public List<Village> getVillages() {
        return (villages != null) ? villages : new ArrayList<>();
    }

    public void setVillages(List<Village> villages) {
        this.villages = villages;
    }

    public String toString() {
        return "{id: " + getId().toString() +
                ", regency_id: " + getRegency_id().toString() +
                ", name: " + getName() +
                ", alt_name: " + getAlt_name() +
                ", latitude: " + getLatitude().toString() +
                ", longitute: " + getLongitude().toString() +
                "}";
    }
}
