package servicesinterfaces.amazon;

import enums.ApplicationsUnderTestLinks;
import enums.Protocols;

public interface INavigationService {

	public void navigateToApplication(ApplicationsUnderTestLinks application, Protocols protocol);
}
