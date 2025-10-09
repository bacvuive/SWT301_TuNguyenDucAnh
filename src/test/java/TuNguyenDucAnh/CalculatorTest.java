package TuNguyenDucAnh;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    static Calculator calculator;

    // YÊU CẦU: dùng @BeforeAll
    @BeforeAll
    static void initAll() {
        calculator = new Calculator();
    }

    // YÊU CẦU: dùng @AfterAll
    @AfterAll
    static void cleanupAll() {
        calculator = null;
    }

    @DisplayName("Cộng 2 + 3 = 5")
    @Test
    void testAddition() {
        assertEquals(5, calculator.add(2, 3));
    }

    @DisplayName("Chia 6 / 3 = 2")
    @Test
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
    }

    @DisplayName("Chia cho 0 phải ném IllegalArgumentException với đúng thông điệp")
    @Test
    void testDivideByZero() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
        assertEquals("Cannot divide by zero", ex.getMessage());
    }

    // YÊU CẦU: ParameterizedTest với @CsvSource cho multiply
    @DisplayName("Parameterized: nhân hai số (multiply)")
    @ParameterizedTest(name = "case {index}: {0} * {1} = {2}")
    @CsvSource({
            "2, 3, 6",
            "0, 5, 0",
            "-1, 4, -4",
            "-3, -3, 9",
            "7, 1, 7"
    })
    void testMultiply_WithCsvSource(int a, int b, int expected) {
        assertEquals(expected, calculator.multiply(a, b));
    }
}
