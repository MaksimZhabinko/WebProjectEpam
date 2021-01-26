package edu.epam.project.command;

import edu.epam.project.command.impl.InvalidCommand;

public class CommandProvider {
    public static Command defineCommand(String commandName) {
        Command command;
        try {
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            command = type.getCommand();
        } catch (IllegalArgumentException e) {
            command = new InvalidCommand();
        }
        return command;
    }
}
