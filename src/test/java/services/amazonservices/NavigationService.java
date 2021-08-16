package services.amazonservices;

import org.openqa.selenium.WebDriver;

import com.wolverine66.taf.core.browserhub.BrowserService;

import enums.ApplicationsUnderTestLinks;
import enums.Protocols;
import services.BaseService;
import servicesinterfaces.amazon.INavigationService;

public class NavigationService extends BaseService implements INavigationService {

	public void navigateToApplication(ApplicationsUnderTestLinks application, Protocols protocol) {
		WebDriver driver = BrowserService.getNewDriver();
		driver.get(protocol.getProtocolLinkPrefix()+application.getLink());
		logger.info("Navigating to " + protocol.getProtocolLinkPrefix()+application.getLink());
	}
}
