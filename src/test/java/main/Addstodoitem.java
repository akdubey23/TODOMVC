package main;

import baseFile.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TodoActions;

public class Addstodoitem extends BaseTest {

    @Test(groups = "add")
    public void addAndVerifyTodoFromUITest() throws Exception {
        // Actions class already written and reusable
        test = extent.createTest("Add ToDoItems ");
        TodoActions actions = new TodoActions(driver);

        String ToDOitem = "Task is added for MVC Test 01";

        actions.addTodo(ToDOitem);
        String screenshotPath = actions.takeScreenshot("Task is added for MVC Test 01");

        boolean isDisplayed = actions.isTodoDisplayed(ToDOitem);
        System.out.println("Todo displayed: " + isDisplayed);

        // Assertion
        Assert.assertTrue(isDisplayed, "Todo item was not displayed after adding it.");

        test.pass("Step  - ToDoItems are added",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

    }
}
