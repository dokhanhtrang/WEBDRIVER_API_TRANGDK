package selenium_api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JavascriptExcute {
	WebDriver driver;

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

//	@BeforeMethod
//	public void stepForAll() {
//
//	}

	@Test
	public void Tc01_JE() {
		driver.get("http://live.guru99.com");
		String domain = (String) executeScript("return document.domain");
		assertEquals("live.guru99.com", domain);
		String url = (String) executeScript("return document.URL");
		assertEquals("http://live.guru99.com/", url);
		WebElement mobile = driver.findElement(By.xpath("//a[contains(text(),'Mobile')]"));
		highlightElement(mobile);
		clickToElementByJS(mobile);
		WebElement addToCard = driver.findElement(
				By.xpath("//h2[contains(.,'Samsung Galaxy')]/following-sibling::div[@class='actions']//button"));
		highlightElement(addToCard);
		clickToElementByJS(addToCard);
		String mess = (String) executeScript("return document.documentElement.innerText");
		assertTrue(mess.contains("Samsung Galaxy was added to your shopping cart."));
		WebElement policy = driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]"));
		highlightElement(policy);
		clickToElementByJS(policy);
		String title = (String) executeScript("return document.title");
		assertEquals("Privacy Policy", title);
		executeScript("window.scrollBy(0,document.body.scrollHeight)");
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String domain1 = (String) executeScript("return document.domain");
		assertEquals("demo.guru99.com", domain1);
	}

	@Test
	public void TC02_RemoveAttribute() throws Exception {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		WebElement iframe = driver.findElement(By.id("iframeResult"));
		driver.switchTo().frame(iframe);
		WebElement fname = driver.findElement(By.xpath("//input[@name = 'fname']"));
		highlightElement(fname);
		sendkeyToElementByJS(fname, "Khanh");
		WebElement lname = driver.findElement(By.xpath("//input[@name = 'lname']"));
		removeAttributeInDOM(lname, "disabled");
		highlightElement(lname);
		sendkeyToElementByJS(lname, "Trang");
		WebElement submit = driver.findElement(By.xpath("//input[@type = 'submit']"));
		highlightElement(submit);
		clickToElementByJS(submit);
		Thread.sleep(5000);
		String mess = (String) executeScript("return document.documentElement.innerText");
		assertTrue(mess.contains("fname=Khanh&lname=Trang"));
	}

	public Object removeAttributeInDOM(WebElement element, String attribute) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}

	public Object sendkeyToElementByJS(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public Object executeScript(String javaSript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object clickToElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='6px groove red'", element);
	}

	public Object navigateToUrlByJS(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + url + "'");
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
