package Mccm.Pega.Calc.NBA.StartOffer.PegaTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.function.Function;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.openqa.selenium.remote.HttpSessionId;
import org.testng.Assert;
import org.testng.annotations.Test;

import Mccm.Pega.Outbound.PegaTestBase.TestBase;
import Mccm.Pega.excel.utility.Excel_Reader;


public class APICallCalcNBAStOffFlowMobBanTest extends TestBase  {

	public static String KeystorePath;
	public static String hostName;
	public static String port;
	public static String Keystorepassword;
	public static String json;
	public static String mccminternaltrust;
	public static String css1identity;


	@Test

	public void VerifyCalcNBAandStartOfferFlowMobileBanAPIcallSuccessfully() {

	


		//   public static void main(String[] args) {

		try {
			//          String hostName="mccm-191102761.eu-central-1.elb.amazonaws.com";
			//          String hostName = "192.57.138.25";
			//          String port = "18576";// 8573
		//	Assert.assertEquals("NBA_hostName","NBA_hostName");


			hostName=general_ReadProperty("HTTPS_OSF_HOSTNAME");
		//	log.info("NBA_hostName : " +hostName);
			port=general_ReadProperty("HTTPS_OSF_PORT");
	//		log.info("NBA_port : " +port);
			KeystorePath=general_ReadProperty("KeystorePath");
	//		log.info("KeystorePath : " +KeystorePath);
			Keystorepassword=general_ReadProperty("Keystorepassword");
	//		log.info("Keystorepassword : " +Keystorepassword);

			mccminternaltrust=general_ReadProperty("KEYSTORE_TRUST_FILENAME");
	//		log.info("Keystorepassword : " +mccminternaltrust);
			css1identity=general_ReadProperty("KEYSTORE_CLIENT_FILENAME");


		 	URL urlForGetRequest = new URL("https://" + hostName + ":" + port
	 				+ "/prweb/PRRestService/MCCMOSF/Services/CalcNBAAndStartOfferFlow");
					
	//		URL urlForGetRequest = new URL("https://ukwtsvulx386.elabs.svcs.entsvcs.net:18576/prweb/PRRestService/MCCMOSF/Services/CalcNBAAndStartOfferFlow");
			String readLine = null;
			//	System.setProperty("javax.net.ssl.keyStore",(KeystorePath+"/css1identity.jks"));  
			System.setProperty("javax.net.ssl.keyStore",(KeystorePath+css1identity)); 
			System.setProperty("javax.net.ssl.keyStorePassword", Keystorepassword);
			System.setProperty("javax.net.ssl.keyStoreType", "JKS");
			//      System.setProperty("javax.net.ssl.trustStore",(KeystorePath+"/mccminternaltrust.jks"));
			System.setProperty("javax.net.ssl.trustStore",(KeystorePath+mccminternaltrust));
			System.setProperty("javax.net.ssl.trustStorePassword", Keystorepassword);
			System.setProperty("javax.net.ssl.trustStoreType", "JKS");

			//	 String json = "{\"Account\": {\"SubscriptionID\": \"GSM1721234585\",\"SI\": \"MobileSubscr\"},\"ContainerName\": \"CustomerNBAOSF\",\"Channel\": \"OSF\",\"Direction\": \"Inbound\",\"Context\": [\"StatusChange\",\"Winback\"],\"TargetChannels\": [\"SMS\",\"AppPush\"]}";

			json = general_ReadProperty("CalNBA_json_MobileBan");

	 //	String json   = "{\"Account\": {\"AccountID\": \"1130541259\",\"SI\": \"MobileBAN\"},\"EventName\": \"Track\",\"Channel\": \"Track\",\"Direction\": \"Inbound\",\"Context\": [\"StatusChange\",\"Winback\"],\"TargetChannels\": [\"SMS\",\"AppPush\"]}";

			// Create all-trusting host name verifier
	 	   HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			HttpsURLConnection connection = (HttpsURLConnection) urlForGetRequest.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("x-mccm-usecase", "CalcNBAAndStartOfferFlow");
			connection.setRequestProperty("X-MCCM-CorrelationID", "GUID like a45ed-eded");
		//	connection.setRequestProperty("X-MCCM-CorrelationID", "GUID like ad64557");
			connection.setRequestProperty("x-request-id", "87654321");
		//	connection.setRequestProperty("x-request-id", "GUID like 45656-eade");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			StringBuffer response = new StringBuffer();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			OutputStream os = connection.getOutputStream();
			os.write(json.getBytes());
			os.flush();
			os.close();
			int responseCode = connection.getResponseCode();

			BufferedReader in = null;
			if (responseCode == 200)
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			else
				in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			while ((readLine = in.readLine()) != null) {
				response.append(readLine);
			}
			in.close();
			System.out.println("response: " + response.toString());
			System.out.println(responseCode);
			Assert.assertEquals(responseCode, 200, "Status code is not 200 ,");

		} catch (Exception e) {
			e.printStackTrace();


		}


	}

}

