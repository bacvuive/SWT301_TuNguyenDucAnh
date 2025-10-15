package tunguyenducanh;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MissingOverrideAnnotationExample {

    private static final Logger LOG = Logger.getLogger(MissingOverrideAnnotationExample.class.getName());

    static class Animal {
        void speak() {
            LOG.log(Level.INFO, "Animal speaks");
        }
    }

    static class Dog extends Animal {
        @Override
        void speak() {
            LOG.log(Level.INFO, "Dog barks");
        }
    }

    // Dùng các method để IDE/Sonar không cảnh báo "never used"
    public static void main(String[] args) {
        Animal a = new Animal();
        a.speak();

        Animal d = new Dog(); // đa hình: sẽ gọi Dog.speak()
        d.speak();
    }
}
