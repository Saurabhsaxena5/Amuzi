package Amuzi;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
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

public class ViewAllTestCase extends Footer {
	@Test
	public void viewAllTest() throws InterruptedException {
		driver.get("https://chull.tv/"); // Always good to ensure you’re on the right page

		// Click login
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		Thread.sleep(2000);

		// Enter mobile number
		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
		enterphoneNumber.sendKeys("8920689888");

		// Click Send OTP
		driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();

		// Enter OTP
		driver.findElement(By.xpath("//input[@name='otp']")).sendKeys("1234");
		Thread.sleep(4000);

		// Click Verify OTP
		WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']"));
		Thread.sleep(2000);
		clickOnSendOtp.click();

		// Wait for navigation or home screen to load fully
		Thread.sleep(3000); // Adjust depending on speed or replace with proper wait

		
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)");
		
		// Wait until the "Top 10 in India" link is present and clickable
		try {
			WebElement top10Link = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href=\"/View/home/top-10-in-india/103\"]")));
			top10Link.click();
		} catch (TimeoutException e) {
			System.out.println("Top 10 link not clickable: " + e.getMessage());

		}

		// Now check if the view-list div is visible
		WebElement checkDataVisible = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'view-list')]")));
		boolean check = checkDataVisible.isDisplayed();

		System.out.println("Is Top 10 content visible: " + check);
		Thread.sleep(2000);
	}


}
