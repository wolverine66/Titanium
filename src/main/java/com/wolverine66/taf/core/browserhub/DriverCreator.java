package com.wolverine66.taf.core.browserhub;

import java.io.File;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public class DriverCreator {

	public WebDriver instantiateDriver() {
		WebDriver driver;
		setupDriverType();
		String driverExecutablePath = WebDriverManager.getInstance(findDriverManagerType()).getDownloadedDriverPath();
		driver = new DriverUtils(BrowserSettings.getBrowserSettings().getBrowser(), new File(driverExecutablePath));
		return driver;
	}
	
	private void setupDriverType() {
		String driverPath = String.format("%s/Downloads/webdriver", System.getProperty("user.home"));
		System.out.println(driverPath);
		try 
		{
			WebDriverManager.getInstance(findDriverManagerType()).cachePath(driverPath).forceDownload().setup();
		}
		catch(Exception e) {
			throw new RuntimeException();
		}
	}
	
	private DriverManagerType findDriverManagerType() {
		Browser browser = BrowserSettings.getInstance().getBrowser();
		DriverManagerType driverManagerType = null;
		switch(browser)
		{
			case CHROME:
				driverManagerType = DriverManagerType.CHROME;
				break;
			case FIREFOX:
				driverManagerType = DriverManagerType.FIREFOX;
				break;
		}
		return driverManagerType;
	}
}
