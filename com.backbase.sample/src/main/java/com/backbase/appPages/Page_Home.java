package com.backbase.appPages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.backbase.baseSetUp.BaseSetUp;
import com.backbase.errorCollectors.ErrorCollector;
import com.backbase.utilLibrary.ApplicationUtilityLib;
import com.backbase.utilLibrary.ExtentTestManager;
import com.backbase.utilLibrary.ExtentTestManager1;
import com.backbase.utilLibrary.ExtentTestManager2;
import com.backbase.utilLibrary.ExtentTestManager3;
import com.backbase.utilLibrary.GenericUtilLib;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;



public class Page_Home extends BaseSetUp{
	
	static boolean isEventSuccessful=false;
	String totalCompFoundData;
	String successMsg;
	ArrayList<String> rowsData=new ArrayList<String>();
	
	public Page_Home(WebDriver driver)
    {
    	this.driver = driver;
    	PageFactory.initElements(driver,this);
    }

	@FindBy(xpath = "//a[contains(text(),'Play sample application')]")
	public static WebElement lnk_sampleApp;

	@FindBy(xpath = "//a[@id='add']")
	public static WebElement btn_addNewComp;

	@FindBy(xpath = "//section[@id='main']/h1")
	public static WebElement hd_computerFound;
	
	@FindBy(xpath = "//div[@class='alert-message warning']")
	public static WebElement altMsg_success;
	
	@FindBy(xpath = "//input[@id='searchbox']")
	public static WebElement txt_searchBox;
	
	@FindBy(xpath = "//input[@id='searchsubmit']")
	public static WebElement btn_Filter;
	
	@FindBy(xpath = "//table[@class='computers zebra-striped']")
	public static WebElement table_CompDts;
	
	@FindBy(xpath = "//div[@class='well']//em")
	public static WebElement msg_NthToDisplay;

	
	/*
	 * 
	 * This method will verify the existence of sample 
	 * application link display on home page
	 * 
	 * 
	 * 
	 * 
	 */
	
	public void verifyHomePage(String moduleName)
    {

        try
        {
           
        	isEventSuccessful=GenericUtilLib.verifyLinkDisplay(lnk_sampleApp, "Play sample application — Computer database");
            Assert.assertTrue(isEventSuccessful);
            logger.info("--------Home Page is displayed successfully-----------------");
            System.out.println("Home Page is displayed successfully");  
            if(moduleName.equalsIgnoreCase("Addition"))
            {
            	//ext_logger.log(LogStatus.PASS, "Home Page is displayed successfully");
            	ExtentTestManager.getTest().log(LogStatus.PASS, "Home Page is displayed successfully" );
            }
            
            else if (moduleName.equalsIgnoreCase("Reading"))
            {
            	//ext_logger.log(LogStatus.PASS, "Home Page is displayed successfully");
            	ExtentTestManager1.getTest().log(LogStatus.PASS, "Home Page is displayed successfully" );
            }
            else if (moduleName.equalsIgnoreCase("Updation"))
            {
            	//ext_logger.log(LogStatus.PASS, "Home Page is displayed successfully");
            	ExtentTestManager2.getTest().log(LogStatus.PASS, "Home Page is displayed successfully" );
            }
            else if (moduleName.equalsIgnoreCase("Deletion"))
            {
            	//ext_logger.log(LogStatus.PASS, "Home Page is displayed successfully");
            	ExtentTestManager3.getTest().log(LogStatus.PASS, "Home Page is displayed successfully" );
            }
            	

           
        }

        catch(Exception e)
        {
            ErrorCollector.addVerificationFailure(e);
            logger.info("--------Home Page is not displayed-----------------");
            logger.info(e.getMessage());
            if(moduleName.equalsIgnoreCase("Addition"))
            	ExtentTestManager.getTest().log(LogStatus.FAIL, "Home Page is not displayed" );          
            else if (moduleName.equalsIgnoreCase("Reading"))         
            	ExtentTestManager1.getTest().log(LogStatus.FAIL, "Home Page is not displayed" );
            else if (moduleName.equalsIgnoreCase("Updation"))
            	ExtentTestManager2.getTest().log(LogStatus.FAIL, "Home Page is not displayed" );
            else if (moduleName.equalsIgnoreCase("Deletion"))
            	ExtentTestManager3.getTest().log(LogStatus.FAIL, "Home Page is not displayed" );
        }
    }
	
