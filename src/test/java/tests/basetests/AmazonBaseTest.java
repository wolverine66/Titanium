package tests.basetests;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;

@Guice(modules = AmazonModules.class)
public class AmazonBaseTest {

	@BeforeSuite
	public void instantiateBrowser() {
		
	}
}
