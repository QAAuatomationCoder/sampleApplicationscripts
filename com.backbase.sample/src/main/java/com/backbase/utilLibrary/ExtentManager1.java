package com.backbase.utilLibrary;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager1 {
	
	static ExtentReports extent1;
   // final static String filePath = "./test-output/html/Extent.html";
	//final static String filePath1 = "./ExistingComputers_TestResultReport/TestResults_Report.html";
	final static String filePath1 = "./Report_ExistingComputer/ExistingComputers_TestResultReport.html";
	
    
    public synchronized static ExtentReports getReporter() {
        if (extent1 == null) {
            extent1 = new ExtentReports(filePath1, true);
            
        }
        
        return extent1;
    }

}
