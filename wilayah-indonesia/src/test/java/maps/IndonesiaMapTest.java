package maps;

import maps.indonesia.Location;
import maps.indonesia.model.Province;
import org.junit.*;

import java.util.List;

/**
 * Created by yusuf on 06/11/16.
 */
public class IndonesiaMapTest {
    @Test
    public void Loader() throws Exception {
        org.apache.log4j.BasicConfigurator.configure();
        List<Province> p = Location.ProvinceTrees(Location.TREE_LEVEL.VILLAGE);

        Assert.assertEquals("DI YOGYAKARTA", p.get(13).getName());
        Assert.assertEquals("KABUPATEN KULON PROGO", p.get(13).getRegencies().get(0).getName());
        Assert.assertEquals("TEMON", p.get(13).getRegencies().get(0).getDistricts().get(0).getName());
        Assert.assertEquals("JANGKARAN", p.get(13).getRegencies().get(0).getDistricts().get(0).getVillages().get(0).getName());
    }
}
