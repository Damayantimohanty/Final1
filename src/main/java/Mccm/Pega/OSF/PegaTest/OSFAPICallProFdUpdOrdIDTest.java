package Mccm.Pega.OSF.PegaTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.testng.Assert;
import org.testng.annotations.Test;

import Mccm.Pega.Outbound.PegaTestBase.TestBase;
import Mccm.Pega.excel.utility.Excel_Reader;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.apache.log4j.Logger;


public class OSFAPICallProFdUpdOrdIDTest extends TestBase  {

	public static String KeystorePath;
	public static String hostName;
	public static String port;
	public static String Keystorepassword;
	public static String json;
	public static String mccminternaltrust;
	public static String css1identity;
	public static String OSF_URL_PF;

	Logger log = Logger.getLogger(OSFAPICallProFdUpdOrdIDTest.class.getName());

	@Test

	public void VerifyOSFProcessFeedBackUpdateOrderIDAPIcallSuccessfully() {

		//   public static void main(String[] args) {

		try {
			//          String hostName="mccm-191102761.eu-central-1.elb.amazonaws.com";
			//          String hostName = "192.57.138.25";
			//          String port = "18576";// 8573

			log.info("**** Started the OSF Process Fedback update Order ID API call ****");
			System.out.println("Started the OSF Process Fedback update Order ID API call");
			hostName=general_ReadProperty("HTTPS_OSF_HOSTNAME");
			port=general_ReadProperty("HTTPS_OSF_PORT");
			KeystorePath=general_ReadProperty("KeystorePath");
			Keystorepassword=general_ReadProperty("Keystorepassword");

			mccminternaltrust=general_ReadProperty("KEYSTORE_TRUST_FILENAME");

			css1identity=general_ReadProperty("KEYSTORE_CLIENT_FILENAME");

			OSF_URL_PF= general_ReadProperty("OSFPF_URL");
			URL url = new URL(OSF_URL_PF);
			String readLine = null;
			//	System.setProperty("javax.net.ssl.keyStore",(KeystorePath+"/css1identity.jks"));   
			System.setProperty("javax.net.ssl.keyStore",(KeystorePath+css1identity));  
			System.setProperty("javax.net.ssl.keyStorePassword", Keystorepassword);
			System.setProperty("javax.net.ssl.keyStoreType", "JKS");
			//	System.setProperty("javax.net.ssl.trustStore",(KeystorePath+"/mccminternaltrust.jks"));
			System.setProperty("javax.net.ssl.trustStore",(KeystorePath+mccminternaltrust));
			System.setProperty("javax.net.ssl.trustStorePassword", Keystorepassword);
			System.setProperty("javax.net.ssl.trustStoreType", "JKS");


			json = general_ReadProperty("OSF_Json_PfdbOrderID");
			log.info("-----Successfully read the OSF ProcessFeedback update Order ID  jSON Request file------");

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("x-mccm-usecase", "OSF_ProcessFeedback");
			connection.setRequestProperty("X-MCCM-CorrelationID", "GUID like ad64557");
			connection.setRequestProperty("x-request-id", "GUID like 45656-eade");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			log.debug("Header passed" );
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
			log.info("response: " + response.toString());
			log.info(+ responseCode);
			Assert.assertEquals(responseCode, 200, "Status code is not 200 ,");
			log.info("**** Ended the OSF Process Fedback update Order ID API call ****");
			System.out.println("Ended the OSF Process Fedback update Order ID API call");
		} catch (Exception e) {
			e.printStackTrace();


		}


	}

}

