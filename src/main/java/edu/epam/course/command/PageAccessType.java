package edu.epam.course.command;

import java.util.Set;

/**
 * The enum Page access type.
 */
public enum PageAccessType {
    /**
     * Guest page access type.
     */
    GUEST(Set.of(
            CommandType.SIGN_IN_PAGE,
            CommandType.SIGN_IN,
            CommandType.SIGN_UP_PAGE,
            CommandType.SIGN_UP,
            CommandType.MAIN_PAGE,
            CommandType.CHANGE_LANGUAGE,
            CommandType.FORGOT_PASSWORD_PAGE,
            CommandType.FORGOT_PASSWORD,
            CommandType.REVIEW_PAGE,
            CommandType.LECTURE_PAGE,
            CommandType.ABOUT_US_PAGE,
            CommandType.PERSONAL_AREA_PAGE
    )),
    /**
     * User page access type.
     */
    USER(Set.of(
            CommandType.MAIN_PAGE,
            CommandType.CHANGE_LANGUAGE,
            CommandType.SIGN_OUT,
            CommandType.REVIEW_PAGE,
            CommandType.REVIEW_DELETE,
            CommandType.REVIEW_ADD,
            CommandType.LECTURE_PAGE,
            CommandType.BALANCE_REPLENISHMENT_PAGE,
            CommandType.BALANCE_REPLENISHMENT,
            CommandType.ABOUT_US_PAGE,
            CommandType.PERSONAL_AREA_PAGE,
            CommandType.UPLOAD_FILE,
            CommandType.ENROLL_COURSE,
            CommandType.UPDATE_USER_NAME_SURNAME,
            CommandType.UPDATE_PASSWORD
    )),
    /**
     * Admin page access type.
     */
    ADMIN(Set.of(
            CommandType.MAIN_PAGE,
            CommandType.CHANGE_LANGUAGE,
            CommandType.SIGN_OUT,
            CommandType.REVIEW_PAGE,
            CommandType.REVIEW_DELETE,
            CommandType.LECTURE_PAGE,
            CommandType.COURSE_ADD,
            CommandType.COURSE_DELETE,
            CommandType.ABOUT_US_PAGE,
            CommandType.ABOUT_US_UPDATE,
            CommandType.LECTURE_ADD,
            CommandType.LECTURE_DELETE,
            CommandType.LECTURE_UPDATE,
            CommandType.PERSONAL_AREA_PAGE,
            CommandType.UPLOAD_FILE,
            CommandType.SHOW_ALL_USERS,
            CommandType.BLOCK_USER,
            CommandType.UNBLOCK_USER,
            CommandType.HIDE_ALL_USERS,
            CommandType.SHOW_ALL_TEACHERS,
            CommandType.HIDE_ALL_TEACHERS,
            CommandType.TEACHER_ADD,
            CommandType.TEACHER_DELETE,
            CommandType.UPLOAD_FILE_TEACHER,
            CommandType.ADD_COURSE_DETAILS,
            CommandType.UPDATE_DESCRIPTION,
            CommandType.UPDATE_HOURS,
            CommandType.UPDATE_START_OF_CLASS,
            CommandType.UPDATE_COST,
            CommandType.COURSE_UPDATE,
            CommandType.UPDATE_TEACHER,
            CommandType.UPDATE_START_END,
            CommandType.UPDATE_NEW_COURSE,
            CommandType.CREATE_ADMIN,
            CommandType.SHOW_ALL_USERS_ENROLLED_COURSE,
            CommandType.HIDE_ALL_USERS_ENROLLED_COURSE
    ));

    private final Set<CommandType> commands;

    PageAccessType(Set<CommandType> commandNames) {
        this.commands = commandNames;
    }

    /**
     * Gets commands.
     *
     * @return the commands
     */
    public Set<CommandType> getCommands() {
        return commands;
    }
}
