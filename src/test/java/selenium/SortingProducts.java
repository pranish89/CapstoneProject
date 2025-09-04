package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.SeleniumHelper;
import utilities.MSAutomationExcel;

public class SortingProducts {
	
WebDriver driver;
	
	//@BeforeTest
	
	public void driverInit() throws Exception
	{
		SeleniumHelper.setupDriver();
	}
	
//@BeforeClass
	
	public void Login() throws Exception
	{
		BaseClass.login();

	}

	
	@Test(priority=-1)
	public void searchProducts() throws Exception {
		driver=SeleniumHelper.getDriver();
		SeleniumHelper.maxImplicitWait();

		SeleniumHelper.getTitle();
		WebElement search_box=driver.findElement(By.xpath("//input[@id = 'twotabsearchtextbox' and @placeholder='Search Amazon.in']"));
		search_box.click();
		search_box.clear();
		search_box.sendKeys(MSAutomationExcel.getExcelData("Sheet2", 3, 0));
		search_box.sendKeys(Keys.ENTER);
		SeleniumHelper.maxImplicitWait();

	}
	
	@Test(dependsOnMethods = {"searchProducts"})
	public void sortPrice()
	{
		WebElement arrow_sort = driver.findElement(By.xpath("//span[text()='Featured']"));
		arrow_sort.click();
		WebElement low_to_high = driver.findElement(By.xpath("//a[text() ='Price: Low to High']"));
		low_to_high.click();
		//Select dropdown = new Select(driver.findElement(By.xpath("//span[@id='a-autoid-73']/child::*")));
		//dropdown.selectByVisibleText("Price: Low to High");
		
		
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
