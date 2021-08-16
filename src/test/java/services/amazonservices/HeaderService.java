package services.amazonservices;

import pageobjects.amazon.components.headers.LocationComponent;
import pageobjects.amazon.dialogs.headers.LocationDialog;
import pageobjects.amazon.pages.HomePage;
import services.BaseService;
import servicesinterfaces.amazon.IHeaderService;

public class HeaderService extends BaseService implements IHeaderService{

	public boolean isLogoDisplayed() {
		boolean isDisplayed = instantiatePage(HomePage.class).header.logoComponent.isLogoDisplayed();
		logger.info("Is Logo displayed : " + isDisplayed);
		return isDisplayed;
	}
	
	public boolean isLocationDisplayed() {
		boolean isDisplayed = instantiatePage(HomePage.class).header.locationComponent.isLocationDisplayed();
		logger.info("Is Location displayed : " + isDisplayed);
		return isDisplayed;
	}
	
	public String getLocationText() {
		String text = instantiatePage(HomePage.class).header.locationComponent.getLocation();
		logger.info("Location displayed : " + text);
		return text;
	}
	
	public void selectLocationByPincode(String pincode) {
		LocationComponent locationComponent = instantiatePage(HomePage.class).header.locationComponent;
		LocationDialog locationDialog = locationComponent.clickLocationLink();
		locationDialog.enterPincode(pincode);
		locationApply();
	}
	
	public void locationApply() {
		LocationDialog locationDialog = instantiatePage(HomePage.class).header.locationComponent.locationDialog;
		locationDialog.clickApply();
	}
}
