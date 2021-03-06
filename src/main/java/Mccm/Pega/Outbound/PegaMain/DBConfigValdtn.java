package Mccm.Pega.Outbound.PegaMain;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.JsonException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Mccm.Pega.Outbound.PegaTestBase.TestBase;
import Mccm.Pega.QAUtil.TestUtil;
import Mccm.Pega.excel.utility.Excel_Reader;

public class DBConfigValdtn extends TestBase {
	
	public static String ExcelFilePath;
	
	@FindBy(xpath="(//h3[@class='layout-group-item-title'])[5]")
	WebElement Recordclk;
	
	@FindBy(xpath="(//div[@id='iconExpandCollapse'])[15]")
	WebElement SysAdmin;
	
	@FindBy(xpath="//a[text()='Dynamic System Settings']")
	WebElement webelement4;
		
	@FindBy(xpath="//input[@type='text'][@name='$PpyDisplayHarness$ppySearchText']")
	WebElement MCCMLCOutbound;
	
	@FindBy(xpath="//*[@class='pi pi-search-2']")
	WebElement SerchClick;
	
	@FindBy(xpath="//a[text()='Data Set']")
	WebElement ClickDataSet;
	
	@FindBy(xpath="//a[text()='MCCMLCOutbound']")
	WebElement ClickMCCMLCOutbound;


	@FindBy(xpath="//*[@class='pi pi-caret-down margin-l-1x']")
	WebElement ActionClkMCCMLCOutbound;
	 
	@FindBy(xpath="(//text()[.='Run']/ancestor::a[1])[2]")
	WebElement RunActionClk;
	
	@FindBy(xpath="//select[@name='$PD_pzRunRecord$ppxRunWindow$gTABTHREAD1$ppxRunParameters$ppyTestInputs$ppyOperationIndex']")
	WebElement BrowseClk;
 	
	@FindBy(xpath="(//div[@class='pzbtn-mid'])[2]")
	WebElement RunClk;
	
	@FindBy(xpath="//span[contains(.,'PR-')]")
	WebElement CampRunidValidation;
	
	@FindBy(xpath="(//div[@class='pzbtn-mid'])[4]")
	WebElement Refresh;
	 
	 	
	Excel_Reader obj= new Excel_Reader(ExcelFilePath+"/UseCaseConfigFile/TestData/PegaTestData.xlsx");
			
	String MCCMLCOutboundIN = obj.getCellValue("PegaTestData", 1, 13);
	
	
	
	public DBConfigValdtn( ) {
    	PageFactory.initElements(driver, this);
    }
	
	public void Recordclk() throws InterruptedException
	{
		//wait.until(ExpectedConditions.visibilityOf(Recordclk));
		Thread.sleep(8000);
	 	Recordclk.click( );
		Thread.sleep(8000);
	//	System.out.println("hi");
//		 Actions  action = new Actions(driver);
//	     action.moveToElement(Recordclk).click().build().perform();
//	     Thread.sleep(1000);
//	
 
	}
	public void SysAdmin() throws InterruptedException
	{
		//wait.until(ExpectedConditions.elementToBeClickable(SysAdmin));
	//	waitVisibility(SysAdmin);
		Thread.sleep(8000);
		 SysAdmin.click( );
		 Thread.sleep(8000); 
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
		 
	}
		
	public void MCCMLCOutbound() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(MCCMLCOutbound));
		MCCMLCOutbound.sendKeys(MCCMLCOutboundIN);
        
	}
	
	public void SerchClick() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(SerchClick));
		SerchClick.click( );
        
	}
	public void ClickMCCMLCOutbound() throws InterruptedException
	{
 	//	wait.until(ExpectedConditions.elementToBeClickable(ClickDataSet));
    	Thread.sleep(1000);
 		ClickDataSet.click();
	// 	wait.until(ExpectedConditions.elementToBeClickable(ClickMCCMLCOutbound));
 		  Thread.sleep(1000);
		 ClickMCCMLCOutbound.click( );
		 driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
	}
	public void ActionClkMCCMLCOutbound() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(ActionClkMCCMLCOutbound));
		ActionClkMCCMLCOutbound.click( );
        
	}
	
	public void RunActionClk() throws InterruptedException
	{
		TestUtil obj=new TestUtil();
		obj.ActionMouseMov(RunActionClk);
	}	
	
	public void RunClick() throws InterruptedException
	{
		String winHandleBefore = driver.getWindowHandle();
	for (String winHandle : driver.getWindowHandles()) {
		driver.switchTo().window(winHandle);
	}
	Thread.sleep(6000); 
	WebElement element6 = BrowseClk;
	//WebElement element6 = driver.findElement(By.xpath("//select[@name='$PD_pzRunRecord$ppxRunWindow$gTABTHREAD1$ppxRunParameters$ppyTestInputs$ppyOperationIndex']"));
	 element6.sendKeys("Browse");
	 Thread.sleep(3000);
	 RunClk.click( );
	 Thread.sleep(3000);
	 String winHandleAfter = driver.getWindowHandle();
		for(String winChildHandle : driver.getWindowHandles()) {
			if(!winChildHandle.equals(winHandleBefore) 
					&& !winChildHandle.equals(winHandleAfter)) {
				driver.switchTo().window(winChildHandle);
			}
		}
	 
		for(int i=0;i<5;i++){
			Thread.sleep(8000);
		}
      }
  
 
	
	// display validation of the Camp RUN id data
	
	public void  CampRunidValidation() throws InterruptedException
	{
		WebElement w4 = CampRunidValidation;
	//	w4=driver.findElement(By.xpath("//span[contains(.,'PR-')]"));

		String CAMPAIGNRUNID = w4.getText();

		System.out.println(CAMPAIGNRUNID);

		Excel_Reader obj= new Excel_Reader(ExcelFilePath+"/UseCaseConfigFile/TestData/PegaOutputData.xlsx");

		String CampaigRUNid = obj.getCellValue("PegaOutputData", 1, 0);

		System.out.println(CampaigRUNid);

		Assert.assertEquals(CAMPAIGNRUNID, CampaigRUNid); 
	
	}
	
	
} 