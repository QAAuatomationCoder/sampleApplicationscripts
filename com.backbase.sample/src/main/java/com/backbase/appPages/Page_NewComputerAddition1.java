package com.backbase.appPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.backbase.baseSetUp.BaseSetUp;
import com.backbase.errorCollectors.ErrorCollector;
import com.backbase.utilLibrary.ExtentTestManager;
import com.backbase.utilLibrary.GenericUtilLib;
import com.relevantcodes.extentreports.LogStatus;


public class Page_NewComputerAddition1 extends BaseSetUp{
	
	static boolean isEventSuccessful=false;
	
	public Page_NewComputerAddition1(WebDriver driver)
    {
    	this.driver = driver;
    	PageFactory.initElements(driver,this);
    }
	
	@FindBy(xpath = "//h1[text()='Add a computer']")
	public static WebElement hd_newCompPg;

	@FindBy(xpath = "//input[@id='name']")
	public static WebElement txtBox_compName;

	@FindBy(xpath = "//input[@id='introduced']")
	public static WebElement txtBox_intDate;

	@FindBy(xpath = "//input[@id='discontinued']")
	public static WebElement txtBox_disDate;

	@FindBy(xpath = "//select[@id='company']")
	public static WebElement dropDw_company;

	@FindBy(xpath = "//input[@class='btn primary']")
	public static WebElement btn_createComp;

	@FindBy(xpath = "//a[@href='/computers']")
	public static WebElement btn_cancel;

	public void verify_Header_Display(String browser, String tcName) {

		try {

			isEventSuccessful = GenericUtilLib.verifyPageHeader(hd_newCompPg, "Add a computer");
			Assert.assertTrue(isEventSuccessful);
			System.out.println("Add a new computer page is displayed");
			if (browser.contains("Firefox")) {
				ext_logger.log(LogStatus.PASS, "Add a new computer page is displayed");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Add a new computer page is displayed");
			}

		}

		catch (Throwable t) {
			ErrorCollector.addVerificationFailure(t);
			logger.info("#########Add a new computer page is NOT displayed###########");
			ext_logger.log(LogStatus.PASS, "Add a new computer page is NOT displayed");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Add a new computer page is NOT displayed");
		}
	}
	 
	 public void enterNewCompData(String browser,String dataType, String data,String tcName)
	 {
		
		try {
			switch (dataType) {
			case "CompName":
				isEventSuccessful = GenericUtilLib.EnterText(txtBox_compName, data, "Computer Name");
				Assert.assertTrue(isEventSuccessful);
				System.out.println("Computer name entered successfully");
				logger.info("-----Computer name entered successfully-----");
				System.out.println("");
				if (browser.contains("Firefox")) {
					ext_logger.log(LogStatus.PASS, "Compter name entered successfully");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Compter name entered successfully");
				}
				break;
			case "introDate":
				isEventSuccessful = GenericUtilLib.EnterText(txtBox_intDate, data, "Introduced Date");
				Assert.assertTrue(isEventSuccessful);
				System.out.println("Introdced date entered successfully");
				logger.info("-----Introdced date entered successfully-----");
				System.out.println("");
				if (browser.contains("Firefox")) {
					ext_logger.log(LogStatus.PASS, "Introduced date entered successfully");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Introduced date entered successfully");
				}
				break;
			case "disContDate":
				isEventSuccessful = GenericUtilLib.EnterText(txtBox_disDate, data, "Discontinuation Date");
				Assert.assertTrue(isEventSuccessful);
				System.out.println("Introdced date entered successfully");
				logger.info("-----Introdced date entered successfully-----");
				System.out.println("");
				if (browser.contains("Firefox")) {
					ext_logger.log(LogStatus.PASS, "Introduced date entered successfully");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Introduced date entered successfully");
				}
				break;
			default:
				System.out.println("No match");
			}
		}
		
		catch(Throwable t)
		{
			ErrorCollector.addVerificationFailure(t);

			if (dataType.equalsIgnoreCase("CompName")) {
				logger.info("#########Could not enter new computer name###########");
				ext_logger.log(LogStatus.PASS, "Could not enter new computer name");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not entered new computer name");
			} else if (dataType.equalsIgnoreCase("introDate")) {
				logger.info("#########Could not enter new computer introduction date###########");
				ext_logger.log(LogStatus.PASS, "could not enter new computer introduction date");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not enter new computer introduction date");
			} else if (dataType.equalsIgnoreCase("disContDate")) {
				logger.info("#########Could not enter new computer discontinuation date###########");
				ext_logger.log(LogStatus.PASS, "Could not enter new computer discontinuation date");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not enter new computer discontinuation date");
			}

			else {
				logger.info("#########Add a new computer page is NOT displayed###########");
				ext_logger.log(LogStatus.PASS, "Add a new computer page is NOT displayed");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Add a new computer page is NOT displayed");
			}
		}
	 }
	 
	 public void selectCompanyName(String browser,String companyName,String tcName)
	 {
		 try
		 {
			 isEventSuccessful= GenericUtilLib.selectFromDropdown(dropDw_company, companyName, "Company Drop down");
			 Assert.assertTrue(isEventSuccessful);
			 System.out.println("Company selected successfully");
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Company selected successfully");
			 
		 }
		 
		 catch (Throwable t) {
				ErrorCollector.addVerificationFailure(t);
				logger.info("#########Add a new computer page is NOT displayed###########");
				ext_logger.log(LogStatus.PASS, "Company not selected successfully");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Company not selected successfully");
			}
	 }
	 
	 
	 public Page_Home clickBtn(String browser,String btnName,String tcName)
	 {
		 try
		 {
			 if(btnName.equalsIgnoreCase("Create this computer"))
			 {
				 isEventSuccessful=GenericUtilLib.click_Element(btn_createComp, "Create this computer");
				 Assert.assertTrue(isEventSuccessful);
				 if (browser.contains("Firefox")) {
						ext_logger.log(LogStatus.PASS, "Clicked Createa a new computer ");
						ExtentTestManager.getTest().log(LogStatus.PASS, "Create this computer clicked successfully");
					}
				 
			 }
			 
			 else if (btnName.equalsIgnoreCase("Cancel"))
			 {
				 isEventSuccessful=GenericUtilLib.click_Element(btn_cancel, "Cancel button");
				 Assert.assertTrue(isEventSuccessful);
				 if (browser.contains("Firefox")) {
						ext_logger.log(LogStatus.PASS, "Clicked Cancel button ");
						ExtentTestManager.getTest().log(LogStatus.PASS, "Cancel button clicked successfully");
					}
			 }

			 
		 }
		 
		 catch (Throwable t) {
				ErrorCollector.addVerificationFailure(t);
				if(btnName.equalsIgnoreCase("Create this computer")){
					logger.info("#########Could not click Create this computer button ###########");
					ext_logger.log(LogStatus.PASS, "Create this computer button could not be clicked");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Create this computer button could not be clicked");
				}
				
				else if(btnName.equalsIgnoreCase("Cancel"))
				{
					logger.info("#########Could not click Cancel button ###########");
					ext_logger.log(LogStatus.PASS, "Cancel button could not be clicked");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Cancel button could not be clicked");
				}
			}
		 return new Page_Home(driver);
	 }

	 
	 

}
