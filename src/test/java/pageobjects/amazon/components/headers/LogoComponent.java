package pageobjects.amazon.components.headers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageobjects.amazon.components.AmazonBaseComponent;

public class LogoComponent extends AmazonBaseComponent{

	protected final By logoBy = By.id("nav-logo-sprites");
	
	public LogoComponent(WebDriver driver) {
		super(driver);
	}
	
	public boolean isLogoDisplayed() {
		return driver.findElement(logoBy).isDisplayed();
	}
	
	public void clickLogo() {
		driver.findElement(logoBy).click();
//		return instantiatePage(driver, HomePage.class);
	}
}
