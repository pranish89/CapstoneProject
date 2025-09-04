package appium;
/*
 * Demonstrate the "Add to wishlist" module by navigating to Categories-->Appliances-->QLED
 */

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utilities.AppiumHelper;
import utilities.Scroll;

public class AddToWishlist {
	
AndroidDriver driver;
	
	@BeforeTest
	public void setupDriver() throws Exception {
		
		AppiumHelper.setupDriver();
	}
	
	@BeforeClass
	public void login() throws Exception
	{
		BaseClass.login();
		
	}
	
	@Test
	public void search_by_image() {
		driver=AppiumHelper.getDriver();
		System.out.println("search Products and Add to wishlist");
		AppiumHelper.minImplicitWait();
		
		//finding the element categories
		WebElement categories =driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Categories']"));
		categories.click();
		AppiumHelper.minImplicitWait();
		
		//finding the element Appliances inside Categories
		WebElement appliances = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Appliances']"));
		appliances.click();
		AppiumHelper.minImplicitWait();
		
		//scrolling to see the QLED's
		Scroll.scrollview(driver);
		
		//finding the element QLED's
		WebElement QLED = driver.findElement(AppiumBy.accessibilityId("QLED TVs"));
		QLED.click();
		AppiumHelper.maxImplicitWait();
		
		//scrolling to find the device
		Scroll.scrollview(driver);
		
		WebElement device= driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='MOTOROLA 80 cm (32 inch) QLED HD Ready Smart Google TV 2025 Edition']"));
		device.click();
		AppiumHelper.maxImplicitWait();

		WebElement wishlist = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Buy now']"));
				//androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(4)"));
		wishlist.click();
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();

		
	}
//	@AfterClass
	public void logout() {
		
		BaseClass.logout();
		
	}
	
//	@AfterTest
	public void tearDown()
	{
		AppiumHelper.tearDown();
		
	}

}
