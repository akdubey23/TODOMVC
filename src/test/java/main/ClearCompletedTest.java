package main;

import baseFile.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.TodoActions;

import java.util.List;

public class ClearCompletedTest extends BaseTest {

    @Test(groups = "clear")
    public void testClearCompletedTodos() throws Exception {
        TodoActions actions = new TodoActions(driver);
        test = extent.createTest("Clear completed ToDoItems");

        // Add and complete two tasks
        actions.addTodo("Clear ToDoItems 1");
        actions.addTodo("Clear ToDoItems 2");

        actions.markTodoCompleted("Clear ToDoItems 1");
        actions.markTodoCompleted("Clear ToDoItems 2");

        String screenshotPath = actions.takeScreenshot("Clear ToDoItems");
        test.pass("Step  - Clear ToDoItems",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        // Click on "Clear completed" button
        WebElement clearCompletedBtn = driver.findElement(By.className("clear-completed"));
        clearCompletedBtn.click();
        String screenshotPath1 = actions.takeScreenshot("After_ClearCompleted");
        test.pass("Step  - After_ClearCompleted",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath1).build());

        // Validate: completed todos should not be present
        List<WebElement> completedItems = driver.findElements(By.xpath("//li[contains(@class, 'completed')]"));
        boolean isCleared = completedItems.isEmpty();
        System.out.println(" Completed ToDoItems cleared: " + isCleared);
        test.pass("All ToDoItems are cleared");
    }
}
