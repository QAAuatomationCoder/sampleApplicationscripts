package com.backbase.ExistingComputerTestCases;

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
import com.backbase.utilLibrary.ExtentTestManager1;
import com.relevantcodes.extentreports.LogStatus;


public class Read_Existing_Computer_Functionality_TestCases extends BaseSetUp{
	
	ActionBeforeTest actionBeforeTest=new ActionBeforeTest();
	ActionAfterTest actionAfterTest=new ActionAfterTest();
	Page_Home homePgObj;
	Page_EditComputerDetails editPgObj;
	List<String> searchedData= new ArrayList<String>();
	String introFormattedDate=null;
	String dcFormattedDate=null;
	boolean isEventSuccessful;
	List<String> editPgInputData= new ArrayList<String>();
	
	
	@BeforeMethod()
	public void getMethodName(Method method) throws InterruptedException
	{
		 String mthdName=method.getName();
		 System.out.println("Starting " + mthdName + "test case" );
		 // Also overridden start test for description
		 ExtentTestManager1.startTest(mthdName);
		 actionBeforeTest.beforeTestAction3(mthdName,mthdName);
	
	}
	
	/** ##############################################################################################################
	 * TC_001_Verify existing computer details in computer data grid
	 * Description: To verify the details of already existing computer
	 * Precondition - Computer should exists in system
	 * Test Case Type: Positive
	 * @param : extCompName
	 * @throws ParseException 
	 * @Author: Hitesh Ghai
	 * @Expected Result: System should display correct details for existing computers in data grid on home page
	 * 
	 * 
	 * ######################################################################################################################
	 */
	
	@Test(dataProviderClass=DataProviderRepository.class,dataProvider="existingCompData",priority=1)
	public void TC_01_Verify_Existing_Computer_Details(String extCompName)
	{
		homePgObj= new Page_Home(driver);	
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Reading");
	
		// Step 2: Enter existing computer name in search field
		
		homePgObj.enterCompName_InSrchField(extCompName,"Reading");
				
		// Step 3: Click on 'Filter by name' button
		
		homePgObj.click_FilterBtn("Reading");
		
		// Step 4: Verify filtered results shows correct computer details
				
		// 4.1 First fetch the searched new computer details
		
		searchedData= homePgObj.fetchTableData(extCompName);
		
		// 4.2 Check the existing computer details in searched results
		
		if(extCompName.equals(searchedData.get(0)))
		{
			
				//ext_logger.log(LogStatus.PASS, "Existing computer i.e " + extCompName + " searched successfully");
				ExtentTestManager1.getTest().log(LogStatus.PASS, "Existing computer i.e " + extCompName + " searched successfully");
				logger.info("######### Existing computer searched successfully ###########");
		}
		else
		{
			//ext_logger.log(LogStatus.FAIL, "Existing computer i.e " + extCompName + " could not be searched successfully");
			ExtentTestManager1.getTest().log(LogStatus.FAIL, "Existing computer i.e " + extCompName + " could not be searched successfully");
			logger.info("######### Existing computer could not be searched successfully ###########");
		}		
	}
	

	/** ##############################################################################################################
	 * TC_02_Verify Non existing computer details
	 * Description: To verify the details of already existing computer
	 * Precondition - Computer should exists in system
	 * Test Case Type: Positive
	 * @param : extCompName
	 * @throws ParseException 
	 * @Author: Hitesh Ghai
	 * @Expected Result: System should display correct details for existing computers
	 * 
	 * 
	 * ######################################################################################################################
	 */
	
