package selenium;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.SeleniumHelper;
import utilities.MSAutomationExcel;

public class AddToCart {

	WebDriver driver;

	// @BeforeTest

	public void driverInit() throws Exception {
		SeleniumHelper.setupDriver();
	}

	//@BeforeClass

	public void Login() throws Exception {
		BaseClass.login();

	}

	@Test(priority = -1)
	public void search_products() throws Exception {
		driver = SeleniumHelper.getDriver();

		SeleniumHelper.maxImplicitWait();

		SeleniumHelper.getTitle();

		WebElement search_box = driver
				.findElement(By.xpath("//input[@id = 'twotabsearchtextbox' and @placeholder='Search Amazon.in']"));
		search_box.click();
		search_box.clear();
		search_box.sendKeys(MSAutomationExcel.getExcelData("Sheet2", 4, 0));
		search_box.sendKeys(Keys.ENTER);
		SeleniumHelper.maxImplicitWait();
	}

	@Test(dependsOnMethods = { "search_products" })
	public void filter_select_products() {
		SeleniumHelper.maxImplicitWait();
		
		// click on the filter Mobile phone chargers
		WebElement filter = driver.findElement(By.xpath("//span[text()='Consoles & Organizers']"));
		filter.click();
		SeleniumHelper.maxImplicitWait();
		System.out.println("Filter Console & Organizers is applied");
		WebElement cup_holders = driver.findElement(By.xpath("//span[text()='Cup Holders']"));
		cup_holders.click();
		SeleniumHelper.minImplicitWait();

		// selecting the brand Detachi 80W
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		System.out.println("scrolling to find the filter");

		WebElement product = driver.findElement(By.xpath("//span[contains(text(),'DETACHI')]"));
		product.click();

		SeleniumHelper.maxImplicitWait();
		
		// assertion if selected product is displayed
		/*if (driver.findElement(By.xpath("//span[@id ='productTitle' and contains(text(),'Portronics')]"))
				.isDisplayed()) {
			System.out.println("Selected product is displayed");
		}*/

		// incresing the quantity to 2
		// Select select = new
		// Select(driver.findElement(By.xpath("//select[@name='quantity']")));
		// select.selectByVisibleText("2");

		SeleniumHelper.maxImplicitWait();
	}

	@Test(dependsOnMethods = { "filter_select_products" })

	public void add_to_cart() {

		//adding to cart
		String parent = driver.getWindowHandle();
		WebElement add_to_cart = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(add_to_cart));
		add_to_cart.click();
		SeleniumHelper.maxImplicitWait();
		
		/*WebElement skip = driver.findElement(By.xpath("//input[@data-asin='B0BVMV683N']"));
		skip.click();
		SeleniumHelper.minImplicitWait();*/

	}

	@Test(dependsOnMethods = { "add_to_cart" })

	public void payment() {
		// click on proceed to payment button
		WebElement proceed = driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']"));
		proceed.click();
		SeleniumHelper.maxImplicitWait();
		// check the price
		// WebElement total =driver.findElement(By.xpath("//td[@class='a-color-base
		// a-size-medium a-text-right grand-total-price aok-nowrap a-text-bold
		// a-nowrap']"));
		// System.out.println("The total price in cart " +total.getText());

		// check if address is displayed
		if (driver.findElement(By.xpath("//span[@id='deliver-to-address-text']")).isDisplayed()) {
			System.out.println("Address is displayed");
		}
		driver.navigate().back();

		/*
		 * JavascriptExecutor js = (JavascriptExecutor)driver;
		 * js.executeScript("window.scrollBy(0,500)");
		 * 
		 * //select netbanking //WebElement net_banking =
		 * driver.findElement(By.xpath("//span[contains(text(),'Net Banking')]"));
		 * //net_banking.click(); js.executeScript("window.scrollBy(0,-500)");
		 */
	}

	//@AfterClass

	public void logout() {
		BaseClass.logout();

	}

	//@AfterTest

	public void driverClose() {
		SeleniumHelper.tearDown();
	}

}
