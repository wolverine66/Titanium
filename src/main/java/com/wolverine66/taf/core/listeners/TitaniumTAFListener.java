package com.wolverine66.taf.core.listeners;

import org.testng.IConfigurationListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.wolverine66.taf.core.services.ScreenshotService;

public class TitaniumTAFListener implements ITestListener, IConfigurationListener, ISuiteListener {

	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
	}

	public void onTestFailure(ITestResult result) {
		ScreenshotService.takeFullPageScreenshot(result.getName());
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}

	public void onConfigurationSuccess(ITestResult result) {
	}

	public void onConfigurationFailure(ITestResult result) {
		ScreenshotService.takeFullPageScreenshot(result.getName());
	}

	public void onConfigurationSkip(ITestResult result) {
		ScreenshotService.takeFullPageScreenshot(result.getName());
	}

	public void onStart(ISuite suite) {
	}

	public void onFinish(ISuite suite) {	
	}
}
