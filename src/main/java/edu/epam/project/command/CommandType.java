package edu.epam.project.command;

import edu.epam.project.command.impl.ChangeLanguageCommand;
import edu.epam.project.command.impl.PageCommand;
import edu.epam.project.command.impl.SignInCommand;
import edu.epam.project.command.impl.SignUpCommand;
import edu.epam.project.service.impl.UserServiceImpl;

public enum CommandType {
    SIGN_IN_PAGE(new PageCommand(PagePath.SIGN_IN)),
    SIGN_IN(new SignInCommand(new UserServiceImpl())),
    SIGN_UP_PAGE(new PageCommand(PagePath.SIGN_UP)),
    SIGN_UP(new SignUpCommand(new UserServiceImpl())),
    CHANGE_LANGUAGE(new ChangeLanguageCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
