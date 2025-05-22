package main;

import baseFile.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.TodoActions;

import java.util.List;

public class ViewTodoTest extends BaseTest {

    @Test(groups = "view")
    public void testViewTodos() throws Exception {
        TodoActions actions = new TodoActions(driver);

        // Start Extent Report for this test
        test = extent.createTest("View Todos Test");

        // Add multiple todos
        actions.addTodo("Task 1");
        actions.addTodo("Task 2");
        actions.addTodo("Task 3");

        // Take screenshot and log to report
        String screenshotPath = actions.takeScreenshot("ViewTodo_Added");
        test.pass("Step 1 - Tasks added",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        // Fetch and log all todo items
        List<WebElement> allTodos = driver.findElements(By.cssSelector("ul.todo-list li"));
        test.info("---- Todos Displayed ----");
        for (WebElement todo : allTodos) {
            test.info("• " + todo.getText());
        }

        // Final screenshot after viewing
        String viewScreenshot = actions.takeScreenshot("ViewTodo_Listed");
        test.pass("Step 2 - View Added Tasks",
                MediaEntityBuilder.createScreenCaptureFromPath(viewScreenshot).build());
    }
}
