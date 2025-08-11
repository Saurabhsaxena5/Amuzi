package Amuzi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class UpdateProfile extends LoginTestCase{
	
	@Test
	public void updateProfileTest() throws InterruptedException {
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
		Thread.sleep(4000);

		driver.findElement(By.xpath("//a[@href='/Profile']")).click();

		WebElement editBtn = driver.findElement(By.xpath("//button[@class=\"edit-button\"]"));
		editBtn.click();
		WebElement input = driver.findElement(By.xpath("//input[@class='input-name']"));
		input.clear();
		input.sendKeys("Hey this update message");
		driver.findElement(By.xpath("//button[@class='edit-button']")).click();
	}

}
