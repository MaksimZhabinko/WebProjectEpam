package edu.epam.course.controller;

import edu.epam.course.command.Command;
import edu.epam.course.command.CommandProvider;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.model.connection.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Controller.
 */
@WebServlet(urlPatterns = {"/controller" , "*.do"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().destroyPool();
    }
}
