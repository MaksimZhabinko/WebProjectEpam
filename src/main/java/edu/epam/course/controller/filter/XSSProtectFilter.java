package edu.epam.course.controller.filter;

import edu.epam.course.command.RequestWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * The type Xss protect filter.
 */
public class XSSProtectFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        RequestWrapper wrapper = new RequestWrapper(request);
        Map<String, String[]> enumeration = wrapper.getParameterMap();
        for (Map.Entry<String, String[]> entry: enumeration.entrySet()){
            String parameter = entry.getValue()[0];
            if(parameter != null){
                String newParameter = parameter.replaceAll("<", "&lt;").replaceAll(">","&gt;").
                        replaceAll("\"","&quot;").replaceAll("'","&#39;");
                wrapper.setParameter(entry.getKey(),newParameter);
            }
        }
        filterChain.doFilter(wrapper,servletResponse);
    }
}
