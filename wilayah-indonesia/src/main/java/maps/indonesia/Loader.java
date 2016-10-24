package maps.indonesia;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by yusuf on 24/10/16.
 */
public class Loader {

    /**
     * Load a file content to string using BufferedReader since it fast
     * @param file
     * @return
     * @throws Exception
     */
    protected static String read(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            String string_line = "";
            while ((line = br.readLine()) != null) {
                // process the line.
                string_line += line;
            }

            return string_line;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
