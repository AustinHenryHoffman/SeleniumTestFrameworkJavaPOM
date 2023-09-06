package tests;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
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
import org.testng.Assert;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        // The driver is inherited from the BaseTest class, so it's already initialized
        // Navigating to test page.
        driver.get("https://demo.guru99.com/");

        // Create an instance of LoginPage and pass the driver to it
        LoginPage loginPage = new LoginPage(driver);

        // Set up wait parameters
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for up to 10 seconds

        // Perform login using Page Object methods
        loginPage.enterEmail("abc@example.com");
        //Verify that submit button is present
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageLocators.SUBMIT_BUTTON));
        // Add a sleep of 5 seconds. I still don't understand why this is necessary,
        // but the test will fail otherwise
        try {
            Thread.sleep(5000); // Sleep for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginPage.clickSubmitButton();
        WebElement user_id_field = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageLocators.USER_ID_FIELD));
        String user_id_field_text = user_id_field.getText();
        //System.out.println(user_id_field_text);
        Assert.assertEquals(user_id_field_text, "User ID :");
        WebElement user_id = driver.findElement(LoginPageLocators.USER_ID);
        String user_id_text = user_id.getText();
        //System.out.println("Element Text: " + user_id_text);
        Assert.assertEquals(user_id_text, "mngr526051");
        //wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));

    }
}

