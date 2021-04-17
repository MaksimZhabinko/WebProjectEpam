package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Lecture;
import edu.epam.course.model.service.CourseDetailsService;
import edu.epam.course.model.service.CourseService;
import edu.epam.course.model.service.LectureService;
import edu.epam.course.util.IdUtil;
import edu.epam.course.validator.CourseDetailsValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The type Course details update new course command.
 */
public class CourseDetailsUpdateNewCourseCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CourseDetailsUpdateNewCourseCommand.class);
    private CourseDetailsService courseDetailsService;
    private LectureService lectureService;
    private CourseService courseService;

    /**
     * Instantiates a new Course details update new course command.
     *
     * @param courseDetailsService the course details service
     * @param lectureService       the lecture service
     * @param courseService        the course service
     */
    public CourseDetailsUpdateNewCourseCommand(CourseDetailsService courseDetailsService,
                                               LectureService lectureService, CourseService courseService) {
        this.courseDetailsService = courseDetailsService;
        this.lectureService = lectureService;
        this.courseService = courseService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String start = request.getParameter(RequestParameter.START);
        String end = request.getParameter(RequestParameter.END);
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            Long courseId = IdUtil.stringToLong(courseIdString);
            Optional<CourseDetails> courseDetailsOptional = courseDetailsService.findCourseDetailsByCourseId(courseId);
            if (courseDetailsOptional.isPresent()) {
                if (!CourseDetailsValidator.isValidDate(start, end)) {
                    session.setAttribute(SessionAttribute.ERROR_START_END_UPDATE, true);
                    router.setType(Router.Type.REDIRECT);
                    router.setPagePath(PagePath.LECTURE.getServletPath() + courseId);
                }
                if (dataCorrect) {
                    List<Lecture> lectures = lectureService.findAllLectureByCourseId(courseDetailsOptional.get().getCourse().getId());
                    courseDetailsOptional.get().setStartCourse(LocalDate.parse(start));
                    courseDetailsOptional.get().setEndCourse(LocalDate.parse(end));
                    Long newCourseId = courseService.updateStartAndEndNewCourse(courseDetailsOptional.get(), lectures);
                    router.setType(Router.Type.REDIRECT);
                    router.setPagePath(PagePath.LECTURE.getServletPath() + newCourseId);
                }
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
