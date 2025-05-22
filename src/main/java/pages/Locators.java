package pages;

import org.openqa.selenium.By;

public class Locators {

    // Input box to enter new todo
    public static final By TODO_INPUT = By.className("new-todo");

    // Method to get locator for a specific todo item (by visible label)
    public static By getTodoItem(String itemText) {

        return By.xpath("//label[text()='" + itemText + "']");
    }

    // Method to get checkbox for a specific todo item
    public static By getCheckbox(String itemText) {
        return By.xpath("//label[text()='" + itemText + "']/preceding-sibling::input");
    }

    // Method to get 'Clear completed' button
    public static final By CLEAR_COMPLETED = By.className("clear-completed");

    // Method to get filter buttons (All, Active, Completed)
    public static By getFilter(String filterText) {

        return By.linkText(filterText);  // e.g., All, Active, Completed
    }
}