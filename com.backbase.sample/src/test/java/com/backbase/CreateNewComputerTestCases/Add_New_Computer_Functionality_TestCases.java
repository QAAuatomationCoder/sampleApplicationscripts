package com.backbase.CreateNewComputerTestCases;


import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.backbase.appPages.Page_Home;
import com.backbase.appPages.Page_NewComputerAddition;
import com.backbase.baseSetUp.BaseSetUp;
import com.backbase.utilLibrary.ActionAfterTest;
import com.backbase.utilLibrary.ActionBeforeTest;
import com.backbase.utilLibrary.ApplicationUtilityLib;
import com.backbase.utilLibrary.DataProviderRepository;
import com.backbase.utilLibrary.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

//@Listeners(com.backbase.listeners.CustomListeners.class)
public class Add_New_Computer_Functionality_TestCases extends BaseSetUp{
	String methodName=null;
	String SheetName=null;
	String testCasesSheetName=null;
	ActionBeforeTest actionBeforeTest=new ActionBeforeTest();
	ActionAfterTest actionAfterTest=new ActionAfterTest();
	String testCaseName;
	static int i=0;
	Page_Home homePgObj;
	Page_NewComputerAddition newCompAddObj;
	String compFoundBeforeAddition;
	String compFoundAfterAddition;
	String successMsg;
	String introFormattedDate=null;
	String dCFormatteddate=null;
	ArrayList<String> searchedData= new ArrayList<String>();
	String errActColorCode;
	boolean isEventSuccessful;
	String errExpColorCode="#fae5e3";
	String compFoundHeader;
	
	
	//@Parameters({"browserType"})
	//@BeforeMethod()
	//public void getMethodName(Method method,String browserType) throws InterruptedException
	/*public void getMethodName(Method method) throws InterruptedException
	{
		 scrtCtnInilization();
		 testCaseName=ExcelRd_Obj_Test_Suite.getCellData("NewComputerAddition", 0, ++i);
		 SheetName = "NewComputerAddition";
		 testCasesSheetName="NewComputerAddition";
		 ExtentTestManager.startTest(testCaseName);
		// actionBeforeTest.beforeTestAction2(browserType,testCaseName,methodName,SheetName,"Verify new computer addition");
		 actionBeforeTest.beforeTestAction2(testCaseName,methodName,SheetName,"Verify new computer addition");
	}*/
	
	@BeforeMethod()
	public void getMethodName(Method method) throws InterruptedException
	{
		 String mthdName=method.getName();
		 System.out.println("Starting " + mthdName + "test case" );
		 ExtentTestManager.startTest(mthdName);
		 actionBeforeTest.beforeTestAction3(mthdName,mthdName);
	}

	
	/** #########################################################################################################
	 * TC_001_New_Computer_Addition_with Valid data
	 * Description: To verify the addition of new computer on providing the valid new computer details.
	 * Precondition - N/A
	 * Test Case Type: Positive
	 * @param : newcompName,introDate,dcDate,companyName
	 * @throws ParseException 
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Computer should be added successfully. Correct New computer details should be displayed on home page
	 * Note: 1 - This test case will also cover TC_002_New_Computer_Addition_With new computer name consisting of alphanumeric data
	 *       2 - This test case will also cover TC_003_New_Computer_Addition_With new computer name consisting of special characters
	 * 
	 * #########################################################################################################
	 */
	
	
	
	@Test(dataProviderClass=DataProviderRepository.class,dataProvider="newComputerData",priority=1)
	public void TC_01_02_03_Verify_New_Computer_Addition_With_Valid_Data(String newcompName,String introDate,String dcDate,String companyName) throws ParseException
	{
		homePgObj= new Page_Home(driver);
				
		newCompAddObj= new Page_NewComputerAddition(driver);
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Addition");
		
		newCompAddObj= new Page_NewComputerAddition(driver);
		
		// Step 2: Get the total computer count found header text from the home page
		
		compFoundBeforeAddition=homePgObj.getComputerFoundData();
		
		// To get number of computers from header text
		
		int computerFoundBeforeAddition=ApplicationUtilityLib.getTotalComputerValue(compFoundBeforeAddition);
				
		// Step 2: Click on 'Add a new computer' button 
		
		newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
		
		// Step 3: Verify Add New Computer page is displayed
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
		// Step 4: Enter New Computer name in Computer Name field
		
		newCompAddObj.enterNewCompData("Firefox", "CompName", newcompName, testCaseName);
		
		// Step 5: Enter New Computer Introduction date
		
		newCompAddObj.enterNewCompData("Firefox", "introDate", introDate, testCaseName);
		
		// Step 6: Enter New Computer Discontinue date
		
		newCompAddObj.enterNewCompData("Firefox", "disContDate", dcDate, testCaseName);
		
		// Step 7: Select company 'Apple Inc.' from company drop down
		
		newCompAddObj.selectCompanyName("Firefox", companyName, testCaseName);
		
		// Step 8: Click on 'Create this computer' button
		
		homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
		
				
		// Step 9: To verify to ascertain whether newly added computer results in correct updation of computer inventory by
		// checking the new computer found count on the home page
		
		compFoundAfterAddition=homePgObj.getComputerFoundData();
		int computerFoundAfterAddition=ApplicationUtilityLib.getTotalComputerValue(compFoundAfterAddition);
		if(computerFoundAfterAddition==++computerFoundBeforeAddition)
		{
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Computer found is showing correct count hence new computer added successfully. Before Count is : " + computerFoundBeforeAddition + " & Computer Computer after addition is " + computerFoundAfterAddition);
			logger.info("#########Computer found is showing correct count hence new computer added successfully###########");
		}
		
		else
		{
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Computer found is failing to show correct count hence new computer was not added successfully");
			logger.info("#########Computer found is failing to show correct count hence new computer was not added successfully###########");
		}
		
		// Step 10: To Verify success message consisting of new computer name on home page
		
		successMsg=homePgObj.getSuccessMgs();
		if(successMsg.contains("Done") || successMsg.contains(newcompName))
		{
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Success message contains new computer name hence new computer added successfully");
			logger.info("#########Success message contains new computer name hence new computer added successfully###########");
		}
		else
		{
			ext_logger.log(LogStatus.FAIL, "Success message does not contain new computer name hence new computer was NOT added successfully");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Success message does not contain new computer name hence new computer was NOT added successfully");
			logger.info("#########Success message does not contain new computer name hence new computer was NOT added successfully###########");
		}
		
		// Step 11: Enter new computer name in search field
		
		homePgObj.enterCompName_InSrchField(newcompName,"Addition");
		
		
		// Step 12: Click on 'Filter by name' button
		
		homePgObj.click_FilterBtn("Addition");
		
		// Step 13: Verify filtered results shows correct data
		
		
		// 13.1 First fetch the searched new computer details
		
		searchedData= homePgObj.fetchTableData(newcompName);
		
		// 13.2 - Format the Introduce date of filtered record in accordance and comparable with input data
		
		introFormattedDate=ApplicationUtilityLib.converstionDates("dd MMM yyyy","yyyy-MM-dd",searchedData.get(1));
		
		System.out.println("Introduced formatted date is " + introFormattedDate );
		
		// 13.3 Format the Discontinue date of filtered record in accordance and comparable with input Discontinue date

		dCFormatteddate=ApplicationUtilityLib.converstionDates("dd MMM yyyy","yyyy-MM-dd",searchedData.get(2));
		
		System.out.println("Discontinued formatted date is " + dCFormatteddate );
		
		// 13.4 Verify the correctness of new computer name in filtered record on home page
		
		// Assert.assertEquals(introDate, introFormattedDate);
		if(newcompName.equals(searchedData.get(0)))
		{
			
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Correct computer name i.e-->" + newcompName + " is displayed in filtered record on home page");
				logger.info("#########Correct new computer name is displayed in filtered record###########");
		}
		else
		{
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Incorrect new computer name i.e --> " + searchedData.get(0) + "is displayed in filtered record on home page");
			logger.info("#########Incorrect new computer name is displayed in filtered record###########");
		}	
		
		// 13.5 Verify the correctness of Introduced date in filtered record on home page
		
		if(introDate.equals(introFormattedDate))
		{
			
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Correct-->" + introDate + " is displayed in filtered record on home page");
				logger.info("#########Correct Introduced date is displayed in filtered record###########");
		}
		else
		{
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Incorrect Introduced date i.e -->" + introFormattedDate + "is displayed in filtered record on home page");
			logger.info("#########Incorrect Introduced date is displayed in filtered record###########");
		}
		
		// 13.6 Verify the correctness of Discontinued date in filtered record on home page
		
		if(dcDate.equals(dCFormatteddate))
		{
			
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Correct Discontinued date i.e.-->" + dcDate + " is displayed in filtered record on home page");
				logger.info("#########Correct Introduced date is displayed in filtered record###########");
		}
		else
		{
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Incorrect Discontinued date i.e.-->" + dCFormatteddate + " is displayed in filtered record on home page");
			logger.info("#########Incorrect Discontinued date is displayed in filtered record###########");
		}
		
		// 13.7 Verify the correctness of Company name in filtered record on home page
		
		if(companyName.equals(searchedData.get(3)))
		{
			
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Correct company name i.e -->" + companyName + " is displayed in filtered record on home page");
				logger.info("#########Correct new computer name is displayed in filtered record###########");
		}
		else
		{
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Incorrect company name i.e -->" + searchedData.get(3) + "is displayed in filtered record on home page");
			logger.info("#########Incorrect company name is displayed in filtered record###########");
		}
		
		
		
	}
	

