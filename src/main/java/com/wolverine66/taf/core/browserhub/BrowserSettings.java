package com.wolverine66.taf.core.browserhub;

public class BrowserSettings {
	
	private Browser browser;
	
	private static BrowserSettings browserSettings;

	public static BrowserSettings getBrowserSettings() {
		return browserSettings;
	}

	public static void setBrowserSettings(BrowserSettings browserSettings) {
		BrowserSettings.browserSettings = browserSettings;
	}

	public BrowserSettings() {
		this.browser = Browser.CHROME;
	}
	
	public static BrowserSettings getInstance() {
		if(browserSettings == null) {
			browserSettings = new BrowserSettings();
		}
		return browserSettings;
	}
	
	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}
}
