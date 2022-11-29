package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver,this);
	}

	@FindBy(name = "uid")
	@CacheLookup
	WebElement txtusername;
	
	@FindBy(name = "password")
	@CacheLookup
	WebElement txtpassword;
	
	@FindBy(xpath = "//input[@name='btnLogin']")
	@CacheLookup
	WebElement loginbutton;
	
	@FindBy(xpath="//body/div[3]/div/ul/li[15]/a")
	@CacheLookup
	WebElement logoutbtn;
	
	public void setusername(String uname)
	{
		txtusername.sendKeys(uname);
	}
	
	public void setPassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	
	public void clickButton()
	{
		System.out.println("inside POM clickbutton");
		loginbutton.click();
	}
	
	public void clickLogout()
	{
		System.out.println("inside POM logout button");
	logoutbtn.click();
	}
	
}
