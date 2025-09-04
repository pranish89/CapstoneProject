/*
 * This class implements ITestListener interface
 * which has predefined methods when the test starts or ends etc..
 * Using that we can log information like passes status and Screenshots for failed test cases.
 */

package utilities;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class SeleniumListener implements ITestListener {

	public void onTestStart(ITestResult result) {
	    
		Reporter.log("Starting the test case " +result.getName());
		System.out.println("Starting the test ");
	  }
	
	 public void onTestSuccess(ITestResult result) {
		 
		 Reporter.log("Status of the execution " +result.getStatus());
		 System.out.println("Test Case passed");
	 }
	 
	 public void onTestFailure(ITestResult result) {
		 Reporter.log("Test Case Failed " +result.getTestName());
		 System.out.println("Test Case Failed");
		 
		 try {
			SeleniumHelper.takeScreenshot();
		 	}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public void onTestSkipped(ITestResult result) {
		 System.out.println("Test Case Skipped");
}

	
}
