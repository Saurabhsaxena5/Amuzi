package Amuzi;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonTestCase extends ViewAllTestCase {

	@Test
	public void verifyCurrentUrlTest() {
		String url = driver.getCurrentUrl();

		String expected = "https://amuzi.in/";
		Assert.assertEquals(url, expected);

		if (url.equals(expected)) {
			System.out.println("True ");
		} else {
			System.out.println("url mismatch");
		}
	}

	@Test
	public void verifyurl() {

		String actualurl = driver.getCurrentUrl();
		String Expectedurl = "https://amuzi.in/";
		if (actualurl.equals(Expectedurl)) {
			System.out.println("passed");
		} else {
			System.out.println("Mismatch url");
			Assert.assertEquals(actualurl, Expectedurl, "Passed");
		}

	}

	@Test
	public void countTotalVideosWithNames() {
		try {
			// Locate all video containers
			List<WebElement> videoElements = wait
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='custom-div']")));

			int count = videoElements.size();
			System.out.println("Total number of videos: " + count);

		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to count or list video names.");
		}
	}

	@Test
	public void checkTagname() {

		String actual = driver.getTitle();
		String expected = "Amuzi Sports";

		if (actual.equals(expected)) {
			System.out.println("verify the url");
		} else {
			System.out.println("Mismatchurl");
		}

	}

	@Test
	public void Banner_is_visible() throws InterruptedException {

		try {
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

			WebElement bannerIsvisible = driver
					.findElement(By.xpath("//img[@class=\"d-block w-100 mscreen image-gradient\"]"));
			if (bannerIsvisible.isDisplayed()) {
				System.out.println("Banner is visible");
			} else {
				System.out.println("Banner is not visible");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	@Test
	public void filterButtonClickTest() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('.dropdown-menu').scrollBy(0,100)");
		WebElement button = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary mt-2']")));
		button.click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://amuzi.in//LanguageData/26", "URL mismatch");
	}

	@Test
	public void LanguageButton() throws InterruptedException {

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

		// WebElement dropdown =
		// driver.findElement(By.xpath("//div[@class='dropdown'][1]"));
		// dropdown.click();
		Thread.sleep(4000);
		WebElement ClickonfilterButton = driver.findElement(By.xpath("(//img[@class='Movieslogo'])[4]"));
		ClickonfilterButton.click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"card\"])[4]"))).click();

		Thread.sleep(6000);

		String url = driver.getCurrentUrl();
		System.out.println(url);
	}

	@Test
	public void Validate_No_Data_message_is_visible() throws InterruptedException {

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

		driver.findElement(By.xpath("//a[@href='/search']")).click();
		Thread.sleep(3000);
		WebElement searchBox = driver.findElement(By.xpath("//input[@type='text']"));
		searchBox.sendKeys("Hey only put for testing purpose", Keys.ENTER);
		Thread.sleep(3000);

		String message = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[normalize-space()='No data available']")))
				.getText();
		System.out.println(message);
	}

	@Test
	public void checkAlllanguageIsvisible() {
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class=\"Movieslogo\"])[4]"))).click();
	    List<WebElement> listOfLanguages = driver.findElements(By.xpath("//div[@class='card']"));
	    
	    System.out.println("Total languages found: " + listOfLanguages.size());

	    for (int i = 0; i < listOfLanguages.size(); i++) {
	        WebElement languageCard = listOfLanguages.get(i);
	        String languageName = languageCard.getText(); // or use .findElement(...) if text is inside a child
	        System.out.println("Language " + (i + 1) + ": " + languageName);
	    }
	}

	@Test
	public void checkNoofBannerpresendInHomepage() throws InterruptedException {
		try {
			Thread.sleep(4000);

			List<WebElement> banners = driver.findElements(By.xpath("//img[@class='d-block w-100 mscreen']"));
			int totalBanners = banners.size();
			System.out.println(totalBanners);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
