package pageobjects.amazon.components.headers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageobjects.amazon.components.AmazonBaseComponent;
import pageobjects.amazon.dialogs.headers.LocationDialog;

public class LocationComponent extends AmazonBaseComponent{

	protected final By locationComponentBy 	= By.id("nav-global-location-slot");
	protected final By locationLinkBy 		= By.id("nav-global-location-popover-link");
	protected final By selectAddressBy 		= By.id("glow-ingress-block");
	protected final By locationBy 			= By.id("glow-ingress-line2");
	
	public LocationDialog locationDialog;
	
	public LocationComponent(WebDriver driver) {
		super(driver);
		this.locationDialog = new LocationDialog(driver);
	}

	public String getSelectAddress() {
		return driver.findElement(selectAddressBy).getText().trim();
	}
	
	public boolean isLocationDisplayed() {
		return driver.findElement(locationComponentBy).isDisplayed();
	}
	
	public LocationDialog clickLocationLink() {
		driver.findElement(locationLinkBy).click();
		waitUntilPageToLoad();
		return locationDialog;
	}
	
	public String getLocation() {
		return driver.findElement(locationBy).getText().trim();
	}
}
