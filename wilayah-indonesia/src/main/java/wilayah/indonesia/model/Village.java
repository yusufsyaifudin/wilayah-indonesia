package wilayah.indonesia.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by yusuf on 24/10/16.
 */
@Data
@AllArgsConstructor
public class Village {

    private String id;
    private String district_id;
    private String name;
    private String alt_name;
    private Double latitude;
    private Double longitude;
}
