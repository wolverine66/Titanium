package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.inject.Inject;

import servicesinterfaces.amazon.IHeaderService;
import tests.basetests.AmazonBaseTest;

public class AmazonTests extends AmazonBaseTest{
	
	@Inject
	IHeaderService headerService;
	
	@Test
	public void NavigationUsingLinkTest() {
		Assert.assertTrue(headerService.isLogoDisplayed(), "Verify Logo is displayed in header");
	}
	
	@Test
	public void LocationChangeTest() {
		Assert.assertTrue(headerService.isLocationDisplayed(), "Verify Location is displayed in header");
		Assert.assertTrue(headerService.getLocationText().contains("Select your address"), "Verify default location in header");
		headerService.selectLocationByPincode("500082");
		Assert.assertTrue(headerService.getLocationText().contains("500082"), "Verify new location in header");
	}
}
