package pageobjects.amazon.dialogs.headers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageobjects.amazon.dialogs.AmazonBaseDialog;

public class LocationDialog extends AmazonBaseDialog{

	protected final By locationDialogHeadingBy 	= By.id("a-popover-header-2");
	protected final By enterPincodeBy 			= By.xpath("//input[@aria-label='or enter an Indian pincode']");
	protected final By applyButtonBy			= By.xpath("//div[@role='button']//span[text()='Apply']/ancestor::div[@role='button']");
	
	public LocationDialog(WebDriver driver) {
		super(driver);
	}

	public String getHeaderText() {
		return driver.findElement(locationDialogHeadingBy).getText().trim();
	}
	
	public void enterPincode(String pincode) {
		driver.findElement(enterPincodeBy).sendKeys(pincode);
	}
	
	public void clickApply() {
		driver.findElement(applyButtonBy).click();
		waitUntilPageToLoad();
		waitUntilElementToDisappear(applyButtonBy);
	}
}
