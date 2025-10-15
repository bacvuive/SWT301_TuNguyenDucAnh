package tunguyenducanh;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PathTraversalExample {

    private static final Logger LOG = Logger.getLogger(PathTraversalExample.class.getName());

    public static void main(String[] args) {
        // Đầu vào giả lập: lấy từ args; nếu không có, dùng "data.txt"
        String userInput = (args.length > 0) ? args[0] : "data.txt";

        // Thư mục gốc an toàn (ví dụ: ./data). Cần tồn tại và có quyền phù hợp.
        Path baseDir;
        try {
            baseDir = Paths.get("data").toRealPath(LinkOption.NOFOLLOW_LINKS);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Base directory not accessible", e);
            return;
        }

        try {
            // 1) Resolve trong baseDir, 2) normalize để loại bỏ "..", 3) toRealPath để theo symlink an toàn
            Path candidate = baseDir.resolve(userInput).normalize();

            // Từ chối đường dẫn tuyệt đối hoặc thoát ra ngoài baseDir
            if (candidate.isAbsolute() && !candidate.startsWith(baseDir)) {
                LOG.warning("Absolute path is not allowed");
                return;
            }
            // đảm bảo vẫn nằm trong baseDir
            if (!candidate.startsWith(baseDir)) {
                LOG.warning("Path traversal attempt blocked");
                return;
            }

            // Chỉ mở file thường
            if (!Files.isRegularFile(candidate)) {
                LOG.log(Level.WARNING, "Target is not a regular file: {0}", candidate);
                return;
            }

            // Mở và đọc an toàn bằng try-with-resources
            try (BufferedReader reader = Files.newBufferedReader(candidate, StandardCharsets.UTF_8)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    LOG.log(Level.INFO, line); // thay cho System.out
                }
            }
        } catch (InvalidPathException e) {
            LOG.log(Level.WARNING, "Invalid path provided", e);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "I/O error during file access", e);
        }
    }
}
