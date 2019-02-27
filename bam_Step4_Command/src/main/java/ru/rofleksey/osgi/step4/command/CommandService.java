package ru.rofleksey.osgi.step4.command;

import org.apache.felix.service.command.CommandProcessor;
import org.osgi.service.component.annotations.Component;

@Component(
        service = CommandService.class,
        immediate = true,
        property = {
                CommandProcessor.COMMAND_SCOPE + ":String=practice",
                CommandProcessor.COMMAND_FUNCTION + ":String=hello",
        }
)
public class CommandService {
    public void hello(String whom) {
        System.out.println("Hello, " + whom);
    }
}
