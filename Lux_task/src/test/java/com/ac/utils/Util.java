package com.ac.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Util {

	static By fieldName = By.xpath("/html/body/div[2]/div[3]/div[2]/div[2]/div/form/input[1]");
	static By addButton = By.xpath("/html/body/div[2]/div[3]/div[2]/div[2]/div/form/input[2]");
	static By remField = By.cssSelector("span.ng-binding");
	static By fieldTodo = By.cssSelector("ul.unstyled");
	static By listTodo = By.cssSelector("li.ng-scope");
	static By classStatus = By.cssSelector("span.done-false");

	/**
	 *  Method checks remaining field;
	 * @param driver
	 * @param str
	 */
	public static void checkRemaining(WebDriver driver, String str) {
		Assert.assertEquals(driver.findElement(remField).getAttribute("innerText").trim(), str);
	}

	/**
	 *  Method adds new Todo;
	 * @param driver
	 * @param name
	 */
	public static void addNewTodo(WebDriver driver, String name) {
		driver.findElement(fieldName).sendKeys(name);
		driver.findElement(addButton).click();

	}

	/**
	 *  Method validates new Todo;
	 * @param driver
	 * @param name
	 */
	public static void checkNewTodo(WebDriver driver, String name) {
		List<WebElement> list = driver.findElement(fieldTodo).findElements(listTodo);
		int i = list.size() - 1;
		Assert.assertEquals(list.get(i).getAttribute("innerText").trim(), name);
		Assert.assertEquals(list.get(i).findElement(classStatus).getAttribute("className").trim(), "done-false");
	}

}
