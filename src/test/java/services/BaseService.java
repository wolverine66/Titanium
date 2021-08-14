package services;

import java.lang.reflect.Constructor;

import org.openqa.selenium.WebDriver;

import pageobjects.base.BasePage;

public class BaseService <P extends Object>{

//	public P instantiatePage(Class c) throws NoSuchMethodException, SecurityException{
//		Constructor<? extends Class> construct = c.getClass().getConstructor(WebDriver.class,BasePage.class);
//		construct.setAccessible(true);
//		return construct.newInstance(null);
//	}
}
