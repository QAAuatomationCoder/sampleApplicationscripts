package com.backbase.appPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.backbase.baseSetUp.BaseSetUp;
import com.backbase.errorCollectors.ErrorCollector;
import com.backbase.utilLibrary.ApplicationUtilityLib;
import com.backbase.utilLibrary.ExtentTestManager;
import com.backbase.utilLibrary.ExtentTestManager1;
import com.backbase.utilLibrary.ExtentTestManager2;
import com.backbase.utilLibrary.ExtentTestManager3;
import com.backbase.utilLibrary.GenericUtilLib;
import com.relevantcodes.extentreports.LogStatus;

public class Page_EditComputerDetails extends BaseSetUp{
	
	static boolean isEventSuccessful=false;
	List<String> inputValues=new ArrayList<String>();
	
	
	public Page_EditComputerDetails(WebDriver driver)
    {
    	this.driver = driver;
    	PageFactory.initElements(driver,this);
    }

	@FindBy(xpath = "//h1[text()='Edit computer']")
	public static WebElement hd_editCompPg;

	@FindBy(xpath = "//input[@id='name']")
	public static WebElement txtBox_compName;

	@FindBy(xpath = "//input[@id='introduced']")
	public static WebElement txtBox_intDate;

	@FindBy(xpath = "//input[@id='discontinued']")
	public static WebElement txtBox_disDate;

	@FindBy(xpath = "//select[@id='company']")
	public static WebElement dropDw_company;

	@FindBy(xpath = "//input[@class='btn primary']")
	public static WebElement btn_saveComp;
	
	@FindBy(xpath = "//a[@href='/computers']")
	public static WebElement btn_cancel;
	
	@FindBy(xpath = "//input[@value='Delete this computer']")
	public static WebElement btn_delete;
	
	@FindAll(@FindBy(how = How.XPATH, using = "//*[(@type='text')]"))
	List<WebElement> input_TxtFields;
	
