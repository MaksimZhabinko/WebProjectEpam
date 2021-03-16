package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.User;
import edu.epam.course.model.service.UserService;
import edu.epam.course.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

public class UploadFileCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UploadFileCommand.class);
    private static final String PATH = "/Users/dasik/Desktop/photoUsersCourses/";
    private UserService userService;

    public UploadFileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RequestAttribute.USER);
        Router router = new Router();
        boolean isCorrect = false;
        try {
            for (Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null) {
                    String fileName = part.getSubmittedFileName();
                    String newFileName = FileUtil.generateName(fileName);
                    part.write(PATH + newFileName);
                    user.setPhoto(newFileName);
                    isCorrect = true;
                }
            }
        } catch (IOException |ServletException e) {
            logger.error("some error save file" + e);
        }
        try {
            if (isCorrect) {
                userService.updateUserPhoto(user);
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
                session.setAttribute(RequestAttribute.USER, user);
                // todo придумать как удалить фото
            } else {
                session.setAttribute(SessionAttribute.UPLOAD_FILE_ERROR, true);
                router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
