package pageobjects.amazon.pages;

import org.openqa.selenium.WebDriver;

import pageobjects.amazon.components.headers.MainHeader;

public class HomePage extends AmazonBasePage{

	public MainHeader header;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.header = new MainHeader(driver);
		waitUntilPageToLoad();
	}
}
