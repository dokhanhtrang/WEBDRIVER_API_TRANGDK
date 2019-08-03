package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UploadFile {
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
	public void Sendkeys() {
		driver.get("http://www.helloselenium.com/2015/03/how-to-upload-file-using-sendkeys.html"); //Page error
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath);
	}

	@Test
	public void AutoIT() {
	}

	@Test
	public void Robot() {
	}
	public boolean checkAnyImgLoad(WebElement image) {
		JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
		return (boolean) jsExcutor.executeScript("return arguments[0].complete && "+"typeof arguments[0].naturalWidth ! = 'undifined' && arguments[0].naturalWidth > 0", image);
	}

	@AfterClass
	public void afterClass() {
	}

}