	public void clickSampleAppLink(String browser,String testCaseName)
    {

        try
        {
           
        	isEventSuccessful=GenericUtilLib.click_Element(lnk_sampleApp, "Sample application link");
            Assert.assertTrue(isEventSuccessful);
            logger.info("--------Sample Application clicked successfully-----------------");
            System.out.println("Sample Application clicked successfully");     
            //ext_logger.log(LogStatus.PASS, "Sample application link is clicked successfully");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Sample application link is clicked successfully" );

           
        }

        catch(Exception e)
        {
            ErrorCollector.addVerificationFailure(e);
            logger.info("--------Home Page is not displayed-----------------");
            logger.info(e.getMessage());
            //ext_logger.log(LogStatus.PASS, "Sample application link is NOT clicked successfully");
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Sample application link is NOT clicked successfully" );
        }
    }
	
	
	public Page_NewComputerAddition clickAddNewCompBtn(String browser, String tcName) {
		try 
		{
			isEventSuccessful = GenericUtilLib.click_Element(btn_addNewComp, "Add New Computer");
			Assert.assertTrue(isEventSuccessful);
			logger.info("--------Add new computer clicked successfully-----------------");
			System.out.println("Add new computer clicked successfully");
			if (browser.contains("Firefox")) {
				//ext_logger.log(LogStatus.PASS, "Compter name entered successfully in search box");
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Add New computer' button clicked successfully");

			}

		}

		catch (Exception e) 
		{
			ErrorCollector.addVerificationFailure(e);
			logger.info("--------Sample Application Home Page is NOT displayed-----------------");
			logger.info(e.getMessage());
			//ext_logger.log(LogStatus.PASS, "Compter name entered successfully in search box");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Add 'New computer' button is not clicked successfully");
		}

		return new Page_NewComputerAddition(driver);
	}
	
	
	public String getComputerFoundData()
	{
		
		try
		{
			totalCompFoundData=GenericUtilLib.GetText(hd_computerFound);
		}
		
		catch(Exception e)
		{
			logger.info(e.getMessage());
			e.getMessage();
		}
		return totalCompFoundData;
	}
	
public String getSuccessMgs()
{
	try
	{
		successMsg=GenericUtilLib.GetText(altMsg_success);
	}
	
	catch (Exception e) 
	{
		logger.info(e.getMessage());
		e.getMessage();
	}
	
	return successMsg;
}
public void enterCompName_InSrchField(String data,String moduleName)
{
	
		try {

			isEventSuccessful = GenericUtilLib.EnterText(txt_searchBox, data, "New Computer Name");
			Assert.assertTrue(isEventSuccessful);
			logger.info("-----Computer name entered successfully in search box-----");
			System.out.println("");
			  if(moduleName.equalsIgnoreCase("Addition"))
	            {
	            	//ext_logger.log(LogStatus.PASS, data+ "entered successfully");
	            	ExtentTestManager.getTest().log(LogStatus.PASS, "Computer name i.e " + data+ " entered successfully" );
	            }
	            
	            else if (moduleName.equalsIgnoreCase("Reading"))
	            {
	            	//ext_logger.log(LogStatus.PASS, data+ "entered successfully");
	            	ExtentTestManager1.getTest().log(LogStatus.PASS, "Computer name i.e " + data+ " entered successfully");
	            }
	            else if (moduleName.equalsIgnoreCase("Updation"))
	            {
	            	//ext_logger.log(LogStatus.PASS, data+ "entered successfully");
	            	ExtentTestManager2.getTest().log(LogStatus.PASS, "Computer name i.e " + data+ " entered successfully" );
	            }
	            else if (moduleName.equalsIgnoreCase("Deletion"))
	            {
	            	//ext_logger.log(LogStatus.PASS, data+ "entered successfully");
	            	ExtentTestManager3.getTest().log(LogStatus.PASS, "Computer name i.e " + data+ " entered successfully" );
	            }

		}

		catch (Exception e) {
			ErrorCollector.addVerificationFailure(e);
			logger.info("#########Could not enter new computer name###########");
			logger.info(e.getMessage());
			if(moduleName.equalsIgnoreCase("Addition"))
            {
            	//ext_logger.log(LogStatus.FAIL, data+ "was not entered");
            	ExtentTestManager.getTest().log(LogStatus.FAIL, data+ " was not entered successfully" );
            }
            
            else if (moduleName.equalsIgnoreCase("Reading"))
            {
            	//ext_logger.log(LogStatus.FAIL, data+ "was not entered");
            	ExtentTestManager1.getTest().log(LogStatus.FAIL, data+ " was not entered successfully" );
            }
			
            else if (moduleName.equalsIgnoreCase("Updation"))
            {
            	//ext_logger.log(LogStatus.FAIL, data+ "was not entered");
            	ExtentTestManager2.getTest().log(LogStatus.FAIL, data+ " was not entered successfully" );
            }
			
            else if (moduleName.equalsIgnoreCase("Deletion"))
            {
            	//ext_logger.log(LogStatus.FAIL, data+ "was not entered");
            	ExtentTestManager3.getTest().log(LogStatus.FAIL, data+ " was not entered successfully" );
            }
		

		}

	}


public void click_FilterBtn(String moduleName)
{

	try {
		isEventSuccessful = GenericUtilLib.click_Element(btn_Filter, "Filter button");
		Assert.assertTrue(isEventSuccessful);
		if(moduleName.equalsIgnoreCase("Addition"))
        {
        	//ext_logger.log(LogStatus.PASS, "Filter button clicked successfully");
        	ExtentTestManager.getTest().log(LogStatus.PASS, "Filter button clicked successfully");
        }
        
        else if (moduleName.equalsIgnoreCase("Reading"))
        {
        	//ext_logger.log(LogStatus.PASS, "Filter button clicked successfully");
        	ExtentTestManager1.getTest().log(LogStatus.PASS, "Filter button clicked successfully");
        }
        else if (moduleName.equalsIgnoreCase("Updation"))
        {
        	//ext_logger.log(LogStatus.PASS, "Filter button clicked successfully");
        	ExtentTestManager2.getTest().log(LogStatus.PASS,"Filter button clicked successfully");
        }
        else if (moduleName.equalsIgnoreCase("Deletion"))
        {
        	//ext_logger.log(LogStatus.PASS, "Filter button clicked successfully");
        	ExtentTestManager3.getTest().log(LogStatus.PASS,"Filter button clicked successfully" );
        }
		
	}
	catch (Exception e) {
		ErrorCollector.addVerificationFailure(e);
		logger.info("######### Could not click filter button ###########");
		logger.info(e.getMessage());
		if(moduleName.equalsIgnoreCase("Addition"))
        {
        	//ext_logger.log(LogStatus.FAIL, "Filter button was not clicked");
        	ExtentTestManager.getTest().log(LogStatus.FAIL, "Filter button was not clicked" );
        }
        
        else if (moduleName.equalsIgnoreCase("Reading"))
        {
        	//ext_logger.log(LogStatus.FAIL, "Filter button was not clicked");
        	ExtentTestManager.getTest().log(LogStatus.FAIL, "Filter button was not clicked" );
        }
		
        else if (moduleName.equalsIgnoreCase("Updation"))
        {
        	//ext_logger.log(LogStatus.FAIL, "Filter button was not clicked");
        	ExtentTestManager2.getTest().log(LogStatus.FAIL, "Filter button was not clicked" );
        }
		
        else if (moduleName.equalsIgnoreCase("Deletion"))
        {
        	//ext_logger.log(LogStatus.FAIL, "Filter button was not clicked");
        	ExtentTestManager3.getTest().log(LogStatus.FAIL, "Filter button was not clicked" );
        }
	}

	}

	


	
	
