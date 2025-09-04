package selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.MSAutomationExcel;
import utilities.SeleniumHelper;

public class FilterProducts {

	WebDriver driver;

	// @BeforeTest

	public void driverInit() throws Exception {
		SeleniumHelper.setupDriver();
	}

	// @BeforeClass

	public void login() throws Exception {

		// AppiumHelper.setupDriver();
		BaseClass.login();

	}

	@Test(priority = -1)
	public void searchProducts() throws Exception {
		driver = SeleniumHelper.getDriver();
		SeleniumHelper.maxImplicitWait();

		SeleniumHelper.getTitle();
		
		WebElement search_box = driver
				.findElement(By.xpath("//input[@id = 'twotabsearchtextbox' and @placeholder='Search Amazon.in']"));
		search_box.click();
		search_box.clear();
		search_box.sendKeys(MSAutomationExcel.getExcelData("Sheet2", 2, 0));
		search_box.sendKeys(Keys.ENTER);
		SeleniumHelper.maxImplicitWait();

	}

	@Test(dependsOnMethods = { "searchProducts" })
	public void filterProducts() {
		// filter by brand "DELL"
		WebElement filter = driver.findElement(By.xpath("//span[text()='Dell']"));
		filter.click();
		SeleniumHelper.maxImplicitWait();
		System.out.println("Dell Laptops are displayed");
		
		List<WebElement> product = driver.findElements(By.xpath("//span[contains(text(),'Dell Inspiron')]"));
		String laptop_name = product.get(0).getText();
		System.out.println("Laptop Name is" + laptop_name);
		product.get(0).click();
		System.out.println("The product details page is displayed");
		SeleniumHelper.maxImplicitWait();
		
		/*WebElement selected_product = driver.findElement(By.xpath("//span[contains(text(),'" + laptop_name + "')]"));
		System.out.println(selected_product.getText());
		AppiumHelper.maxImplicitWait();

		Assert.assertEquals(laptop_name, selected_product.getText());*/

	}

	// @AfterClass

	public void logout() {

		BaseClass.logout();
		SeleniumHelper.tearDown();

	}

	// @AfterTest

	public void driverClose() {
		SeleniumHelper.tearDown();
	}

}
