package selenium_api;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class iframe_Frame_Windows {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", ".\\.\\.\\driver\\chromedriver.exe");
//		driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void StepforAll() {
		// open app
		driver.get("http://www.hdfcbank.com/");
		// wait page load sucessfully
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void TC01() {
		if (driver.findElement(By.xpath("//div[@id='parentdiv']")).isDisplayed()) {
			driver.findElement(By.xpath("//img[@class='popupCloseButton']")).click();
			WebElement iframe = driver.findElement(By.id("ifr_NewCereberusDetails_R1_Borrower"));
			driver.switchTo().frame(iframe);
//			String text = driver.findElement(By.xpath("//div[@id='messageContainer']")).getText();
//			assertEquals("What are you looking for?", text);
			assertTrue(driver.findElement(By.xpath("//div[@id='messageContainer']")).isDisplayed());
			assertTrue(driver.findElement(By.xpath("//div[@class =\"login_panel\"]/following-sibling::div"))
					.isDisplayed());
			WebElement ele = driver.findElement(By.xpath(
					"//div[@class =\"login_panel\"]/following-sibling::div/div[@class='owl-wrapper-outer']/div/div"));
			assertEquals(6, ele.getSize());
			System.out.println("Done");
		} else {
			assertTrue(driver.findElement(By.xpath("//div[@id='messageContainer']")).isDisplayed());
			assertTrue(driver.findElement(By.xpath("//div[@class =\"login_panel\"]/following-sibling::div"))
					.isDisplayed());
			WebElement ele = driver.findElement(By.xpath(
					"//div[@class =\"login_panel\"]/following-sibling::div/div[@class='owl-wrapper-outer']/div/div"));
			assertEquals(6, ele.getSize());
			System.out.println("Done");
		}

	}

	@AfterClass
	public void cleanUp() {
		driver.close();
	}

}
