package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import config.Constants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


public class AppiumHelper {

	private static AppiumHelper helper;
	private static AndroidDriver driver;
	private String wait;
	private static int max_wait_time;
	private static int min_wait_time;
	static File file = new File("..\\CapstoneProject\\TestData\\SeleniumTestData.xlsx");

	@SuppressWarnings({ "deprecation" })
	private AppiumHelper() throws Exception {

		UiAutomator2Options option = new UiAutomator2Options()
				.setChromedriverExecutable(Constants.chrome_executable)
				.setDeviceName(Constants.device_name)
				.setPlatformName(Constants.platform_name)
				.setPlatformVersion(Constants.platform_version)
				.setAutomationName(Constants.automation_name)
				.setAppPackage(Constants.app_package)
				//.setIntentAction("android.intent.action.Main")
				//.setIntentCategory("android.intent.category.LAUNCHER")
				.setAppActivity(Constants.app_activity)
				//.setAppPackage("com.android.chrome")
				//.setAppActivity("com.google.android.apps.chrome.Main")
				.setAutoGrantPermissions(true)
				.setNoReset(true)
				.setFullReset(false)
				;

				//point at the running server
				URL server = new URL("http://127.0.0.1:4723/wd/hub");

				//driver creation
				driver = new AndroidDriver(server,option);
				
				
				
				//init wait time
				wait = MSAutomationExcel.getExcelData("Sheet4", 1, 4);

				max_wait_time = Integer.parseInt(wait.substring(1));

				System.out.println("Retrieved max wait time " + max_wait_time);

				wait = MSAutomationExcel.getExcelData("Sheet1", 4, 5);

				min_wait_time = Integer.parseInt(wait.substring(1));

				System.out.println("Retrieved max wait time " + min_wait_time);
				

		}

		


public static void setupDriver() throws Exception {

		if (helper == null) {
			helper = new AppiumHelper();
		}
	}

	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		helper = null;

	}

	public static void minImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(min_wait_time));
	}

	public static void maxImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(max_wait_time));

	}

	public static void OpenUrl(String url) {
		driver.get(url);
	}

	public static String getTitle() {
		return (driver.getTitle());
	}

	public static void navBack() {
		driver.navigate().back();
	}

	public static AndroidDriver getDriver() {
		return driver;
	}
	
	public static void takeScreenshot() throws IOException {
		Date currentdate = new Date();
		String targetfile= currentdate.toString().replace(" ","-").replace(":","-");
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrfile, new File("..//Screenshots//" +targetfile +".png"));
		
	}

}
