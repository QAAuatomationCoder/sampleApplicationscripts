package com.backbase.utilLibrary;


import org.testng.ITestResult;
import com.backbase.baseSetUp.BaseSetUp;
import com.relevantcodes.extentreports.LogStatus;

public class ActionAfterTest extends BaseSetUp {

	BaseSetUp baseSetUp;
	
	public void testCaseReportUp(ITestResult result,String moduleName) throws InterruptedException
	{
		try
		{	
			if (moduleName.equalsIgnoreCase("Addition")) {
				if (result.getStatus() == ITestResult.FAILURE) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
				} else if (result.getStatus() == ITestResult.SKIP) {
					ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Test Executed");
				}

				ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
				ExtentManager.getReporter().flush();
			}
			
			else if (moduleName.equalsIgnoreCase("Reading"))
			{
				if (result.getStatus() == ITestResult.FAILURE) {
					ExtentTestManager1.getTest().log(LogStatus.FAIL, result.getThrowable());
				} else if (result.getStatus() == ITestResult.SKIP) {
					ExtentTestManager1.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
				} else {
					ExtentTestManager1.getTest().log(LogStatus.PASS, "Test Executed");
				}

				ExtentManager1.getReporter().endTest(ExtentTestManager1.getTest());
				ExtentManager1.getReporter().flush();
			}
			
			else if (moduleName.equalsIgnoreCase("Updation"))
			{
				if (result.getStatus() == ITestResult.FAILURE) {
					ExtentTestManager2.getTest().log(LogStatus.FAIL, result.getThrowable());
				} else if (result.getStatus() == ITestResult.SKIP) {
					ExtentTestManager2.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
				} else {
					ExtentTestManager2.getTest().log(LogStatus.PASS, "Test Executed");
				}

				ExtentManager2.getReporter().endTest(ExtentTestManager2.getTest());
				ExtentManager2.getReporter().flush();
			}
			
			else if (moduleName.equalsIgnoreCase("Deletion"))
			{
				if (result.getStatus() == ITestResult.FAILURE) {
					ExtentTestManager3.getTest().log(LogStatus.FAIL, result.getThrowable());
				} else if (result.getStatus() == ITestResult.SKIP) {
					ExtentTestManager3.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
				} else {
					ExtentTestManager3.getTest().log(LogStatus.PASS, "Test Executed");
				}

				ExtentManager3.getReporter().endTest(ExtentTestManager3.getTest());
				ExtentManager3.getReporter().flush();
			}
			
    	        driver.quit();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	
	}
}
