package com.backbase.DeleteComputerTestCases;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.backbase.appPages.Page_EditComputerDetails;
import com.backbase.appPages.Page_Home;
import com.backbase.baseSetUp.BaseSetUp;
import com.backbase.utilLibrary.ActionAfterTest;
import com.backbase.utilLibrary.ActionBeforeTest;
import com.backbase.utilLibrary.DataProviderRepository;
import com.backbase.utilLibrary.ExtentTestManager3;
import com.relevantcodes.extentreports.LogStatus;

public class Delete_Computer_Functionality_TestCases extends BaseSetUp{
	ActionBeforeTest actionBeforeTest=new ActionBeforeTest();
	ActionAfterTest actionAfterTest=new ActionAfterTest();
	Page_Home homePgObj;
	Page_EditComputerDetails editPgObj;
	boolean isEventSuccessful;
	String successMsg=null;
	
	@BeforeMethod()
	public void getMethodName(Method method) throws InterruptedException
	{
		 String mthdName=method.getName();
		 System.out.println("Starting " + mthdName + "test case" );
		 ExtentTestManager3.startTest(mthdName);
		 actionBeforeTest.beforeTestAction3(mthdName,mthdName);
	
	}
	

	/** ##############################################################################################################
	 * TC_001_Verify Deletion of existing computers
	 * Description: To verify that user should be able to delete existing computers
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
	
	@Test(dataProviderClass=DataProviderRepository.class,dataProvider="deleteTestData",priority=1)
	public void TC_01_Delete_Existing_Compuer(String extCompName) throws Exception
	{
		
		homePgObj= new Page_Home(driver);	
		
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Deletion");
	
		// Step 2: Enter existing computer name in search field
		
		homePgObj.enterCompName_InSrchField(extCompName,"Deletion");
				
		// Step 3: Click on 'Filter by name' button
		
		homePgObj.click_FilterBtn("Deletion");
		
		// Step 4: Click on searched computer name link in grid
				
		editPgObj=homePgObj.clickLinkComputerName(extCompName,"Deletion");
		
		// Step 5: Verify 'Edit Computer' page is displayed
		
		editPgObj.verify_Header_Display("Deletion");
		
		editPgObj.clickBtn("Delete");
		
		successMsg = homePgObj.getSuccessMgs();
		if(successMsg.contains("Done") && successMsg.contains("deleted"))
		{
			
			ExtentTestManager3.getTest().log(LogStatus.PASS, "Success message correctly displays about computer deletion");
			logger.info("#########Success message correctly displays about computer deletion###########");
		}
		else
		{
			ExtentTestManager3.getTest().log(LogStatus.FAIL, "Success message fails to display hence computer was NOT deleted");
			logger.info("#########Success message fails to display hence computer was NOT deleted###########");
		}
		
		// Step 6: Enter deleted computer name in search field
		
		homePgObj.enterCompName_InSrchField(extCompName,"Deletion");
				
		// Step 7: Click on 'Filter by name' button
		
		homePgObj.click_FilterBtn("Delete");
		
		// Step 8: Check 'Nothing to display' warning message
		
		isEventSuccessful = homePgObj.verifyMsg_NthToDisplay();
		if (isEventSuccessful) {
			ExtentTestManager3.getTest().log(LogStatus.PASS,"Deleted computer can not be searched as 'Nothing to display' message is displayed");
			logger.info("#########Deleted computer can not be searched as 'Nothing to display' message is displayed###########");
		} else {
			ExtentTestManager3.getTest().log(LogStatus.FAIL,"'Nothing to Display ' message is not displayed hence computer  " + extCompName + " can not be deleted");
			logger.info("#########'Nothing to Display ' message is not displayed hence computer  " + extCompName + " can not be deleted###########");
		}
		
		
	}
	
	@AfterMethod
    public void afterEachTest(ITestResult result) throws InterruptedException {
        try
        {

            System.out.println("Inside After Method of test case");
            actionAfterTest.testCaseReportUp(result, "Deletion");
        }

        catch (Exception e)
        {
            System.out.println("Excpetion is " + e.getMessage());
        }
	}

}
