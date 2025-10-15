package tunguyenducanh;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OvercatchingExceptionExample {

    private static final Logger LOG = Logger.getLogger(OvercatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        int[] arr = new int[]{10, 20, 30, 40, 50};  // có dữ liệu
        int index = (args.length > 0) ? Integer.parseInt(args[0]) : 2; // mặc định lấy phần tử thứ 3

        if (index >= 0 && index < arr.length) {
            LOG.log(Level.INFO, "arr[{0}] = {1}", new Object[]{index, arr[index]});
        } else {
            LOG.log(Level.WARNING, "Index {0} out of bounds for length {1}",
                    new Object[]{index, arr.length});
        }
    }
}
