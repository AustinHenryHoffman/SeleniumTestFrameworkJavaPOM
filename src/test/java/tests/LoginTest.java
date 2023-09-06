package tests;
import locators.LoginPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import testbase.BaseTest;
import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        // The driver is inherited from the BaseTest class, so it's already initialized
        // You can directly use it to navigate to a webpage
        driver.get("https://demo.guru99.com/"); // Replace with your website URL

        // Create an instance of LoginPage and pass the driver to it
        LoginPage loginPage = new LoginPage(driver);

        // Wait for the submit button to become visible (adjust the timeout as needed)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for up to 10 seconds

        // Perform login using Page Object methods
        loginPage.enterEmail("abc@example.com"); // Replace with a valid email address
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageLocators.SUBMIT_BUTTON)); // Replace with the actual button locator
        loginPage.clickSubmitButton();
        // If there's a password field, enter it here: loginPage.enterPassword("your_password");

        // Verify that the submit button is visible
        //if (submitButton.isDisplayed()) {
            // Click the submit button
            //submitButton.click();
        //} else {
            // Handle the case where the button is not visible
            // You can throw an exception or take any necessary action

       // }

        // Add assertions or further actions as needed
    }
}
