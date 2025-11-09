package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Exercise 5 - DemoQA Automation Practice Form (with image upload)")
public class RegistrationTest extends BaseTest {

    /** Helper: lấy đường dẫn tuyệt đối của file trong src/test/resources */
    private String resourceAbsolutePath(String resourcePath) {
        try {
            ClassLoader cl = getClass().getClassLoader();
            URL url = Objects.requireNonNull(cl.getResource(resourcePath),
                    "Không tìm thấy resource: " + resourcePath);
            return Paths.get(url.toURI()).toFile().getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException("Lấy đường dẫn resource thất bại: " + resourcePath, e);
        }
    }

    @Test
    @DisplayName("Submit form thành công + upload ảnh")
    void submit_ok_with_upload() {
        RegistrationPage p = new RegistrationPage(driver);

        p.open();
        p.fillBasic("Kum", "Ton", "kum.ton@example.com", "0912345678", "123 Main St");
        p.pickDate("10 Oct 1998");
        p.subjects("Maths", "English");
        p.hobbySports();
        p.stateCity("NCR", "Delhi");

        // <- Ảnh đặt tại src/test/resources/images/avatar.png
        String abs = resourceAbsolutePath("images/avatar.png");
        p.upload(abs);

        p.submit();
        assertTrue(p.submitted(), "Không thấy modal xác nhận sau khi submit");
    }
}
