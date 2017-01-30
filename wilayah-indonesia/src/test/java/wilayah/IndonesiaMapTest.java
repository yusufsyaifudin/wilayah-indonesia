package wilayah;

import wilayah.indonesia.Location;
import wilayah.indonesia.model.Province;
import org.junit.*;

import java.util.List;

/**
 * Created by yusuf on 06/11/16.
 */
public class IndonesiaMapTest {

    public IndonesiaMapTest() {
    }

    @Test
    public void Loader() throws Exception {
        org.apache.log4j.BasicConfigurator.configure();
        List<Province> p1 = Location.ProvinceTrees(Location.TREE_LEVEL.VILLAGE);

        Assert.assertEquals("DI YOGYAKARTA", p1.get(13).getName());
        Assert.assertEquals("KABUPATEN KULON PROGO", p1.get(13).getRegencies().get(0).getName());
        Assert.assertEquals("TEMON", p1.get(13).getRegencies().get(0).getDistricts().get(0).getName());
        Assert.assertEquals("JANGKARAN", p1.get(13).getRegencies().get(0).getDistricts().get(0).getVillages().get(0).getName());

        List<Province> p2 = Location.ProvinceTrees();

        Assert.assertEquals("DI YOGYAKARTA", p2.get(13).getName());
        Assert.assertEquals("KABUPATEN KULON PROGO", p2.get(13).getRegencies().get(0).getName());
        Assert.assertEquals("TEMON", p2.get(13).getRegencies().get(0).getDistricts().get(0).getName());
        Assert.assertEquals("JANGKARAN", p2.get(13).getRegencies().get(0).getDistricts().get(0).getVillages().get(0).getName());
    }

}
