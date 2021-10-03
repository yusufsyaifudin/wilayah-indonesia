package wilayah.indonesia;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

/**
 * Created by yusuf on 24/10/16.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Loader {

    /**
     * Load a file content to string using BufferedInputStream since it fast
     *
     * @param file file path
     * @return content string
     * @throws IOException throws if failed to load the file
     */
    public static String read(String file) throws IOException {
        final long start = System.currentTimeMillis();
        try (final InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            var bytesRead = 0;
            var line = new StringBuilder();
            final byte[] buf = new byte[50130];
            while ((bytesRead = in.read(buf)) != -1) {
                line.append(new String(buf, 0, bytesRead));
            }
            log.debug("Load {} took {} ms", file, (System.currentTimeMillis() - start));
            return line.toString();
        }
    }
}
