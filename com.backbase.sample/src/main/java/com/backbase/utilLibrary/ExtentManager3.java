package com.backbase.utilLibrary;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager3 {
	
	static ExtentReports extent3;
	final static String filePath3 = "./Report_DeleteComputer/DeleteComputers_TestResultReport.html";
	
    
    public synchronized static ExtentReports getReporter() {
        if (extent3 == null) {
            extent3 = new ExtentReports(filePath3, true);
            
        }
        
        return extent3;
    }

}
