package wilayah.indonesia;

import java.io.*;
import org.apache.log4j.Logger;

/**
 * Created by yusuf on 24/10/16.
 */
public class Loader {

    final static Logger logger = Logger.getLogger(Loader.class);
    /**
     * Load a file content to string using BufferedInputStream since it fast
     * @param file
     * @return
     * @throws Exception
     */
    public static String read(String file) throws Exception {
        final long start = System.currentTimeMillis();
        final InputStream in = new BufferedInputStream(new FileInputStream(file));
        int bytesRead = 0;
        String line = "";
        final byte[] buf = new byte[50130];
        while ((bytesRead = in.read(buf)) != -1) {
            line += new String(buf, 0, bytesRead);
        }
        in.close();

        logger.debug("Load " + file + " took " + (System.currentTimeMillis() - start) + " ms");
        return line;
    }
}
