package selenium;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.SeleniumHelper;
import utilities.MSAutomationExcel;

public class SearchProducts {

	WebDriver driver;
	Logger logger = LogManager.getLogger(this.getClass().getName());

	@BeforeTest

	public void driverInit() throws Exception {
		SeleniumHelper.setupDriver();
	}

	@BeforeClass

	public void Login() throws Exception {
		BaseClass.login();

	}

	@Test(priority = -1)
	public void search_products() throws Exception {
		// logger.info("Searching products");
		
		driver = SeleniumHelper.getDriver();

		SeleniumHelper.OpenUrl("https://www.amazon.in");
		SeleniumHelper.maxImplicitWait();

		SeleniumHelper.getTitle();

		WebElement search_box = driver
				.findElement(By.xpath("//input[@id = 'twotabsearchtextbox' and @placeholder='Search Amazon.in']"));
		search_box.click();
		search_box.clear();
		search_box.sendKeys(MSAutomationExcel.getExcelData("Sheet2", 1, 0));
		search_box.sendKeys(Keys.ENTER);
		SeleniumHelper.maxImplicitWait();
	}

	@Test(dependsOnMethods = { "search_products" })
	public void select_products() {
		System.out.println("Selecting the products");
		List<WebElement> elements = driver.findElements(By.xpath("//img[@class='s-image']"));
		String alt_text = elements.get(1).getAttribute("alt");
		System.out.println(alt_text);
		String text = alt_text.split("'")[0].trim();
		System.out.println(text);

		elements.get(1).click();// clicking the 2nd product

		// using variable
		// WebElement
		// select_element=driver.findElement(By.xpath("//img[contains(@alt,'"+text+"')]"));
		// select_element.click();

		// clicking on the product
		WebElement select_product = driver.findElement(By.xpath("//div[@id ='imgTagWrapperId']"));
		select_product.click();
		SeleniumHelper.minImplicitWait();
		System.out.println("Selected product has been opened for image view");

		// selecting and viewing the images,
		List<WebElement> click_product = driver.findElements(By.xpath("//div[@class ='ivThumb']"));
		click_product.get(0).click();
		SeleniumHelper.maxImplicitWait();
		click_product.get(1).click();
		SeleniumHelper.maxImplicitWait();
		click_product.get(2).click();
		SeleniumHelper.maxImplicitWait();

		// closing the image view
		WebElement close_btn = driver
				.findElement(By.xpath("//button[@class=' a-button-close a-declarative a-button-top-right']"));
		close_btn.click();
		System.out.println("Closed the image view");

	}

	@Test(dependsOnMethods = { "select_products" })
	public void addCart() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		System.out.println("Scroll down is done");
		SeleniumHelper.maxImplicitWait();

		// scroll to find the add to cart button
		WebElement add_to_cart = driver.findElement(By.id("buy-now-button"));

		// js.executeScript("arguments[0].scrollIntoView(); ", add_to_cart);
		add_to_cart.click();
		System.out.println("Product is added to the cart");

		SeleniumHelper.maxImplicitWait();
		driver.navigate().back();

	}

	// @AfterClass

	public void logout() {

		BaseClass.logout();

	}

	// @AfterTest

	public void driverClose() {
		SeleniumHelper.tearDown();
	}
}
