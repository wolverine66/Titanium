package tests.basetests;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import services.amazonservices.HeaderService;
import services.amazonservices.NavigationService;
import servicesinterfaces.amazon.IHeaderService;
import servicesinterfaces.amazon.INavigationService;

public class AmazonModules extends AbstractModule{

	@Override
    protected void configure() {
        bind(INavigationService.class).to(NavigationService.class).in(Scopes.SINGLETON);
        bind(IHeaderService.class).to(HeaderService.class).in(Scopes.SINGLETON);
    }
}
