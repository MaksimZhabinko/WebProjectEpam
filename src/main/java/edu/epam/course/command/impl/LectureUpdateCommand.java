package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Lecture;
import edu.epam.course.model.service.LectureService;
import edu.epam.course.util.IdUtil;
import edu.epam.course.validator.MessageValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Lecture update command.
 */
public class LectureUpdateCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(LectureUpdateCommand.class);
    private LectureService lectureService;

    /**
     * Instantiates a new Lecture update command.
     *
     * @param lectureService the lecture service
     */
    public LectureUpdateCommand(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String message = request.getParameter(RequestParameter.MESSAGE);
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String lectureIdString = request.getParameter(RequestParameter.LECTURE_ID);
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            Long courseId = IdUtil.stringToLong(courseIdString);
            Long lectureId = IdUtil.stringToLong(lectureIdString);
            //todo нет проверки на courseId
            if (!MessageValidator.isValidMessage(message)) {
                session.setAttribute(SessionAttribute.ERROR_MESSAGE, true);
                dataCorrect = false;
            }
            if (dataCorrect) {
                Lecture lecture = new Lecture();
                lecture.setLecture(message);
                lecture.setId(lectureId);
                lectureService.updateLecture(lecture);
            }
            router.setType(Router.Type.REDIRECT);
            router.setPagePath(PagePath.LECTURE.getServletPath() + courseId);
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
