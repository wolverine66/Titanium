package tests.basetests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;

import com.google.inject.Inject;
import com.wolverine66.taf.core.browserhub.BrowserService;

import enums.ApplicationsUnderTestLinks;
import enums.Protocols;
import servicesinterfaces.amazon.INavigationService;

@Guice(modules = AmazonModules.class)
public class AmazonBaseTest {

	@Inject
	INavigationService loginService;
	
	@BeforeSuite
	public void instantiateBrowser() {
		loginService.navigateToApplication(ApplicationsUnderTestLinks.AMAZON_INDIA, Protocols.HTTPS);
	}
	
	@AfterSuite
	public void tearDown() {
		BrowserService.closeAllOpenedBrowsersInCurrentThread();
	}
}
