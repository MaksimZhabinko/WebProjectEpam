package edu.epam.course.controller;

import edu.epam.course.command.Command;
import edu.epam.course.command.CommandProvider;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * The type File uploading servlet.
 */
@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("url");
        File file = new File(url);
        BufferedImage bi = ImageIO.read(file);
        OutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandString = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandProvider.defineCommand(commandString);
        Router router = command.execute(request);

        if (router.getType().equals(Router.Type.FORWARD)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(router.getPagePath());
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect(router.getPagePath());
        }
    }
}
