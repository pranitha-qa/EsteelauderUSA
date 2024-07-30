package com.ELUS.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
	WebDriver driver;
	
	@FindBy(css="h3.account-page__header")
	private WebElement myaccountText;

	
	
	public MyAccountPage(WebDriver driver)
	{
	this.driver= driver;
	
	PageFactory.initElements(driver, this);
		
	}
	
	
	public String getMyaccountText()
	{
		
		String text= myaccountText.getText();
	
		return text;
	}
	
}
