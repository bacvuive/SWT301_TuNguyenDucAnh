package tunguyenducanh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLInjectionExample {

    private static final Logger LOG = Logger.getLogger(SQLInjectionExample.class.getName());

    // Đọc cấu hình từ biến môi trường (KHÔNG hardcode)
    private static String env(String key) {
        return System.getenv(key);
    }

    // Ẩn bớt bí mật khi log
    private static String mask(String s) {
        if (s == null || s.length() <= 4) return "****";
        return "****" + s.substring(s.length() - 4);
    }

    public static void main(String[] args) {
        String userInput = (args.length > 0) ? args[0] : "alice";

        final String sql = "SELECT id, username, email FROM users WHERE username = ?";

        String url  = env("DB_URL");
        String user = env("DB_USER");
        String pass = env("DB_PASS");

        // Nếu chưa cấu hình datasource qua biến môi trường, chỉ log cảnh báo và thoát
        if (url == null || user == null || pass == null) {
            LOG.log(Level.WARNING,
                    "DB config is missing. Set env DB_URL/DB_USER/DB_PASS to enable DB access. " +
                            "Current: url={0}, user={1}, pass={2}",
                    new Object[]{url, user, mask(pass)});
            return;
        }

        // Chỉ log template câu lệnh, KHÔNG log dữ liệu nhạy cảm
        LOG.log(Level.INFO, "Executing parameterized query template: {0}", sql);

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userInput); // bind tham số => chống SQLi
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LOG.log(Level.INFO, "Found user: {0}", rs.getString("username"));
                }
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Database error while executing query", ex);
        }
    }
}
