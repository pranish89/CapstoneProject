package appium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import utilities.AppiumHelper;
import utilities.Scroll;

public class BaseClass {

	static AndroidDriver driver;

	@Test
	protected static void login() throws Exception

	{
		AppiumHelper.setupDriver();

		driver = AppiumHelper.getDriver();

		WebElement account = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Account']"));
		account.click();
		AppiumHelper.minImplicitWait();

	WebElement login = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Log In']"));
		login.click();
		AppiumHelper.maxImplicitWait();

		WebElement phone_number = driver
				.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc='Phone Number']"));
		phone_number.click();

		/*
		 * Send Keys does not work, since unlike Amazon this screen displays a keypad,
		 * for sending the number through sendkeys(String.valueof(877767676L))*/
		 

		driver.pressKey(new KeyEvent(AndroidKey.DIGIT_8));
		driver.pressKey(new KeyEvent(AndroidKey.DIGIT_8));
		driver.pressKey(new KeyEvent(AndroidKey.DIGIT_8));
		driver.pressKey(new KeyEvent(AndroidKey.DIGIT_4));
		driver.pressKey(new KeyEvent(AndroidKey.DIGIT_9));
		driver.pressKey(new KeyEvent(AndroidKey.DIGIT_0));
		driver.pressKey(new KeyEvent(AndroidKey.DIGIT_0));
		driver.pressKey(new KeyEvent(AndroidKey.DIGIT_0));
		driver.pressKey(new KeyEvent(AndroidKey.DIGIT_3));
		driver.pressKey(new KeyEvent(AndroidKey.DIGIT_9));

		WebElement continue_btn = driver.findElement(
				AppiumBy.xpath("//android.widget.TextView[@resource-id='com.flipkart.android:id/button']"));
		continue_btn.click();

		driver.activateApp("com.google.android.apps.messaging");

		AppiumHelper.maxImplicitWait();
		List<WebElement> first_msg = driver.findElements(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id='com.google.android.apps.messaging:id/conversation_snippet']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println(first_msg.size());
		wait.until(ExpectedConditions.visibilityOf(first_msg.get(0)));

		String fullmsg = first_msg.get(0).getText();
		System.out.println(fullmsg);

		String otp = verify_otp(fullmsg);

		System.out.println("OTP is " + otp);

		driver.activateApp("com.flipkart.android");

		AppiumHelper.minImplicitWait();
		WebElement password_otp = driver.findElement(AppiumBy.id("com.flipkart.android:id/otp_view"));
		password_otp.sendKeys(otp);
		AppiumHelper.minImplicitWait();

		WebElement verify = driver.findElement(
				AppiumBy.xpath("//android.widget.TextView[@resource-id='com.flipkart.android:id/button']"));
		verify.click();
		AppiumHelper.maxImplicitWait();
		Thread.sleep(5000);
		
		//finding the element Home
				WebElement home =driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Home']"));
				home.click();
				AppiumHelper.minImplicitWait();

	}

	public static String verify_otp(String fullmsg) {
		System.out.println("removing all Alpha from message");
		String parser = fullmsg.replaceAll("[^0-9]", "");
		System.out.println("After Parsing " + parser);
		String otp = parser.substring(0, 6);
		System.out.println("Otp " + otp);
		return otp;
	}
	
	@Test
	public static void logout() {
		
		Scroll.scrollview(driver);
		
		WebElement logout = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Log Out']"));
		logout.click();
		AppiumHelper.minImplicitWait();
		
		WebElement logout_device = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Logout from this device']"));
		logout_device.click();
		System.out.println("Logged Out");
	}

}
