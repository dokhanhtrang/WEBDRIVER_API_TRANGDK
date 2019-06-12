package selenium_api;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic02_Xpath {
	// Create global variable
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Open browser
		driver = new FirefoxDriver();
		// Open app
		driver.get("http://live.guru99.com");
		// Wait page load
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Maximize browsers fullscreen
		driver.manage().window().maximize();
	}

	@Test
	public void TC01() {
		String title = driver.getTitle();
		assertEquals("Home page", title);
		driver.get("http://live.guru99.com/index.php/customer/account");
		driver.findElement(By.xpath("//a[contains(@title,'Create an Account')]")).click();
		driver.findElement(By.xpath("//a[contains(@class,'back-link')]")).click();
		String url1 = driver.getCurrentUrl();
		assertEquals("http://live.guru99.com/index.php/customer/account/",url1);
		driver.findElement(By.xpath("//a[contains(@title,'Create an Account')]")).click();
		String url2 = driver.getCurrentUrl();
		assertEquals("http://live.guru99.com/index.php/customer/account/create/",url2);
		System.out.println("Done TC01 - Verify URL");
	}
	public void TC02() {
		driver.get("http://live.guru99.com/");
		driver.get("http://live.guru99.com/index.php/customer/account");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		System.out.println("Done TC02 - Login Empty");
	}
	
	public void TC03() {
		driver.get("http://live.guru99.com/");
		driver.get("http://live.guru99.com/index.php/customer/account");
		driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("Hello@gmail.com");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		System.out.println("Done TC02 - Login Empty");
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
