package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) { super(driver); }

    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By userEmail = By.id("userEmail");
    private final By genderMale = By.cssSelector("label[for='gender-radio-1']");
    private final By userNumber = By.id("userNumber");
    private final By dateOfBirthInput = By.id("dateOfBirthInput");
    private final By subjectsInput = By.id("subjectsInput");
    private final By hobbiesSports = By.cssSelector("label[for='hobbies-checkbox-1']");
    private final By uploadPicture = By.id("uploadPicture");
    private final By currentAddress = By.id("currentAddress");
    private final By state = By.id("react-select-3-input");
    private final By city  = By.id("react-select-4-input");
    private final By submitBtn = By.id("submit");
    private final By modalTitle = By.id("example-modal-sizes-title-lg");

    public void open() {
        navigateTo("https://demoqa.com/automation-practice-form");
        hideAdsIfAny();
    }

    public void fillBasic(String fn, String ln, String email, String phone, String address) {
        type(firstName, fn);
        type(lastName, ln);
        type(userEmail, email);
        click(genderMale);
        type(userNumber, phone);
        type(currentAddress, address);
    }

    public void pickDate(String dateStr) {
        click(dateOfBirthInput);
        WebElement el = waitForVisible(dateOfBirthInput);
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(dateStr);
        el.sendKeys(Keys.ENTER);
    }

    public void subjects(String... subs) {
        for (String s : subs) {
            WebElement input = waitForVisible(subjectsInput);
            input.sendKeys(s);
            input.sendKeys(Keys.ENTER);
        }
    }

    public void hobbySports() {
        new Actions(driver).moveToElement(waitForVisible(hobbiesSports)).click().perform();
    }

    public void stateCity(String st, String c) {
        WebElement stInput = waitForVisible(state);
        stInput.sendKeys(st);
        stInput.sendKeys(Keys.ENTER);
        WebElement cInput = waitForVisible(city);
        cInput.sendKeys(c);
        cInput.sendKeys(Keys.ENTER);
    }

    /** Truyền đường dẫn tuyệt đối file ảnh */
    public void upload(String absolutePath) {
        type(uploadPicture, absolutePath);
    }

    public void submit() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", waitForVisible(submitBtn));
        click(submitBtn);
    }

    public boolean submitted() { return isVisible(modalTitle); }

    private void hideAdsIfAny() {
        try {
            String js = """
                var ids=['fixedban','adplus-anchor'];
                ids.forEach(function(id){var el=document.getElementById(id); if(el){el.style.display='none';}});
                var cls=document.querySelectorAll('[class*="banner"],[class*="ads"]');
                cls.forEach(function(el){el.style.display='none';});
            """;
            ((JavascriptExecutor) driver).executeScript(js);
        } catch (Exception ignored) {}
    }
}