	/** #########################################################################################################
	 * TC_004_New_Computer_Addition_New 'Computer Name' Mandatory Field Verification	
	 * Description: To validate the warning message while trying to add new computer with blank new computer name
	 * Precondition - N/A
	 * Test Case Type: Negative
	 * @param : tcID
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Computer should not be added as proper validation message should be displayed
	 * #########################################################################################################
	 */
	
	@Test(priority=2)
	public void TC_004_New_Computer_Addition_New_Computer_Name_Mandatory_Field_Verification(){
		
		homePgObj= new Page_Home(driver);
		
		newCompAddObj= new Page_NewComputerAddition(driver);
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Addition");
		
		newCompAddObj= new Page_NewComputerAddition(driver);
		
		// Step 2: Click on 'Add a new computer' button 
		
		newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
		
		// Step 3: Verify Add New Computer page is displayed
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
		// Step 4: Enter New Computer name in Computer Name field
		
		newCompAddObj.enterNewCompData("Firefox", "CompName", "", testCaseName);
		
		// Step 5: Enter New Computer Introduction date
		
		newCompAddObj.enterNewCompData("Firefox", "introDate", "2017-10-30", testCaseName);
		
		// Step 6: Enter New Computer Discontinue date
		
		newCompAddObj.enterNewCompData("Firefox", "disContDate", "2018-10-30", testCaseName);
		
		// Step 7: Select company 'Apple Inc.' from company drop down
		
		newCompAddObj.selectCompanyName("Firefox", "Thinking Machines", testCaseName);
		
		// Step 8: Click on 'Create this computer' button
		
		homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
		
		
		// Step 9: Verification Method 1: 
		// Since 'Computer Name' section would be highlighted in color after clicking on 'Create this button', So, fetch the color of this area
		// and verify it. It should return the correct color code. 
		
		// Note: Actual color is extracted from CSS sheet from actual html page
		// Here actual color code is "fae5e3"
		
		errActColorCode=newCompAddObj.getElementHightlight_Color();
		if(errExpColorCode.equalsIgnoreCase(errActColorCode))
		{
			
			//ext_logger.log(LogStatus.PASS, "User is correctly unable to create new computer due to missing of new computer name");
			ExtentTestManager.getTest().log(LogStatus.PASS, "User is correctly unable to create new computer due to missing of new computer name");
			logger.info("#########User is correctly unable to create new computer due to missing of new computer name###########");
				
		}
			
		else
		{
			//ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
			//Assert.assertTrue(false);
		}
		
		// Step 9.1 Verification Method 2: 
		// After clicking on 'Create this computer' , attributes/properties/state of 'Computer Name' section changes. So, availability/visibility/state of updated
		// element can be checked to ascertain that user could not create new computer.
		// For example: Element's attribute value before clicking the button : <div class = "clearfix">
		//				Element's attribute value after clicking the button  : <div class = "clearfix error">
		
		isEventSuccessful=newCompAddObj.check_ErrorElementState();
		
		if(isEventSuccessful)
		{
			
			//ext_logger.log(LogStatus.PASS, "User is correctly unable to create new computer due to missing of new computer name");
			ExtentTestManager.getTest().log(LogStatus.PASS, "User is correctly unable to create new computer due to missing of new computer name");
			logger.info("#########User is correctly unable to create new computer due to missing of new computer name###########");
				
		}
			
		else
		{
			//ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
		}
		
		
		// Step 9.2 Verification Method 3: 
		// Since after clicking on 'Create this Computer', user remains on 'Add a Computer' page, so check the heading of this page
		// to confirm whether user still remain on this page.
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
}
	
