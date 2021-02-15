
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <c:import url="fragment/bootstrap_style.jsp"/>
    <c:import url="fragment/bootstrap_script.jsp"/>
    <title>Lecture</title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<c:import url="fragment/sidebar.jsp"/>

<h1>${courseDetails.getCourse().getName()}</h1>
<p><fmt:message key="lecture.description"/>: ${courseDetails.getDescription()}</p>
<p><fmt:message key="lecture.hours"/>: ${courseDetails.getHours()}</p>
<p><fmt:message key="lecture.start_course"/>: ${courseDetails.getStartCourse()}</p>
<p><fmt:message key="lecture.end_course"/>: ${courseDetails.getEndCourse()}</p>
<p><fmt:message key="lecture.time_course"/>: ${courseDetails.getStartOfClass()}</p>
<p><fmt:message key="lecture.teacher"/>: ${courseDetails.getTeacher().getName()} ${courseDetails.getTeacher().getSurname()}</p>
<p><fmt:message key="lecture.cost"/>: ${courseDetails.getCost()}</p>
<div>
    <c:forEach var="lecture" items="${lectures}">
        <p>${lecture.getLecture()}</p>
    </c:forEach>
</div>
</body>
</html>
