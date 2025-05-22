package main;

import baseFile.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TodoActions;

import java.util.List;

public class EditTodoTest extends BaseTest {
    @Test(groups = "edit")
    public void testEditTodo() throws Exception {
        TodoActions actions = new TodoActions(driver);
        test = extent.createTest("EditTodoTest item");


        String originalTask = "Initial Task";


        // Step 1: Add a task
        actions.addTodo(originalTask);
        String screenshotPath = actions.takeScreenshot("Screenshot1_Original_Task_Added");
        test.pass("Step  - Screenshot1_Original_Task_Added",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        // Step 2: Edit the task using native double-click method
        actions.editTodoNativeAppend(originalTask);
        Thread.sleep(1000); // optional: let DOM settle
        String screenshotPath3 = actions.takeScreenshot("Task_Edited");
        test.pass("Step  - Task_Edited",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath3).build());

        // Step 3: Click on "All" to ensure visibility
        driver.findElement(By.linkText("All")).click();
        Thread.sleep(500);

        // Step 4: List all todos and check updated text
        List<WebElement> items = driver.findElements(By.cssSelector("ul.todo-list li"));
        boolean matchFound = false;
        for (WebElement item : items) {
            String labelText = item.findElement(By.cssSelector("label")).getText().trim();
            System.out.println("Visible item: " + labelText);
            if (labelText.equals(originalTask + " editedTest")) {
                matchFound = true;
            }
        }

        actions.takeScreenshot("Screenshot3_Verify_Updated_Task");

        // Step 5: Assertion
        Assert.assertTrue(matchFound, "Updated task not found in 'All' tab.");
        test.pass("ToDoItems are Edited");
    }
}


