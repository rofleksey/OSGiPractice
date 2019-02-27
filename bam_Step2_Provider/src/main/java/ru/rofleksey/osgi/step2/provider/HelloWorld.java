package ru.rofleksey.osgi.step2.provider;

public class HelloWorld implements HelloWorldInterface {
    @Override
    public void sayHello() {
        System.out.println("Hello OSGi World!");
    }
}