	/** #########################################################################################################################################
	 * TC_005_New_Computer_Addition_Introduced Date Incorrect Date Format Verification_Case1
	 * Description: To validate the warning message while trying to add new computer with invalid Introduced Date format (i.e. mm/dd/yyyy)
	 * Precondition - N/A
	 * Test Case Type: Negative
	 * @param : N/A
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Computer should not be added as proper validation message should be displayed due to invalid 'Introduced Date' format
	 * #############################################################################################################################################
	 */
	
	
	@Test(priority=3)
	public void TC_005_New_Computer_Addition_New_Computer_Invalid_Introduced_Date_Format_Verification_Case1(){
		
		homePgObj= new Page_Home(driver);
		
		newCompAddObj= new Page_NewComputerAddition(driver);
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Addition");
		
		newCompAddObj= new Page_NewComputerAddition(driver);
		
		// Step 2: Click on 'Add a new computer' button 
		
		newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
		
		// Step 3: Verify Add New Computer page is displayed
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
		// Step 4: Enter New Computer name in Computer Name field
		
		newCompAddObj.enterNewCompData("Firefox", "CompName", "Innova Test Computer ", testCaseName);
		
		// Step 5: Enter New Computer Introduction date
		
		newCompAddObj.enterNewCompData("Firefox", "introDate", "11-15-2017", testCaseName);
		
		// Step 6: Enter New Computer Discontinue date
		
		newCompAddObj.enterNewCompData("Firefox", "disContDate", "2018-10-30", testCaseName);
		
		// Step 7: Select company 'Apple Inc.' from company drop down
		
		newCompAddObj.selectCompanyName("Firefox", "Thinking Machines", testCaseName);
		
		// Step 8: Click on 'Create this computer' button
		
		homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
		
		
		// Step 9: Verification Method 1: 
		// Since 'Introduced Date' section would be highlighted in color after clicking on 'Create this button', So, fetch the color of this area
		// and verify it. It should return the correct color code. 
		
		// Note: Actual color is extracted from CSS sheet from actual html page
		// Here actual color code is "fae5e3"
		
		errActColorCode=newCompAddObj.getElementHightlight_Color();
		if(errExpColorCode.equalsIgnoreCase(errActColorCode))
		{
			
			//ext_logger.log(LogStatus.PASS, "Introduced Date is highlighed successfully due to incorrect date format as 'yyyy/mm/dd.'Please enter date in correct format as yyyy/mm/dd");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Introduced Date is highlighed successfully due to incorrect date format as 'mm/dd/yyyy. 'Please enter date in correct format in yyyy/mm/dd");
			logger.info("#########User is correctly unable to create new computer due to invalid Introduced Date format###########");
				
		}
			
		else
		{
			ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
			//Assert.assertTrue(false);
		}
		
		// Step 9.1 Verification Method 2: 
		// After clicking on 'Create this computer' , attributes/properties/state of 'Introduced Date' section changes. So, availability/visibility/state of updated
		// element can be checked to ascertain that user could not create new computer.
		// For example: Element's attribute value before clicking the button : <div class = "clearfix">
		//				Element's attribute value after clicking the button  : <div class = "clearfix error">
		
		isEventSuccessful=newCompAddObj.check_ErrorElementState();
		
		if(isEventSuccessful)
		{
			
			//ext_logger.log(LogStatus.PASS, "User is correctly unable to create new computer due to invalid Introduced Date format");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Introduced Date is highlighed successfully due to incorrect date format as 'mm/dd/yyyy. 'Please enter date in correct format in yyyy/mm/dd");
			logger.info("#########User is correctly unable to create new computer due to invalid Introduced Date format###########");
				
		}
			
		else
		{
			ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
		}
		
		
		// Step 9.2 Verification Method 3: 
		// Since after clicking on 'Create this Computer', user remains on 'Add a Computer' page, so check the heading of this page
		// to confirm whether user still remain on this page.
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
	}

	
	/** #########################################################################################################################################
	 * TC_006_New_Computer_Addition_Introduced Date Incorrect Date Format Verification_Case2
	 * Description: To validate the warning message while trying to add new computer with invalid Introduced Date format (i.e. dd/mm/yyyy)
	 * Precondition - N/A
	 * Test Case Type: Negative
	 * @param : N/A
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Computer should not be added as proper validation message should be displayed due to invalid 'Introduced Date' format
	 * #############################################################################################################################################
	 */
	
	
	@Test(priority=4)
	public void TC_006_New_Computer_Addition_New_Computer_Invalid_Introduced_Date_Format_Verification_Case2(){
		
		homePgObj= new Page_Home(driver);
		
		newCompAddObj= new Page_NewComputerAddition(driver);
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Addition");
		
		newCompAddObj= new Page_NewComputerAddition(driver);
		
		// Step 2: Click on 'Add a new computer' button 
		
		newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
		
		// Step 3: Verify Add New Computer page is displayed
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
		// Step 4: Enter New Computer name in Computer Name field
		
		newCompAddObj.enterNewCompData("Firefox", "CompName", "Mini Computer", testCaseName);
		
		// Step 5: Enter New Computer Introduction date
		
		newCompAddObj.enterNewCompData("Firefox", "introDate", "22-11-2015", testCaseName);
		
		// Step 6: Enter New Computer Discontinue date
		
		newCompAddObj.enterNewCompData("Firefox", "disContDate", "2018-10-30", testCaseName);
		
		// Step 7: Select company 'Apple Inc.' from company drop down
		
		newCompAddObj.selectCompanyName("Firefox", "Tandy Corporation", testCaseName);
		
		// Step 8: Click on 'Create this computer' button
		
		homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
		
		
		// Step 9: Verification Method 1: 
		// Since 'Introduced Date' section would be highlighted in color after clicking on 'Create this button', So, fetch the color of this area
		// and verify it. It should return the correct color code. 
		
		// Note: Actual color is extracted from CSS sheet from actual html page
		// Here actual color code is "fae5e3"
		
		errActColorCode=newCompAddObj.getElementHightlight_Color();
		if(errExpColorCode.equalsIgnoreCase(errActColorCode))
		{
			
			//ext_logger.log(LogStatus.PASS, "User is correctly unable to create new computer due to invalid Introduced Date format");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Introduced Date is highlighed successfully due to incorrect date format as 'dd/mm/yyyy'. 'Please enter date in correct format as yyyy/mm/dd");
			logger.info("#########User is correctly unable to create new computer due to invalid Introduced Date format###########");
				
		}
			
		else
		{
			ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
			//Assert.assertTrue(false);
		}
		
		// Step 9.1 Verification Method 2: 
		// After clicking on 'Create this computer' , attributes/properties/state of 'Introduced Date' section changes. So, availability/visibility/state of updated
		// element can be checked to ascertain that user could not create new computer.
		// For example: Element's attribute value before clicking the button : <div class = "clearfix">
		//				Element's attribute value after clicking the button  : <div class = "clearfix error">
		
		isEventSuccessful=newCompAddObj.check_ErrorElementState();	
		if(isEventSuccessful)
		{
			
			//ext_logger.log(LogStatus.PASS, "User is correctly unable to create new computer due to invalid Introduced Date format");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Introduced Date is highlighed successfully due to incorrect date format as 'dd/mm/yyyy'. 'Please enter date in correct format as yyyy/mm/dd");
			logger.info("#########User is correctly unable to create new computer due to invalid Introduced Date format###########");
				
		}
			
		else
		{
			ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
		}
		
		
		// Step 9.2 Verification Method 3: 
		// Since after clicking on 'Create this Computer', user remains on 'Add a Computer' page, so check the heading of this page
		// to confirm whether user still remain on this page.
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
	}
	
