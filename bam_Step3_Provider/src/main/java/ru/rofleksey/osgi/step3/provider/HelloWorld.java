package ru.rofleksey.osgi.step3.provider;

import org.osgi.service.component.annotations.Component;

@Component(
        service = HelloWorldService.class,
        immediate = true
)
public class HelloWorld implements HelloWorldService {
    @Override
    public void sayHello() {
        System.out.println("Hello OSGi World!");
    }
}
