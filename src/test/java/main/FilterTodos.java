package main;

import baseFile.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.TodoActions;

public class FilterTodos extends BaseTest {

    @Test(groups = "filter")
    public void testFilterTodos() throws Exception {
        TodoActions actions = new TodoActions(driver);
        test = extent.createTest("Filter Task ");

        // Add multiple todos
        actions.addTodo("Filter Task 1");  // Active
        actions.addTodo("Filter Task 2");  // Will mark completed
        actions.addTodo("Filter Task 3");  // Active

        // Mark one task as completed
        actions.markTodoCompleted("Filter Task 2");


        // View All
        driver.findElement(By.linkText("All")).click();
        String screenshotPath3 = actions.takeScreenshot("Filter_All");
        test.pass("Step  - Filter_All",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath3).build());

        // View Active
        driver.findElement(By.linkText("Active")).click();
        String screenshotPath4 = actions.takeScreenshot("Filter_Active");
        test.pass("Step  - Filter_Active",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath4).build());

        // View Completed
        driver.findElement(By.linkText("Completed")).click();
        String screenshotPath5 = actions.takeScreenshot("Filter_Completed");
        test.pass("Step  - Filter_Completed",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath5).build());

    }
}
