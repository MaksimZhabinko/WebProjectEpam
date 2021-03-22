package edu.epam.course.command;

import java.util.Set;

public enum PageAccessType {
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
            CommandType.ENROLL_COURSE
    )),
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
            CommandType.UNBLOCK_USER
    ));

    private final Set<CommandType> commands;

    PageAccessType(Set<CommandType> commandNames) {
        this.commands = commandNames;
    }

    public Set<CommandType> getCommands() {
        return commands;
    }
}