package com.ELUS.testcases;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ELUS.qa.base.base;
import com.ELUS.qa.pages.HomePage;
import com.ELUS.qa.pages.LoginPage;
import com.ELUS.qa.pages.MyAccountPage;
import com.ELUS.qa.utils.Utils;

public class LoginTest extends base {
	 LoginPage Lp;
	public LoginTest()
	{
		super();
	}
	WebDriver driver;

	@BeforeMethod
	public void setup() {
	driver=	intializeBrowserAndOpenApplicationUrl("chrome" );
	HomePage hm = new HomePage(driver);
	hm.closeThePopup();
    Lp= hm.clickOnSignIn();

	}
// After method is code
	@AfterMethod
	void teardown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider="dataSupplier")
	public void VerifyWithValidCredentials( String Email, String Password) {
		
		
		
		Lp.EnterEmailPasswordandSubmit(Email, Password);
		MyAccountPage ma = new MyAccountPage(driver);
		String Wtext = ma.getMyaccountText();
		Assert.assertEquals(Wtext, dataProp.getProperty("loginValidText"));

	}
	
	@DataProvider
	public Object[][] dataSupplier()
	{
		
		Object[][] data = Utils.getTestDataFromExcel("login");
		return data;
		
	}
	
	@Test(priority = 2)
	public void VerifyWithInValidCredentials() {
		LoginPage Lp = new LoginPage(driver);
		Lp.EnterEmailPasswordandSubmit(Utils.generateTimeStamp(), prop.getProperty("validPassword"));
		String Wtext =  Lp.getErrorMessage();
		Assert.assertEquals(Wtext, dataProp.getProperty("loginInvalidText"));

	}
	@Test(priority = 3)
	public void VerifyWithInValidEmail() {
		LoginPage Lp = new LoginPage(driver);
		Lp.EnterEmailPasswordandSubmit(Utils.generateTimeStamp(), prop.getProperty("invalidPassword"));
		String Wtext = driver.findElement(By.id("no_account..signin")).getText();
		Assert.assertEquals(Wtext, dataProp.getProperty("loginInvalidText"));

	}
	
	@Test(priority = 4)
	public void VerifyWithInValidPassword() {

		driver.findElement(By.id("form--signin--field--EMAIL_ADDRESS")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("form--signin--field--PASSWORD")).sendKeys(prop.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@data-test-id='form_signin_continue']")).click();
		String Wtext = driver.findElement(By.id("no_account..signin")).getText();
		Assert.assertEquals(Wtext, dataProp.getProperty("loginInvalidText"));
	}
	@Test(priority = 5)
	public void VerifyWithoutCredentilas() {

		driver.findElement(By.id("form--signin--field--EMAIL_ADDRESS")).sendKeys("");
		driver.findElement(By.id("form--signin--field--PASSWORD")).sendKeys("");
		driver.findElement(By.xpath("//input[@data-test-id='form_signin_continue']")).click();
		String Wtext = driver.findElement(By.id("required.email_address.signin")).getText();
		Assert.assertEquals(Wtext, dataProp.getProperty("LoginInvalidEmailText"));

	}
}
