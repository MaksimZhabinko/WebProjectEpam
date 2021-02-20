<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
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

                <c:if test="${user == null}">
                    <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller"
                          method="get">
                        <input type="hidden" name="command" value="sign_in_page">
                        <button class="btn btn-outline-success mx-2 my-2 my-sm-0" type="submit">
                            <fmt:message key="button.signIn"/>
                        </button>
                    </form>
                </c:if>

                <c:if test="${user == null}">
                    <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller"
                          method="get">
                        <input type="hidden" name="command" value="sign_up_page">
                        <button class="btn btn-outline-success mx-2 my-2 my-sm-0" type="submit">
                            <fmt:message key="button.signUp"/>
                        </button>
                    </form>
                </c:if>

                <c:if test="${user != null}">
                    <ctg:headerUserTag role="${user.getEmail()}"/>
                </c:if>
                <c:if test="${user != null}">
                    <c:if test="${user.getRole().toString() eq 'USER'}">
                        <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="balance_replenishment_page">
                            <button class="btn btn-outline-success mx-2 my-2 my-sm-0" type="submit">
                                <fmt:message key="header.balance_replenishment"/>
                            </button>
                        </form>
                    </c:if>
                </c:if>

                <c:if test="${user != null}">
                    <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller"
                          method="post">
                        <input type="hidden" name="command" value="sign_out">
                        <button class="btn btn-outline-success mx-2 my-2 my-sm-0" type="submit">
                            <fmt:message key="header.button.signOut"/>
                        </button>
                    </form>
                </c:if>
            </div>
        </div>
    </nav>
</header>
