package Mccm.Pega.Outbound.PegaMain;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import Mccm.Pega.OSF.PegaTest.OSFAPICallCaptResponseTest;
import Mccm.Pega.Outbound.PegaTestBase.TestBase;
import Mccm.Pega.QAUtil.TestUtil;
import Mccm.Pega.excel.utility.Excel_Reader;


public class ExtractChannelFile extends TestBase {

	public static String ExcelFilePath;


	@FindBy(xpath="(//h3[@class='layout-group-item-title'])[5]")
	WebElement Recordclk;

	@FindBy(xpath="(//div[@id='iconExpandCollapse'])[15]")
	WebElement SysAdmin;

	@FindBy(xpath="//a[text()='Dynamic System Settings']")
	WebElement webelement4;

	@FindBy(xpath="//input[@type='text'][@name='$PpyDisplayHarness$ppySearchText']")
	WebElement GetDataExtractDetails;

	@FindBy(xpath="//*[@class='pi pi-search-2']")
	WebElement SerchClick;

	@FindBy(xpath="//a[text()='Report definition']")
	WebElement ClickReportdeftion;

	@FindBy(xpath="//a[text()='GetDataExtractDetails']")
	WebElement ClickGetDataExtractDetails;


	@FindBy(xpath="//*[@class='pi pi-caret-down margin-l-1x']")
	WebElement ActionClkGetDataExtractDetails;

	@FindBy(xpath="(//text()[.='Run']/ancestor::a[1])[2]")
	WebElement RunActionClk;

	@FindBy(xpath="(//a[@id=\"pui_colmenu\"])[1]")
	WebElement FileNameClk;

	@FindBy(xpath="(//span[@class=\"menu-item-title\"])[1]")
	WebElement FilterClk;


	@FindBy(xpath="//input[@type='text'][@name='$PpyFilterCriteria_pyReportContentPage_pxResults_pzRRListBody_1$ppyColumnFilterCriteria$gFileName1$ppySearchText']")
	WebElement FileNameSerach;

	@FindBy(xpath="//button[text()='Apply']")
	WebElement FilterApplayClk;

	@FindBy(xpath="(//span[contains(.,'Y')])[7]")
	WebElement EXTFLAGChk;



	Excel_Reader obj= new Excel_Reader(ExcelFilePath+"/UseCaseConfigFile/TestData/PegaTestData.xlsx");

	String ChanelFileNameIN = obj.getCellValue("PegaTestData", 1, 16);

	String GetDataExtractDetailsIN = obj.getCellValue("PegaTestData", 1, 15);

	public ExtractChannelFile( ) {
		PageFactory.initElements(driver, this);
	}


	public void Recordclk() throws InterruptedException
	{
		//		log.info("**** Started the Outbond Extract Channel File Validation****");
		//wait.until(ExpectedConditions.visibilityOf(Recordclk));
		Thread.sleep(8000);
		Recordclk.click( );
		Thread.sleep(8000);


		//	log.info("**** Click Record****");

	}

	public void SysAdmin() throws InterruptedException
	{
		Thread.sleep(8000);
		SysAdmin.click( );
		Thread.sleep(8000); 
		//	log.info("**** Click SysAdmin****");
	}
	public void javaexictor4() throws InterruptedException
	{
		TestUtil obj=new TestUtil();
		obj.JavascriptExecutor(webelement4);
	}	
	public void DynamicSystemSettins() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(webelement4));
		webelement4.click( );
		//	log.info("**** Click DynamicSystemSettins****");
	}

	public void GetDataExtractDetails() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(GetDataExtractDetails));
		GetDataExtractDetails.sendKeys(GetDataExtractDetailsIN);

	}

	public void SerchClick() throws InterruptedException
	{

		wait.until(ExpectedConditions.visibilityOf(SerchClick));
		SerchClick.click( );

		System.out.println("**** Search and Click ****");
	}
	public void ClickGetDataExtractDetails() throws InterruptedException
	{

		wait.until(ExpectedConditions.elementToBeClickable(ClickReportdeftion));

		try {

			ClickReportdeftion.click();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{

			ClickReportdeftion.click();

		}


		wait.until(ExpectedConditions.elementToBeClickable(ClickGetDataExtractDetails));

		try {

			ClickGetDataExtractDetails.click( );
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{

			ClickGetDataExtractDetails.click( );

		}
		Thread.sleep(8000);
		System.out.println("****ClickGetDataExtractDetails Click ****");
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);

	}

	public void ActionClkGetDataExtractDetails() throws InterruptedException
	{


		wait.until(ExpectedConditions.visibilityOf(ActionClkGetDataExtractDetails));

		ActionClkGetDataExtractDetails.click( );



	}

	public void RunActionClk() throws InterruptedException
	{

		TestUtil obj=new TestUtil();
		obj.ActionMouseMov(RunActionClk);

	}	

	public void FileNameClk() throws InterruptedException
	{

		String mainWindow=driver.getWindowHandle();
		Set<String> set =driver.getWindowHandles();
		// Using Iterator to iterate with in windows
		Iterator<String> itr= set.iterator();
		while(itr.hasNext())
		{
			String childWindow=itr.next();
			// Compare whether the main windows is not equal to child window. If not equal, we will close.
			if(!mainWindow.equals(childWindow)){
				driver.switchTo().window(childWindow);

				driver.manage().window().maximize();

				wait.until(ExpectedConditions.visibilityOf(FileNameClk));
				Thread.sleep(6000); 
				FileNameClk.click( );
				wait.until(ExpectedConditions.visibilityOf(FilterClk));
				Thread.sleep(6000); 
				FilterClk.click( );

			}
		}
	}
	public void FileNameSerach() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(FileNameSerach));
		Thread.sleep(6000); 
		FileNameSerach.sendKeys(ChanelFileNameIN);
		wait.until(ExpectedConditions.visibilityOf(FilterApplayClk));
		Thread.sleep(6000); 
		FilterApplayClk.click( );
	}
	// display validation of the Flag id data

	public void EXTFLAGCheck() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(EXTFLAGChk));
		WebElement w4= EXTFLAGChk;
		Thread.sleep(6000); 
		String EXTFLAG = w4.getText();

		System.out.println(w4.getText());

		Assert.assertEquals(EXTFLAG, "Y"); 
	}

}
