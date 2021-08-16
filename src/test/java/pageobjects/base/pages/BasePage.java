package pageobjects.base.pages;

import java.lang.reflect.Constructor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wolverine66.taf.core.browserhub.DriverUtils;

public class BasePage {

	protected WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public <T extends BasePage> T instantiatePage(DriverUtils driver, Class<T> pageClass) {
		try {
			Constructor<T> construct = pageClass.getConstructor(WebDriver.class, BasePage.class);
			construct.setAccessible(true);
			return construct.newInstance(driver, pageClass);
		} catch (Exception e) {
			throw new RuntimeException("Unable to instanitiate the Page object : " + e.getMessage());
		}
	}
	
	public boolean waitUntilPageToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		// wait for jQuery to load
	    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	      public Boolean apply(WebDriver driver) {
	    	  try {
	    	        return (Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery()!=null");
	    	    } catch (Exception e) {
	    	        return true;
	    	    }
	      }
	    };

	    // wait for Javascript to load
	    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	      public Boolean apply(WebDriver driver) {
	        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"); 
	      }
	    };

	  return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
	
	public void waitUntilElementToDisappear(By applyButtonBy) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(applyButtonBy));
	}
}
