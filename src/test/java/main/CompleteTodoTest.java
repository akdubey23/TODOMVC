package main;

import baseFile.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.TodoActions;

public class CompleteTodoTest extends BaseTest {

    @Test(groups = "complete")
    public void testMarkTodoAsCompleted() throws Exception {
        TodoActions actions = new TodoActions(driver);
        test = extent.createTest("Mark ToDoItems as Complete");

        String task = "Complete this task";
        actions.addTodo(task);

        // Mark the todo as completed
        actions.markTodoCompleted(task);

        // Take a screenshot
        String screenshotPath1 = actions.takeScreenshot("TodoMarkedCompleted");
        test.pass("Step  - TodoMarkedCompleted",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath1).build());

        // Verify visual indication - strikethrough via CSS class
        WebElement completedItem = driver.findElement(By.xpath("//label[text()='" + task + "']/ancestor::li"));
        String classValue = completedItem.getAttribute("class");
        System.out.println("CSS Class after completion: " + classValue);

        if (classValue.contains("completed")) {
            System.out.println("Todo is marked as completed with visual indication.");
        } else {
            System.out.println("Todo was not visually marked as completed.");
        }
    }

}
