package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Exercise 4 - Login using BasePage & BaseTest")
public class LoginTest extends BaseTest {

    WebDriverWait wait;
    LoginPage page;

    @BeforeAll
    void init() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        page = new LoginPage(driver);   // <— truyền driver vào constructor
    }

    @Test @Order(1)
    void login_ok() {
        page.open();
        page.login("tomsmith", "SuperSecretPassword!");
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(page.successLocator()));
        assertTrue(msg.getText().contains("You logged into a secure area!"));
    }

    @Test @Order(2)
    void login_fail() {
        page.open();
        page.login("wrong", "wrong");
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(page.errorLocator()));
        assertTrue(msg.getText().toLowerCase().contains("invalid"));
    }

    @ParameterizedTest @Order(3)
    @CsvSource({"tomsmith,SuperSecretPassword!,success","wrong,SuperSecretPassword!,error","tomsmith,wrong,error","'', '',error"})
    void login_csv_source(String u,String p,String expect){
        page.open(); page.login(u,p);
        By loc = expect.equalsIgnoreCase("success") ? page.successLocator() : page.errorLocator();
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
        assertTrue(expect.equalsIgnoreCase("success")
                ? msg.getText().contains("You logged into a secure area!")
                : msg.getText().toLowerCase().contains("invalid"));
    }

    @ParameterizedTest @Order(4)
    @CsvFileSource(resources="/login-data.csv", numLinesToSkip=1)
    void login_csv_file(String u,String p,String expect){
        page.open(); page.login(u==null?"":u.trim(), p==null?"":p.trim());
        By loc = expect.equalsIgnoreCase("success") ? page.successLocator() : page.errorLocator();
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
        assertTrue(expect.equalsIgnoreCase("success")
                ? msg.getText().contains("You logged into a secure area!")
                : msg.getText().toLowerCase().contains("invalid"));
    }
}
