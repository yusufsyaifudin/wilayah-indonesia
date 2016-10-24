package maps.indonesia.model;

/**
 * Created by yusuf on 24/10/16.
 */
public class District {
    private Integer id;
    private Integer regency_id;
    private String name;
    private String alt_name;
    private Double latitude;
    private Double longitude;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getRegency_id() { return regency_id; }

    public void setRegency_id(Integer regency_id) { this.regency_id = regency_id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAlt_name() { return alt_name; }

    public void setAlt_name(String alt_name) { this.alt_name = alt_name; }

    public Double getLatitude() { return latitude; }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }

    public void setLongitude(Double longitude) { this.longitude = longitude; }

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
