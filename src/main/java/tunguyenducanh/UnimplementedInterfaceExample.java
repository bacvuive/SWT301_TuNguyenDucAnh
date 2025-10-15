package tunguyenducanh;

import java.util.logging.Level;
import java.util.logging.Logger;

interface Drawable {
    void draw();
}

public class UnimplementedInterfaceExample {

    private static final Logger LOG = Logger.getLogger(UnimplementedInterfaceExample.class.getName());

    // record: field radius là final, equals/hashCode/toString sinh tự động
    record Circle(int radius) implements Drawable {
        // Compact constructor để validate
        public Circle {
            if (radius <= 0) throw new IllegalArgumentException("radius must be > 0");
        }

        @Override
        public void draw() {
            LOG.log(Level.INFO, () -> "Drawing circle with radius = " + radius);
        }
    }

    public static void main(String[] args) {
        Drawable d = new Circle(10);
        d.draw();
        LOG.info("Done.");
    }
}
