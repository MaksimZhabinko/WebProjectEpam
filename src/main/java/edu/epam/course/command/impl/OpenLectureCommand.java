package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Lecture;
import edu.epam.course.model.service.CourseDetailsService;
import edu.epam.course.model.service.CourseService;
import edu.epam.course.model.service.LectureService;
import edu.epam.course.util.IdUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The type Open lecture command.
 */
public class OpenLectureCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(OpenLectureCommand.class);
    private LectureService lectureService;
    private CourseDetailsService courseDetailsService;
    private CourseService courseService;

    /**
     * Instantiates a new Open lecture command.
     *
     * @param lectureService       the lecture service
     * @param courseDetailsService the course details service
     * @param courseService        the course service
     */
    public OpenLectureCommand(LectureService lectureService, CourseDetailsService courseDetailsService,
                              CourseService courseService) {
        this.lectureService = lectureService;
        this.courseDetailsService = courseDetailsService;
        this.courseService = courseService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        HttpSession session = request.getSession();
        Router router = new Router();
        try {
            Long courseId = IdUtil.stringToLong(courseIdString);
            Optional<Course> courseOptional = courseService.findById(courseId);
            if (courseOptional.isPresent()) {
                List<Lecture> lectures = lectureService.findAllLectureByCourseId(courseId);
                Optional<CourseDetails> courseDetails = courseDetailsService.findCourseDetailsById(courseId);
                if (!lectures.isEmpty()) {
                    request.setAttribute(RequestAttribute.LECTURES, lectures);
                }
                if (courseDetails.isPresent()) {
                    request.setAttribute(RequestAttribute.COURSE_DETAILS, courseDetails.get());
                }
                request.setAttribute(RequestAttribute.COURSE_ID, courseOptional.get().getId());
                router.setPagePath(PagePath.LECTURE.getDirectUrl());
                session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.LECTURE.getServletPath() + courseId);
            } else {
                router.setPagePath(PagePath.ERROR_404.getDirectUrl());
                // todo можно заменить на собственный error course not found
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
