package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) { super(driver); }   // <— BẮT BUỘC có

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By success  = By.cssSelector(".flash.success");
    private final By error    = By.cssSelector(".flash.error");

    public void open() { navigateTo("https://the-internet.herokuapp.com/login"); }

    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginBtn);
    }

    public By successLocator() { return success; }
    public By errorLocator()   { return error; }
}
