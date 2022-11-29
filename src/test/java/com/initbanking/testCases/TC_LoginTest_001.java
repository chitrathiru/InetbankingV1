package com.initbanking.testCases;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	
	@Test
	public void loginTest() throws IOException
	{
		System.out.println("testcase entered");
		//logger.info("URL is open");
		LoginPage lp = new LoginPage(driver);
		lp.setusername(username);
		System.out.println("username entered");
		//logger.info("Username Entered");
		lp.setPassword(password);
		System.out.println("password entered");
	//	logger.info("password Entered");
		lp.clickButton();
		
		if (driver.getTitle().equals("Welcome to Manager's Page of Guru99 Bank"))
		{
			Assert.assertTrue(true);
	//		logger.info("Login testcase passed");
		}
		else
		{
			captureScreen(driver,"TC_LoginTest_001");
			Assert.assertFalse(false);
	//		logger.info("Login testcase failed");
		}
		
	}

}
