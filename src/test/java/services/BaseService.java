package services;

import java.lang.reflect.Constructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.wolverine66.taf.core.browserhub.BrowserService;

public class BaseService {
	
	protected static final Logger logger = LogManager.getLogger();

	public <T> T instantiatePage(Class<T> pageClass) {
		try {
			Constructor<T> construct = pageClass.getConstructor(WebDriver.class);
			construct.setAccessible(true);
			return construct.newInstance(BrowserService.getDriver());
		} catch (Exception e) {
			throw new RuntimeException("Unable to instanitiate the Page object : " + e.getMessage());
		}
	}
}