	/** #########################################################################################################################################
	 * TC_007_New_Computer_Addition_Text Data in Introduced Date field
	 * Description: To validate the warning message while trying to add new computer with invalid Introduced Date format (e.g 'twentySecondNovember')
	 * Precondition - N/A
	 * Test Case Type: Negative
	 * @param : N/A
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Computer should not be added as proper validation message should be displayed due to invalid 'Introduced Date' format
	 * #############################################################################################################################################
	 */
	
	@Test(priority=5)
	public void TC_007_New_Computer_Addition_New_Computer_Text_Data_In_Introduced_Date_Format_Verification(){
		
		homePgObj= new Page_Home(driver);
		
		newCompAddObj= new Page_NewComputerAddition(driver);
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Addition");
		
		newCompAddObj= new Page_NewComputerAddition(driver);
		
		// Step 2: Click on 'Add a new computer' button 
		
		newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
		
		// Step 3: Verify Add New Computer page is displayed
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
		// Step 4: Enter New Computer name in Computer Name field
		
		newCompAddObj.enterNewCompData("Firefox", "CompName", "Mini Computer", testCaseName);
		
		// Step 5: Enter New Computer Introduction date
		
		newCompAddObj.enterNewCompData("Firefox", "introDate", "Twenty Second Nov Two Thousand Eighteen", testCaseName);
		
		// Step 6: Enter New Computer Discontinue date
		
		newCompAddObj.enterNewCompData("Firefox", "disContDate", "2018-10-30", testCaseName);
		
		// Step 7: Select company 'Apple Inc.' from company drop down
		
		newCompAddObj.selectCompanyName("Firefox", "Tandy Corporation", testCaseName);
		
		// Step 8: Click on 'Create this computer' button
		
		homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
		
		
		// Step 9: Verification Method 1: 
		// Since 'Introduced Date' section would be highlighted in color after clicking on 'Create this button', So, fetch the color of this area
		// and verify it. It should return the correct color code. 
		
		// Note: Actual color is extracted from CSS sheet from actual html page
		// Here actual color code is "fae5e3"
		
		errActColorCode=newCompAddObj.getElementHightlight_Color();
		if(errExpColorCode.equalsIgnoreCase(errActColorCode))
		{
			
			//ext_logger.log(LogStatus.PASS, "User is correctly unable to create new computer due to invalid Introduced Date format");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Introduced Date is highlighed successfully as introduced data was entered in text format. 'Please enter date in correct format as yyyy/mm/dd");
			logger.info("#########User is correctly unable to create new computer due to invalid Introduced Date format###########");
				
		}
			
		else
		{
			ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
			//Assert.assertTrue(false);
		}
		
		// Step 9.1 Verification Method 2: 
		// After clicking on 'Create this computer' , attributes/properties/state of 'Introduced Date' section changes. So, availability/visibility/state of updated
		// element can be checked to ascertain that user could not create new computer.
		// For example: Element's attribute value before clicking the button : <div class = "clearfix">
		//				Element's attribute value after clicking the button  : <div class = "clearfix error">
		
		isEventSuccessful=newCompAddObj.check_ErrorElementState();	
		if(isEventSuccessful)
		{
			
			//ext_logger.log(LogStatus.PASS, "User is correctly unable to create new computer due to invalid Introduced Date format");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Introduced Date is highlighed successfully as introduced data was entered in text format. 'Please enter date in correct format as yyyy/mm/dd");
			logger.info("#########User is correctly unable to create new computer due to invalid Introduced Date format###########");
				
		}
			
		else
		{
			ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
		}
	
	}
	
	/** #########################################################################################################################################
	 * TC_008_New_Computer_Addition_Discontinued Date Incorrect Date Format Verification_Case1
	 * Description: To validate the warning message while trying to add new computer with invalid Discontinued Date format (i.e. mm/dd/yyyy)
	 * Precondition - N/A
	 * Test Case Type: Negative
	 * @param : N/A
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Computer should not be added as proper validation message should be displayed due to invalid 'Discontinued Date' format
	 * #############################################################################################################################################
	 */
	
	@Test(priority=6)
	public void TC_008_New_Computer_Addition_New_Computer_Invalid_Discontinued_Date_Format_Verification_Case1(){
		
		homePgObj= new Page_Home(driver);
		
		newCompAddObj= new Page_NewComputerAddition(driver);
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Addition");
		
		newCompAddObj= new Page_NewComputerAddition(driver);
		
		// Step 2: Click on 'Add a new computer' button 
		
		newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
		
		// Step 3: Verify Add New Computer page is displayed
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
		// Step 4: Enter New Computer name in Computer Name field
		
		newCompAddObj.enterNewCompData("Firefox", "CompName", "Sony", testCaseName);
		
		// Step 5: Enter New Computer Introduction date
		
		newCompAddObj.enterNewCompData("Firefox", "introDate", "2018-11-30", testCaseName);
		
		// Step 6: Enter New Computer Discontinue date
		
		newCompAddObj.enterNewCompData("Firefox", "disContDate", "11-15-2019", testCaseName);
		
		// Step 7: Select company 'Apple Inc.' from company drop down
		
		newCompAddObj.selectCompanyName("Firefox", "Thinking Machines", testCaseName);
		
		// Step 8: Click on 'Create this computer' button
		
		homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
		
		
		// Step 9: Verification Method 1: 
		// Since 'Discontinued Date' section would be highlighted in color after clicking on 'Create this button', So, fetch the color of this area
		// and verify it. It should return the correct color code. 
		
		// Note: Actual color is extracted from CSS sheet from actual html page
		// Here actual color code is "fae5e3"
		
		errActColorCode=newCompAddObj.getElementHightlight_Color();
		if(errExpColorCode.equalsIgnoreCase(errActColorCode))
		{
			
			//ext_logger.log(LogStatus.PASS, "Introduced Date is highlighed successfully due to incorrect date format as 'yyyy/mm/dd.'Please enter date in correct format as yyyy/mm/dd");
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Discontinued Date' is highlighed successfully due to incorrect date format as 'mm/dd/yyyy. 'Please enter date in correct format in yyyy/mm/dd");
			logger.info("#########User is correctly unable to create new computer due to invalid 'Discontinued Date' format###########");
				
		}
			
		else
		{
			ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
			//Assert.assertTrue(false);
		}
		
		// Step 9.1 Verification Method 2: 
		// After clicking on 'Create this computer' , attributes/properties/state of 'Introduced Date' section changes. So, availability/visibility/state of updated
		// element can be checked to ascertain that user could not create new computer.
		// For example: Element's attribute value before clicking the button : <div class = "clearfix">
		//				Element's attribute value after clicking the button  : <div class = "clearfix error">
		
		isEventSuccessful=newCompAddObj.check_ErrorElementState();
		
		if(isEventSuccessful)
		{
			
			//ext_logger.log(LogStatus.PASS, "User is correctly unable to create new computer due to invalid Introduced Date format");
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Discontinued Date' is highlighed successfully due to incorrect date format as 'mm/dd/yyyy.'Please enter date in correct format in yyyy/mm/dd");
			logger.info("#########User is correctly unable to create new computer due to invalid 'Discontinued Date' format###########");
				
		}
			
		else
		{
			ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
		}
	
	}
	
