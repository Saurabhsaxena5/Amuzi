package Amuzi;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class Subscription extends ViewAllTestCase {

	public void buyRentVideoTest() {

		driver.findElement(By.xpath("//div[@class='dropdown'][2]")).click();
		driver.findElement(By.xpath("//div[@class='dropdown-menu show']//a[1]")).click();
		driver.findElement(By.xpath("//div[@class='card movie-card']")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");

		WebElement watchNow = driver.findElement(By.xpath("//button[contains(@class,'goYXOV')]"));
		watchNow.click();
		// Add assertion if needed
	}

	@Test
	public void subscription() {

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

			WebElement subscriptionButtoncheck = driver.findElement(By.xpath("(//img[@class='Movieslogo'])[5]"));
			if (subscriptionButtoncheck.isDisplayed()) {
				System.out.println("Subscription button is visible");
			} else {
				System.out.println("Subscription Button is not visible");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void check_SubscriptionisVisible() {
		try {
			driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
			Thread.sleep(2000);

			WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
			enterphoneNumber.sendKeys("8920689888");

			driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

			driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(.)='Verify OTP']")))
					.click();
			Thread.sleep(2000);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[5]"))).click();
			Thread.sleep(4000);

			List<WebElement> buttons = driver.findElements(By.className("plan_button"));

			for (int i = 0; i < buttons.size(); i++) {
				WebElement button = buttons.get(i);
				System.out.println("Button " + i + " text: " + button.getText());

			}

		} catch (Exception e) {
			System.out.println((e.getMessage()));

		}
	}

	@Test
	public void checkUserBuySubscription() {
		try {
			driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
			Thread.sleep(2000);

			WebElement enterPhoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
			enterPhoneNumber.sendKeys("8920689888");

			driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

			driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(.)='Verify OTP']")))
					.click();
			Thread.sleep(2000);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[5]"))).click();
			Thread.sleep(4000);

			WebElement validateuseralreadybuy = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[@class=\"subscription_button btn btn-lg btn-block planBtn\"]")));

			if (validateuseralreadybuy.isDisplayed()) {
				System.out.println("User already purchase a subscription");
			} else {
				System.out.println("User not a subscription");
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void verifyPopupIsVisibleuserBuysubscription() {
		try {
			driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
			Thread.sleep(2000);

			WebElement enterPhoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
			enterPhoneNumber.sendKeys("8920689888");

			driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

			driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(.)='Verify OTP']")))
					.click();
			Thread.sleep(2000);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[5]"))).click();
			Thread.sleep(4000);

			WebElement validateuseralreadybuy = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[@class=\"subscription_button btn btn-lg btn-block planBtn\"]")));

			if (validateuseralreadybuy.isDisplayed()) {

				WebElement verify = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//button[@class=\"subscription_button btn btn-lg btn-block planBtn\"]")));
				verify.click();
				Alert popup = driver.switchTo().alert();
				Thread.sleep(5000);
				popup.accept();

				System.out.println(popup.getText());

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void checkcouponprice() {
		try {
			driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//button[normalize-space(.)=\"Login with Email\"]")).click();

			driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("rajnish.kumar@unvii.com");
			driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
			driver.findElement(By.xpath("//input[@class='otp-input form-control']")).sendKeys("1234");
			driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[5]"))).click();
			Thread.sleep(4000);

			WebElement clickonchooseplan = wait.until(ExpectedConditions.elementToBeClickable(
					(By.xpath("(//button[@class=\"subscription_button btn btn-lg btn-block planBtn\"])[1]"))));
			clickonchooseplan.click();

			Thread.sleep(4000);

			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"Enter coupon code...\"]")))
					.sendKeys("4OefxM");

			WebElement applyOncouponcode = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"apply-button\"]")));
			applyOncouponcode.click();
			Thread.sleep(4000);

			WebElement click = driver.findElement(By.xpath("//div[@class=\"text\"]"));
			System.out.println(click.getText());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void couponPercentage() {
		try {
			driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//button[normalize-space(.)=\"Login with Email\"]")).click();

			driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("rajnish.kumar@unvii.com");
			driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
			driver.findElement(By.xpath("//input[@class='otp-input form-control']")).sendKeys("1234");
			driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[5]"))).click();
			Thread.sleep(4000);

			WebElement clickonchooseplan = wait.until(ExpectedConditions.elementToBeClickable(
					(By.xpath("(//button[@class=\"subscription_button btn btn-lg btn-block planBtn\"])[1]"))));
			clickonchooseplan.click();

			Thread.sleep(4000);

			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"Enter coupon code...\"]")))
					.sendKeys("t7fnlx");

			WebElement applyOncouponcode = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"apply-button\"]")));
			applyOncouponcode.click();
			Thread.sleep(4000);

			WebElement click = driver.findElement(By.xpath("//div[@class=\"text\"]"));
			System.out.println(click.getText());
			String checkcouponcodeapply = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"error-message\"]")))
					.getText();
			String validate = "Coupon applied";
			if (checkcouponcodeapply.equals(validate)) {
				System.out.println("Applied coupon code");
			} else {
				System.out.println("Something went wrong");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void checkrazorpayopen() throws InterruptedException {
		try {
			// Login steps
			driver.findElement(By.xpath("//a[@href='/login']")).click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//button[normalize-space(.)='Login with Email']")).click();
			driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("rajnish.kumar@unvii.com");
			driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
			driver.findElement(By.xpath("//input[@class='otp-input form-control']")).sendKeys("1234");
			driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();

			// Select a movie
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[5]"))).click();
			Thread.sleep(4000);

			// Choose subscription plan
			WebElement clickOnChoosePlan = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("(//button[@class='subscription_button btn btn-lg btn-block planBtn'])[1]")));
			clickOnChoosePlan.click();
			Thread.sleep(4000);

			// Apply coupon
			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter coupon code...']")))
					.sendKeys("t7fnlx");

			WebElement applyOnCouponCode = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='apply-button']")));
			applyOnCouponCode.click();
			Thread.sleep(4000);

			// Confirm coupon application
			WebElement click = driver.findElement(By.xpath("//div[@class='text']"));
			System.out.println(click.getText());

			String checkCouponCodeApply = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='error-message']")))
					.getText();

			if ("Coupon applied".equals(checkCouponCodeApply)) {
				System.out.println("Applied coupon code");
			} else {
				System.out.println("Something went wrong");
			}

			// Click Razorpay payment button
			WebElement clickOnRazorpayButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='text']")));
			clickOnRazorpayButton.click();

			Thread.sleep(4000);

			// Switch to Razorpay iframe
			WebElement razorpayFrame = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe[src*='razorpay']")));
			driver.switchTo().frame(razorpayFrame);

			// Enter mobile number
			WebElement mobileInput = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Mobile number']")));
			mobileInput.clear();
			mobileInput.sendKeys("8920689888");
			Thread.sleep(5000);

			// Click continue (optional)
			WebElement continueBtn = driver.findElement(By.xpath("//button[normalize-space()='Continue']"));
			continueBtn.click();

			// Switch back to main content
			driver.switchTo().defaultContent();

			// Optional: Confirm Razorpay container is visible
			boolean check = driver.findElement(By.id("razorpay-checkout-v2-container")).isDisplayed();
			System.out.println("Razorpay modal displayed: " + check);

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	@Test
	public void insidecouponpagebannerisvisible() {

		try {
			driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//button[normalize-space(.)=\"Login with Email\"]")).click();

			driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("rajnish.kumar@unvii.com");
			driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
			driver.findElement(By.xpath("//input[@class='otp-input form-control']")).sendKeys("1234");
			driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[5]"))).click();
			Thread.sleep(4000);

			WebElement clickonchooseplan = wait.until(ExpectedConditions.elementToBeClickable(
					(By.xpath("(//button[@class=\"subscription_button btn btn-lg btn-block planBtn\"])[1]"))));
			clickonchooseplan.click();

			Thread.sleep(4000);
			List<WebElement> banners = driver.findElements(By.xpath("//img[@class='d-block w-100 mscreen']"));
			int totalBanners = banners.size();

			System.out.println("Total number of banners: " + totalBanners);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@Test
	public void clickcancelButtonOnrazorpay() {
	    try {
	        // Login steps
	        driver.findElement(By.xpath("//a[@href='/login']")).click();
	        Thread.sleep(2000);

	        driver.findElement(By.xpath("//button[normalize-space(.)='Login with Email']")).click();
	        driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("rajnish.kumar@unvii.com");
	        driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
	        driver.findElement(By.xpath("//input[@class='otp-input form-control']")).sendKeys("1234");
	        driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();

	        // Select a movie
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[5]"))).click();
	        Thread.sleep(4000);

	        // Choose subscription plan
	        WebElement clickOnChoosePlan = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("(//button[@class='subscription_button btn btn-lg btn-block planBtn'])[1]")));
	        clickOnChoosePlan.click();
	        Thread.sleep(4000);

	        // Apply coupon
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter coupon code...']")))
	                .sendKeys("t7fnlx");

	        WebElement applyOnCouponCode = wait
	                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='apply-button']")));
	        applyOnCouponCode.click();
	        Thread.sleep(4000);

	        // Confirm coupon application
	        WebElement click = driver.findElement(By.xpath("//div[@class='text']"));
	        System.out.println(click.getText());

	        String checkCouponCodeApply = wait
	                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='error-message']")))
	                .getText();

	        if ("Coupon applied".equals(checkCouponCodeApply)) {
	            System.out.println("Applied coupon code");
	        } else {
	            System.out.println("Something went wrong");
	        }

	        // Click Razorpay payment button
	        WebElement clickOnRazorpayButton = wait
	                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='text']")));
	        clickOnRazorpayButton.click();

	        Thread.sleep(4000);

	        // Switch to Razorpay iframe
	        WebElement razorpayFrame = wait.until(ExpectedConditions.presenceOfElementLocated(
	                By.cssSelector("iframe[src*='razorpay']")));
	        driver.switchTo().frame(razorpayFrame);

	        // Enter mobile number
	        WebElement mobileInput = wait.until(
	                ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Mobile number']")));
	        mobileInput.clear();
	        mobileInput.sendKeys("8920689888");
	        Thread.sleep(3000);

	        // Click Continue in Razorpay iframe
	        WebElement continueBtn = wait.until(
	                ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Continue']")));
	        continueBtn.click();

	        Thread.sleep(4000); // Optional wait after clicking Continue

	        // Wait for Close button and click it via JavaScript if needed
	        WebElement closeBtn = wait.until(
	                ExpectedConditions.elementToBeClickable(By.cssSelector("button[title*='Close']")));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);

	        // Optional: Switch back to main content
	        driver.switchTo().defaultContent();

	        System.out.println("Razorpay modal closed.");

	    } catch (Exception e) {
	        System.out.println("Exception: " + e.getMessage());
	    }
	}

}
