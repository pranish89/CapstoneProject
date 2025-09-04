package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import junit.framework.Assert;
import utilities.SeleniumHelper;

public class OrderDetails {

	WebDriver driver;

	// @BeforeTest

	public void driverInit() throws Exception {
		SeleniumHelper.setupDriver();
	}

	// @BeforeClass

	public void Login() throws Exception {
		BaseClass.login();
	}

	@Test

	public void Order_Details() {
		driver = SeleniumHelper.getDriver();

		// clicking on orders link
		WebElement orders = driver.findElement(By.xpath("//span[text()='& Orders']"));
		orders.click();
		String title = SeleniumHelper.getTitle();
		Assert.assertEquals("Your Orders", title);

	}
	
	//This will run even when one or more test cases failed or skipped.
	
	@AfterClass(alwaysRun=true)

	public void logout() {

		BaseClass.logout();

	}

	@AfterTest(alwaysRun=true)

	public void driverClose() {
		SeleniumHelper.tearDown();
	}

}
