package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Teacher;
import edu.epam.course.model.service.CourseDetailsService;
import edu.epam.course.model.service.TeacherService;
import edu.epam.course.util.IdUtil;
import edu.epam.course.validator.TeacherValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The type Course details teacher update command.
 */
public class CourseDetailsTeacherUpdateCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CourseDetailsTeacherUpdateCommand.class);
    private TeacherService teacherService;
    private CourseDetailsService courseDetailsService;

    /**
     * Instantiates a new Course details teacher update command.
     *
     * @param teacherService       the teacher service
     * @param courseDetailsService the course details service
     */
    public CourseDetailsTeacherUpdateCommand(TeacherService teacherService, CourseDetailsService courseDetailsService) {
        this.teacherService = teacherService;
        this.courseDetailsService = courseDetailsService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String teacherName = request.getParameter(RequestParameter.TEACHER_NAME);
        String teacherSurname = request.getParameter(RequestParameter.TEACHER_SURNAME);
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            Long courseId = IdUtil.stringToLong(courseIdString);
            Optional<CourseDetails> courseDetailsOptional = courseDetailsService.findCourseDetailsByCourseId(courseId);
            Optional<Teacher> teacherOptional = Optional.empty();
            if (courseDetailsOptional.isPresent()) {
                if (!TeacherValidator.isValidNameAndSurname(teacherName, teacherSurname)) {
                    session.setAttribute(SessionAttribute.ERROR_TEACHER_UPDATE, true);
                    dataCorrect = false;
                }
                if (dataCorrect) {
                    teacherOptional = teacherService.findByNameAndSurname(teacherName, teacherSurname);
                    if (!teacherOptional.isPresent()) {
                        session.setAttribute(SessionAttribute.ERROR_TEACHER_NOT_FOUND, true);
                        dataCorrect = false;
                    }
                }
                if (dataCorrect) {
                    CourseDetails courseDetails = CourseDetails.builder()
                            .setId(courseDetailsOptional.get().getId())
                            .setTeacher(teacherOptional.get())
                            .build();
                    courseDetailsService.updateTeacher(courseDetails);
                }
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.LECTURE.getServletPath() + courseId);
            } else {
                session.setAttribute(SessionAttribute.ERROR_COURSE_NOT_FOUND, true);
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.MAIN.getServletPath());
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
