<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <c:import url="fragment/bootstrap_style.jsp"/>
    <c:import url="fragment/bootstrap_script.jsp"/>
    <title><fmt:message key="title.lecture"/></title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<c:import url="fragment/sidebar.jsp"/>

<c:if test="${courseDetails != null}">
    <h1>${courseDetails.getCourse().getName()}</h1>
    <p><fmt:message key="lecture.description"/>: ${courseDetails.getDescription()}</p>
    <p><fmt:message key="lecture.hours"/>: ${courseDetails.getHours()}</p>
    <p><fmt:message key="lecture.start_course"/>: ${courseDetails.getStartCourse()}</p>
    <p><fmt:message key="lecture.end_course"/>: ${courseDetails.getEndCourse()}</p>
    <p><fmt:message key="lecture.time_course"/>: ${courseDetails.getStartOfClass()}</p>
    <p><fmt:message
            key="lecture.teacher"/>: ${courseDetails.getTeacher().getName()} ${courseDetails.getTeacher().getSurname()}</p>
    <p><fmt:message key="lecture.cost"/>: ${courseDetails.getCost()}</p>
</c:if>
<c:if test="${lectures != null}">
    <div>
        <c:forEach var="lecture" items="${lectures}">
            <p>${lecture.getLecture()}</p>
            <c:if test="${user.getRole().toString() eq 'ADMIN'}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="lecture_update">
                    <input type="hidden" name="lecture_id" value="${lecture.getId()}">
                    <input type="hidden" name="course_id" value="${courseId}">
                    <input type="text" name="message" required>
                    <input type="submit" value="<fmt:message key="button.update_lecture"/>">
                </form>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="lecture_delete">
                    <input type="hidden" name="lecture_id" value="${lecture.getId()}">
                    <input type="hidden" name="course_id" value="${courseId}">
                    <input type="submit" value="<fmt:message key="button.delete_lecture"/>">
                </form>
            </c:if>
        </c:forEach>
    </div>
</c:if>

<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="lecture_add">
        <input type="hidden" name="course_id" value="${courseId}">
        <input type="text" name="message" required>
        <input type="submit" value="<fmt:message key="button.add_lecture"/>">
    </form>
</c:if>

<c:if test="${errorUserHaveLittleMoney}">
    <script>
        $(document).ready(function() {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorUserHaveCourse}">
    <script>
        $(document).ready(function() {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<div id="myModalBox" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Заголовок модального окна -->
<%--            <div class="modal-header">--%>
<%--                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>--%>
<%--                <h4 class="modal-title">Error</h4>--%>
<%--            </div>--%>
            <!-- Основное содержимое модального окна -->
            <div class="modal-body">
                <c:if test="${errorUserHaveLittleMoney}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.lecture.user_have_little_money"/>
                    </div>
                </c:if>
                <c:if test="${errorUserHaveCourse}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.lecture.user_have_course"/>
                    </div>
                </c:if>
            </div>
            <!-- Футер модального окна -->
            <div class="modal-footer">
                <c:if test="${errorUserHaveLittleMoney}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorUserHaveLittleMoney" scope="session"/>">Ок</button>
                </c:if>
                <c:if test="${errorUserHaveCourse}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorUserHaveCourse" scope="session"/>">Ок</button>
                </c:if>
            </div>
        </div>
    </div>
</div>

<c:if test="${user.getRole().toString() eq 'USER'}">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="enroll_course">
        <input type="hidden" name="course_id" value="${courseId}">
        <input type="hidden" name="course_cost" value="${courseDetails.getCost()}">
        <input type="submit" value="<fmt:message key="button.submit.lecture"/>">
    </form>
</c:if>

<c:if test="${lectures == null}">
    <h1><fmt:message key="h1.no_lecture"/></h1>
</c:if>
<c:if test="${courseDetails == null}">
    <h1><fmt:message key="h1.no_course_details"/></h1>
</c:if>

<c:import url="fragment/footer.jsp"/>
</body>
</html>
