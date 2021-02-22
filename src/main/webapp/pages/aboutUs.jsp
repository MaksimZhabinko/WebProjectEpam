<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <c:import url="fragment/bootstrap_style.jsp"/>
    <c:import url="fragment/bootstrap_script.jsp"/>
    <title><fmt:message key="title.aboutUs"/></title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<c:import url="fragment/sidebar.jsp"/>

<div>
    <c:forEach var="aboutUs" items="${aboutUsList}">
        <button>
            ${aboutUs.getMessage()}
        </button>
        <c:if test="${user.getRole().toString() eq 'ADMIN'}">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="about_us_update">
                <input type="hidden" name="about_us_id" value="${aboutUs.getId()}">
                <textarea name="message" required></textarea>
                <input type="submit" value="UPDATE ABOUT US">
<%--                todo localization--%>
            </form>
        </c:if>
    </c:forEach>
</div>

<c:import url="fragment/footer.jsp"/>
</body>
</html>
