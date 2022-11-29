package com.initbanking.testCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.initbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass 
{

	@Test(dataProvider="LoginData")
	public void loginDDT(String uname,String pwd) throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setusername(uname);
		System.out.println("username from excel file :" + uname);
		lp.setPassword(pwd);
		System.out.println("pwd from excel file :" + pwd);
		lp.clickButton();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(6000);
		
		if (isAlertpresent()==true)
		{
			System.out.println("inside isAlert if");
			driver.switchTo().alert().accept();//go to alert and accept it
			driver.switchTo().defaultContent(); // go to main page after accepting alert
			Assert.assertTrue(false);
		}
		else 
		{
			System.out.println("inside isAlert else");
			Assert.assertTrue(true);
			Thread.sleep(6000);
			lp.clickLogout();
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			driver.switchTo().alert().accept();//go to logout alert
			driver.switchTo().defaultContent();
		}
	}
	
	
	public boolean isAlertpresent()
	{
		System.out.println("iside is alert method before try");
		try {
			System.out.println("iside is alert method try");
		driver.switchTo().alert();	
		return true;
		}
		catch (NoAlertPresentException e)
		{
			System.out.println("iside is alert method catch");
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		String Filepath = System.getProperty("user.dir")+"/src/test/java/com/initbanking/testData/inetbanktestdatafinal.xlsx";
		System.out.println("inside data provider");
		int rowcount = XLUtils.getRowCount(Filepath,"Sheet1");
		int colcount = XLUtils.getCellCount(Filepath, "Sheet1", 1);
		String logindata[][]=new String[rowcount][colcount];
		
		for (int i=1;i<=rowcount;i++)
		{
			for (int j=0;j<colcount;j++)
			{
				System.out.println("before getcelldata");
				logindata[i-1][j]=XLUtils.getCellData(Filepath, "Sheet1", i, j);//0,0 <- 1,0
				//logindata[i-1][j]=XLUtils.getCell(j,i).getContents();
				System.out.println("after getcelldata  " + logindata[i-1][j]);
			}
		}
		
		return logindata;
	}
}
