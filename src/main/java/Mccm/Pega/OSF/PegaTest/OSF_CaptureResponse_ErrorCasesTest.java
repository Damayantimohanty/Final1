package Mccm.Pega.OSF.PegaTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.tools.sjavac.Log;
import com.sun.xml.fastinfoset.sax.Properties;

//import com.sun.tools.sjavac.Log;

import Mccm.Pega.Outbound.PegaTestBase.TestBase;
import Mccm.Pega.excel.utility.Excel_Reader;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

public class OSF_CaptureResponse_ErrorCasesTest extends TestBase {

	public static String KeystorePath;
	public static String hostName;
	public static String port;
	public static String Keystorepassword;
	public static String json;
	public static String ExcelFilePath;
	public static String OSF_URL_CR;
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	DataFormatter formatter = new DataFormatter();

	
	
	@Test(dataProvider = "exceldata")

	public void VerifyOSFCaptureResponseOutcomeAPIcallSuccessfully(String json1) throws IOException {

		log.info("**** Capturing the NBA_Hostname , NBA_PORT , Keystorepath , Keystorepassword ****");
		hostName = general_ReadProperty("NBA_hostName");
		port = general_ReadProperty("NBA_port");
		KeystorePath = general_ReadProperty("KeystorePath");
		Keystorepassword = general_ReadProperty("Keystorepassword");

		log.info("**** Getting the OSF CaptureResponse URL  ****");
		OSF_URL_CR= general_ReadProperty("OSFCR_URL");
		URL url = new URL(OSF_URL_CR);
		String readLine = null;
		System.setProperty("javax.net.ssl.keyStore", (KeystorePath + "/css1identity.jks"));
		System.setProperty("javax.net.ssl.keyStorePassword", Keystorepassword);
		System.setProperty("javax.net.ssl.keyStoreType", "JKS");
		System.setProperty("javax.net.ssl.trustStore", (KeystorePath + "/mccminternaltrust.jks"));
		System.setProperty("javax.net.ssl.trustStorePassword", Keystorepassword);
		System.setProperty("javax.net.ssl.trustStoreType", "JKS");

		log.info("-----Reading the OSF CaptureResponse Request file------");

		json = json1;
		System.out.println("json file is:" + json);

		log.info("-----Successfully read the OSF CaptureResponse Request file------");

		// Create all-trusting host name verifier

		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("X-MCCM-UseCase", "OSF_CaptureResponse");
		connection.setRequestProperty("X-MCCM-CorrelationID", "GUID likead64557");
		connection.setRequestProperty("X-Request-Id", "GUID likead785657");
		connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

		OutputStream os = connection.getOutputStream();
		os.write(json.getBytes());
		os.flush();
		os.close();
		int responseCode = connection.getResponseCode();
		System.out.println("response code is " + responseCode);
		BufferedReader in = null;
		StringBuffer response = new StringBuffer();

		if (responseCode == 200)
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		else
			in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
		while ((readLine = in.readLine()) != null) {
			response.append(readLine);
		}
		in.close();

		System.out.println("response: " + response.toString());
		log.info(+responseCode);
		Assert.assertEquals(responseCode, 400, "Status code is not 200 ,");

	}

	@DataProvider(name = "exceldata")
	public Object[][] testData() throws Exception {
		/*
		 * String projectfolderpath = System.getProperty("user.dir"); FileInputStream
		 * fis= new FileInputStream(projectfolderpath+
		 * "\\UseCaseConfigFile\\TestData\\OSF_Error_Scenarios.xlsx"); XSSFWorkbook wb =
		 * new XSSFWorkbook(fis); XSSFSheet sheet=
		 * wb.getSheet("Capture_response_error_data"); int rowcount =
		 * sheet.getPhysicalNumberOfRows(); XSSFRow row= sheet.getRow(0); int col=
		 * row.getLastCellNum();
		 * 
		 * 
		 * Object excelData[][] = new Object[rowcount-1][col];
		 * 
		 * for(int i=0; i<rowcount-1; i++) { row = sheet.getRow(i+1);
		 * 
		 * for(int j=0; j<col; j++) {
		 * 
		 * XSSFCell cell= row.getCell(j); // excelData[i][j]= cell.getStringCellValue();
		 * excelData[i][j]= formatter.formatCellValue(cell);
		 * 
		 * 
		 * }
		 * 
		 * } return excelData;
		 */

		

		Excel_Reader obj = new Excel_Reader(ExcelFilePath + "/UseCaseConfigFile/TestData/OSF_Error_Scenarios.xlsx");

		obj.getSheet("Capture_response_error_data");
		int rowcount1 = obj.getrowsnum("Capture_response_error_data");

		System.out.println(rowcount1);

		XSSFRow row = obj.getfirstrow("Capture_response_error_data");

		int col = row.getLastCellNum();

		System.out.println(col);

		Object excelData[][] = new Object[rowcount1 - 1][col];

		for (int i = 0; i < rowcount1 - 1; i++) {

			row = obj.getfirstrow1("Capture_response_error_data", i);

			for (int j = 0; j < col; j++) {
				XSSFCell cell = row.getCell(j);
				excelData[i][j] = formatter.formatCellValue(cell);
				

			}

		}
		return excelData;

	}

}
