package Utils;

import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    // This method captures a screenshot of the current browser state and saves it with a timestamped filename.
    public static void takeScreenshot(WebDriver driver, String baseFileName) {
        // Step 1: Cast the WebDriver to TakesScreenshot so we can use getScreenshotAs()
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Step 2: Capture the screenshot as a File
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Step 3: Generate a timestamp to make each filename unique (e.g., 20250529_120135)
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Step 4: Clean the base filename by replacing unsafe characters (e.g., slashes, colons)
        String safeFileName = baseFileName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");

        // Step 5: Build the final filename with timestamp (e.g., login_failed_20250529_120135.png)
        String fullFileName = safeFileName + "_" + timestamp + ".png";

        try {
            // Step 6: Set the destination file path (./screenshots/filename_timestamp.png)
            File destination = new File("./screenshots/" + fullFileName);

            // Step 7: Ensure that the directory exists; if not, create it
            destination.getParentFile().mkdirs();

            // Step 8: Copy the screenshot from the temporary file to the final location
            Files.copy(source.toPath(), destination.toPath());

            // Step 9: Log the saved location of the screenshot
            System.out.println("📸 Screenshot saved: " + destination.getAbsolutePath());

        } catch (IOException e) {
            // Step 10: Log an error message if saving fails
            System.err.println("⚠️ Failed to save screenshot: " + e.getMessage());
        }
    }
}
