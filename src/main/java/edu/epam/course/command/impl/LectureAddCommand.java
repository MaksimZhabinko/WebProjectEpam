package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.Lecture;
import edu.epam.course.model.service.CourseService;
import edu.epam.course.model.service.LectureService;
import edu.epam.course.util.IdUtil;
import edu.epam.course.validator.MessageValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The type Lecture add command.
 */
public class LectureAddCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(LectureAddCommand.class);
    private LectureService lectureService;
    private CourseService courseService;

    /**
     * Instantiates a new Lecture add command.
     *
     * @param lectureService the lecture service
     * @param courseService  the course service
     */
    public LectureAddCommand(LectureService lectureService, CourseService courseService) {
        this.lectureService = lectureService;
        this.courseService = courseService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String message = request.getParameter(RequestParameter.MESSAGE);
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            Long courseId = IdUtil.stringToLong(courseIdString);
            Optional<Course> courseOptional = courseService.findById(courseId);
            if (courseOptional.isPresent()) {
                if (!MessageValidator.isValidMessage(message)) {
                    session.setAttribute(SessionAttribute.ERROR_MESSAGE, true);
                    dataCorrect = false;
                }
                if (dataCorrect) {
                    Course course = Course.builder()
                            .setId(courseId)
                            .build();
                    Lecture lecture = Lecture.builder()
                            .setLecture(message)
                            .setCourse(course)
                            .build();
                    lectureService.add(lecture);
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
