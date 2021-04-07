package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.User;
import edu.epam.course.model.service.UserService;
import edu.epam.course.util.FileUtil;
import edu.epam.course.util.PropertyReaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * The type Upload file command.
 */
public class UploadFileCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(UploadFileCommand.class);
    /**
     * The constant path.
     */
    private static final String PATH = PropertyReaderUtil.getPath();
    private UserService userService;

    /**
     * Instantiates a new Upload file command.
     *
     * @param userService the user service
     */
    public UploadFileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RequestAttribute.USER);
        Router router = new Router();
        boolean isCorrect = false;
        String oldUserImage = user.getPhoto();
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
                FileUtil.deleteImage(PATH + oldUserImage);
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
