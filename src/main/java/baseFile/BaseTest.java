package baseFile;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent = ExtentReport.getInstance(); // shared across classes
    protected ExtentTest test; // instance for each test

    @BeforeClass
    public void setUp() {
        // Set the path to your ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Akanksha\\OneDrive\\Documents\\Akanksha Documents\\Java Learnings\\chromedriver-win64\\chromedriver.exe");

        // Initialize the WebDriver
        driver = new ChromeDriver();

        // Maximize the window
        driver.manage().window().maximize();

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Launch the application
        driver.get("https://todomvc.com/examples/react/dist/");
    }

    @AfterClass
    public void closeUI() {
        // Close all browser windows and end the session
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();  // This is mandatory to write HTML
        }
    }


}
