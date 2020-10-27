package wilayah.indonesia.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by yusuf on 24/10/16.
 */
@Data
@AllArgsConstructor
public class Province {
    private String id;
    private String name;
    private String alt_name;
    private Double latitude;
    private Double longitude;
    private List<Regency> regencies;
}
