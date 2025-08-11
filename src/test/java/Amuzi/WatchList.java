package Amuzi;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class WatchList extends Subscription {

	@Test
	public void watchlist() throws InterruptedException {

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

		driver.findElement(By.xpath("//a[@href='/search']")).click();
		Thread.sleep(3000);
		WebElement searchBox = driver.findElement(By.xpath("//input[@type='text']"));
		searchBox.sendKeys("Athletics Goa", Keys.ENTER);
		Thread.sleep(3000);

		WebElement clickonVideo = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Athletics Goa']")));
		clickonVideo.click();

		WebElement checkvideoIsalreadyinwatchlist = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root watchlist-container']")));
		if (checkvideoIsalreadyinwatchlist.isDisplayed()) {
			System.out.println("Video is already Added in the watchlist");
			WebElement clickonwatchlist = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='WatchList']")));
			clickonwatchlist.click();
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Watchlist']"))).click();

		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Watchlist']"))).click();

	}

}
