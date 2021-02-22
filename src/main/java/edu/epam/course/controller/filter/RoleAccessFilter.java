package edu.epam.course.controller.filter;

import edu.epam.course.command.CommandType;
import edu.epam.course.command.PageAccessFilter;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.model.entity.RoleType;
import edu.epam.course.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;


public class RoleAccessFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(RoleAccessFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String commandName = request.getParameter(RequestParameter.COMMAND);
        if(commandName == null){
            logger.error("command name isn't exist");
            request.getRequestDispatcher(PagePath.ERROR_404.getDirectUrl()).forward(servletRequest, servletResponse);
            return;
        }

        RoleType role = RoleType.GUEST;
        Set<CommandType> commandsByRole;

        User user = (User) session.getAttribute(RequestAttribute.USER);
        if (user != null) {
            role = user.getRole();
        }
        switch (role) {
            case ADMIN:
                commandsByRole = PageAccessFilter.ADMIN.getCommands();
                break;
            case USER:
                commandsByRole = PageAccessFilter.USER.getCommands();
                break;
            default:
                commandsByRole = PageAccessFilter.GUEST.getCommands();
                break;
        }

        commandName = commandName.toUpperCase();
        boolean isHaveCommandType = false;
        for (CommandType commandType : commandsByRole) {
            String commandTypeString = commandType.toString();
            if (commandTypeString.equals(commandName)) {
                isHaveCommandType = true;
                break;
            }
        }
        if (isHaveCommandType) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.error("command name isn't exist");
            request.getRequestDispatcher(PagePath.ERROR_404.getDirectUrl()).forward(servletRequest, servletResponse);
        }
    }
}
