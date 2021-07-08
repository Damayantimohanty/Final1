package Mccm.Pega.Outbound.PegaTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Mccm.Pega.Outbound.PegaMain.HomePage;
import Mccm.Pega.Outbound.PegaMain.LoginPage;
import Mccm.Pega.Outbound.PegaMain.NBAOutboundValdtn;
//import Mccm.Pega.Outbound.PegaSubClass.NBAOutboundValdtn;
import Mccm.Pega.Outbound.PegaTestBase.TestBase;
import Mccm.Pega.QAUtil.TestUtil;

public class NBAOutboundValdtnTest extends TestBase {

	private static final String priorty = null;
	LoginPage loginpage;
	HomePage  homepage;
	NBAOutboundValdtn NBAOutboundValdtn1;
	TestUtil testutil;
	
	public NBAOutboundValdtnTest()
	{
		super();
	}
 @BeforeMethod
 
    public void setup() throws InterruptedException {
	    initialization();
	    loginpage = new LoginPage();
	    testutil = new TestUtil();
	    NBAOutboundValdtn1 =new NBAOutboundValdtn();
	    homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password")); 
	    NBAOutboundValdtn1=homepage.NBAOutboundValdtn();
 }
 @Test (priority=1)
 public void VerifyOutboundNBABatchProcessCompletedSuccessfully() throws InterruptedException { 
	 NBAOutboundValdtn1.Recordclk();
	 NBAOutboundValdtn1.SysAdmin();
	 NBAOutboundValdtn1.javaexictor4();
	 NBAOutboundValdtn1.DynamicSystemSettins();
	 NBAOutboundValdtn1.D_LatestNBACampaignFlag();
	 NBAOutboundValdtn1.SerchClick();
	 NBAOutboundValdtn1.ClickD_LatestNBACampaignFlag();
	 testutil.SwitcTodefaultContent();
	 testutil.SwitchToFrame("PegaGadget1Ifr");
	 NBAOutboundValdtn1.ActionClkD_LatestNBACampaignFlag();
	 NBAOutboundValdtn1.RunActionClk();
	 NBAOutboundValdtn1.FulshRunClick();
	 NBAOutboundValdtn1.CampRUNid();
	 NBAOutboundValdtn1.ActiveOfferCountCmpltd();
	 NBAOutboundValdtn1.CmpgnDailyCycleCmpltd();
	 NBAOutboundValdtn1.DataExpCmpltdDMP();
//	 NBAOutboundValdtn1.DataExpCmpltdEDWH();
	 NBAOutboundValdtn1.DataExpCmpltdKIASEPOS();
//	 NBAOutboundValdtn1.DataExpCmpltdMAMODB();
	 NBAOutboundValdtn1.DataExpCmpltdOS();
	 NBAOutboundValdtn1.IsCmpgnSuccess();
	 NBAOutboundValdtn1.SaveLCSSCmpltd();
 
 }
 
 
 @AfterMethod
 
      public void teardown() {
	
      driver.quit();
	 
      
 	 System.out.println("Outbound NBA Batch process completed");  
 }
    
}
