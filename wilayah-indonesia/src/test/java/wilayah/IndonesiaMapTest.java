package wilayah;

import java.io.IOException;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import wilayah.indonesia.Loader;
import wilayah.indonesia.Location;
import wilayah.indonesia.LocationException;

/**
 * Created by yusuf on 06/11/16.
 */
public class IndonesiaMapTest {

    @BeforeClass
    public static void prepareDefault() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure();
    }

    @Test
    public void throwable() {
        Assert.assertThrows(LocationException.class, () -> {
            Location.provinceTrees(null);
        });
    }

    @SneakyThrows
    @Test
    public void provinceTrees() {
        val p1 = Location.provinceTrees(Location.TREE_LEVEL.VILLAGE);

        Assert.assertEquals("DI YOGYAKARTA", p1.get(13).getName());
        Assert.assertEquals("KABUPATEN KULON PROGO", p1.get(13).getRegencies().get(0).getName());
        Assert.assertEquals("TEMON",
            p1.get(13).getRegencies().get(0).getDistricts().get(0).getName());
        Assert.assertEquals("JANGKARAN",
            p1.get(13).getRegencies().get(0).getDistricts().get(0).getVillages().get(0).getName());

        val p2 = Location.provinceTrees();

        Assert.assertEquals("DI YOGYAKARTA", p2.get(13).getName());
        Assert.assertEquals("KABUPATEN KULON PROGO", p2.get(13).getRegencies().get(0).getName());
        Assert.assertEquals("TEMON",
            p2.get(13).getRegencies().get(0).getDistricts().get(0).getName());
        Assert.assertEquals("JANGKARAN",
            p2.get(13).getRegencies().get(0).getDistricts().get(0).getVillages().get(0).getName());
    }

    @SneakyThrows
    @Test
    public void province() {
        val p3 = Location.provinces();
        Assert.assertFalse(p3.isEmpty());
    }

    @SneakyThrows
    @Test
    public void regency() {
        val p4 = Location.regencies();
        Assert.assertFalse(p4.isEmpty());
    }

    @SneakyThrows
    @Test
    public void district() {
        val p5 = Location.districts();
        Assert.assertFalse(p5.isEmpty());
    }

    @SneakyThrows
    @Test
    public void village() {

        val p6 = Location.villages();
        Assert.assertFalse(p6.isEmpty());
    }

    @SneakyThrows
    @Test
    public void Loader() {
        Assert.assertThrows(IOException.class, () -> {
            Loader.read("xyz");
        });
        Assert.assertNotNull(Loader.read(Location.PROVINCE_PATH));
    }

}
