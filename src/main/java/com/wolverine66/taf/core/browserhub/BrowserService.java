package com.wolverine66.taf.core.browserhub;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class BrowserService {
	
	private static ThreadLocal<WebDriver> defaultDriver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<List<WebDriver>> driverList = new ThreadLocal<List<WebDriver>>();
	private static List<WebDriver> drivers = new CopyOnWriteArrayList<WebDriver>();

	public static WebDriver getDriver() {
		if(defaultDriver.get() == null) {
			getNewDriver();
		}
		return defaultDriver.get();
	}
	
	public static synchronized WebDriver getNewDriver() {
		WebDriver driver = new DriverCreator().instantiateDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		defaultDriver.set(driver);
		drivers.add(driver);
		List<WebDriver> localDriver = (driverList.get() == null) ? new ArrayList<WebDriver>() : driverList.get();
		localDriver.add(driver);
		driverList.set(localDriver);
		return driver;
	}
	
	public static void setDefaultDriver(WebDriver driver) {
		defaultDriver.set(driver);
	}
	
	public static WebDriver reOpenDefaultDriver() {
		closeDefaultDriver();
		getDriver();
		return defaultDriver.get();
	}

	private static void closeDefaultDriver() {
		WebDriver driver = defaultDriver.get();
		int totalActiveBrowsersBeforeClosing = drivers.size();
		try
		{
			closeDriver(driver);
		}
		finally
		{
			int totalActiveBrowsersAfterClosing = drivers.size();
			if(!driverList.get().isEmpty() && totalActiveBrowsersBeforeClosing > totalActiveBrowsersAfterClosing) {
				int previousDriver = driverList.get().size() -1;
				defaultDriver.set(driverList.get().get(previousDriver));
			}
			else {
				defaultDriver.remove();
			}
		}
		
	}

	private static void closeDriver(WebDriver driver) {
		try
		{
			if(driver!=null) {
				driver.close();
				driver.quit();
			}
			if(driverList.get()!=null) {
				driverList.get().remove(driver);
			}
		}
		catch(RuntimeException e) {
			if(e.getMessage().contains("Error communicating with the remote browser, It may have died"));
			{
				System.out.println("Warn : "+e.getMessage());
			}
			System.out.println("Error : "+e.getMessage());
		}
	}
	
	public static void closeAllOpenedBrowsers() {
		for(WebDriver driver : drivers) {
			closeDriver(driver);
		}
		defaultDriver.remove();
	}
	
	public static void closeAllOpenedBrowsersInCurrentThread() {
		List<WebDriver> driversList = new ArrayList<WebDriver>(driverList.get());
		for(WebDriver driver : driversList) {
			closeDriver(driver);
		}
		defaultDriver.remove();
	}
}
