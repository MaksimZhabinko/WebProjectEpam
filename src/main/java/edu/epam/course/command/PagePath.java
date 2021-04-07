package edu.epam.course.command;

/**
 * The enum Page path.
 */
public enum  PagePath {
    /**
     * Index page path.
     */
    INDEX("index.jsp", ""),
    /**
     * Main page path.
     */
    MAIN("pages/main.jsp", "controller?command=main_page"),
    /**
     * Sign in page path.
     */
    SIGN_IN("pages/signIn.jsp", "controller?command=sign_in_page"),
    /**
     * Sign up page path.
     */
    SIGN_UP("pages/signUp.jsp", "controller?command=sign_up_page"),
    /**
     * Forgot password page path.
     */
    FORGOT_PASSWORD("pages/forgotPassword.jsp", "controller?command=forgot_password_page"),
    /**
     * Review page path.
     */
    REVIEW("pages/review.jsp", "controller?command=review_page"),
    /**
     * Lecture page path.
     */
    LECTURE("pages/lecture.jsp", "controller?command=lecture_page&course_id="),
    /**
     * Balance replenishment page path.
     */
    BALANCE_REPLENISHMENT("pages/balanceReplenishment.jsp", "controller?command=balance_replenishment"),
    /**
     * About us page path.
     */
    ABOUT_US("pages/aboutUs.jsp", "controller?command=about_us_page"),
    /**
     * Personal area page path.
     */
    PERSONAL_AREA("pages/personalArea.jsp", "controller?command=personal_area_page"),
    /**
     * Error 500 page path.
     */
    ERROR_500("pages/errors/error500.jsp", ""),
    /**
     * Error 404 page path.
     */
    ERROR_404("pages/errors/error404.jsp", ""),
    /**
     * Show all teachers page path.
     */
    SHOW_ALL_TEACHERS("", "controller?command=show_all_teachers"),
    /**
     * Show all users page path.
     */
    SHOW_ALL_USERS("", "controller?command=show_all_users&page=");


    private final String directUrl;
    private final String servletPath;

    PagePath(String directUrl, String servletPath) {
        this.directUrl = directUrl;
        this.servletPath = servletPath;
    }

    /**
     * Gets direct url.
     *
     * @return the direct url
     */
    public String getDirectUrl() {
        return directUrl;
    }

    /**
     * Gets servlet path.
     *
     * @return the servlet path
     */
    public String getServletPath() {
        return servletPath;
    }
}
