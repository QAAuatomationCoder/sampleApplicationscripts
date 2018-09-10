package com.backbase.utilLibrary;


import org.testng.SkipException;
import com.backbase.baseSetUp.BaseSetUp;
import com.relevantcodes.extentreports.LogStatus;

public class ActionBeforeTest extends BaseSetUp {

	
	//public void beforeTestAction2(String browserType, String testCaseName, String methodName, String shName,String tcDesc) throws InterruptedException
	public void beforeTestAction2(String testCaseName, String methodName, String shName,String tcDesc) throws InterruptedException{

		try {			
			System.out.println("Inside Before Method & TestCaseName Is : " + testCaseName);
			String ToRunColumnNameTestCase = "CaseToRun";

			if (!SuiteUtility.checkToRunUtility(ExcelRd_Obj_Test_Suite, shName, ToRunColumnNameTestCase,testCaseName)) {
				SuiteUtility.WriteResultUtility(BaseSetUp.ExcelRd_Obj_Test_Suite, shName, "Pass/Fail/Skip",++suiteRow_AllURIs, "Skipped");
				ext_logger = report.startTest(testCaseName, testCaseName);
				ext_logger.log(LogStatus.SKIP, testCaseName + "is skipped");
				BaseSetUp.report.endTest(BaseSetUp.ext_logger);
				Thread.sleep(1000);
				//BaseSetUp.report.flush();
				System.out.println("Extent report is flushed for Firefox");
				throw new SkipException(
						testCaseName + "'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of " + testCaseName);
			}

			else
			{
				ext_logger = report.startTest(testCaseName, testCaseName);
				//initializeTestBaseSetup(browserType);
				initializeTestBaseSetup();
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void beforeTestAction3(String testCaseName, String methodName) throws InterruptedException{

		try {			
				//ext_logger = report1.startTest(testCaseName, testCaseName);
				//initializeTestBaseSetup(browserType);
				initializeTestBaseSetup();			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
