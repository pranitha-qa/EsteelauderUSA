package com.ELUS.testcases;

import java.awt.Desktop.Action;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ELUS.qa.base.base;
import com.ELUS.qa.utils.Utils;

public class RegisterTest extends base{
	public RegisterTest()
	{
		super();
	}
	WebDriver driver;

	@BeforeMethod
	void setup() {
		driver = intializeBrowserAndOpenApplicationUrl(prop.getProperty("browser") );
		driver.findElement(By.cssSelector("button[data-testid='join-loyalty-button-close']")).click();
		driver.findElement(By.className("signed-out-link__content")).click();
		driver.findElement(By.cssSelector("a.js-create-account")).click();

	}

	@AfterMethod
	void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void registerWithMandateFields() {

		driver.findElement(By.id("form--registration--field--PC_EMAIL_ADDRESS")).sendKeys(Utils.generateTimeStamp());
		driver.findElement(By.id("form--registration--field--PASSWORD")).sendKeys(prop.getProperty("validPassword"));
		WebElement element = driver.findElement(By.xpath("(//span[@class='label-content']/strong)[1]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		executor.executeScript("window.scrollBy(0,500)");
		executor.executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector("input[value='Create Account']")));
		driver.findElement(By.cssSelector("input[value='Create Account']")).click();
		driver.findElement(By.cssSelector("button[id=cboxClose]")).click();
		String wText = driver.findElement(By.cssSelector("h3.account-page__header")).getText();
		Assert.assertEquals(wText, dataProp.getProperty("registerValidText"));
	}

	@Test(priority = 2)
	public void registerWithExistingEmail() {
		// FriJul2613_12_35IST2024@gmail.com
		driver.findElement(By.id("form--registration--field--PC_EMAIL_ADDRESS"))
				.sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("form--registration--field--PASSWORD")).sendKeys("Password@123");
		WebElement element = driver.findElement(By.xpath("(//span[@class='label-content']/strong)[1]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		executor.executeScript("window.scrollBy(0,500)");
		executor.executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector("input[value='Create Account']")));

		String wText = driver.findElement(By.id("account_exists..")).getText();
		Assert.assertEquals(wText, dataProp.getProperty("registerValidEamilText"));
	}

	@Test(priority = 3)
	public void registerWithoutEnteringAnyFields() {
		driver.findElement(By.id("form--registration--field--PC_EMAIL_ADDRESS")).sendKeys("");
		driver.findElement(By.id("form--registration--field--PASSWORD")).sendKeys("");
		WebElement element = driver.findElement(By.xpath("(//span[@class='label-content']/strong)[1]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		executor.executeScript("window.scrollBy(0,500)");
		executor.executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector("input[value='Create Account']")));

		String wText = driver.findElement(By.id("required.pc_email_address.registration")).getText();
		Assert.assertEquals(wText, dataProp.getProperty("registerNoEntryText"));
		
	}

	
}
