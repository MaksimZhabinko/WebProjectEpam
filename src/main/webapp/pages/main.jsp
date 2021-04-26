
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
            <c:if test="${course.getEnrollmentActive() == true}">
                <p>
                    <button type="button">${course.getName()}</button>
                </p>
            </c:if>
        </a>
        <c:if test="${course.getEnrollmentActive() == true}">
            <c:if test="${user.getRole().toString() eq 'ADMIN'}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="course_update">
                    <input type="hidden" name="course_id" value="${course.getId()}">
                    <input type="text" name="course_name" value="${course.getName()}" required>
                    <input type="submit" value="<fmt:message key="button.main.update_course"/>">
                </form>
            </c:if>
            <c:if test="${user.getRole().toString() eq 'ADMIN'}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="course_delete">
                    <input type="hidden" name="course_id" value="${course.getId()}">
                    <input type="submit" value="<fmt:message key="button.delete_course"/>">
                </form>
            </c:if>
        </c:if>
    </c:forEach>
</div>
<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="course_add">
        <input type="text" name="course_name" required>
        <input type="submit" value="<fmt:message key="button.add_course"/>">
    </form>
</c:if>
<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    Набор на данные курсы окончен
    <c:forEach var="course" items="${courses}">
        <c:if test="${course.getEnrollmentActive() == false}">
            <a href="${pageContext.request.contextPath}/controller?command=lecture_page&course_id=${course.getId()}">
                <p>
                    <button type="button">${course.getName()}</button>
                </p>
            </a>
        </c:if>
    </c:forEach>
</c:if>

<c:if test="${errorIsNotValidCourseName}">
    <script>
        $(document).ready(function () {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorCourseNotFound}">
    <script>
        $(document).ready(function () {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorLectureNotFound}">
    <script>
        $(document).ready(function () {
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
                <c:if test="${errorIsNotValidCourseName}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.main.is_not_valid_course_name"/>
                    </div>
                </c:if>
                <c:if test="${errorCourseNotFound}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.main.course_not_found"/>
                    </div>
                </c:if>
                <c:if test="${errorLectureNotFound}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.lecture.lecture_not_found"/>
                    </div>
                </c:if>
            </div>
            <!-- Футер модального окна -->
            <div class="modal-footer">
                <c:if test="${errorIsNotValidCourseName}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorIsNotValidCourseName" scope="session"/>">Ок
                    </button>
                </c:if>
                <c:if test="${errorCourseNotFound}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorCourseNotFound" scope="session"/>">Ок
                    </button>
                </c:if>
                <c:if test="${errorLectureNotFound}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorLectureNotFound" scope="session"/>">Ок
                    </button>
                </c:if>
            </div>
        </div>
    </div>
</div>

<c:import url="fragment/footer.jsp"/>
</body>
</html>
