package com.wolverine66.taf.core.browserhub;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.remote.service.DriverService;

public class DriverUtils implements WebDriver {
	
	WebDriver driver;
	DriverService driverService;
	JavascriptExecutor js;

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
				throw new RuntimeException();
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
				throw new RuntimeException();
			}
		}
		
	}

	public void get(String url) {
		driver.get(url);		
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	public WebElement findElement(By by) {
		System.out.println("FindElement");
		return driver.findElement(by);
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	public Navigation navigate() {
		return driver.navigate();
	}

	public Options manage() {
		return driver.manage();
	}

	public void executeJse(String script) {
		js.executeScript(script);
	}
}
