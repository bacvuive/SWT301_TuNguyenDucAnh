package TuNguyenDucAnh;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private final AccountService service = new AccountService();

    @ParameterizedTest(name = "row {index}: registerAccount({0},{1},{2}) => {3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterAccountFromCsv(String username, String password, String email, boolean expected) {
        boolean result = service.registerAccount(username, password, email);
        assertEquals(expected, result);
    }
}
