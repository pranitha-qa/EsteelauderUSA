package com.ELUS.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ELUS.qa.base.base;

public class SearchTest extends base{
	
	public SearchTest()
	{
		super();
	}
	
	WebDriver driver;

	@BeforeMethod
	void setup() {
	driver = intializeBrowserAndOpenApplicationUrl("chrome" );
		driver.findElement(By.cssSelector("button[data-testid='join-loyalty-button-close']")).click();
		driver.findElement(By.cssSelector("button.bg-none")).click();
		
		

	}

	@AfterMethod
	void teardown() {
		driver.quit();
	}

	
	@Test(priority=1)
	public void searchWithExistingProduct()
	{
		
	    driver.findElement(By.name("search-input")).sendKeys("Lipstick");
	    driver.findElement(By.name("search-input")).sendKeys(Keys.ENTER);
		 
		driver.findElement(By.cssSelector("button[title='Search Again']")).click();
		boolean flag = driver.findElement(By.id("downshift-0-label")).isDisplayed();
		Assert.assertEquals(flag, true);
		
	}
	

	@Test(priority=2)
	public void searchWithNonExistingProduct()
	{
		
	    driver.findElement(By.name("search-input")).sendKeys("Laptop");
	    driver.findElement(By.name("search-input")).sendKeys(Keys.ENTER);
		 
		driver.findElement(By.cssSelector("button[title='Search Again']")).click();
		boolean flag = driver.findElement(By.cssSelector("p[data-test-id='SEA-NoResultsMessage-Results']")).isDisplayed();
		Assert.assertEquals(flag, true);
		
	}

}
