package tests;

import utils.DriverFactory;              // <— dùng package utils như dự án của bạn
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeAll
    void setUpDriver() {
        driver = DriverFactory.create();
        driver.manage().window().maximize();
    }

    @AfterAll
    void quitDriver() {
        if (driver != null) driver.quit();
    }
}