	/** #########################################################################################################################################
	 * TC_009_New_Computer_Addition_Discontinued Date Incorrect Date Format Verification_Case2
	 * Description: To validate the warning message while trying to add new computer with invalid 'Discontinued Date' format (i.e. dd/mm/yyyy)
	 * Precondition - N/A
	 * Test Case Type: Negative
	 * @param : N/A
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Computer should not be added as proper validation message should be displayed due to invalid 'Discontinued Date' format
	 * #############################################################################################################################################
	 */
	
		
	//@Test(priority=7)
	@Test(priority=7)	
	public void TC_009_New_Computer_Addition_New_Computer_Invalid_Discontiuned_Date_Format_Verification_Case2(){
		
		homePgObj= new Page_Home(driver);
		
		newCompAddObj= new Page_NewComputerAddition(driver);
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Addition");
		
		newCompAddObj= new Page_NewComputerAddition(driver);
		
		// Step 2: Click on 'Add a new computer' button 
		
		newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
		
		// Step 3: Verify Add New Computer page is displayed
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
		// Step 4: Enter New Computer name in Computer Name field
		
		newCompAddObj.enterNewCompData("Firefox", "CompName", "Supersonic Computer", testCaseName);
		
		// Step 5: Enter New Computer Introduction date
		
		newCompAddObj.enterNewCompData("Firefox", "introDate", "2018-01-25", testCaseName);
		
		// Step 6: Enter New Computer Discontinue date
		
		newCompAddObj.enterNewCompData("Firefox", "disContDate", "22-11-2015", testCaseName);
		
		// Step 7: Select company 'Apple Inc.' from company drop down
		
		newCompAddObj.selectCompanyName("Firefox", "Tandy Corporation", testCaseName);
		
		// Step 8: Click on 'Create this computer' button
		
		homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
		
		
		// Step 9: Verification Method 1: 
		// Since 'Discontinued Date' section would be highlighted in color after clicking on 'Create this button', So, fetch the color of this area
		// and verify it. It should return the correct color code. 
		
		// Note: Actual color is extracted from CSS sheet from actual html page
		// Here actual color code is "fae5e3"
		
		errActColorCode=newCompAddObj.getElementHightlight_Color();
		if(errExpColorCode.equalsIgnoreCase(errActColorCode))
		{
			
			//ext_logger.log(LogStatus.PASS, "User is correctly unable to create new computer due to invalid Introduced Date format");
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Discontinued Date' is highlighed successfully due to incorrect date format as 'dd/mm/yyyy'. 'Please enter date in correct format as yyyy/mm/dd");
			logger.info("#########User is correctly unable to create new computer due to invalid 'Discontinued Date' format###########");
				
		}
			
		else
		{
			ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
			//Assert.assertTrue(false);
		}
		
		// Step 9.1 Verification Method 2: 
		// After clicking on 'Create this computer' , attributes/properties/state of 'Introduced Date' section changes. So, availability/visibility/state of updated
		// element can be checked to ascertain that user could not create new computer.
		// For example: Element's attribute value before clicking the button : <div class = "clearfix">
		//				Element's attribute value after clicking the button  : <div class = "clearfix error">
		
		isEventSuccessful=newCompAddObj.check_ErrorElementState();	
		if(isEventSuccessful)
		{
			
			//ext_logger.log(LogStatus.PASS, "User is correctly unable to create new computer due to invalid Introduced Date format");
			ExtentTestManager.getTest().log(LogStatus.PASS, "'Discontinued Date' is highlighed successfully due to incorrect date format as 'dd/mm/yyyy'. 'Please enter date in correct format as yyyy/mm/dd");
			logger.info("#########User is correctly unable to create new computer due to invalid 'Discontiuned Date format###########");
				
		}
			
		else
		{
			ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
		}
		
		

	}
	
	/** #########################################################################################################################################
	 * TC_010_New_Computer_Addition_Text Data in Discontinued Date field
	 * Description: To validate the warning message while trying to add new computer with invalid (text data) in Discontinued Date format (e.g 'twentySecondNovember')
	 * Precondition - N/A
	 * Test Case Type: Negative
	 * @param : N/A
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Computer should not be added as proper validation message should be displayed due to invalid 'Discontinued Date' format
	 * #############################################################################################################################################
	 */

