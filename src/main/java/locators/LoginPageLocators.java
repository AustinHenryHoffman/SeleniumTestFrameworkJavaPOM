package locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
    // Locators for elements on the Login page
    public static final By EMAIL_INPUT = By.xpath("//input[@name='emailid']");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By SUBMIT_BUTTON = By.xpath("//input[@name='btnLogin']");
}
