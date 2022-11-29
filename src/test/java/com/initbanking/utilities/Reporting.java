package com.initbanking.utilities;
//Listener class used to generate Extent Report
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.lookup.MarkerLookup;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	
	public ExtentSparkReporter htmlreport;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testcontext)
	{
	String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//timestamp
	String repname = "Test-Report-"+timestamp+".html";
	htmlreport = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repname);//specifiy location on Report
	try {
		htmlreport.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	extent = new ExtentReports();
	extent.attachReporter(htmlreport);
	extent.setSystemInfo("Hostname", "localhost");
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("User", "Chitra");
	
	htmlreport.config().setDocumentTitle("Inet Bank Testing");
	htmlreport.config().setReportName("Functional Testing");
	htmlreport.config().setTheme(Theme.DARK);
	 
	
	}
	
	public void onTestSuccess(ITestResult itr)
	{
		logger = extent.createTest(itr.getName()); // create a new entry in report
		logger.log(Status.PASS,MarkupHelper.createLabel(itr.getName(),ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult itr)
	{
		logger = extent.createTest(itr.getName()); // create a new entry in report
		logger.log(Status.FAIL,MarkupHelper.createLabel(itr.getName(),ExtentColor.RED));
		
		String screenshotpath = System.getProperty("user.dir")+"/Screenshots/"+itr.getName()+".png";
		File f = new File(screenshotpath);
		
		if (f.exists())
		{
			logger.fail("screen shot is below " + logger.addScreenCaptureFromPath(screenshotpath));
		}
		
	}
	
	public void onTestSkipped(ITestResult itr)
	{
		logger = extent.createTest(itr.getName()); // create a new entry in report
		logger.log(Status.SKIP,MarkupHelper.createLabel(itr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testcontext)
	{
		extent.flush();
	}
}
