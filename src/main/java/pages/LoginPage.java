package pages;
import locators.LoginPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to interact with elements
    public void enterEmail(String email) {
        WebElement emailElement = driver.findElement(LoginPageLocators.EMAIL_INPUT);
        emailElement.sendKeys(email);
    }

    public void clickSubmitButton() {
        WebElement submitButtonElement = driver.findElement(LoginPageLocators.SUBMIT_BUTTON);
        submitButtonElement.click();
    }

    public void login(String email) {
        enterEmail(email);
        clickSubmitButton();
    }
}
