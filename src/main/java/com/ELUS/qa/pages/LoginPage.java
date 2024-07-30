package com.ELUS.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(id="form--signin--field--EMAIL_ADDRESS")
	private WebElement emailField;
	@FindBy(id="form--signin--field--PASSWORD")
	private WebElement passwordField;
	@FindBy(xpath="//input[@data-test-id='form_signin_continue']")
	private WebElement signInButton;
	@FindBy(id="no_account..signin")
	private WebElement noAccountError;
	
	public LoginPage(WebDriver driver)
	{
		
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void EnterEmailPasswordandSubmit(String email, String password)
	{
		
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		signInButton.click();
		
	}
	
	public String getErrorMessage()
	{
		
		String errorMsg= noAccountError.getText();
		return errorMsg;
		
		
	}

}

