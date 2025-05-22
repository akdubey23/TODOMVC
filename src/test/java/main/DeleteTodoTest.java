package main;

import baseFile.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.TodoActions;

public class DeleteTodoTest extends BaseTest {

    @Test(groups = "delete")
    public void testDeleteTodo() throws Exception {
        TodoActions actions = new TodoActions(driver);
        test = extent.createTest("Delete item");

        String task = "Task to Delete";
        actions.addTodo(task);
        String screenshotPath = actions.takeScreenshot("Add ToDoItems first");
        test.pass("Step  - Added Task to delete",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        // Hover over the todo to make the delete button appear
        WebElement todoItem = driver.findElement(By.xpath("//label[text()='" + task + "']/.."));
        actions.hoverOverElement(todoItem); // Must implement this

        // Click the delete (X) button
        WebElement deleteButton = todoItem.findElement(By.cssSelector("button.destroy"));
        deleteButton.click();

        String screenshotPathdel = actions.takeScreenshot("Delete ToDoItems");
        test.pass("Step  - Delete ToDoItems",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPathdel).build());

        // Verify the task is no longer present
        boolean isDeleted = driver.findElements(By.xpath("//label[text()='" + task + "']")).isEmpty();
        System.out.println("Todo deleted: " + isDeleted);
        test.pass("ToDoItems are Deleted");
    }
}
