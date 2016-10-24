package maps.indonesia.model;

/**
 * Created by yusuf on 24/10/16.
 */
public class Village {
    private Integer id;
    private Integer district_id;
    private String name;
    private String alt_name;
    private Double latitude;
    private Double longitude;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getDistrict_id() { return district_id; }

    public void setDistrict_id(Integer district_id) { this.district_id = district_id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Double getLatitude() { return latitude; }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }

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
