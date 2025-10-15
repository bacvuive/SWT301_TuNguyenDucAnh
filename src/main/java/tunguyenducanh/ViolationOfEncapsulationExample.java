package tunguyenducanh;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViolationOfEncapsulationExample {

    private static final Logger LOG = Logger.getLogger(ViolationOfEncapsulationExample.class.getName());

    // Package-private inner class to avoid public-class/file-name mismatch.
    static class User {
        private static final Logger USER_LOG = Logger.getLogger(User.class.getName());

        private String name;
        private int age;

        public User(String name, int age) {
            setName(name);
            setAge(age);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            String trimmed = Objects.requireNonNull(name, "name must not be null").trim();
            if (trimmed.isEmpty()) {
                throw new IllegalArgumentException("name must not be blank");
            }
            this.name = trimmed;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            if (age < 0) {
                throw new IllegalArgumentException("age must be >= 0");
            }
            this.age = age;
        }

        // Use lazy logging (Supplier) to satisfy Sonar S2629.
        public void display() {
            USER_LOG.log(Level.INFO, () -> "Name: " + name + ", Age: " + age);
        }

        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + '}';
        }
    }

    // Use the API so IDE/Sonar won't flag as "never used".
    public static void main(String[] args) {
        User u = new User("An", 20);

        // Lazy logging to avoid unnecessary string creation if INFO is disabled.
        LOG.log(Level.INFO, u::toString);

        String n = u.getName();
        int a = u.getAge();
        LOG.log(Level.INFO, () -> "Got user via getters -> " + n + ", " + a);

        u.display();
    }
}
