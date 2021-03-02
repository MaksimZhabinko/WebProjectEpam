package edu.epam.course.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HeaderUserTag extends TagSupport {
    private String email;

    public void setRole(String email) {
        this.email = email;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write("<button class=\"btn btn-outline-success mx-2 my-2 my-sm-0\" type=\"submit\">" + email + "</button>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
