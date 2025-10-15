package tunguyenducanh;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NullPointerExample {

    private static final Logger LOG = Logger.getLogger(NullPointerExample.class.getName());

    public static void main(String[] args) {
        // Lấy đầu vào từ tham số; nếu không truyền coi như null (mô phỏng dữ liệu chưa có)
        String text = (args.length > 0) ? args[0] : null;

        if (text == null || text.isEmpty()) {
            LOG.log(Level.INFO, "Text is null or empty");
            return;
        }

        LOG.log(Level.INFO, "Text is not empty");
    }
}
