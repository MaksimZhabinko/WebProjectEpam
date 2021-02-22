package edu.epam.course.command;

import edu.epam.course.command.impl.*;
import edu.epam.course.model.service.impl.*;

public enum CommandType {
    SIGN_IN_PAGE(new PageCommand(PagePath.SIGN_IN.getDirectUrl())),
    SIGN_IN(new SignInCommand(new UserServiceImpl())),
    SIGN_UP_PAGE(new PageCommand(PagePath.SIGN_UP.getDirectUrl())),
    SIGN_UP(new SignUpCommand(new UserServiceImpl())),
    SIGN_OUT(new SignOutCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    MAIN_PAGE(new OpenMainCommand(new CourseServiceImpl())),
    FORGOT_PASSWORD_PAGE(new PageCommand(PagePath.FORGOT_PASSWORD.getDirectUrl())),
    FORGOT_PASSWORD(new ForgotPasswordCommand(new UserServiceImpl())),
    REVIEW_PAGE(new OpenReviewsCommand(new ReviewServiceImpl())),
    REVIEW_DELETE(new ReviewDeleteCommand(new ReviewServiceImpl())),
    REVIEW_ADD(new ReviewAddCommand(new ReviewServiceImpl())),
    LECTURE_PAGE(new OpenLectureCommand(new LectureServiceImpl(), new CourseDetailsServiceImpl())),
    BALANCE_REPLENISHMENT_PAGE(new PageCommand(PagePath.BALANCE_REPLENISHMENT.getDirectUrl())),
    BALANCE_REPLENISHMENT(new BalanceReplenishmentCommand(new UserServiceImpl())),
    COURSE_ADD(new CourseAddCommand(new CourseServiceImpl())),
    COURSE_DELETE(new CourseDeleteCommand(new CourseServiceImpl())),
    ABOUT_US_PAGE(new OpenAboutUsCommand(new AboutUsServiceImpl())),
    ABOUT_US_UPDATE(new AboutUsUpdateCommand(new AboutUsServiceImpl()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