	@Test(priority=8)
	public void TC_010_New_Computer_Addition_New_Computer_Text_Data_In_Discontinued_Date_Format_Verification(){
		
		homePgObj= new Page_Home(driver);
		
		newCompAddObj= new Page_NewComputerAddition(driver);
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Addition");
		
		newCompAddObj= new Page_NewComputerAddition(driver);
		
		// Step 2: Click on 'Add a new computer' button 
		
		newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
		
		// Step 3: Verify Add New Computer page is displayed
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
		// Step 4: Enter New Computer name in Computer Name field
		
		newCompAddObj.enterNewCompData("Firefox", "CompName", "Blazaing Computer", testCaseName);
		
		// Step 5: Enter New Computer Introduction date
		
		newCompAddObj.enterNewCompData("Firefox", "introDate", "2015-11-22", testCaseName);
		
		// Step 6: Enter New Computer Discontinue date
		
		newCompAddObj.enterNewCompData("Firefox", "disContDate", "Twenty Second Nov Two Thousand Eighteen", testCaseName);
		
		// Step 7: Select company 'Apple Inc.' from company drop down
		
		newCompAddObj.selectCompanyName("Firefox", "Tandy Corporation", testCaseName);
		
		// Step 8: Click on 'Create this computer' button
		
		homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
		
		
		// Step 9: Verification Method 1: 
		// Since 'Discontinued Date' section would be highlighted in color after clicking on 'Create this button', So, fetch the color of this area
		// and verify it. It should return the correct color code. 
		
		// Note: Actual color is extracted from CSS sheet from actual html page
		// Here actual color code is "fae5e3"
		
		errActColorCode=newCompAddObj.getElementHightlight_Color();
		if(errExpColorCode.equalsIgnoreCase(errActColorCode))
		{
			
			//ext_logger.log(LogStatus.PASS, "User is correctly unable to create new computer due to invalid Introduced Date format");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Discontinued Date is highlighed successfully as Discontinued date was entered in text format. 'Please enter date in correct format as yyyy/mm/dd");
			logger.info("#########User is correctly unable to create new computer due to invalid Discontinued date format###########");
				
		}
			
		else
		{
			ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
			//Assert.assertTrue(false);
		}
		
		// Step 9.1 Verification Method 2: 
		// After clicking on 'Create this computer' , attributes/properties/state of 'Introduced Date' section changes. So, availability/visibility/state of updated
		// element can be checked to ascertain that user could not create new computer.
		// For example: Element's attribute value before clicking the button : <div class = "clearfix">
		//				Element's attribute value after clicking the button  : <div class = "clearfix error">
		
		isEventSuccessful=newCompAddObj.check_ErrorElementState();	
		if(isEventSuccessful)
		{
			
			//ext_logger.log(LogStatus.PASS, "User is correctly unable to create new computer due to invalid Introduced Date format");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Discontinued Date is highlighed successfully as introduced data was entered in text format. 'Please enter date in correct format as yyyy/mm/dd");
			logger.info("#########User is correctly unable to create new computer due to invalid Introduced Date format###########");
				
		}
			
		else
		{
			ext_logger.log(LogStatus.FAIL, "User is able to create new computer");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "User is able to create new computer");
			logger.info("#########User is able to create new computer###########");
		}
	
	}
	
	/** ###########################################################################################################################################################
	 * TC_011_New_Computer_Addition_When Introduced Date less than Discontinued Date
	 * Description: To validate the warning message while trying to add new computer when 'Discontinued date' is less than 'Introduced Date'
	 * Precondition - N/A
	 * Test Case Type: Negative
	 * @param : N/A
	 * @throws ParseException 
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Computer should not be added as proper validation message should be displayed due to 'Discontinued date' is less than 'Introduced Date'
	 * ##############################################################################################################################################################
	 */
	
	@Test(priority=9)
	public void TC_011_New_Computer_Addition_New_Computer_Discontinued_Date_Less_than_Introduced_Date_Verification() throws ParseException{
		
		String newCompName="Cosmic Computer Systems";
		homePgObj= new Page_Home(driver);
		
		newCompAddObj= new Page_NewComputerAddition(driver);
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Addition");
		
		newCompAddObj= new Page_NewComputerAddition(driver);
		
		// Step 2: Click on 'Add a new computer' button 
		
		newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
		
		// Step 3: Verify Add New Computer page is displayed
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
		// Step 4: Enter New Computer name in Computer Name field
		
		newCompAddObj.enterNewCompData("Firefox", "CompName", newCompName, testCaseName);
		
		// Step 5: Enter New Computer Introduction date
		
		newCompAddObj.enterNewCompData("Firefox", "introDate", "2018-11-22", testCaseName);
		
		// Step 6: Enter New Computer Discontinue date
		
		newCompAddObj.enterNewCompData("Firefox", "disContDate", "2017-11-22", testCaseName);
		
		// Step 7: Select company 'Apple Inc.' from company drop down
		
		newCompAddObj.selectCompanyName("Firefox", "Samsung Electronics", testCaseName);
		
		// Step 8: Click on 'Create this computer' button
		
		homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
		
		
		/*
		 * Step 9: Verification Alternative 1: Since 'Discontinued Date' section
		 * would be highlighted in color after clicking on 'Create this button',
		 * So, the existence of this error message/element. if error
		 * element/message exits, then this step is passed otherwise failed
		 * 
		 */			
		isEventSuccessful=newCompAddObj.check_ErrorElementState();
			try {
				if (!isEventSuccessful) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,"No Validation message for 'Discontinued Date' less than 'Introducation Date' is thrown");
					logger.info("#########  No Validation message for 'Discontinued Date' less than 'Introducation Date' is thrown###########");
					}
				}

			catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "'Discontinued Date' is highlighed successfully as 'Discontinued date is less than 'Introducation Date'.'Please enter date valid 'Discontinued Date");
				logger.info("#########User is able to create new computer###########");
					// Assert.assertTrue(false);
				}
		
		/*
		 * Step 9.1: Verification Alternative 2: If user routes to home page, again in case, this step is failed 
		 * 
		 */		
		
		successMsg = homePgObj.getSuccessMgs();
		if (successMsg.contains("Done") || successMsg.contains(newCompName)) {

			ExtentTestManager.getTest().log(LogStatus.FAIL,"No Validation message is thrown about discontinued date less than Introducation as user routes to home page after creating a new computer i.e "
							+ newCompName);
			logger.info("#########No Validation message is thrown as new computer i.e " + newCompName + "created###########");
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS,"Success message does not contain new computer name hence new computer was NOT added successfully");
			logger.info("#########Success message does not contain new computer name hence new computer was NOT added successfully###########");
		}
	
	}
	
	/** ###########################################################################################################################################################
	 * TC_012_New_Computer_Addition_Invalid Day Data for Non-leap year in 'Introduced' or 'Discontinued' dates for February Month
	 * Description: To validate the warning message while trying to add new computer when providing invalid non-leap year 'Introduced' or 'Discontinued' dates are for non leap years
	 * e.g. '2018/02/29'
	 * Precondition - N/A
	 * Test Case Type: Negative
	 * @param : N/A
	 * @throws ParseException 
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Computer should not be added as proper validation message should be displayed due to incorrect 'Introduced' or 'Discontinued' dates
	 * ##############################################################################################################################################################
	 */
	
	@Test(priority=10)
	public void TC_012_New_Computer_Addition_New_Computer_Leap_And_Non_Leap_Years_Date_Validation() throws ParseException{
			
			String newCompName="Tesla Computer Inc.";
			String nonLeapYrIntDate="2018/02/29";
			homePgObj= new Page_Home(driver);
			
			newCompAddObj= new Page_NewComputerAddition(driver);
				
			// Step 1: Verify home page is displayed
			
			homePgObj.verifyHomePage("Addition");
			
			newCompAddObj= new Page_NewComputerAddition(driver);
			
			// Step 2: Click on 'Add a new computer' button 
			
			newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
			
			// Step 3: Verify Add New Computer page is displayed
			
			newCompAddObj.verify_Header_Display("Firefox", testCaseName);
			
			// Step 4: Enter New Computer name in Computer Name field
			
			newCompAddObj.enterNewCompData("Firefox", "CompName", newCompName, testCaseName);
			
			// Step 5: Enter New Computer Introduction date
			
			newCompAddObj.enterNewCompData("Firefox", "introDate", nonLeapYrIntDate, testCaseName);
			
			// Step 6: Enter New Computer Discontinue date
			
			newCompAddObj.enterNewCompData("Firefox", "disContDate", "2019-11-22", testCaseName);
			
			// Step 7: Select company 'Apple Inc.' from company drop down
			
			newCompAddObj.selectCompanyName("Firefox", "Samsung Electronics", testCaseName);
			
			// Step 8: Click on 'Create this computer' button
			
			homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
			
			
			/*
			 * Step 9: Verification Alternative 1: Since 'Introduced Date' section
			 * would be highlighted in color after clicking on 'Create this button',
			 * So, the existence of this error message/element. if error
			 * element/message exits, then this step is passed otherwise failed
			 * 
			 */			
			isEventSuccessful=newCompAddObj.check_ErrorElementState();
					
			if (!isEventSuccessful) {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"No Validation message for incorrect 'Introduced Date' i.e. " + nonLeapYrIntDate + "is thrown");
				logger.info("#########  No Validation message for incorrect introduced date is thrown###########");
				}
			else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Correct Validation message for incorrect 'Introduced Date' i.e. " + nonLeapYrIntDate + " (a leap year) is thrown");
					logger.info("#########User is able to create new computer###########");
						// Assert.assertTrue(false);
				}
			
			/*
			 * Step 9.1: Verification Alternative 2: If user routes to home page, again in case, this step is failed 
			 * 
			 */		
			
			successMsg=homePgObj.getSuccessMgs();
				if(successMsg.contains("Done") || successMsg.contains(newCompName))
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL,"No Validation message for incorrect 'Introduced Date' i.e. " + nonLeapYrIntDate + "is thrown");
					logger.info("#########  No Validation message for incorrect introduced date is thrown###########");
				}
				else
				{
					ExtentTestManager.getTest().log(LogStatus.PASS, "Success message does not contain new computer name hence new computer was NOT added");
					logger.info("#########Success message does not contain new computer name hence new computer was NOT added successfully###########");
				}	
		
		}
	
	/** ###########################################################################################################################################################
	 * TC_013_New_Computer_Addition_Valid leap year in 'Introduced' or 'Discontinued' dates for February Month
	 * Description: To Verify the successful addition of new computer for 'Introduce' or 'Discontinued' leap year dates
	 * e.g. '2020/02/29'
	 * Precondition - N/A
	 * Test Case Type: Positive
	 * @param : N/A
	 * @throws ParseException 
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Computer should be added successfully
	 * ##############################################################################################################################################################
	 */	
	
	@Test(priority=11)
	public void TC_013_Verify_New_Computer_Addition_With_Leap_Years() throws ParseException
	{
		String newCompName="_Apple#Ver2.0";
		String introDate="2018/02/15";
		String leapYearDisConDate="2020/02/29";
		String comanyName="Nokia";
		homePgObj= new Page_Home(driver);
				
		newCompAddObj= new Page_NewComputerAddition(driver);
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Addition");
		
		newCompAddObj= new Page_NewComputerAddition(driver);
				
		// Step 2: Click on 'Add a new computer' button 
		
		newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
		
		// Step 3: Verify Add New Computer page is displayed
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
		// Step 4: Enter New Computer name in Computer Name field
		
		newCompAddObj.enterNewCompData("Firefox", "CompName", newCompName, testCaseName);
		
		// Step 5: Enter New Computer Introduction date
		
		newCompAddObj.enterNewCompData("Firefox", "introDate", introDate, testCaseName);
		
		// Step 6: Enter New Computer Discontinue date
		
		newCompAddObj.enterNewCompData("Firefox", "disContDate", leapYearDisConDate, testCaseName);
		
		// Step 7: Select company 'Apple Inc.' from company drop down
		
		newCompAddObj.selectCompanyName("Firefox", comanyName, testCaseName);
		
		// Step 8: Click on 'Create this computer' button
		
		homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
				
		/*
		 * Step 9: Check the validation message for Leap year Discontinued Date. No Validation message should be displayed.If it's displayed,then
		 * it is fail
		 *
		 */			
		isEventSuccessful=newCompAddObj.check_ErrorElementState();
			try {
				if (isEventSuccessful) {
					ExtentTestManager.getTest().log(LogStatus.FAIL,"Validation message for valid 'Discontinued Date' leap year i.e " + leapYearDisConDate + " is thrown");
					logger.info("#########  Validation message for valid 'Discontinued Date' leap year is thrown ####");
					}
				}

			catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No Validation message for valid 'Discontinued Date' leap year i.e " + leapYearDisConDate + " is thrown");
				logger.info("#########User is able to create new computer###########");
					// Assert.assertTrue(false);
				}			
	}	
	
	/** ###########################################################################################################################################################
	 * TC_014_New_Computer_Addition_Incorrect Month Ending Date for Introduced or Discontinued Date 
	 * Description: To Verify the validation message for incorrect month ending dates for 'Introduced' or 'Discontinued' dates
	 * e.g. '2018/09/31'
	 * Precondition - N/A
	 * Test Case Type: Negative
	 * @param : N/A
	 * @throws ParseException 
	 * @Author: Hitesh Ghai
	 * @Expected Result: A validation message should be shown for incorrect month ending dates for 'Introduced' or 'Discontinued' dates
	 * ##############################################################################################################################################################
	 */
	
	@Test(priority=12)
	public void TC_014_Verify_New_Computer_Addition_With_Incorrect_Month_Ending_Dates() throws ParseException
	{
		String newCompName="_TVS#Ver2.0";
		String introDate="2018/09/31";
		String leapYearDisConDate="2019/09/30";
		String comanyName="Nokia";
		homePgObj= new Page_Home(driver);
				
		newCompAddObj= new Page_NewComputerAddition(driver);
			
		// Step 1: Verify home page is displayed
		
		homePgObj.verifyHomePage("Addition");
		
		newCompAddObj= new Page_NewComputerAddition(driver);
		
		// Step 2: Click on 'Add a new computer' button 
		
		newCompAddObj=homePgObj.clickAddNewCompBtn("Firefox", testCaseName);		
		
		// Step 3: Verify Add New Computer page is displayed
		
		newCompAddObj.verify_Header_Display("Firefox", testCaseName);
		
		// Step 4: Enter New Computer name in Computer Name field
		
		newCompAddObj.enterNewCompData("Firefox", "CompName", newCompName, testCaseName);
		
		// Step 5: Enter New Computer Introduction date
		
		newCompAddObj.enterNewCompData("Firefox", "introDate", introDate, testCaseName);
		
		// Step 6: Enter New Computer Discontinue date
		
		newCompAddObj.enterNewCompData("Firefox", "disContDate", leapYearDisConDate, testCaseName);
		
		// Step 7: Select company 'Apple Inc.' from company drop down
		
		newCompAddObj.selectCompanyName("Firefox", comanyName, testCaseName);
		
		// Step 8: Click on 'Create this computer' button
		
		homePgObj= newCompAddObj.clickBtn("Firefox", "Create this computer", testCaseName);
				
		/*
		 * Step 9: Check the validation message for Leap year Discontinued Date. No Validation message should be displayed.If it's displayed,then
		 * it is fail
		 *
		 */			
		isEventSuccessful=newCompAddObj.check_ErrorElementState();
			try {
				if (isEventSuccessful) {
					ExtentTestManager.getTest().log(LogStatus.PASS,"Validation message for incorrect month ending date for 'Introduced Date' i.e " + introDate + " is shown");
					logger.info("#########  Validation message for incorrect 'Introduced Date' is shown ####");
					}
				}

			catch (Exception e) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Validation message for incorrect month ending date for 'Introduced Date' i.e " + introDate + " is shown");
				logger.info("#########User is able to create new computer###########");
					// Assert.assertTrue(false);
				}			
	}	
	
	/** ###########################################################################################################################################################
	 * TC_015_New_Computer_Addition_Cancel Button functionality
	 * Description: To verify the cancel button functionality on 'Add a computer' page.
	 * Precondition - N/A
	 * Test Case Type: Positive
	 * @param : N/A
	 * @throws ParseException 
	 * @Author: Hitesh Ghai
	 * @Expected Result: Clicking on 'Cancel' should route user to application home page without creating new computer
	 * ##############################################################################################################################################################
	 */
	
	@Test(priority=13)
	public void TC_015_New_Computer_Addition_Click_Cancel_Btn_Verification() throws ParseException{
		
		String newCompName = "Solar Computer services";
		homePgObj = new Page_Home(driver);

		newCompAddObj = new Page_NewComputerAddition(driver);

		// Step 1: Verify home page is displayed

		homePgObj.clickSampleAppLink("Firefox", testCaseName);

		newCompAddObj = new Page_NewComputerAddition(driver);

		// Step 2: Click on 'Add a new computer' button

		newCompAddObj = homePgObj.clickAddNewCompBtn("Firefox", testCaseName);

		// Step 3: Verify Add New Computer page is displayed

		newCompAddObj.verify_Header_Display("Firefox", testCaseName);

		// Step 4: Enter New Computer name in Computer Name field

		newCompAddObj.enterNewCompData("Firefox", "CompName", newCompName, testCaseName);

		// Step 5: Enter New Computer Introduction date

		newCompAddObj.enterNewCompData("Firefox", "introDate", "2018-11-22", testCaseName);

		// Step 6: Enter New Computer Discontinue date

		newCompAddObj.enterNewCompData("Firefox", "disContDate", "2019-11-22", testCaseName);

		// Step 7: Select company 'Apple Inc.' from company drop down

		newCompAddObj.selectCompanyName("Firefox", "BBN Technologies", testCaseName);

		// Step 8: Click on 'Cancel' button

		homePgObj = newCompAddObj.clickBtn("Firefox", "Cancel", testCaseName);

		// Step 9.1: Method 1: Verify User routes to Application Home Page by
		// checking the 'Total Computer Found' header

		compFoundHeader = homePgObj.getComputerFoundData();

		if (compFoundHeader != null && compFoundHeader.contains("computers found")) {

			ExtentTestManager.getTest().log(LogStatus.PASS,"User is routed to Home page after clicking on cancel button");
			logger.info("#########User is routed to Home page after clicking on cancel button###########");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,"User is not routed to Home page after clicking on cancel button");
			logger.info("#########User is not routed to Home page after clicking on cancel button###########");
		}

		// Step 9.2: Method 2: Search/filter the computer name entered in Step#
		// 4. No Search results should be shown.
		// Check 'Nothing to display' message to validate it further.

		// Step 9.2.1: Enter new computer name in search field

		homePgObj.enterCompName_InSrchField(newCompName,"Addition");

		// Step 9.2.2: Click on 'Filter by name' button

		homePgObj.click_FilterBtn("Addition");

		// Step 9.2.3 - Check the 'Nothing to Display' message

		isEventSuccessful = homePgObj.verifyMsg_NthToDisplay();
		if (isEventSuccessful) {
			ExtentTestManager.getTest().log(LogStatus.PASS,
					"New Computer is not created as 'Nothing to display' message is displayed");
			logger.info("#########User is routed to Home page after clicking on cancel button###########");
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Cancel button functionality is not working as it created a new computer");
			logger.info("#########Cancel button functionality is not working as it created a new computer###########");
		}
	}
		
		/** ###########################################################################################################################################################
		 * TC_016_New_Computer_Addition_Clicking on sample app link Displays Home Page
		 * Description: To verify the cancel button functionality on 'Add a computer' page.
		 * Precondition - N/A
		 * Test Case Type: Positive
		 * @param : N/A
		 * @throws ParseException 
		 * @throws InterruptedException 
		 * @Author: Hitesh Ghai
		 * @Expected Result: Clicking on 'Play sample application-Computer Database' link on 'Add New Computer' page should route user to application home page without creating new computer
		 * ##############################################################################################################################################################
		 */
	
	
	@Test(priority=14)
	public void TC_016_New_Computer_Addition_Click_Sample_Application_Link_Displays_Home_Page_Verification() throws ParseException, InterruptedException{
					
			homePgObj = new Page_Home(driver);

			newCompAddObj = new Page_NewComputerAddition(driver);

			// Step 1: Verify home page is displayed

		    homePgObj.clickSampleAppLink("Firefox", testCaseName);

			newCompAddObj = new Page_NewComputerAddition(driver);

			// Step 2: Click on 'Add a new computer' button

			newCompAddObj = homePgObj.clickAddNewCompBtn("Firefox", testCaseName);
			
			// Step 3: Click on 'Play Sample Application - Computer Database link
			
			homePgObj.clickSampleAppLink("", "");
			
			// Step 4: Wait for 'Computer Found' header
			
			homePgObj.checkEleVisiblity();
			
			// Step 5: Verify User routes to Application Home Page by checking the 'Total Computer Found' header

			compFoundHeader = homePgObj.getComputerFoundData();

			if (compFoundHeader != null && compFoundHeader.contains("computers found")) {

				ExtentTestManager.getTest().log(LogStatus.PASS,"User is routed to Home page after clicking on sample application link");
				logger.info("#########User is routed to Home page after clicking on sample application link###########");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,"User is not routed to Home page after clicking on sample application link");
				logger.info("#########User is not routed to Home page after clicking on sample application link###########");
			}
			
			
		
	}
	
   @AfterMethod
   public void afterEachTest(ITestResult result) throws InterruptedException {
        try
        {

            System.out.println("Inside After Method of test case");
            actionAfterTest.testCaseReportUp(result, "Addition");
        }

        catch (Exception e)
        {
            System.out.println("Excpetion is " + e.getMessage());
        }
	}

}