	public ArrayList<String> fetchTableData(String newcompName)
	
	{
		try 
		{
			
			rowsData=ApplicationUtilityLib.fetchGridData(table_CompDts,newcompName);
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return rowsData;
	}
	
	public boolean verifyMsg_NthToDisplay()
    {
		isEventSuccessful=false;
        try
        {
           
        	isEventSuccessful=GenericUtilLib.verifyText(msg_NthToDisplay,"Nothing to display");
            Assert.assertTrue(isEventSuccessful);
            logger.info("--------Nothing to display message is displayed-----------------");
            System.out.println("--------Nothing to display message is displayed-----------------");
            isEventSuccessful= true;
           
        }

        catch(Exception e)
        {
            ErrorCollector.addVerificationFailure(e);
            logger.info("--------Nothing to display message is not displayed-----------------");
            logger.info(e.getMessage());
            isEventSuccessful=false;
            
        }
        return isEventSuccessful;
    }
	
	public void checkEleVisiblity() throws InterruptedException
	
	{
		try
		{
			GenericUtilLib.checkElementVisiblity(hd_computerFound);
		}
		catch(Exception e)
		{
			System.out.println("Element not available");
			logger.info(e.getMessage());
		}
	}
	
	public Page_EditComputerDetails clickLinkComputerName(String compName,String moduleName) throws Exception
	{
			
		try
		{
		isEventSuccessful=GenericUtilLib.clickLink_BasedOn_LinkName(table_CompDts, compName);
		Thread.sleep(2000);
		Assert.assertTrue(isEventSuccessful);
		System.out.println("Computer in table grid clicked successfully");
		
			if(moduleName.equalsIgnoreCase("Addition"))
	        {
	        	//ext_logger.log(LogStatus.PASS, "Filter button clicked successfully");
	        	ExtentTestManager.getTest().log(LogStatus.PASS, "Computer link clicked successfully");
	        }
	        
	        else if (moduleName.equalsIgnoreCase("Reading"))
	        {
	        	//ext_logger.log(LogStatus.PASS, "Filter button clicked successfully");
	        	ExtentTestManager1.getTest().log(LogStatus.PASS, "Computer link clicked successfully");
	        }
	        else if (moduleName.equalsIgnoreCase("Updation"))
	        {
	        	//ext_logger.log(LogStatus.PASS, "Filter button clicked successfully");
	        	ExtentTestManager2.getTest().log(LogStatus.PASS,"Computer link clicked successfully");
	        }
	        else if (moduleName.equalsIgnoreCase("Deletion"))
	        {
	        	//ext_logger.log(LogStatus.PASS, "Filter button clicked successfully");
	        	ExtentTestManager3.getTest().log(LogStatus.PASS,"Computer link clicked successfully" );
	        }
			
		}
		
		
		catch(Exception e)
		{
			ErrorCollector.addVerificationFailure(e);
			logger.info("######### Could not click computer link in grid ###########");
			logger.info(e.getMessage());
			if(moduleName.equalsIgnoreCase("Addition"))
	        {
	        	//ext_logger.log(LogStatus.FAIL, "Filter button was not clicked");
	        	ExtentTestManager.getTest().log(LogStatus.FAIL, "Computer link could not be clicked" );
	        }
	        
	        else if (moduleName.equalsIgnoreCase("Reading"))
	        {
	        	//ext_logger.log(LogStatus.FAIL, "Filter button was not clicked");
	        	ExtentTestManager.getTest().log(LogStatus.FAIL, "Computer link could not be clicked" );
	        }
			
			 
	        else if (moduleName.equalsIgnoreCase("Updation"))
	        {
	        	//ext_logger.log(LogStatus.FAIL, "Filter button was not clicked");
	        	ExtentTestManager2.getTest().log(LogStatus.FAIL, "Computer link could not be clicked" );
	        }
			
			 
	        else if (moduleName.equalsIgnoreCase("Deletion"))
	        {
	        	//ext_logger.log(LogStatus.FAIL, "Filter button was not clicked");
	        	ExtentTestManager3.getTest().log(LogStatus.FAIL, "Computer link could not be clicked" );
	        }
		}

		
	return new Page_EditComputerDetails(driver);
	}
			
	}
	
	


