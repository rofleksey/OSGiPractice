package ru.rofleksey.osgi.step2.consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import ru.rofleksey.osgi.step2.provider.HelloWorldInterface;

public class ByeWorldActivator implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        ServiceReference<?> ref = context.getServiceReference(HelloWorldInterface.class);
        HelloWorldInterface hw = (HelloWorldInterface) context.getService(ref);
        hw.sayHello();
    }

    @Override
    public void stop(BundleContext context) throws Exception {

    }
}
