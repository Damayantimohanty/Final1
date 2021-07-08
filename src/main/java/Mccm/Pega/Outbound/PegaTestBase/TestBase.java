package Mccm.Pega.Outbound.PegaTestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import ExecutionUSECase.MainClass;
import Mccm.Pega.Calc.NBA.StartOffer.CalNBADMCCMSettings;
import Mccm.Pega.Calc.NBA.StartOffer.CalcNBAStartOffDSS;
import Mccm.Pega.Calc.NBA.StartOffer.PegaMarktCalcNBAandStartOff;
import Mccm.Pega.Calc.NBA.StartOffer.PegaPreChkValidCalcNBACampCd;
import Mccm.Pega.DMP.RealTime.PegaMarktDMPRealTime;
import Mccm.Pega.DMP.RealTime.PegaPreChkValidDMPCampCd;
import Mccm.Pega.OSF.OSFDMCCMSettings;
import Mccm.Pega.OSF.OSFDSS;
import Mccm.Pega.OSF.PegaMarktOSF;
import Mccm.Pega.OSF.PegaPreChkValidOSFCampCd;
import Mccm.Pega.Outbound.PegaMain.DBConfigValdtn;
import Mccm.Pega.Outbound.PegaMain.DataLoadDetails;
import Mccm.Pega.Outbound.PegaMain.ExtractChannelFile;
import Mccm.Pega.Outbound.PegaMain.HomePage;
import Mccm.Pega.Outbound.PegaMain.PegaMarketPage;
//import Mccm.Pega.Outbound.PegaMain.PegaMrktNBAReSchd;
//import Mccm.Pega.Outbound.PegaMain.PegaMrktNBAValuChk;
import Mccm.Pega.Outbound.PegaMain.PegaPreChkValidCampCd;
//import Mccm.Pega.Outbound.PegaMain.PreChkNBACampValu;
import Mccm.Pega.Outbound.PegaMain.HomePageDetails;
import Mccm.Pega.Outbound.PegaMain.NBAOutboundValdtn;
//import Mccm.Pega.Outbound.PegaSubClass.HomePageDetails;
import Mccm.Pega.QAUtil.TestUtil;
import Mccm.RESTful.APICall.DMPRTPNBAMobHash;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.util.concurrent.TimeUnit;


public class TestBase {

	public static WebDriver driver;
	public static Properties prop;

	public static String CampRunID;
	public static WebDriverWait wait;



	public TestBase()
	{
		try {
			prop=new Properties();

			String projectPath = System.getProperty("user.dir");

			//	FileInputStream ip=new FileInputStream(projectPath+"/src/main/java/Mccm/Pega/ConfigPega/Config.properties");
			FileInputStream ip=new FileInputStream(projectPath+"/UseCaseConfigFile/Config.properties");
			prop.load(ip);

			PegaMarketPage.ExcelFilePath = System.getProperty("user.dir");
			HomePageDetails.ExcelFilePath = System.getProperty("user.dir");
			PegaMarktDMPRealTime.ExcelFilePath = System.getProperty("user.dir");
			DataLoadDetails.ExcelFilePath = System.getProperty("user.dir");
			DBConfigValdtn.ExcelFilePath = System.getProperty("user.dir");
			ExtractChannelFile.ExcelFilePath = System.getProperty("user.dir");
			NBAOutboundValdtn.ExcelFilePath = System.getProperty("user.dir");
			//			PegaMrktNBAValuChk.ExcelFilePath = System.getProperty("user.dir");
			//			PreChkNBACampValu.ExcelFilePath = System.getProperty("user.dir");
			//			PegaMrktNBAReSchd.ExcelFilePath = System.getProperty("user.dir");
			PegaPreChkValidCampCd.ExcelFilePath = System.getProperty("user.dir");
			PegaPreChkValidDMPCampCd.ExcelFilePath = System.getProperty("user.dir");
			PegaMarktCalcNBAandStartOff.ExcelFilePath = System.getProperty("user.dir");
			CalcNBAStartOffDSS.ExcelFilePath=System.getProperty("user.dir");
			PegaPreChkValidCalcNBACampCd.ExcelFilePath=System.getProperty("user.dir");
			OSFDSS.ExcelFilePath=System.getProperty("user.dir");
			OSFDMCCMSettings.ExcelFilePath=System.getProperty("user.dir");
			PegaMarktOSF.ExcelFilePath=System.getProperty("user.dir");
			PegaPreChkValidOSFCampCd.ExcelFilePath=System.getProperty("user.dir");
			CalNBADMCCMSettings.ExcelFilePath=System.getProperty("user.dir");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}      

	}

	public static void initialization() throws InterruptedException {
		String browserName = prop.getProperty("browser");
		String chromedriverPath = prop.getProperty("chromedriver.dir");
		String chromeDriver = prop.getProperty("chromedriver");
		String headLessMode = prop.getProperty("headless");
		if(browserName.equals("chrome")) {

		 	System.setProperty("webdriver.chrome.driver", chromedriverPath + "/" + chromeDriver);

			String projectPath = System.getProperty("user.dir");

		//	System.setProperty("webdriver.chrome.driver", projectPath+"/chromedriver/chromedriver.exe");  


			if(headLessMode.equals("true")) {

				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--no-sandbox");
				chromeOptions.addArguments("--disable-dev-shm-usage");
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--disable-gpu"); 
				driver = new ChromeDriver(chromeOptions);
				wait =new WebDriverWait(driver , 60);
				//	TimeUnit.SECONDS.sleep(1);
			}
			else{
				driver = new ChromeDriver();
				wait =new WebDriverWait(driver , 60);
			}
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//    driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		//      System.setProperty("webdriver.chrome.silentOutput","true");
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

		//    wait =new WebDriverWait(driver , 60);
	}

	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;
	}

	//  chromeOptions.addArguments("--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
	//*******************************************************************************************
	//*Function:	general_ReadProperty														*
	//*Arguments:	Property Key as String													 	*
	//*Return 		Poverty Value as String														*
	//*Descriptions: 																			*
	//*	This function will read the Properties file and return property value to based on key.	*
	//*Author: 														*
	//*******************************************************************************************
	public static String general_ReadProperty(String propertyKey)
	{
		String propertyValue="";
		File cfgfile = new File("UseCaseConfigFile/configurationFile.properties");

		if(cfgfile.exists())
		{
			Properties propties = new Properties();
			FileInputStream propFile;

			try {
				propFile = new FileInputStream(cfgfile);
				propties.load(propFile);
				propertyValue=propties.getProperty(propertyKey);
			} catch (Exception e1) {

				e1.printStackTrace();
			} 
		}
		return propertyValue;


	}

	public static String general_ReadProperty_UseCase(String propertyKey)
	{
		String propertyValue="";
		File cfgfile = new File("UseCaseConfigFile/UseCaseConfigFile.properties");

		if(cfgfile.exists())
		{
			Properties propties = new Properties();
			FileInputStream propFile;

			try {
				propFile = new FileInputStream(cfgfile);
				propties.load(propFile);
				propertyValue=propties.getProperty(propertyKey);
			} catch (Exception e1) {

				e1.printStackTrace();
			} 
		}
		return propertyValue;


	}



}
