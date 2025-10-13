package tunguyenducanh;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsecureLogin {

    private static final Logger logger = Logger.getLogger(InsecureLogin.class.getName());
    private static final String ADMIN_USER = "admin";

    /**
     * Đăng nhập: trả về true nếu thành công.
     * Mật khẩu kỳ vọng đọc từ biến môi trường ADMIN_PASSWORD (không hard-code).
     */
    public static boolean login(String username, String password) {
        // Đọc mật khẩu chuẩn từ biến môi trường (set trước khi chạy)
        String expected = System.getenv("ADMIN_PASSWORD");

        boolean ok = ADMIN_USER.equals(username)
                && expected != null
                && Objects.equals(password, expected);

        if (ok) {
            logger.info("Login successful");
        } else {
            logger.warning("Login failed");
        }
        return ok;
    }

    /** Demo logging an toàn: dùng formatting built-in, tránh cộng chuỗi và hạn chế lộ PII. */
    public void printUserInfo(String user) {
        if (user == null || user.isBlank()) {
            logger.info("User is empty");
            return;
        }
        // java.util.logging hỗ trợ placeholder {0}, {1}, ...
        logger.log(Level.INFO, "User: {0}", user);
    }
}
