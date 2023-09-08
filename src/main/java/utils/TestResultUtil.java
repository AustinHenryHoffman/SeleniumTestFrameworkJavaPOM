package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestResultUtil {
    private static Logger logger = LogManager.getLogger(TestResultUtil.class);
    private WebDriver driver;
    private String testRunName;
    private String resultsDirName;

    public TestResultUtil(WebDriver driver, String testRunName) {
        this.driver = driver;
        this.testRunName = testRunName;

        // Create a unique directory for the test run
        resultsDirName = createResultsDirectory();

        // Configure Log4j to use this directory for log files
        System.setProperty("logDirectory", resultsDirName);
        //System.setProperty("logFilename", "test.log");
        //Initialize logger after properties are set
        // Initialize logger after properties are set
        logger = LogManager.getLogger(TestResultUtil.class);

    }

    private String generateTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return dateFormat.format(new Date());
    }

    public String createResultsDirectory() {
        String timestamp = generateTimestamp();
        String resultsDirName = "results/" + testRunName + "_" + timestamp; // Unique directory for each test
        File resultsDir = new File(resultsDirName);

        if (!resultsDir.exists()) {
            if (resultsDir.mkdirs()) {
                logger.info("Results directory created: " + resultsDirName);
            } else {
                logger.error("Failed to create results directory: " + resultsDirName);
            }
        }

        return resultsDirName;
    }

    public void saveScreenshot(String screenshotName) {
        // Replace 'driver' with your WebDriver instance
        String timestamp = generateTimestamp(); // Generate a timestamp
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = resultsDirName + "/" + screenshotName + "_" + timestamp + ".png"; // Use logDirectory here

        try {
            Files.copy(screenshotFile.toPath(), new File(screenshotPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
            logger.info("Screenshot saved: " + screenshotPath);
        } catch (Exception e) {
            logger.error("Failed to save screenshot: " + e.getMessage(), e);
        }
    }

}