	@FindBy(xpath = "//select[@id='company']")
	public static WebElement dropDown_visibleValue;
	
	
	public List<String> fetchInputData()
	{
		try
		{
			inputValues= new ArrayList<String>();
			inputValues=ApplicationUtilityLib.fetchInputTextFieldsData(input_TxtFields);
			System.out.println(inputValues);
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return	inputValues;
		
	}
	
	
	public String getElement_DropDown()
	{
		String dropDownValue=GenericUtilLib.getSelectVisibleVal(dropDown_visibleValue);
		System.out.println(dropDownValue);
		return dropDownValue;
	}
	
	 public void enterCompData(String dataType, String data)
	 {
		
		try {
			switch (dataType) {
			case "CompName":
				isEventSuccessful = GenericUtilLib.EnterText(txtBox_compName, data, "Computer Name");
				Assert.assertTrue(isEventSuccessful);
				System.out.println("Computer name entered successfully");
				logger.info("-----Computer name entered successfully-----");
				System.out.println("");
				ExtentTestManager2.getTest().log(LogStatus.PASS, "Compter name entered successfully");
			
				break;
			case "introDate":
				isEventSuccessful = GenericUtilLib.EnterText(txtBox_intDate, data, "Introduced Date");
				Assert.assertTrue(isEventSuccessful);
				System.out.println("Introdced date entered successfully");
				logger.info("-----Introdced date entered successfully-----");
				System.out.println("");
				ExtentTestManager2.getTest().log(LogStatus.PASS, "Introduced date entered successfully");
				
				break;
			case "disContDate":
				isEventSuccessful = GenericUtilLib.EnterText(txtBox_disDate, data, "Discontinuation Date");
				Assert.assertTrue(isEventSuccessful);
				System.out.println("Introdced date entered successfully");
				logger.info("-----Introdced date entered successfully-----");
				ExtentTestManager2.getTest().log(LogStatus.PASS, "Introduced date entered successfully");
				
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
				//ext_logger.log(LogStatus.PASS, "Could not enter new computer name");
				ExtentTestManager2.getTest().log(LogStatus.FAIL, "Could not entered new computer name");
			} else if (dataType.equalsIgnoreCase("introDate")) {
				logger.info("#########Could not enter new computer introduction date###########");
				//ext_logger.log(LogStatus.PASS, "could not enter new computer introduction date");
				ExtentTestManager2.getTest().log(LogStatus.FAIL, "Could not enter new computer introduction date");
			} else if (dataType.equalsIgnoreCase("disContDate")) {
				logger.info("#########Could not enter new computer discontinuation date###########");
				//ext_logger.log(LogStatus.PASS, "Could not enter new computer discontinuation date");
				ExtentTestManager2.getTest().log(LogStatus.FAIL, "Could not enter new computer discontinuation date");
			}

			else {
				logger.info("#########Add a new computer page is NOT displayed###########");
				//ext_logger.log(LogStatus.PASS, "Add a new computer page is NOT displayed");
				ExtentTestManager2.getTest().log(LogStatus.FAIL, "Add a new computer page is NOT displayed");
			}
		}
	 }
	 
		public void verify_Header_Display(String moduleName) 
		{

			try {

				isEventSuccessful = GenericUtilLib.verifyPageHeader(hd_editCompPg, "Edit computer");
				Assert.assertTrue(isEventSuccessful);
				
				if (moduleName.equalsIgnoreCase("Updation"))
		        {
		        	//ext_logger.log(LogStatus.PASS, "Filter button clicked successfully");
		        	ExtentTestManager2.getTest().log(LogStatus.PASS,"'Edit Computer Page' is displayed successfully");
		        }
		        else if (moduleName.equalsIgnoreCase("Deletion"))
		        {
		        	//ext_logger.log(LogStatus.PASS, "Filter button clicked successfully");
		        	ExtentTestManager3.getTest().log(LogStatus.PASS,"'Edit Computer Page' is displayed successfully" );
		        }

			}

			catch (Throwable t) {
				ErrorCollector.addVerificationFailure(t);
				logger.info("#########Edit Computer page is NOT displayed###########");
				
				if (moduleName.equalsIgnoreCase("Updation"))
		        {
		        	//ext_logger.log(LogStatus.PASS, "Filter button clicked successfully");
		        	ExtentTestManager2.getTest().log(LogStatus.PASS,"'Edit Computer Page' is NOT displayed successfully");
		        }
		        else if (moduleName.equalsIgnoreCase("Deletion"))
		        {
		        	//ext_logger.log(LogStatus.PASS, "Filter button clicked successfully");
		        	ExtentTestManager3.getTest().log(LogStatus.PASS,"'Edit Computer Page' is NOT displayed successfully" );
		        }
			}
		}
		
		 public void selectCompanyName(String companyName)
		 {
			 try
			 {
				 isEventSuccessful= GenericUtilLib.selectFromDropdown(dropDw_company, companyName, "Company Drop down");
				 Assert.assertTrue(isEventSuccessful);
				 System.out.println("Company selected successfully");
				 ExtentTestManager2.getTest().log(LogStatus.PASS, "Company selected successfully");
				 
			 }
			 
			 catch (Throwable t) {
					ErrorCollector.addVerificationFailure(t);
					logger.info("#########Add a new computer page is NOT displayed###########");
					ExtentTestManager2.getTest().log(LogStatus.FAIL, "Company not selected successfully");
				}
		 }
		 
		 public Page_Home clickBtn(String btnName)
		 {
			 try
			 {
				 if(btnName.equalsIgnoreCase("Create this computer"))
				 {
					 isEventSuccessful=GenericUtilLib.click_Element(btn_saveComp, "Create this computer");
					 Assert.assertTrue(isEventSuccessful);
					 ExtentTestManager2.getTest().log(LogStatus.PASS, "Create this computer clicked successfully");
				 }
				 
				 else if (btnName.equalsIgnoreCase("Cancel"))
				 {
					 isEventSuccessful=GenericUtilLib.click_Element(btn_cancel, "Cancel button");
					 Assert.assertTrue(isEventSuccessful);
					ExtentTestManager2.getTest().log(LogStatus.PASS, "Cancel button clicked successfully");					
				 }		
				 
				 else if (btnName.equalsIgnoreCase("Delete"))
				 {
					 isEventSuccessful=GenericUtilLib.click_Element(btn_delete, "Delete button");
					 Assert.assertTrue(isEventSuccessful);
					ExtentTestManager3.getTest().log(LogStatus.PASS, "Delete button clicked successfully");					
				 }	
			 }
			 
			 catch (Throwable t) {
					ErrorCollector.addVerificationFailure(t);
					if(btnName.equalsIgnoreCase("Create this computer")){
						logger.info("#########Could not click Create this computer button ###########");
						ExtentTestManager2.getTest().log(LogStatus.FAIL, "Create this computer button could not be clicked");
					}
					
					else if(btnName.equalsIgnoreCase("Cancel"))
					{
						logger.info("#########Could not click Cancel button ###########");
						//ext_logger.log(LogStatus.PASS, "Cancel button could not be clicked");
						ExtentTestManager2.getTest().log(LogStatus.FAIL, "Cancel button could not be clicked");
					}
					
					else if(btnName.equalsIgnoreCase("Delete"))
					{
						logger.info("#########Could not click Cancel button ###########");
						//ext_logger.log(LogStatus.PASS, "Cancel button could not be clicked");
						ExtentTestManager3.getTest().log(LogStatus.FAIL, "Delete button could not be clicked");
					}
				}
			 return new Page_Home(driver);
		 }


}
