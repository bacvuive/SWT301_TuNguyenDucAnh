package tunguyenducanh;

import java.util.logging.Level;
import java.util.logging.Logger;

interface Shape {
    void draw();
    void resize();
}

public class IncompleteInterfaceImplementationExample {

    private static final Logger LOG = Logger.getLogger(IncompleteInterfaceImplementationExample.class.getName());

    static class Square implements Shape {
        private static final Logger SQUARE_LOG = Logger.getLogger(Square.class.getName());
        private int size;

        public Square(int size) {
            if (size <= 0) {
                throw new IllegalArgumentException("size must be > 0");
            }
            this.size = size;
        }

        @Override
        public void draw() {
            // Thay System.out bằng logger (và dùng lazy/Supplier để tránh cảnh báo S2629).
            SQUARE_LOG.log(Level.INFO, () -> "Drawing square with size = " + size);
        }

        @Override
        public void resize() {
            // Ví dụ logic resize: tăng kích thước lên 1 (demo)
            this.size += 1;
            SQUARE_LOG.log(Level.INFO, () -> "Resized square to size = " + size);
        }
    }

    // Dùng các method để tránh "never used"
    public static void main(String[] args) {
        Shape s = new Square(5);
        s.draw();
        s.resize();
        s.draw();
        LOG.info("Done.");
    }
}
