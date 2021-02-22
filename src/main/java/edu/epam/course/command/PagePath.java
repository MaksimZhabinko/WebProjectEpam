package edu.epam.course.command;

public enum  PagePath {
    INDEX("index.jsp", ""),
    MAIN("pages/main.jsp", "controller?command=main_page"),
    SIGN_IN("pages/signIn.jsp", "controller?command=sign_in_page"),
    SIGN_UP("pages/signUp.jsp", "controller?command=sign_up_page"),
    FORGOT_PASSWORD("pages/forgotPassword.jsp", "controller?command=forgot_password_page"),
    REVIEW("pages/review.jsp", "controller?command=review_page"),
    LECTURE("pages/lecture.jsp", "controller?command=lecture_page&course_id="),
    BALANCE_REPLENISHMENT("pages/balanceReplenishment.jsp", "controller?command=balance_replenishment"),
    ABOUT_US("pages/aboutUs.jsp", "controller?command=about_us_page"),
    ERROR_500("pages/errors/error500.jsp", ""),
    ERROR_404("pages/errors/error404.jsp", "");


    private final String directUrl;
    private final String servletPath;

    PagePath(String directUrl, String servletPath) {
        this.directUrl = directUrl;
        this.servletPath = servletPath;
    }

    public String getDirectUrl() {
        return directUrl;
    }

    public String getServletPath() {
        return servletPath;
    }
}
