package com.ac.luxtask;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ac.utils.Util;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class Test1 {

	public WebDriver driver;

	@BeforeTest
	public void start() {
		System.setProperty("wdm.targetPath", "D://Projects/WebDriverManager/.m2/repository/webdriver");
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://angularjs.org/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.className("span4")));
	}

	@Test
	public void test1() {

		//1) Checks remaining basic scope
		Util.checkRemaining(driver, "1 of 2 remaining");
		
		//2) Adds new todo with the name "test111"
		Util.addNewTodo(driver, "test111");
		
		//3) Checks remaining scope after adding new todo
		Util.checkRemaining(driver, "2 of 3 remaining");
		
		//4) Validates that new todo has been added correctly
		Util.checkNewTodo(driver, "test111");

		//5) Validates work of checkboxes
		List<WebElement> list1 = driver.findElements(By.cssSelector("label.checkbox"));
		list1.get(0).findElement(By.cssSelector("input[type=checkbox]")).click();
		Util.checkRemaining(driver, "3 of 3 remaining");
		list1.get(2).findElement(By.cssSelector("input[type=checkbox]")).click();
		Util.checkRemaining(driver, "2 of 3 remaining");
		Assert.assertEquals(list1.get(2).findElement(By.cssSelector("span.done-true")).getAttribute("className"),
				"done-true");

		//6) Validates archivation working
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[2]/div/a")).click();
		Util.checkRemaining(driver, "2 of 2 remaining");

	}

	@AfterTest
	public void stop() {
		driver.quit();
		driver = null;
	}

}
