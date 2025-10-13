package tunguyenducanh;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InsecureLoginTest {

    @Test
    void testLoginSuccess() {
        // Đặt biến môi trường ADMIN_PASSWORD trước khi chạy test:
        // Windows (PowerShell): setx ADMIN_PASSWORD "123456"  (mở IDE/terminal mới)
        // macOS/Linux: export ADMIN_PASSWORD=123456
        String expected = System.getenv("ADMIN_PASSWORD");

        // Nếu đã set biến môi trường, login với đúng password phải thành công.
        if (expected != null) {
            assertTrue(InsecureLogin.login("admin", expected));
        } else {
            // Nếu chưa set ENV, ít nhất xác nhận không crash và cho password sai là fail.
            assertFalse(InsecureLogin.login("admin", "wrongpassword"));
        }
    }

    @Test
    void testLoginFail() {
        assertFalse(InsecureLogin.login("admin", "definitely-wrong"));
        assertFalse(InsecureLogin.login("someone", "anything"));
    }

    @Test
    void testPrintUserInfo() {
        InsecureLogin insecureLogin = new InsecureLogin();
        // Gọi để tránh "never used" và đảm bảo không ném exception
        insecureLogin.printUserInfo("John Doe");
        insecureLogin.printUserInfo(null);
        insecureLogin.printUserInfo("");
    }
}
