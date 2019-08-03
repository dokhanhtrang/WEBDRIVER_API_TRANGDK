package selenium_api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Wait {
	WebDriver driver;
	String filePath = "D:\\Selenium workspace\\WEBDRIVER_API_TRANGDK\\Upload\\IMG01.jpg";
	String fileName = "IMG01.jpg";

	@BeforeClass
	public void setUp() {
//		System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
//		System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
//		driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void ImplicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='start']")).click();
		String text = driver.findElement(By.xpath("//*[@id='finish']")).getText();
		assertEquals("Hello World!", text);
	}

	@Test
	public void ExplicitWait() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.get(
				"http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ctl00_ContentPlaceholder1_RadCalendar1']")));
		assertTrue(
				driver.findElement(By.xpath("//span[contains(text(),'No Selected Dates to display.')]")).isDisplayed());
		driver.findElement(By.xpath("//a[text()='13']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_RadCalendar1Panel']")));
		assertTrue(driver.findElement(By.xpath("//span[text()='Tuesday, August 13, 2019']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
