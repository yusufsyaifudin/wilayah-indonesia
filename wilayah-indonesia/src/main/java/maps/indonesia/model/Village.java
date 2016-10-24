package maps.indonesia.model;

/**
 * Created by yusuf on 24/10/16.
 */
public class Village {
    private Integer id;
    private Integer district_id;
    private String name;
    private String alt_name;
    private String latitude;
    private String longitude;

    public Integer getId() { return id; }

    public Integer getDistrictId() { return district_id; }

    public String getName() { return name; }

    public String getLatitude() { return latitude; }

    public String getLongitude() { return longitude; }

    public String toString() {
        return "{id: " + getId().toString() +
                ", district_id: " + getDistrictId() +
                ", name: " + getName() +
                ", latitude: " + getLatitude() +
                ", longitute: " + getLongitude() +
                "}";
    }
}
