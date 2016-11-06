package wilayah.indonesia.model;

/**
 * Created by yusuf on 24/10/16.
 */
public class Village {
    private String id;
    private String district_id;
    private String name;
    private String alt_name;
    private Double latitude;
    private Double longitude;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getDistrict_id() { return district_id; }

    public void setDistrict_id(String district_id) { this.district_id = district_id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Double getLatitude() {
        return (latitude == null) ? 0 : latitude;
    }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() {
        return (longitude == null) ? 0 : longitude;
    }

    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String toString() {
        return "{id: " + getId().toString() +
                ", district_id: " + getDistrict_id() +
                ", name: " + getName() +
                ", latitude: " + getLatitude().toString() +
                ", longitute: " + getLongitude().toString() +
                "}";
    }
}
