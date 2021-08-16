package pageobjects.amazon.components.headers;

import org.openqa.selenium.WebDriver;

import pageobjects.amazon.components.AmazonBaseComponent;

public class MainHeader extends AmazonBaseComponent{
	
	public LogoComponent logoComponent;
	public LocationComponent locationComponent;

	public MainHeader(WebDriver driver) {
		super(driver);
		this.logoComponent 		= new LogoComponent(driver);
		this.locationComponent 	= new LocationComponent(driver);
	}
}
