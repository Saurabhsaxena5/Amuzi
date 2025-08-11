package Amuzi;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestCase {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setup() {
		ChromeOptions option = new ChromeOptions();
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://amuzi.in/");

	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void loginWithPhoneNumber() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
		enterphoneNumber.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

		driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
		Thread.sleep(4000);
		WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
		Thread.sleep(2000);
		clickOnSendOtp.click();
	}

	@Test
	public void wrongOtpLoginTest() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
		enterphoneNumber.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

		driver.findElement(By.name("otp")).sendKeys("1235");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[normalize-space(text())=\"Verify OTP\"]")).click();

		String buttonText = driver.findElement(By.xpath("//button[@type='submit']")).getText();
		Assert.assertNotEquals(buttonText, "Incorrect OTP entered. Please enter again.");
		System.out.println(buttonText);
	}

	@Test
	public void loginWithwrongMailTest() throws InterruptedException {

		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[normalize-space(.)=\"Login with Email\"]")).click();

		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("rajnish.kumar@unvii.com");
		driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
		driver.findElement(By.xpath("//input[@class='otp-input form-control']")).sendKeys("1434");
		driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();
		// Assert here on the result of OTP validation
	}

	@Test
	public void loginWithMailTest() throws InterruptedException {

		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[normalize-space(.)=\"Login with Email\"]")).click();

		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("rajnish.kumar@unvii.com");
		driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
		driver.findElement(By.xpath("//input[@class='otp-input form-control']")).sendKeys("1234");
		driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();
		// Assert here on the result of OTP validation
	}

	@Test
	public void logoutTest() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
		enterphoneNumber.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

		driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
		Thread.sleep(4000);
		WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
		Thread.sleep(2000);
		clickOnSendOtp.click();

		// driver.findElement(By.xpath("(//img[@class='Movieslogo'])[8]")).click();
		WebElement Clickonlogout = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[8]")));
		Clickonlogout.click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='removebtn']"))).click();

	}

	@Test
	public void detailedPageWithoutLoginTest() {

		WebElement devotionalTab = driver.findElement(By.xpath("//a[@href='/Devotional/16']"));
		devotionalTab.click();
		WebElement videoElement = driver
				.findElement(By.xpath("//div[@class='scroll-container']//div[@id='custom-div-2']"));
		videoElement.click();

		String videoTitle = driver.findElement(By.xpath("//h3[@class='detailHeading']")).getText();
		Assert.assertEquals(videoTitle, "Ahiya Maiya Pujwa Ke Beriya", "Video title mismatch");
	}

	@Test
	public void withoutLoginSubscriptionButtonisVisible() {
		try {
			WebElement withoutlogincheckSubscriptionButtonIsvisible = driver
					.findElement(By.xpath("(//img[@class='Movieslogo'])[5]"));
			if (withoutlogincheckSubscriptionButtonIsvisible.isDisplayed()) {
				System.out.println("Visible");
			} else {
				System.out.println("Not visible");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@Test
	public void clickwatchnow_userredirect_into_login() throws InterruptedException {
		Thread.sleep(4000);

		WebElement clickonvideo = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class=\"img-top\"])[1]")));
		clickonvideo.click();

		driver.findElement(By.xpath("//button[@class=\"sc-fUnMCh hzdSCt\"]")).click();
		boolean check = driver.findElement(By.xpath("//div[@class=\"signin-form\"]")).isDisplayed();
		System.out.println(check);
	}
}
