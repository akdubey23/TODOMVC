package baseFile;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;


public class ExtentReport {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = "C:\\Users\\Akanksha\\IdeaProjects\\todoMVC\\Screenshots\\ExtentReport.html";;
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("TodoMVC Test Report");
            reporter.config().setDocumentTitle("Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Akanksha");
        }
        return extent;
    }

}
