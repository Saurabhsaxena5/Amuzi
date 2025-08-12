package Amuzi;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class PlayerTestCase extends SearchVideoTest {

	@Test
	public void playVideoTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Step 1: Login
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		WebElement enterPhone = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Mobile Number']")));
		enterPhone.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();

		WebElement otpBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='otp']")));
		otpBox.sendKeys("1234");

		WebElement verifyOtp = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Verify OTP']")));
		verifyOtp.click();

		// Step 2: Search
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/search']"))).click();
		WebElement searchBox = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		searchBox.sendKeys("Athletics Goa", Keys.ENTER);

		WebElement videoThumb = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Athletics Goa']")));
		videoThumb.click();

		// Step 3: Watch Video
		WebElement watchVideo = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'sc-fUnMCh')]")));
		watchVideo.click();
		Thread.sleep(5000);

		// Step 4: Close popup if exists
		try {
			WebElement popup = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='cancelbtn']")));
			popup.click();
			System.out.println("Popup closed.");
		} catch (TimeoutException e) {
			System.out.println("No popup found.");
		}

		// Step 5: Detect player iframe
		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		System.out.println("Total iframes found: " + iframes.size());

		WebElement playerFrame = null;
		for (WebElement iframe : iframes) {
			String src = iframe.getAttribute("src");
			System.out.println("Iframe src: " + src);
			if (src != null && src.contains("Amuzi.in/player.html")) {
				playerFrame = iframe;
				break;
			}
		}

		if (playerFrame == null) {
			System.out.println("Player iframe not found.");
			return;
		}

		// Step 6: Switch to iframe
		try {
			driver.switchTo().frame(playerFrame);
			System.out.println("Switched to iframe: " + playerFrame.getAttribute("src"));
		} catch (Exception e) {
			System.out.println("Unable to switch to iframe. Possibly cross-origin.");
			return;
		}

		// Step 7: Wait for video or play control
		try {
			wait.until(driver1 -> ((JavascriptExecutor) driver1).executeScript(
					"return document.querySelector('video') != null || document.querySelector('.vjs-play-control') != null"));
			System.out.println("Video or play control found in iframe.");
		} catch (TimeoutException e) {
			System.out.println("No video or play control found inside iframe.");
			driver.switchTo().defaultContent();
			return;
		}

		// Step 8: Attempt to play the video via JS
		try {
			((JavascriptExecutor) driver).executeScript("var video = document.querySelector('video');" + "if (video) {"
					+ "  video.muted = true;" + "  video.play();" + "  console.log('HTML5 video playback started');"
					+ "} else {" + "  var playBtn = document.querySelector('.vjs-play-control, [aria-label=\"Play\"]');"
					+ "  if (playBtn) playBtn.click();" + "  console.log('Clicked custom play button');" + "}");
			System.out.println("Playback script executed.");
		} catch (Exception e) {
			System.out.println("Playback script failed: " + e.getMessage());
		}

		// Step 9: Switch back to main content
		driver.switchTo().defaultContent();
		System.out.println("Switched back to main content.");
	}
	
	
	@Test
	public void checkRelatedvideo() throws InterruptedException {
	    driver.findElement(By.xpath("//a[@href='/login']")).click();
	    Thread.sleep(2000);

	    WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
	    enterphoneNumber.sendKeys("8920689888");

	    driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();
	    driver.findElement(By.xpath("//input[@name='otp']")).sendKeys("1234");
	    Thread.sleep(4000);

	    WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
	    Thread.sleep(2000);
	    clickOnSendOtp.click();

	    driver.findElement(By.xpath("//a[@href='/search']")).click();
	    Thread.sleep(3000);

	    WebElement searchBox = driver.findElement(By.xpath("//input[@type='text']"));
	    searchBox.sendKeys("Athletics Goa");

	    // Wait until the image is present
	    WebElement ClickOnvideo = wait.until(ExpectedConditions.presenceOfElementLocated(
	        By.xpath("(//img[@alt='Athletics Goa'])[1]")
	    ));

	    // Scroll into view to avoid sticky headers
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", ClickOnvideo);

	    // Wait for overlays or loaders to disappear if present
	    try {
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".overlay, .loader")));
	    } catch (Exception e) {
	        System.out.println("No blocking overlay/loader found.");
	    }

	    // Try normal click, fallback to JS click if intercepted
	    try {
	        wait.until(ExpectedConditions.elementToBeClickable(ClickOnvideo)).click();
	        System.out.println("Clicked video normally.");
	    } catch (ElementClickInterceptedException e) {
	        System.out.println("Normal click intercepted. Trying JavaScript click...");
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ClickOnvideo);
	    }

	    Thread.sleep(10000);

	    JavascriptExecutor jse = (JavascriptExecutor) driver;
	    jse.executeScript("window.scrollBy(0,700)");
	    Thread.sleep(3000);

	    WebElement clickonvideo = wait
	            .until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='img-top'])[1]")));
	    clickonvideo.click();

	    WebElement watchVideo = wait
	            .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'sc-fUnMCh')]")));
	    watchVideo.click();
	    Thread.sleep(5000);

	    // Close popup if exists
	    try {
	        WebElement popup = wait
	                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='cancelbtn']")));
	        popup.click();
	        System.out.println("Popup closed.");
	    } catch (TimeoutException e) {
	        System.out.println("No popup found.");
	    }

	    // Detect player iframe
	    List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
	    System.out.println("Total iframes found: " + iframes.size());

	    WebElement playerFrame = null;
	    for (WebElement iframe : iframes) {
	        String src = iframe.getAttribute("src");
	        System.out.println("Iframe src: " + src);
	        if (src != null && src.contains("chull.tv/player.html")) {
	            playerFrame = iframe;
	            break;
	        }
	    }

	    if (playerFrame == null) {
	        System.out.println("Player iframe not found.");
	        return;
	    }

	    // Switch to iframe
	    try {
	        driver.switchTo().frame(playerFrame);
	        System.out.println("Switched to iframe: " + playerFrame.getAttribute("src"));
	    } catch (Exception e) {
	        System.out.println("Unable to switch to iframe. Possibly cross-origin.");
	        return;
	    }

	    // Wait for video or play control
	    try {
	        wait.until(driver1 -> ((JavascriptExecutor) driver1).executeScript(
	                "return document.querySelector('video') != null || document.querySelector('.vjs-play-control') != null"));
	        System.out.println("Video or play control found in iframe.");
	    } catch (TimeoutException e) {
	        System.out.println("No video or play control found inside iframe.");
	        driver.switchTo().defaultContent();
	        return;
	    }

	    // Attempt to play video via JS
	    try {
	        ((JavascriptExecutor) driver).executeScript(
	                "var video = document.querySelector('video');" +
	                "if (video) {" +
	                "  video.muted = true;" +
	                "  video.play();" +
	                "  console.log('HTML5 video playback started');" +
	                "} else {" +
	                "  var playBtn = document.querySelector('.vjs-play-control, [aria-label=\"Play\"]');" +
	                "  if (playBtn) playBtn.click();" +
	                "  console.log('Clicked custom play button');" +
	                "}"
	        );
	        System.out.println("Playback script executed.");
	    } catch (Exception e) {
	        System.out.println("Playback script failed: " + e.getMessage());
	    }

	    // Switch back to main content
	    driver.switchTo().defaultContent();
	    System.out.println("Switched back to main content.");
	}

}
