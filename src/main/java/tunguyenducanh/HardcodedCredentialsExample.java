package tunguyenducanh;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HardcodedCredentialsExample {

    private static final Logger LOG = Logger.getLogger(HardcodedCredentialsExample.class.getName());

    // Lấy biến môi trường (trả về null nếu thiếu)
    private static String env(String key) {
        return System.getenv(key);
    }

    // So sánh hằng thời gian (trên byte)
    private static boolean constantTimeEquals(char[] left, String right) {
        if (left == null || right == null) return false;
        byte[] a = new String(left).getBytes(StandardCharsets.UTF_8);
        byte[] b = right.getBytes(StandardCharsets.UTF_8);
        boolean eq = MessageDigest.isEqual(a, b);
        // không giữ bản sao mật khẩu trong bộ nhớ lâu
        java.util.Arrays.fill(left, '\0');
        java.util.Arrays.fill(a, (byte) 0);
        java.util.Arrays.fill(b, (byte) 0);
        return eq;
    }

    public static void main(String[] args) {
        // Nhập user/pass từ tham số dòng lệnh để demo (trong app thật: nhận từ form/CLI an toàn)
        String inputUser = (args.length > 0) ? args[0] : null;
        char[] inputPass = (args.length > 1) ? args[1].toCharArray() : new char[0];

        // Đọc “cred” từ biến môi trường (KHÔNG hardcode)
        String expectedUser = env("APP_USER");
        String expectedPass = env("APP_PASS");

        if (expectedUser == null || expectedPass == null) {
            LOG.log(Level.WARNING, "Missing env APP_USER/APP_PASS; configure secrets via environment or a secret store.");
            return;
        }

        boolean granted = expectedUser.equals(inputUser) && constantTimeEquals(inputPass, expectedPass);
        LOG.log(Level.INFO, granted ? "Access granted" : "Access denied");
    }
}
