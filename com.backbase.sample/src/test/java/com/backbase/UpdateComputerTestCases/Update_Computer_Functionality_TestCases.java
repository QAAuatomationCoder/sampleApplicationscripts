package com.backbase.UpdateComputerTestCases;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.backbase.appPages.Page_EditComputerDetails;
import com.backbase.appPages.Page_Home;
import com.backbase.baseSetUp.BaseSetUp;
import com.backbase.utilLibrary.ActionAfterTest;
import com.backbase.utilLibrary.ActionBeforeTest;
import com.backbase.utilLibrary.ApplicationUtilityLib;
import com.backbase.utilLibrary.DataProviderRepository;
import com.backbase.utilLibrary.ExtentTestManager2;
import com.relevantcodes.extentreports.LogStatus;

public class Update_Computer_Functionality_TestCases extends BaseSetUp{
	
	ActionBeforeTest actionBeforeTest=new ActionBeforeTest();
	ActionAfterTest actionAfterTest=new ActionAfterTest();
	Page_Home homePgObj;
	Page_EditComputerDetails editPgObj;
	List<String> searchedData= new ArrayList<String>();
	String introFormattedDate=null;
	String dcFormattedDate=null;
	boolean isEventSuccessful;
	String successMsg=null;
	String dCFormatteddate=null;
	String updCompName="Silver Line Computer000";
	String updIntroDate="2018-05-21";
	String upddCDate="2018-08-30";
	String updCompany="MOS Technology";
	List<String> editPgInputData= new ArrayList<String>();
	

	@BeforeMethod()
	public void getMethodName(Method method) throws InterruptedException
	{
		 String mthdName=method.getName();
		 System.out.println("Starting " + mthdName + "test case" );
		 ExtentTestManager2.startTest(mthdName);
		 actionBeforeTest.beforeTestAction3(mthdName,mthdName);
	
	}
	

	/** ##############################################################################################################
	 * TC_001_Verify updation of existing computer details
	 * Description: To verify that user should be able to udpate computer details
	 * Precondition - Computer should exists in system
	 * Test Case Type: Positive
	 * @param : extCompName
	 * @throws Exception 
	 * @Author: Hitesh Ghai
	 * @Expected Result: System should correctly update the existing computer details
	 * 
	 * 
	 * ######################################################################################################################
	 */
	
