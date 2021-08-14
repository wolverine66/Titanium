package tests.basetests;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import services.amazonservices.LoginServices;
import servicesinterfaces.amazon.ILoginServices;

public class AmazonModules extends AbstractModule{

	@Override
    protected void configure() {
        bind(ILoginServices.class).to(LoginServices.class).in(Scopes.SINGLETON);
    }
}
