package ru.rofleksey.osgi.step2.provider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class HelloWorldActivator implements BundleActivator {
    private ServiceRegistration reg;

    @Override
    public void start(BundleContext context) throws Exception {
        reg = context.registerService(HelloWorldInterface.class, new HelloWorld(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        reg.unregister();
    }
}
