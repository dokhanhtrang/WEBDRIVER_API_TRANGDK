package selenium_api;

import static org.junit.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Xpath {
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
		assertEquals("http://live.guru99.com/index.php/customer/account/login/", url1);
		driver.findElement(By.xpath("//a[contains(@title,'Create an Account')]")).click();
		String url2 = driver.getCurrentUrl();
		assertEquals("http://live.guru99.com/index.php/customer/account/create/", url2);
		System.out.println("Done TC01 - Verify URL");
	}

	@Test
	public void TC02() {
		driver.get("http://live.guru99.com/index.php/customer/account");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		System.out.println("Done TC02 - Login Empty");
	}

	@Test
	public void TC03() {
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("Hello@gmai");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String email = driver.findElement((By.xpath("//*[@id='advice-validate-email-email']"))).getText();
		assertEquals("Please enter a valid email address. For example johndoe@domain.com.", email);
		System.out.println("Done TC03 - Email Invalid");
	}

	@Test
	public void TC04() {
		driver.get("http://live.guru99.com/index.php/customer/account");
		driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String pass = driver.findElement(By.id("advice-validate-password-pass")).getText();
		assertEquals("Please enter 6 or more characters without leading or trailing spaces.", pass);
		System.out.println("Done TC04 - Password Incorrect");
	}

	@Test
	public void TC05() {
		driver.get("http://live.guru99.com/index.php/customer/account");
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys("Do");
		driver.findElement(By.id("middlename")).sendKeys("Khanh");
		driver.findElement(By.id("lastname")).sendKeys("Trang");
		driver.findElement(By.id("email_address")).sendKeys(getSaltString()+"@outlook.com");
		driver.findElement(By.id("password")).sendKeys("abc1234");
		driver.findElement(By.id("confirmation")).sendKeys("abc1234");;
		driver.findElement(By.xpath("//span[contains(text(),'Register')]")).click();
		String str = driver.findElement(By.xpath("//span[contains(.,'Thank you for registering with Main Website Store.')]")).getText();
		assertEquals("Thank you for registering with Main Website Store.", str);
		driver.findElement(By.xpath("//span[@class='label'][contains(text(),'Account')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();		
		System.out.println("Done TC05 - Create an account");
	}
	
	protected String getSaltString()  { //Random email
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
