package Amuzi;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Footer  extends PlayerTestCase{
	
	@Test
	public void playstore() throws InterruptedException {
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // Login
	    driver.findElement(By.xpath("//a[@href='/login']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Mobile Number']"))).sendKeys("8920689888");
	    driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='otp']"))).sendKeys("1234");
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Verify OTP']"))).click();

	    // Scroll to bottom properly
	    Thread.sleep(5000);
	    ((JavascriptExecutor) driver).executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});");
	    Thread.sleep(3000); // Give time for content to load

	    // Click Play Store (or App Store) button
	    WebElement storeLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='icon']//a[2]")));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", storeLink);
	    Thread.sleep(1000);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", storeLink);

	    // Switch to new tab
	    String mainWindow = driver.getWindowHandle();
	    Set<String> windows = driver.getWindowHandles();
	    for (int i = 0; i < 5; i++) {
	        if (windows.size() > 1) break;
	        Thread.sleep(1000);
	        windows = driver.getWindowHandles();
	    }

	    for (String win : windows) {
	        if (!win.equals(mainWindow)) {
	            driver.switchTo().window(win);
	            break;
	        }
	    }

	    Thread.sleep(4000);
	    String newUrl = driver.getCurrentUrl();
	    System.out.println("🔗 Redirected URL: " + newUrl);

	    // Assertion accepts both Play Store and App Store
	    Assert.assertTrue(
	        newUrl.contains("play.google.com") || newUrl.contains("https://play.google.com/store/apps/details?id=com.ott.chulbull&hl=en_IN"),
	        " Play Store. Actual URL: " + newUrl
	    );

	    driver.close();
	    driver.switchTo().window(mainWindow);
	}
	
	@Test
	public void ApplestoreButtonTest() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // Login
	    driver.findElement(By.xpath("//a[@href='/login']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Mobile Number']"))).sendKeys("8920689888");
	    driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='otp']"))).sendKeys("1234");
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Verify OTP']"))).click();

	    // Scroll to bottom properly
	    Thread.sleep(5000);
	    ((JavascriptExecutor) driver).executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});");
	    Thread.sleep(3000); // Give time for content to load

	    // Click Play Store (or App Store) button
	    WebElement storeLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='icon']//a[1]")));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", storeLink);
	    Thread.sleep(1000);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", storeLink);

	    // Switch to new tab
	    String mainWindow = driver.getWindowHandle();
	    Set<String> windows = driver.getWindowHandles();
	    for (int i = 0; i < 5; i++) {
	        if (windows.size() > 1) break;
	        Thread.sleep(1000);
	        windows = driver.getWindowHandles();
	    }

	    for (String win : windows) {
	        if (!win.equals(mainWindow)) {
	            driver.switchTo().window(win);
	            break;
	        }
	    }

	    Thread.sleep(4000);
	    String newUrl = driver.getCurrentUrl();
	    System.out.println("🔗 Redirected URL: " + newUrl);

	    // Assertion accepts both Play Store and App Store
	    Assert.assertTrue(
	        newUrl.contains("play.google.com") || newUrl.contains("apps.apple.com"),
	        "❌ Not redirected to App Store or Play Store. Actual URL: " + newUrl
	    );

	    driver.close();
	    driver.switchTo().window(mainWindow);
	}
	
	@Test
	public void footerTermsAndConditionsTest() throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Login Flow
	    driver.findElement(By.xpath("//a[@href='/login']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Mobile Number']")))
	            .sendKeys("8920689888");

	    driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='otp']"))).sendKeys("1234");

	    WebElement clickOnSendOtp = wait
	            .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Verify OTP']")));
	    clickOnSendOtp.click();

	    // Wait until footer is present
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("footer")));

	    // Scroll to footer
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	    // Wait for "Terms and Conditions" link to be clickable
	    WebElement termsLink = wait.until(
	            ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Terms and Conditions']")));

	    // Scroll to the link and force click using JavaScript
	    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", termsLink);
	    Thread.sleep(500); // optional short pause to ensure scroll completes
	    js.executeScript("arguments[0].click();", termsLink);

	    // Wait a bit to ensure iframe or new tab is loaded
	    Thread.sleep(2000); // Optional: use explicit wait if a proper condition is available

	    // DEBUG: Print all iframe details
	    List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
	    System.out.println("Total iframes: " + iframes.size());
	    for (WebElement iframe : iframes) {
	        System.out.println("iframe ID: " + iframe.getAttribute("id"));
	        System.out.println("iframe SRC: " + iframe.getAttribute("src"));
	    }

	    if (!iframes.isEmpty()) {
	        // Try switching to the first available iframe
	        driver.switchTo().frame(iframes.get(0));

	        WebElement termsHeader = wait
	                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='container']")));
	        if (termsHeader.isDisplayed()) {
	            System.out.println("✅ Element is visible inside iframe.");
	        } else {
	            System.out.println("❌ Data not displayed inside iframe.");
	        }

	        driver.switchTo().defaultContent();
	    } else {
	        System.out.println("❌ No iframe found. Trying to handle new tab...");

	        String originalWindow = driver.getWindowHandle();
	        Set<String> allWindows = driver.getWindowHandles();
	        for (String window : allWindows) {
	            if (!window.equals(originalWindow)) {
	                driver.switchTo().window(window);
	                break;
	            }
	        }

	        WebElement termsHeader = wait
	                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='container']")));
	        if (termsHeader.isDisplayed()) {
	            System.out.println("✅ Element visible in new tab.");
	        } else {
	            System.out.println("❌ Data Not Displayed in new tab.");
	        }

	        driver.close();
	        driver.switchTo().window(originalWindow);
	    }
	}







}
