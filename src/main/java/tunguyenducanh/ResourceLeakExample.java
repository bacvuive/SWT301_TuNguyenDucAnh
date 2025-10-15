package tunguyenducanh;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ResourceLeakExample {

    private static final Logger LOG = Logger.getLogger(ResourceLeakExample.class.getName());

    public static void main(String[] args) {
        Path path = Paths.get(args.length > 0 ? args[0] : "data.txt");

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                LOG.log(Level.INFO, line);
            }
        } catch (NoSuchFileException e) {
            // KHÔNG nối chuỗi: dùng LogRecord để vừa có message tham số hoá vừa gắn Throwable
            LogRecord rec = new LogRecord(Level.WARNING, "File not found: {0}");
            rec.setParameters(new Object[]{path.toAbsolutePath()});
            rec.setThrown(e);
            LOG.log(rec);
        } catch (IOException e) {
            LogRecord rec = new LogRecord(Level.SEVERE, "I/O error while reading: {0}");
            rec.setParameters(new Object[]{path.toAbsolutePath()});
            rec.setThrown(e);
            LOG.log(rec);
        }
    }
}
