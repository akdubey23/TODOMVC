package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TodoActions {

    WebDriver driver;

    public TodoActions(WebDriver driver) {
        this.driver = driver;
    }

    // Add a new todo item
    public void addTodo(String taskText) {
        driver.findElement(Locators.TODO_INPUT).sendKeys(taskText + Keys.ENTER);
    }

    // Check if a todo item is displayed
    public boolean isTodoDisplayed(String taskText) {
        return driver.findElement(Locators.getTodoItem(taskText)).isDisplayed();
    }

    // Mark a todo item as completed
    public void markTodoCompleted(String taskText) {
        driver.findElement(Locators.getCheckbox(taskText)).click();
    }

    // Click a filter button (All, Active, Completed)
    public void clickFilter(String filterName) {
        driver.findElement(Locators.getFilter(filterName)).click();
    }

    // Clear all completed todos
    public void clearCompletedTodos() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement clearBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.CLEAR_COMPLETED));
        clearBtn.click();
    }

    // Take a screenshot and save with given name
//    public void takeScreenshot(String fileName) throws IOException {
//        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        File dest = new File("Screenshots/" + fileName + "_" + System.currentTimeMillis() + ".png");
//        FileUtils.copyFile(src, dest);
//    }

    public String takeScreenshot(String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String folderPath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator;
        String destPath = folderPath + fileName + "_" + System.currentTimeMillis() + ".png";
        File dest = new File(destPath);
        FileUtils.copyFile(src, dest);
        return dest.getAbsolutePath();  // Return full path
    }

    public void doubleClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void jsDoubleClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "var evt = new MouseEvent('dblclick', {bubbles: true, cancelable: true}); arguments[0].dispatchEvent(evt);";
        js.executeScript(script, element);
    }


    public void editTodoTextByJS(String existingText, String newText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script =
                "const todos = document.querySelectorAll('ul.todo-list li');" +
                        "for (let todo of todos) {" +
                        "  const label = todo.querySelector('label');" +
                        "  if (label && label.innerText === arguments[0]) {" +
                        "    todo.classList.add('editing');" +
                        "    const input = todo.querySelector('input.edit');" +
                        "    if (!input) return;" +
                        "    input.focus();" +
                        "    input.value = arguments[1];" +
                        "    const inputEvent = new Event('input', { bubbles: true });" +
                        "    input.dispatchEvent(inputEvent);" +
                        "    const enterEvent = new KeyboardEvent('keydown', { key: 'Enter', bubbles: true });" +
                        "    input.dispatchEvent(enterEvent);" +
                        "    break;" +
                        "  }" +
                        "}";

        try {
            js.executeScript(script, existingText, newText);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void editTodoNativeAppend(String existingText) {
        // Step 1: Locate the label
        WebElement label = driver.findElement(By.xpath("//label[normalize-space(text())='" + existingText + "']"));

        // Step 2: Double-click the label to enter edit mode
        Actions actions = new Actions(driver);
        actions.doubleClick(label)
                .sendKeys(" editedTest")         // append text directly
                .sendKeys(Keys.ENTER)   // commit the edit
                .perform();
    }




}