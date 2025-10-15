package tunguyenducanh;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

// KHÔNG public để tránh lỗi “public type must be in a file named the same”
interface LoginHandler {
    boolean login(String username, char[] password);
}

public class InterfaceNamingInconsistencyExample {
    private static final Logger LOG = Logger.getLogger(InterfaceNamingInconsistencyExample.class.getName());

    // Triển khai đơn giản ngay trong file (inner class)
    static class SimpleLoginHandler implements LoginHandler {
        private static final Logger L = Logger.getLogger(SimpleLoginHandler.class.getName());
        @Override
        public boolean login(String username, char[] password) {
            try {
                boolean ok = "admin".equals(username) && Arrays.equals(password, "secret".toCharArray());
                L.log(Level.INFO, () -> "Login attempt for user=" + username + ", result=" + ok);
                return ok;
            } finally {
                Arrays.fill(password, '\0');
            }
        }
    }

    public static void main(String[] args) {
        LoginHandler handler = new SimpleLoginHandler();
        boolean ok = handler.login("admin", "secret".toCharArray());
        LOG.info(() -> "Authenticated = " + ok);
    }
}
