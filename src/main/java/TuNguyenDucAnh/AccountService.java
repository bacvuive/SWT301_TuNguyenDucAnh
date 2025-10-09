package TuNguyenDucAnh;

import java.util.regex.Pattern;

public class AccountService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    public boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /** Đăng ký thành công khi:
     * - username không null/empty
     * - password độ dài > 6
     * - email hợp lệ
     */
    public boolean registerAccount(String username, String password, String email) {
        if (username == null || username.isEmpty()) return false;
        if (password == null || password.length() <= 6) return false;
        if (!isValidEmail(email)) return false;
        return true;
    }
}
