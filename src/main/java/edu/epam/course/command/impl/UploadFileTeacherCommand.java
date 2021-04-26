package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Teacher;
import edu.epam.course.model.service.TeacherService;
import edu.epam.course.util.FileUtil;
import edu.epam.course.util.PropertyReaderUtil;
import edu.epam.course.validator.FileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * The type Upload file teacher command.
 */
public class UploadFileTeacherCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(UploadFileTeacherCommand.class);
    /**
     * The constant path.
     */
    private static final String PATH = PropertyReaderUtil.getPath();
    private TeacherService teacherService;

    /**
     * Instantiates a new Upload file teacher command.
     *
     * @param teacherService the teacher service
     */
    public UploadFileTeacherCommand(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String teacherId = request.getParameter(RequestParameter.TEACHER_ID);
        String oldTeacherPhoto = request.getParameter(RequestParameter.TEACHER_PHOTO);
        HttpSession session = request.getSession();
        Teacher teacher = new Teacher();
        Router router = new Router();
        boolean isCorrect = false;
        try {
            for (Part part : request.getParts()) {
                if (FileValidator.isValidFile(part)) {
                    String fileName = part.getSubmittedFileName();
                    String newFileName = FileUtil.generateName(fileName);
                    part.write(PATH + newFileName);
                    teacher.setPhoto(newFileName);
                    isCorrect = true;
                }
            }
        } catch (IOException | ServletException e) {
            logger.error("some error save file" + e);
        }
        try {
            if (isCorrect) {
                teacher.setId(Long.valueOf(teacherId));
                teacherService.updatePhoto(teacher);
                router.setPagePath(PagePath.SHOW_ALL_TEACHERS.getServletPath());
                FileUtil.deleteImage(PATH + oldTeacherPhoto);
            } else {
                session.setAttribute(SessionAttribute.UPLOAD_FILE_ERROR, true);
                router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
