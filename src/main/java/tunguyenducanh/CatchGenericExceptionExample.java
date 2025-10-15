package tunguyenducanh;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CatchGenericExceptionExample {

    private static final Logger LOG = Logger.getLogger(CatchGenericExceptionExample.class.getName());

    public static void main(String[] args) {
        // Lấy chuỗi từ tham số dòng lệnh (nếu không truyền thì coi như null)
        String s = (args.length > 0) ? args[0] : null;

        if (s == null) {
            LOG.log(Level.WARNING, "Input string is null; cannot compute length");
            return;
        }

        // Ở đây s != null -> không có NPE
        int len = s.length();
        // Dùng Supplier để “lazy” message (chuẩn Sonar S2629)
        LOG.log(Level.INFO, () -> "Length = " + len);
    }
}