	@Test(dataProviderClass=DataProviderRepository.class,dataProvider="existingCompData",priority=1)
	public void TC_01_Update_Existing_Computer_Details(String extCompName) throws Exception
	{
		
		homePgObj= new Page_Home(driver);	
		
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Updation");
	
		
		// Step 2: Enter existing computer name in search field
		
		homePgObj.enterCompName_InSrchField(extCompName,"Updation");
				
		// Step 3: Click on 'Filter by name' button
		
		homePgObj.click_FilterBtn("Updation");
		
		// Step 4: Click on searched computer name link in grid
				
		editPgObj=homePgObj.clickLinkComputerName(extCompName,"Updation");
		
		// Step 6: Verify 'Edit Computer' page is displayed
		
		editPgObj.verify_Header_Display("Updation");
		
		// Step 7: Update Computer Name
		
		editPgObj.enterCompData("CompName", updCompName);
		
		// Step 7: Update Introduced date
		
		editPgObj.enterCompData("introDate",updIntroDate );
		
		// Step 9: Update Discontinued date
		
		editPgObj.enterCompData("disContDate", upddCDate);
		
		// Step 10: Update company name
		
		editPgObj.selectCompanyName(updCompany);
		
		// Step 11 : Click on 'Save this computer' button
		
		homePgObj=editPgObj.clickBtn("Create this computer");
		
		// Step 12 : Verify correct updated message containing updated computer name displays on home page
		
		successMsg = homePgObj.getSuccessMgs();
		if(successMsg.contains("Done") && successMsg.contains("updated") && successMsg.contains(updCompName))
		{
			
			ExtentTestManager2.getTest().log(LogStatus.PASS, "Success message contains updated computer name hence computer updated successfully");
			logger.info("#########Success message contains updated computer name hence computer updated successfully###########");
		}
		else
		{
			ExtentTestManager2.getTest().log(LogStatus.FAIL, "Success message does not contain updated computer name hence computer was NOT updated");
			logger.info("#########Success message does not contain updated computer name hence computer was NOT updated###########");
		}	
		
		// Step 13: Search updated computer
		
		homePgObj.enterCompName_InSrchField(updCompName,"Updation");
		
		
		// Step 14: Click on 'Filter by name' button
		
		homePgObj.click_FilterBtn("Updation");
		
		// Step 15: Verify filtered results shows correct data
		
		
		// 15.1 First fetch the details of searched updated computer
		
		searchedData= homePgObj.fetchTableData(updCompName);
		
		// 15.2 - Format the Introduce date of filtered record in accordance and comparable with input data
		
		introFormattedDate=ApplicationUtilityLib.converstionDates("dd MMM yyyy","yyyy-MM-dd",searchedData.get(1));
		
		System.out.println("Introduced formatted date is " + introFormattedDate );
		
		// 15.3 Format the Discontinue date of filtered record in accordance and comparable with input Discontinue date

		dCFormatteddate=ApplicationUtilityLib.converstionDates("dd MMM yyyy","yyyy-MM-dd",searchedData.get(2));
		
		System.out.println("Discontinued formatted date is " + dCFormatteddate );
		
		// 15.4 Verify the correctness of updated computer name in filtered record on home page
		
		if(updCompName.equals(searchedData.get(0)))
		{
			
				
				ExtentTestManager2.getTest().log(LogStatus.PASS, "Correct updated 'computer name' i.e-->" + updCompName + " is displayed in filtered record on home page");
				logger.info("#########Correct updated 'computer name' is displayed in filtered record###########");
		}
		else
		{
			
			ExtentTestManager2.getTest().log(LogStatus.FAIL, "Incorrect updated 'computer name' i.e --> " + searchedData.get(0) + "is displayed in filtered record on home page");
			logger.info("#########Incorrect updated 'computer name' is displayed in filtered record###########");
		}	
		
		// 15.5 Verify the correctness of Introduced date in filtered record on home page
		
		if(updIntroDate.equals(introFormattedDate))
		{
			
				
				ExtentTestManager2.getTest().log(LogStatus.PASS, "Correct 'updated introduced date'-->" + updIntroDate + " is displayed in filtered record on home page");
				logger.info("#########Correct updated Introduced date is displayed in filtered record###########");
		}
		else
		{
			
			ExtentTestManager2.getTest().log(LogStatus.FAIL, "Incorrect updated Introduced date i.e -->" + introFormattedDate + "is not displayed in filtered record on home page");
			logger.info("#########Incorrect updated Introduced date is displayed in filtered record###########");
		}
		
		// 15.6 Verify the correctness of Discontinued date in filtered record on home page
		
		if(upddCDate.equals(dCFormatteddate))
		{
			
				
				ExtentTestManager2.getTest().log(LogStatus.PASS, "Correct updated 'Discontinued date' i.e.-->" + upddCDate + " is displayed in filtered record on home page");
				logger.info("#########Correct updated Introduced date is displayed in filtered record###########");
		}
		else
		{
			
			ExtentTestManager2.getTest().log(LogStatus.FAIL, "Incorrect updated 'Discontinued date' i.e.-->" + dCFormatteddate + " is NOT displayed in filtered record on home page");
			logger.info("#########Incorrect updated Discontinued date is NOT displayed in filtered record###########");
		}
		
		// 15.7 Verify the correctness of Company name in filtered record on home page
		
		if(updCompany.equals(searchedData.get(3)))
		{
			
				
				ExtentTestManager2.getTest().log(LogStatus.PASS, "Correct updated 'company name' i.e -->" + updCompany + " is displayed in filtered record on home page");
				logger.info("#########Correct updated 'company name' is displayed in filtered record###########");
		}
		else
		{
			
			ExtentTestManager2.getTest().log(LogStatus.FAIL, "Incorrect updated 'company name' i.e -->" + searchedData.get(3) + "is NOT displayed in filtered record on home page");
			logger.info("#########Incorrect updated company name is NOT displayed in filtered record###########");
		}
		
	}
	
	@AfterMethod
    public void afterEachTest(ITestResult result) throws InterruptedException {
        try
        {

            System.out.println("Inside After Method of test case");
            actionAfterTest.testCaseReportUp(result, "Updation");
        }

        catch (Exception e)
        {
            System.out.println("Excpetion is " + e.getMessage());
        }
	}

	}
	
	
