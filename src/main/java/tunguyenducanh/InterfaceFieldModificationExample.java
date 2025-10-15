package tunguyenducanh;

import java.util.logging.Level;
import java.util.logging.Logger;

// KHÔNG public để tránh lỗi "public type must be in a file named the same"
final class AppConstants {
    private AppConstants() {}
    static final int MAX_USERS = 100;
}

public class InterfaceFieldModificationExample {
    private static final Logger LOG = Logger.getLogger(InterfaceFieldModificationExample.class.getName());

    public static void main(String[] args) {
        LOG.log(Level.INFO, "System allows up to {0} users.", new Object[]{AppConstants.MAX_USERS});
    }
}
