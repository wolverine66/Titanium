package com.wolverine66.taf.core.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.wolverine66.taf.core.browserhub.BrowserService;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenshotService {
	
	private static final Logger logger = LogManager.getLogger();
	
	private ScreenshotService() {
	    throw new IllegalStateException("This is ScreenshotService class and avoiding to create object");
	  }
	public static void takeScreenshot() {
		takeScreenshot(null,null);
	}
	
	public static void takeScreenshot(Path path) {
		takeScreenshot(null, path);
	}
	
	public static void takeScreenshot(String testName) {
		takeScreenshot(testName, null);
	}
	public static void takeScreenshot(String testName, Path path) {
		TakesScreenshot scrShot = ((TakesScreenshot)BrowserService.getDriver());
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);		
		try {
			FileUtils.copyFile(srcFile, getScreenshotFile(testName, path));
			logger.info("Screenshot captured");
		} catch (IOException e) {
			logger.warn("Unable to capture the screenshot");
		}
	}
	
	public static void takeFullPageScreenshot() {
		takeFullPageScreenshot(null,null);
	}
	
	public static void takeFullPageScreenshot(Path path) {
		takeFullPageScreenshot(null,path);
	}
	
	public static void takeFullPageScreenshot(String testName) {
		takeFullPageScreenshot(testName,null);
	}
	
	public static void takeFullPageScreenshot(String testName, Path path) {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(0000)).takeScreenshot(BrowserService.getDriver());
		try {
			ImageIO.write(screenshot.getImage(), "jpg", getScreenshotFile(testName, path));
			logger.info("Full page screenshot captured");
		} catch (IOException e) {
			logger.warn("Unable to capture the full page screenshot");
		}
	}
	
	private static File getScreenshotFile(String testName, Path path) {
		String destPath = ".\\src\\test\\resources\\screenshots\\";
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.jpg'").format(new Date());
		
		Optional<String> name = Optional.ofNullable(testName);
		Optional<Path> location = Optional.ofNullable(path);
		
		if(location.isEmpty()) {
			destPath = name.isEmpty()?destPath+"Screenshot_"+fileName:destPath+testName+"_"+fileName;
		}else {
			if(path.toString().endsWith(".jpg")) {
				destPath = path.toString();
			}else {
				destPath = name.isEmpty()?path.toString()+"//Screenshot_"+fileName:path.toString()+"//"+testName+"_"+fileName;
			}
		}
		return new File(destPath);
	}
}
