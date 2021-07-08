package Mccm.Pega.Outbound.PegaTest;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.jna.platform.FileUtils;

import Mccm.Pega.Outbound.PegaMain.DataLoadDetails;
import Mccm.Pega.Outbound.PegaMain.HomePage;
import Mccm.Pega.Outbound.PegaMain.LoginPage;
//import Mccm.Pega.Outbound.PegaSubClass.DataLoadDetails;
import Mccm.Pega.Outbound.PegaTestBase.TestBase;
import Mccm.Pega.QAUtil.TestUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class DataLoadDetailsTest extends TestBase {

	private static final String priorty = null;
	LoginPage loginpage;
	HomePage  homepage;
	DataLoadDetails DataLoadDetails1;
	TestUtil testutil;

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;


	public DataLoadDetailsTest()
	{
		super();
	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		initialization();
		loginpage = new LoginPage();
		testutil = new TestUtil();
		DataLoadDetails1 =new DataLoadDetails();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password")); 
		DataLoadDetails1=homepage.DataLoadValidation();
	}

	@Test (priority=1)
	public void VerifyOutboundOracleCansadraDataLoadedSuccessfully() throws InterruptedException { 
		DataLoadDetails1.Recordclk();
		DataLoadDetails1.SysAdmin();
		DataLoadDetails1.javaexictor4();
		DataLoadDetails1.DynamicSystemSettins();
		DataLoadDetails1.d_latestdataloadFlag();
		DataLoadDetails1.SerchClick();
		DataLoadDetails1.Clickd_latestdataloadFlag();
		testutil.SwitcTodefaultContent();
		testutil.SwitchToFrame("PegaGadget1Ifr");
		DataLoadDetails1.ActionClkd_latestdataloadFlag();
		DataLoadDetails1.RunActionClk();
		DataLoadDetails1.FulshRunClick();
		DataLoadDetails1.DataLoadCmpltdOracle();
		DataLoadDetails1.DataLoadCmpltdCsndra();
	}

	@AfterMethod
	public void teardown() {

	//	driver.quit();

		System.out.println("Outbound DB Data loaded Validation Done");  
	}

}
