package stepdefs;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.*;
import testcode.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class MobileStepDefs extends BaseTest {  // Inherit BaseTest

    @Given("I open the app and select the first item")
    public void openAppAndSelectFirstItem() {
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized. Ensure BaseTest.setUp() runs first.");
        }

        WebElement firstItem = driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"store item\"])[1]/android.view.ViewGroup[1]/android.widget.ImageView"));
        firstItem.click();
    }

    @When("I add the item to the cart")
    public void addItemToCart() {
    	
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Add To Cart\"));"));
		

        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"counter plus button\"]/android.widget.ImageView")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Add To Cart\"]")).click();
    }

    @And("I proceed to checkout")
    public void proceedToCheckout() {
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.ImageView")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Proceed To Checkout\"]")).click();
    }

    @And("I log in with username {string} and password {string}")
    public void login(String username, String password) {
        driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Username input field\"]")).sendKeys(username);
        driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Password input field\"]")).sendKeys(password);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Login button\"]")).click();
    }

    @And("I enter shipping details")
    public void enterShippingDetails() {
        driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Full Name* input field\"]")).sendKeys("My Name");
        driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Address Line 1* input field\"]")).sendKeys("Street 123");

    	
		driver.findElement(AppiumBy.androidUIAutomator(
		         "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"City*\").instance(0))")).click();
		
        driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"City* input field\"]")).sendKeys("New York");
        driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Zip Code* input field\"]")).sendKeys("10001");
        driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Country* input field\"]")).sendKeys("United States");
    }

    @And("I proceed to payment")
    public void proceedToPayment() {
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"To Payment\"]")).click();
    }

    @Then("I should see the payment and review order screens")
    public void verifyPaymentAndReviewOrderScreens() {
        String expectedPaymentText = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Enter a payment method\"]")).getText();
        Assert.assertEquals(expectedPaymentText, "Enter a payment method");

        String expectedReviewOrderText = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Review Order\"]")).getText();
        Assert.assertEquals(expectedReviewOrderText, "Review Order");

        System.out.println("Payment and Review Order screens verified!");
    }
}
