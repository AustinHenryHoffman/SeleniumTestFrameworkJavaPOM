package tests;
import locators.LoginPageLocators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import testbase.BaseTest;
import java.time.Duration;
import org.testng.Assert;
import utils.TestResultUtil;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {

        //Specify test name to bass to TestResultUtil
        String testName = "LoginTest"; // Specify your test name
        // Create an instance of TestResultUtil
        TestResultUtil testResultUtil = new TestResultUtil(driver,testName);

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

        // At the end of your test, create a results directory and save a screenshot

        String screenshotName = "screenshot"; // Specify a screenshot name

        //Not needed because this directory is being definied in the log4j2.xml
        // String resultsDir = testResultUtil.createResultsDirectory();

        // Save a screenshot with the specified name
        testResultUtil.saveScreenshot(screenshotName);
    }
}

