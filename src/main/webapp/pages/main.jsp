
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <c:import url="fragment/bootstrap_style.jsp"/>
    <c:import url="fragment/bootstrap_script.jsp"/>
    <title><fmt:message key="title.main"/></title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<c:import url="fragment/sidebar.jsp"/>

<h1 style="text-align: center"><fmt:message key="main.itCourse"/></h1>
<div>
    <c:forEach var="course" items="${courses}">
        <a href="${pageContext.request.contextPath}/controller?command=lecture_page&course_id=${course.getId()}">
            <p>
                <button type="button">${course.getName()}</button>
            </p>
        </a>
        <c:if test="${user.getRole().toString() eq 'ADMIN'}">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="course_delete">
                <input type="hidden" name="course_id" value="${course.getId()}">
                <input type="submit" value="<fmt:message key="button.delete_course"/>">
            </form>
        </c:if>
    </c:forEach>
</div>
<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="course_add">
        <input type="text" name="course_name">
        <input type="submit" value="<fmt:message key="button.add_course"/>">
    </form>
</c:if>

<c:import url="fragment/footer.jsp"/>
</body>
</html>
