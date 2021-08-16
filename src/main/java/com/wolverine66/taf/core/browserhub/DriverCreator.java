package com.wolverine66.taf.core.browserhub;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.wolverine66.taf.core.exceptions.TitaniumTAFRuntimeException;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public class DriverCreator {

	protected static final Logger logger = LogManager.getLogger();
	
	public WebDriver instantiateDriver() {
		WebDriver driver;
		setupDriverType();
		String driverExecutablePath = WebDriverManager.getInstance(findDriverManagerType()).getDownloadedDriverPath();
		driver = new DriverUtils(BrowserSettings.getBrowserSettings().getBrowser(), new File(driverExecutablePath)).getDriver();
		return driver;
	}
	
	private void setupDriverType() {
		String driverPath = String.format("%s/Downloads/webdriver", System.getProperty("user.home"));
		logger.info("Webdriver download path "+ driverPath);
		try 
		{
			WebDriverManager.getInstance(findDriverManagerType()).cachePath(driverPath).forceDownload().setup();
		}
		catch(Exception e) {
			throw new TitaniumTAFRuntimeException();
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