	@Test(dataProviderClass=DataProviderRepository.class,dataProvider="nonExistingCompData",priority=2)
	public void TC_02_Verify_Non_Existing_Computer_Search(String extCompName)
	{
		homePgObj= new Page_Home(driver);	
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Reading");
		
		System.out.println("");
		
		// Step 2: Enter existing computer name in search field
		
		homePgObj.enterCompName_InSrchField(extCompName,"Reading");
		
		
		// Step 3: Click on 'Filter by name' button
		
		homePgObj.click_FilterBtn("Reading");
		
		// Step 4: Check and verify non existent computer
		
		isEventSuccessful = homePgObj.verifyMsg_NthToDisplay();
		if (isEventSuccessful) {
			ExtentTestManager1.getTest().log(LogStatus.PASS,"Non existing computer can not be searched as 'Nothing to display' message is displayed");
			logger.info("#########Non existing computer can not be searched as 'Nothing to display' message is displayed###########");
		} else {
			ExtentTestManager1.getTest().log(LogStatus.FAIL,"Non existing computer can be searched");
			logger.info("#########Non existing computer can be searched###########");
		}		
	}
	
	
	/** ##############################################################################################################
	 * TC_003_Verify existing computer details on Update computer page
	 * Description: To verify the details of already existing computer
	 * Precondition - Computer should exists in system
	 * Test Case Type: Positive
	 * @param : extCompName
	 * @throws Exception 
	 * @Author: Hitesh Ghai
	 * @Expected Result: System should display warning message while searching non existing computers
	 * 
	 * 
	 * ######################################################################################################################
	 */
	
	@Test(dataProviderClass=DataProviderRepository.class,dataProvider="existingCompData",priority=3)
	public void TC_03_Verify_Existing_Computer_Details_Update_Page(String extCompName) throws Exception
	{
		homePgObj= new Page_Home(driver);	
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Reading");
		
		System.out.println("");
		// Step 2: Enter existing computer name in search field
		
		homePgObj.enterCompName_InSrchField(extCompName,"Reading");
		
		
		// Step 3: Click on 'Filter by name' button
		
		homePgObj.click_FilterBtn("Reading");
		
		// Step 4: Verify filtered results shows correct computer details
		
		
		// 4.1 First fetch the searched new computer details
		
		searchedData= homePgObj.fetchTableData(extCompName);
		
		
		// 4.2 Convert 'Introduced Date'& Discontinued dates. Format the Introduce date of filtered record in accordance and comparable with date on update page
		
		introFormattedDate=ApplicationUtilityLib.converstionDates("dd MMM yyyy","yyyy-MM-dd",searchedData.get(1).trim());
		
		dcFormattedDate=ApplicationUtilityLib.converstionDates("dd MMM yyyy","yyyy-MM-dd",searchedData.get(2).trim());
		
		System.out.println("Formatted introduce date is -->"+ introFormattedDate);
		
		System.out.println("Formatted  disconinued date is -->"+ dcFormattedDate);
		
		// Step 5: Click on Searched computer link in grid
		
		editPgObj=homePgObj.clickLinkComputerName(extCompName,"Reading");
		
		// Step 6: Fetch the input data from input fields on computer edit page
		
		editPgInputData=editPgObj.fetchInputData();
		
		// Fetch the visible company from company drop down
		
		String editPgSelCo= editPgObj.getElement_DropDown();
		
		System.out.println(editPgSelCo);
		
		// Step 7: Compare both the list
		
		//isEventSuccessful=ApplicationUtilityLib.campareList(searchedData,editPgInputData);
		
		if(searchedData.get(0).trim().equals(editPgInputData.get(0).trim()) && editPgInputData.get(1).trim().equals(introFormattedDate.trim()) && editPgInputData.get(2).trim().equals(dcFormattedDate.trim()) && 
				searchedData.get(3).trim().equals(editPgSelCo.trim()))
		{
			ExtentTestManager1.getTest().log(LogStatus.PASS,"Correct data for searched computer is displayed on it's edit page");
			logger.info("#########Non existing computer can not be searched as 'Nothing to display' message is displayed###########");
		} else {
			ExtentTestManager1.getTest().log(LogStatus.FAIL,"Incorrect data for searched computer is displayed on it's edit page");
			logger.info("#########Non existing computer can be searched###########");	
		}
	}
	
	@AfterMethod
    public void afterEachTest(ITestResult result) throws InterruptedException {
        try
        {

            System.out.println("Inside After Method of test case");
            actionAfterTest.testCaseReportUp(result, "Reading");
        }

        catch (Exception e)
        {
            System.out.println("Excpetion is " + e.getMessage());
        }
	}


}
