package com.wolverine66.taf.core.browserhub;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.remote.service.DriverService;

import com.wolverine66.taf.core.exceptions.TitaniumTAFRuntimeException;

public class DriverUtils {
	
	WebDriver driver;
	DriverService driverService;

	public WebDriver getDriver() {
		return driver;
	}

	public DriverUtils(Browser browser, File driverExecutableFile) {
		initializeDriver(browser, driverExecutableFile);
	}
	
	private void initializeDriver(Browser browser, File driverExecutableFile) {
		switch(browser) {
		case CHROME:
			initializeChrome(driverExecutableFile);
			break;
		case FIREFOX:
			initializeFirefox(driverExecutableFile);
		}
	}

	private void initializeFirefox(File driverExecutableFile) {
		if(driverExecutableFile != null) {
			try {
					driverService = new GeckoDriverService.Builder().usingDriverExecutable(driverExecutableFile).usingAnyFreePort().build();
					driverService.start();
					driver = new RemoteWebDriver(driverService.getUrl(), null);
			}
			catch(Exception e){
				throw new TitaniumTAFRuntimeException("Failed to instanitiate Firefox browser");
			}
		}
	}

	private void initializeChrome(File driverExecutableFile) {
		MutableCapabilities capabilities = new MutableCapabilities();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		chromeOptions.addArguments("disable-infobars");
		Map<String,Object> prefs = new LinkedHashMap<String, Object>();
		prefs.put("credentials_enable_service", Boolean.FALSE);
		prefs.put("profile.password_manager_enabled", Boolean.FALSE);
		chromeOptions.setExperimentalOption("prefs", prefs);
		chromeOptions.addArguments("--disable-extension");
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		if(driverExecutableFile != null) {
			try {
				int attempts = StringUtils.isEmpty(System.getProperty("chrome.start.attempts")) ? 1 : Integer.valueOf(System.getProperty("chrome.start.attempts"));			
				for(int attempt=0;attempt<attempts;attempt++) {
					driverService = new ChromeDriverService.Builder().usingDriverExecutable(driverExecutableFile).usingAnyFreePort().build();
					driver = new RemoteWebDriver(new DriverCommandExecutor(driverService), capabilities);
				}
			}
			catch(Exception e){
				throw new TitaniumTAFRuntimeException();
			}
		}		
	}
}
