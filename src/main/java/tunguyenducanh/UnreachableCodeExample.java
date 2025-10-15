package tunguyenducanh;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UnreachableCodeExample {

    private static final Logger LOG = Logger.getLogger(UnreachableCodeExample.class.getName());

    // Thay cho getNumber(): dùng hằng số rõ nghĩa
    public static final int DEFAULT_NUMBER = 42;

    public static void main(String[] args) {
        LOG.log(Level.INFO, "Number = {0}", new Object[]{DEFAULT_NUMBER});
    }
}
