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
    LECTURE_PAGE(new OpenLectureCommand(new LectureServiceImpl(), new CourseDetailsServiceImpl(), new CourseServiceImpl())),
    BALANCE_REPLENISHMENT_PAGE(new PageCommand(PagePath.BALANCE_REPLENISHMENT.getDirectUrl())),
    BALANCE_REPLENISHMENT(new BalanceReplenishmentCommand(new UserServiceImpl())),
    COURSE_ADD(new CourseAddCommand(new CourseServiceImpl())),
    COURSE_DELETE(new CourseDeleteCommand(new CourseServiceImpl())),
    ABOUT_US_PAGE(new OpenAboutUsCommand(new AboutUsServiceImpl())),
    ABOUT_US_UPDATE(new AboutUsUpdateCommand(new AboutUsServiceImpl())),
    LECTURE_ADD(new LectureAddCommand(new LectureServiceImpl())),
    LECTURE_DELETE(new LectureDeleteCommand(new LectureServiceImpl())),
    LECTURE_UPDATE(new LectureUpdateCommand(new LectureServiceImpl())),
    PERSONAL_AREA_PAGE(new OpenPersonalAreaCommand(new CourseServiceImpl())),
    SHOW_ALL_USERS(new ShowAllUsersCommand(new UserServiceImpl())),
    ENROLL_COURSE(new EnrollCourseCommand(new UserServiceImpl())),
    UPLOAD_FILE(new UploadFileCommand(new UserServiceImpl())),
    BLOCK_USER(new BlockUserCommand(new UserServiceImpl())),
    UNBLOCK_USER(new UnblockUserCommand(new UserServiceImpl()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
