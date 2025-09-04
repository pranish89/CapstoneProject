/*
 /* SeleniumRetry class implements IRetryAnalyzer interface
 * here mentions retryCount and Max for test cases
 * need to bind this with individual test in the class
 * @Test(retryAnalyzer = SeleniumRetry.class)
 */

package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class SeleniumRetry implements IRetryAnalyzer{
	private int retryCount =0;
	private static final int maxRetryCount=3;

	@Override
	public boolean retry(ITestResult result) {
		if(retryCount<maxRetryCount)
		{
			retryCount++;
			return true;
		}
		
		return false;
	}

}
