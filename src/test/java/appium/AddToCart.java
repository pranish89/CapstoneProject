package appium;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import config.Constants;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utilities.AppiumHelper;
import utilities.Scroll;
import utilities.Swipe;

public class AddToCart {
	AndroidDriver driver;
	
	@BeforeTest
	public void setupDriver() throws Exception {
		
		AppiumHelper.setupDriver();
	}
	
	//@BeforeClass
	public void login() throws Exception
	{
		BaseClass.login();
		
	}
	
	@Test
	public void search_products() {
		driver=AppiumHelper.getDriver();
		System.out.println("Search Products and Add to Cart");
		
		//finding the element Categories
		WebElement categories =driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Categories']"));
		categories.click();
		AppiumHelper.minImplicitWait();
		
		//finding the element Electronics inside Categories 
		WebElement electronics =driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Electronics']"));
		electronics.click();
		AppiumHelper.minImplicitWait();
		
		//checking for View all
		WebElement view = driver.findElement(AppiumBy.accessibilityId("View All"));
		view.click();
		AppiumHelper.minImplicitWait();
		
		//scrolling to find the device
		Scroll.scrollview(driver);
		
		//Clicking on chrome_laptop
		WebElement chrome_laptop = driver.findElement(AppiumBy.accessibilityId("Chromebooks"));
		chrome_laptop.click();
		AppiumHelper.minImplicitWait();

		
		WebElement select = driver.findElement(AppiumBy.accessibilityId("Lenovo 100e Chromebook Gen 4 MediaTek Kompanio 520 - (4 GB/32 GB EMMC Storage/Chrome OS) 82W00004HA Chromebook"));
		select.click();
		
		Swipe.action(driver, "RIGHT");
		AppiumHelper.minImplicitWait();
		Swipe.action(driver, "LEFT");
		
		WebElement add_to_cart  = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Buy now']"));
		add_to_cart.click();
		
		System.out.println("Added to Cart");
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();

	}

}
