package com.backbase.utilLibrary;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager2 {
	
	static ExtentReports extent2;
   // final static String filePath = "./test-output/html/Extent.html";
	//final static String filePath1 = "./ExistingComputers_TestResultReport/TestResults_Report.html";
	final static String filePath2 = "./Report_UpdateComputer/UpdateComputers_TestResultReport.html";
	
    
    public synchronized static ExtentReports getReporter() {
        if (extent2 == null) {
            extent2 = new ExtentReports(filePath2, true);
            
        }
        
        return extent2;
    }

}
