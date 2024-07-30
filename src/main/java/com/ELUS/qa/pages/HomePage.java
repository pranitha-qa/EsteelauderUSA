package com.ELUS.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
WebDriver driver;	
	//Objects
	@FindBy(css="button[data-testid='join-loyalty-button-close']")
private	WebElement popUp;
	
	@FindBy(className="signed-out-link__content")
private	WebElement signIn;
	
	
	public HomePage(WebDriver driver)
	{
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}

	//Actions
	
	public void closeThePopup()
	{
		
		popUp.click();
		
	}
	
	public LoginPage clickOnSignIn()
	{
		
		signIn.click();
		 return new LoginPage(driver);
		
	}
}
