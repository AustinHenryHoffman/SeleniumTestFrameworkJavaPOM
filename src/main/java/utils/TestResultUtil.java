package utils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestResultUtil {

    private WebDriver driver;

    public TestResultUtil(WebDriver driver) {
        this.driver = driver;
    }

    private String generateTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return dateFormat.format(new Date());
    }

    private String createResultsDirectory() {
        String timestamp = generateTimestamp();
        String resultsDirName = "results/" + timestamp; // Adjust the path as needed
        File resultsDir = new File(resultsDirName);

        if (!resultsDir.exists()) {
            if (resultsDir.mkdirs()) {
                System.out.println("Results directory created: " + resultsDirName);
            } else {
                System.err.println("Failed to create results directory: " + resultsDirName);
            }
        }

        return resultsDirName;
    }

    private void saveScreenshot(String resultsDir, String screenshotName) {
        // Replace 'driver' with your WebDriver instance
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = resultsDir + "/" + screenshotName + ".png";

        try {
            Files.copy(screenshotFile.toPath(), new File(screenshotPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (Exception e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }



}
