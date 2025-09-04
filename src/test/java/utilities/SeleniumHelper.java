package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumHelper {

	private static SeleniumHelper helper;
	private static WebDriver driver;
	private String wait;
	private static int max_wait_time;
	private static int min_wait_time;
	private String browser;
	static File file = new File("..\\CapstoneProject\\TestData\\SeleniumTestData.xlsx");

	private SeleniumHelper() throws Exception {

		// get the browser name from excel sheet
		browser = MSAutomationExcel.getExcelData("Sheet1", 1, 3);

		System.out.println("Retrived browser value from excel is " + browser);

		wait = MSAutomationExcel.getExcelData("Sheet1", 1, 4);

		max_wait_time = Integer.parseInt(wait.substring(1));

		System.out.println("Retrieved max wait time " + max_wait_time);

		wait = MSAutomationExcel.getExcelData("Sheet1", 1, 5);

		min_wait_time = Integer.parseInt(wait.substring(1));

		System.out.println("Retrieved max wait time " + min_wait_time);

		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"..\\CapstoneProject\\ChromeDriver\\chromedriver.exe");

			driver = new ChromeDriver();

			System.out.println("Chrome Browser is launched");
		}

		else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"..\\CapstoneProject\\ChromeDriver\\geckodriver.exe");

			driver = new FirefoxDriver();

			System.out.println("Firefox Browser is launched");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(max_wait_time));

		driver.manage().window().maximize();

		System.out.println("Window is maximized");

	}

	public static void setupDriver() throws Exception {

		if (helper == null) {
			helper = new SeleniumHelper();
		}
	}

	public static void tearDown() {
		if (driver != null) {
			driver.close();
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

	public static WebDriver getDriver() {
		return driver;
	}
	
	public static void takeScreenshot() throws IOException {
		Date currentdate = new Date();
		String targetfile= currentdate.toString().replace(" ","-").replace(":","-");
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile, new File("..\\CapstoneProject\\Screenshots\\" +targetfile +".png"));
		
	}

}
