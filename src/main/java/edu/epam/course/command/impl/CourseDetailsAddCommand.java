package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.Teacher;
import edu.epam.course.model.service.CourseDetailsService;
import edu.epam.course.model.service.CourseService;
import edu.epam.course.model.service.TeacherService;
import edu.epam.course.util.IdUtil;
import edu.epam.course.validator.CourseDetailsValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The type Course details add command.
 */
public class CourseDetailsAddCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CourseDetailsAddCommand.class);
    private CourseService courseService;
    private CourseDetailsService courseDetailsService;
    private TeacherService teacherService;

    /**
     * Instantiates a new Course details add command.
     *
     * @param courseService        the course service
     * @param courseDetailsService the course details service
     * @param teacherService       the teacher service
     */
    public CourseDetailsAddCommand(CourseService courseService, CourseDetailsService courseDetailsService,
                                   TeacherService teacherService) {
        this.courseService = courseService;
        this.courseDetailsService = courseDetailsService;
        this.teacherService = teacherService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        String hours = request.getParameter(RequestParameter.HOURS);
        String startOfClass = request.getParameter(RequestParameter.START_OF_CLASS);
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        String startCourse = request.getParameter(RequestParameter.START_COURSE);
        String endCourse = request.getParameter(RequestParameter.END_COURSE);
        String cost = request.getParameter(RequestParameter.COST);
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!CourseDetailsValidator.isValidDescription(description)) {
                request.setAttribute(RequestAttribute.ERROR_DESCRIPTION_ADD, true);
                dataCorrect = false;
            }
            if (!CourseDetailsValidator.isValidHours(hours)) {
                request.setAttribute(RequestAttribute.ERROR_HOURS_ADD, true);
                dataCorrect = false;
            }
            if (!CourseDetailsValidator.isValidNameAndSurname(name, surname)) {
                request.setAttribute(RequestAttribute.ERROR_NAME_AND_SURNAME_ADD, true);
                dataCorrect = false;
            }
            if (!CourseDetailsValidator.isValidCost(cost)) {
                request.setAttribute(RequestAttribute.ERROR_COST_ADD, true);
                dataCorrect = false;
            }
            if (!CourseDetailsValidator.isValidStartOfClass(startOfClass)) {
                request.setAttribute(RequestAttribute.ERROR_START_OF_CLASS_ADD, true);
                dataCorrect = false;
            }
            if (!CourseDetailsValidator.isValidDate(startCourse, endCourse)) {
                request.setAttribute(RequestAttribute.ERROR_START_AND_END_COURSE_ADD, true);
                dataCorrect = false;
            }
            Long courseId = IdUtil.stringToLong(courseIdString);
            Optional<Course> courseOptional = courseService.findById(courseId);
            if (!courseOptional.isPresent()) {
                session.setAttribute(SessionAttribute.ERROR_COURSE_NOT_FOUND, true);
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.MAIN.getServletPath());
                return router;
            }
            Optional<Teacher> teacherOptional = teacherService.findByNameAndSurname(name, surname);
            if (!teacherOptional.isPresent()) {
                request.setAttribute(RequestAttribute.ERROR_TEACHER_NOT_FOUND_ADD, true);
                dataCorrect = false;
            }
            boolean isHaveDetails = courseDetailsService.isCourseHaveDetails(courseId);
            if (isHaveDetails) {
                request.setAttribute(RequestAttribute.ERROR_IS_HAVE_DETAILS, true);
                dataCorrect = false;
            }
            if (dataCorrect) {
                courseDetailsService.add(hours, description, startCourse, endCourse, startOfClass,
                        cost, courseOptional.get(), teacherOptional.get());
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.LECTURE.getServletPath() + courseId);
            } else {
                request.setAttribute(RequestAttribute.DESCRIPTION, description);
                request.setAttribute(RequestAttribute.HOURS, hours);
                request.setAttribute(RequestAttribute.START_OF_CLASS, startOfClass);
                request.setAttribute(RequestAttribute.NAME, name);
                request.setAttribute(RequestAttribute.SURNAME, surname);
                request.setAttribute(RequestAttribute.START_COURSE, startCourse);
                request.setAttribute(RequestAttribute.END_COURSE, endCourse);
                request.setAttribute(RequestAttribute.COST, cost);
                router.setPagePath(PagePath.LECTURE.getServletPath() + courseId);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
