package selenium;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.MSAutomationExcel;

public class Wishlist {

	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeTest

	public void driverInit(String browser) throws Exception {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"..\\CapstoneProject\\ChromeDriver\\chromedriver.exe");

			driver = new ChromeDriver();

			System.out.println("Chrome Browser is launched");
		} else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					"..\\CapstoneProject\\ChromeDriver\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.manage().window().maximize();

		System.out.println("Window is maximized");

	}

	@BeforeClass

	public void Login() throws Exception {
		
		driver.get("https://www.amazon.in");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement signin_link = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		signin_link.click();
		WebElement mobile_number = driver.findElement(By.xpath("//input[@name='email']"));
		mobile_number.click();
		mobile_number.clear();
		
		String phone = MSAutomationExcel.getExcelData("Sheet1", 1, 1);
		mobile_number.sendKeys(phone.substring(1));
		System.out.println("Username retrived from excel");
		
		WebElement continue_btn = driver.findElement(By.xpath("//input[@type='submit']"));
		continue_btn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		WebElement password = driver.findElement(By.xpath("//input[@id='ap_password']"));
		password.click();
		password.clear();
		String pass = MSAutomationExcel.getExcelData("Sheet1", 1, 2);
		password.sendKeys(pass.substring(1));
		System.out.println("password retrieved from excel");
		
		WebElement signin_btn = driver.findElement(By.xpath("//input[@id='signInSubmit']"));
		signin_btn.click();
		System.out.println("Sign in successfull");

	}

	@Test(priority = -1)
	public void search_products() throws Exception {
		// driver=AppiumHelper.getDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement search_box = driver
				.findElement(By.xpath("//input[@id = 'twotabsearchtextbox' and @placeholder='Search Amazon.in']"));
		search_box.click();
		search_box.clear();
		search_box.sendKeys(MSAutomationExcel.getExcelData("Sheet2", 5, 0));
		search_box.sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test(dependsOnMethods = { "search_products" })
	public void filter_select_products() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// filter based on language English
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		System.out.println("Scroll down is done");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement lang = driver.findElement(By.xpath("//span[text()='English']"));
		lang.click();
		System.out.println("Language Filter applied");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		/*
		 * WebElement
		 * price=driver.findElement(By.xpath("//span[text()='₹300 - ₹600']"));
		 * price.click(); System.out.println("Price Filter applied");
		 * AppiumHelper.maxImplicitWait();
		 */

		// selecting books with title "Ikigai"
		WebElement book = driver.findElement(By.xpath("//span[contains(text(),'Ikigai')]"));
		book.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@Test(dependsOnMethods = { "filter_select_products" })
	public void add_to_wishlist() {
		String parent = driver.getWindowHandle();
		System.out.println("Parent window handle is " + parent);
		
		// scrolling
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		// adding to wishlist
		WebElement wishlist = driver.findElement(By.xpath("//input[@id ='add-to-wishlist-button-submit']"));
		wishlist.click();
		System.out.println("The item is wishlisted");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Continue Shopping
		WebElement continueBtn = driver.findElement(By.xpath("//a[text()='View Your List']"));
		continueBtn.click();

	}

	@AfterClass

	public void logout() {

		WebElement account_arrow = driver.findElement(By.xpath(
				"//button[@class = 'nav-flyout-button nav-icon nav-arrow' and @aria-label ='Expand Account and Lists']"));
		account_arrow.click();
		
		WebElement sign_out = driver.findElement(By.xpath("//span[text()='Sign Out']"));
		sign_out.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		System.out.println("Signed out of Amazon");

	}

	@AfterTest

	public void driverClose() {
		driver.quit();
	}

}
