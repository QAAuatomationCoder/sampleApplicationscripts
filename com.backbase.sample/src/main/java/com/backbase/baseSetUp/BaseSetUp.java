package com.backbase.baseSetUp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.backbase.utilLibrary.ExcelReader;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseSetUp {

	public static WebDriver driver;
	public static final Logger logger = Logger.getLogger(Test.class.getName());
	public static ExtentReports report;
	public static ExtentReports report1;
	public static ExtentTest ext_logger;
	public String app_Url;
	public String browserType;
	
	public static ExtentTest Extnt_loggerAllCities;
	public static ExcelReader excelReader = null;
	public static Properties prop;
	public static ExcelReader ExcelRd_Obj_Test_Suite = null;
	public static int suiteRow_AllURIs = 0;
	public String XMLtestCaseName = null;
	public String XMLtestCaseID = null;

	public BaseSetUp() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//backbase//configuration//configuration.properties");
			prop.load(ip);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*public void scrtCtnInilization() {
		try {
			System.out.println("Inside script controller initlization method");
			String sctCtrlsuitePath = System.getProperty("user.dir") + "\\Test Excel Files\\Suite Ctrl_Test Data.xlsx";
			ExcelRd_Obj_Test_Suite = new ExcelReader(sctCtrlsuitePath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}*/
	
	/*public void scrtCtnInilization1() {
		try {
			System.out.println("Inside script controller initlization method");
			String sctCtrlsuitePath = System.getProperty("user.dir") + "\\Test Excel Files\\Script Controller.xlsx";
			ExcelRd_Obj_Test_Suite = new ExcelReader(sctCtrlsuitePath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}*/

	//public void initializeTestBaseSetup(String browserType) {
	public void initializeTestBaseSetup() {
		app_Url = prop.getProperty("App_URL");
		browserType = prop.getProperty("Browser_Type");
		try {

			if (browserType.contains("Firefox")) {

				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\" + "Browser Exes/geckodriver.exe");
				logger.info("Creating a object of Firefox Browser");
				logger.info("Navigating to " + app_Url + "for Firefox browser");
				driver = new FirefoxDriver();
				driver.get(app_Url);
				driver.manage().window().maximize();
			}

			else if (browserType.contains("Chrome")) {
				String currentDir = System.getProperty("user.dir");
				String chromeDriverLocation = currentDir + "/Browser Exes/chromedriver.exe";
				logger.info("Creating a object of Chrome Browser");
				System.out.println("Launching Chrome browser......");
				System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
				logger.info("Navigating to " + app_Url + "for Chrome browser");
				driver = new ChromeDriver();
				driver.get(app_Url);
				driver.manage().window().maximize();

			}

		} catch (Exception e) {
			System.out.println("Error....." + e.getMessage());
		}

	}
	
	//@BeforeTest	   
	 public void onBeforeTest(ITestContext testContext)
	    {
	
		 String log4jConfigPath=System.getProperty("user.dir")+"\\"+ "log4j.properties";
	     PropertyConfigurator.configure(log4jConfigPath);          
	        try
	        {	            
	                System.out.println("Inside Before Test class of BASE CLASS: FIREFOX");
	                String filePath=System.getProperty("user.dir")+"\\"+ "TestReportsAllURIsValidation.html";
	                String filePath1=System.getProperty("user.dir")+"\\"+ "TestReportsAllURIsValidation1.html";
	                report=new ExtentReports(filePath,true, DisplayOrder.OLDEST_FIRST);  
	                report1=new ExtentReports(filePath1,true, DisplayOrder.OLDEST_FIRST);
	        }
	        catch(Exception e)
	        {
	            System.out.println(e.getMessage());
	        }
	    }
	public static String [][] getExcelData1(String ExcelName,String SheetName)
	 {
	    	String path= System.getProperty("user.dir")+"\\Test Excel Files\\" + ExcelName;
	    	excelReader=new ExcelReader(path);
	    	String[][] excelData = excelReader.getDatafromExcel(SheetName, ExcelName);
	    	return excelData;
	 }	  
}
