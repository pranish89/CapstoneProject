package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.SeleniumHelper;
import utilities.MSAutomationExcel;

public class BaseClass {
	
	private static WebDriver driver;
	
	public static void login() throws Exception
	{
		driver=SeleniumHelper.getDriver();
		SeleniumHelper.OpenUrl("https://www.amazon.in");
		SeleniumHelper.maxImplicitWait();
		SeleniumHelper.getTitle();
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
		SeleniumHelper.minImplicitWait();
		WebElement password = driver.findElement(By.xpath("//input[@id='ap_password']"));
		password.click();
		password.clear();
		String pass=MSAutomationExcel.getExcelData("Sheet1", 1, 2);
		password.sendKeys(pass.substring(1));
		System.out.println("password retrieved from excel");
		WebElement signin_btn = driver.findElement(By.xpath("//input[@id='signInSubmit']"));
		signin_btn.click();
		System.out.println("Sign in successfull");


	}
	
	public static void logout() {
		
		WebElement account_arrow = driver.findElement(By.xpath("//button[@class = 'nav-flyout-button nav-icon nav-arrow' and @aria-label ='Expand Account and Lists']"));
		account_arrow.click();
		WebElement sign_out=driver.findElement(By.xpath("//span[text()='Sign Out']"));
		sign_out.click();
		SeleniumHelper.maxImplicitWait();
		System.out.println("Signed out of Amazon");

		
	}

}
