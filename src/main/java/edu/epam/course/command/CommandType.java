package edu.epam.course.command;

import edu.epam.course.command.impl.*;
import edu.epam.course.model.service.impl.AboutUsServiceImpl;
import edu.epam.course.model.service.impl.CourseDetailsServiceImpl;
import edu.epam.course.model.service.impl.CourseServiceImpl;
import edu.epam.course.model.service.impl.LectureServiceImpl;
import edu.epam.course.model.service.impl.ReviewServiceImpl;
import edu.epam.course.model.service.impl.TeacherServiceImpl;
import edu.epam.course.model.service.impl.UserServiceImpl;

/**
 * The enum Command type.
 */
public enum CommandType {
    /**
     * The Sign in page.
     */
    SIGN_IN_PAGE(new PageCommand(PagePath.SIGN_IN.getDirectUrl())),
    /**
     * The Sign in.
     */
    SIGN_IN(new SignInCommand(new UserServiceImpl())),
    /**
     * The Sign up page.
     */
    SIGN_UP_PAGE(new PageCommand(PagePath.SIGN_UP.getDirectUrl())),
    /**
     * The Sign up.
     */
    SIGN_UP(new SignUpCommand(new UserServiceImpl())),
    /**
     * The Sign out.
     */
    SIGN_OUT(new SignOutCommand()),
    /**
     * The Change language.
     */
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    /**
     * The Main page.
     */
    MAIN_PAGE(new OpenMainCommand(new CourseServiceImpl())),
    /**
     * The Forgot password page.
     */
    FORGOT_PASSWORD_PAGE(new PageCommand(PagePath.FORGOT_PASSWORD.getDirectUrl())),
    /**
     * The Forgot password.
     */
    FORGOT_PASSWORD(new ForgotPasswordCommand(new UserServiceImpl())),
    /**
     * The Review page.
     */
    REVIEW_PAGE(new OpenReviewsCommand(new ReviewServiceImpl())),
    /**
     * The Review delete.
     */
    REVIEW_DELETE(new ReviewDeleteCommand(new ReviewServiceImpl())),
    /**
     * The Review add.
     */
    REVIEW_ADD(new ReviewAddCommand(new ReviewServiceImpl())),
    /**
     * The Lecture page.
     */
    LECTURE_PAGE(new OpenLectureCommand(new LectureServiceImpl(), new CourseDetailsServiceImpl(),
            new CourseServiceImpl())),
    /**
     * The Balance replenishment page.
     */
    BALANCE_REPLENISHMENT_PAGE(new PageCommand(PagePath.BALANCE_REPLENISHMENT.getDirectUrl())),
    /**
     * The Balance replenishment.
     */
    BALANCE_REPLENISHMENT(new BalanceReplenishmentCommand(new UserServiceImpl())),
    /**
     * The Course add.
     */
    COURSE_ADD(new CourseAddCommand(new CourseServiceImpl())),
    /**
     * The Course delete.
     */
    COURSE_DELETE(new CourseDeleteCommand(new CourseServiceImpl())),
    /**
     * The About us page.
     */
    ABOUT_US_PAGE(new OpenAboutUsCommand(new AboutUsServiceImpl())),
    /**
     * The About us update.
     */
    ABOUT_US_UPDATE(new AboutUsUpdateCommand(new AboutUsServiceImpl())),
    /**
     * The Lecture add.
     */
    LECTURE_ADD(new LectureAddCommand(new LectureServiceImpl(), new CourseServiceImpl())),
    /**
     * The Lecture delete.
     */
    LECTURE_DELETE(new LectureDeleteCommand(new LectureServiceImpl(), new CourseServiceImpl())),
    /**
     * The Lecture update.
     */
    LECTURE_UPDATE(new LectureUpdateCommand(new LectureServiceImpl(), new CourseServiceImpl())),
    /**
     * The Personal area page.
     */
    PERSONAL_AREA_PAGE(new OpenPersonalAreaCommand(new CourseServiceImpl())),
    /**
     * The Show all users.
     */
    SHOW_ALL_USERS(new ShowAllUsersCommand(new UserServiceImpl())),
    /**
     * The Enroll course.
     */
    ENROLL_COURSE(new EnrollCourseCommand(new UserServiceImpl(), new CourseDetailsServiceImpl())),
    /**
     * The Upload file.
     */
    UPLOAD_FILE(new UploadFileCommand(new UserServiceImpl())),
    /**
     * The Block user.
     */
    BLOCK_USER(new BlockUserCommand(new UserServiceImpl())),
    /**
     * The Unblock user.
     */
    UNBLOCK_USER(new UnblockUserCommand(new UserServiceImpl())),
    /**
     * The Hide all users.
     */
    HIDE_ALL_USERS(new HideAllUsersCommand()),
    /**
     * The Show all teachers.
     */
    SHOW_ALL_TEACHERS(new ShowAllTeachersCommand(new TeacherServiceImpl())),
    /**
     * The Hide all teachers.
     */
    HIDE_ALL_TEACHERS(new HideAllTeachersCommand()),
    /**
     * The Teacher add.
     */
    TEACHER_ADD(new TeacherAddCommand(new TeacherServiceImpl())),
    /**
     * The Teacher delete.
     */
    TEACHER_DELETE(new TeacherDeleteCommand(new TeacherServiceImpl())),
    /**
     * The Upload file teacher.
     */
    UPLOAD_FILE_TEACHER(new UploadFileTeacherCommand(new TeacherServiceImpl())),
    /**
     * The Add course details.
     */
    ADD_COURSE_DETAILS(new CourseDetailsAddCommand(new CourseServiceImpl(), new CourseDetailsServiceImpl(),
            new TeacherServiceImpl())),
    /**
     * The Update description.
     */
    UPDATE_DESCRIPTION(new CourseDetailsDescriptionUpdateCommand(new CourseDetailsServiceImpl())),
    /**
     * The Update hours.
     */
    UPDATE_HOURS(new CourseDetailsHoursUpdateCommand(new CourseDetailsServiceImpl())),
    /**
     * The Update start of class.
     */
    UPDATE_START_OF_CLASS(new CourseDetailsStartOfClassUpdateCommand(new CourseDetailsServiceImpl())),
    /**
     * The Update cost.
     */
    UPDATE_COST(new CourseDetailsCostUpdateCommand(new CourseDetailsServiceImpl())),
    /**
     * The Course update.
     */
    COURSE_UPDATE(new CourseUpdateCommand(new CourseServiceImpl())),
    /**
     * The Update teacher.
     */
    UPDATE_TEACHER(new CourseDetailsTeacherUpdateCommand(new TeacherServiceImpl(), new CourseDetailsServiceImpl())),
    /**
     * The Update start end.
     */
    UPDATE_START_END(new CourseDetailsUpdateStartEndCommand(new CourseDetailsServiceImpl())),
    /**
     * The Update new course.
     */
    UPDATE_NEW_COURSE(new CourseDetailsUpdateNewCourseCommand(new CourseDetailsServiceImpl(), new LectureServiceImpl(),
            new CourseServiceImpl())),
    /**
     * The Update user name surname.
     */
    UPDATE_USER_NAME_SURNAME(new UpdateUserNameAndSurnameCommand(new UserServiceImpl())),
    /**
     * The Create admin.
     */
    CREATE_ADMIN(new CreateAdminCommand(new UserServiceImpl())),
    /**
     * The Update password.
     */
    UPDATE_PASSWORD(new UpdateUserPasswordCommand(new UserServiceImpl())),
    /**
     * The Show all users enrolled course.
     */
    SHOW_ALL_USERS_ENROLLED_COURSE(new ShowAllUsersEnrolledCourseCommand(new UserServiceImpl())),
    /**
     * The Hide all users enrolled course.
     */
    HIDE_ALL_USERS_ENROLLED_COURSE(new HideAllUsersEnrolledCourseCommand());


    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public Command getCommand() {
        return command;
    }

}
