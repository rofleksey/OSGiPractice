package ru.rofleksey.osgi.step3.consumer;

import org.osgi.service.component.annotations.*;
import ru.rofleksey.osgi.step3.provider.HelloWorldService;

@Component
public class ByeWorld {
    private HelloWorldService greeter;

    @Reference(
            service = HelloWorldService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetGreeter"
    )

    protected void setGreeter(HelloWorldService world) {
        greeter = world;
    }

    @Activate
    protected void onActivate() {
        greeter.sayHello();
    }

    protected void unsetGreeter(HelloWorldService world) {
        greeter = null;
    }
}
