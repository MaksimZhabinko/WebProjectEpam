<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div style="display: flex; flex-grow: inherit; justify-content: flex-end">
                <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller"
                      method="post">
                    <input type="hidden" name="command" value="change_language"/>
                    <div class="dropdown">
                        <button class="btn btn-success dropdown-toggle mx-2" type="button" id="language"
                                data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="header.button.language"/>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="change_language">
                            <button class="dropdown-item" type="submit" name="language" value="en">EN</button>
                            <button class="dropdown-item" type="submit" name="language" value="ru">RU</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </nav>
</header>
