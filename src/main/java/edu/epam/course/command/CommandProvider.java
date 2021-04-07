package edu.epam.course.command;

import edu.epam.course.command.impl.InvalidCommand;

/**
 * The type Command provider.
 */
public class CommandProvider {
    /**
     * Define command command.
     *
     * @param commandName the command name
     * @return the command
     */
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
