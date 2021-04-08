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

<%--        todo required, localization ко всем относится--%>
<c:if test="${courseDetails != null}">
    <h1>${courseDetails.getCourse().getName()}</h1>
    <p><fmt:message key="lecture.description"/>: ${courseDetails.getDescription()}</p>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_description">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="text" name="description" value="${courseDetails.getDescription()}">
            <input type="submit" value="Update description">
        </form>
    </c:if>
    <p><fmt:message key="lecture.hours"/>: ${courseDetails.getHours()}</p>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_hours">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="text" name="hours" value="${courseDetails.getHours()}">
            <input type="submit" value="Update hours">
        </form>
    </c:if>
<%--    todo сделать--%>
    <p><fmt:message key="lecture.start_course"/>: ${courseDetails.getStartCourse()}</p>
    <p><fmt:message key="lecture.end_course"/>: ${courseDetails.getEndCourse()}</p>

    <p><fmt:message key="lecture.time_course"/>: ${courseDetails.getStartOfClass()}</p>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_start_of_class">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="text" name="start_of_class" value="${courseDetails.getStartOfClass()}">
            <input type="submit" value="Update start of class">
        </form>
    </c:if>
    <p><img width="200" height="200"
            src="${pageContext.request.contextPath}/upload?url=/Users/dasik/Desktop/photoUsersCourses/${courseDetails.getTeacher().getPhoto()}"/>
    </p>
    <p>
        <fmt:message key="lecture.teacher"/>: ${courseDetails.getTeacher().getName()} ${courseDetails.getTeacher().getSurname()}
    </p>
<%--    todo ^[\p{L}]+$ --%>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_teacher">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="text" name="teacher_name" value="${courseDetails.getTeacher().getName()}">
            <input type="text" name="teacher_surname" value="${courseDetails.getTeacher().getSurname()}">
            <input type="submit" value="Update TEACHER">
        </form>
    </c:if>

    <p><fmt:message key="lecture.cost"/>: ${courseDetails.getCost()}</p>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_cost">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="text" name="cost" value="${courseDetails.getCost()}">
            <input type="submit" value="Update cost">
        </form>
    </c:if>
</c:if>

<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <c:if test="${courseDetails.getDescription() == null}">
        <h1><fmt:message key="h1.lecture.add_course_details"/></h1>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="add_course_details">
            <input type="hidden" name="course_id" value="${courseId}">
            <p><fmt:message key="p.lecture.description"/></p>
            <input type="text" name="description" value="${description}" required>
            <c:if test="${errorDescription}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.lecture.description"/>
                </div>
            </c:if>
            <p><fmt:message key="p.lecture.number_of_hours"/></p>
            <input type="text" name="hours" value="${hours}" required pattern="\d+">
            <c:if test="${errorHours}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.lecture.hours"/>
                </div>
            </c:if>
            <p><fmt:message key="p.lecture.start_of_class"/></p>
            <input type="text" name="start_of_class" value="${startOfClass}" placeholder="19:00" required pattern="([01]?[0-9]|2[0-3]):[0-5][0-9]">
            <c:if test="${errorStartOfClass}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.lecture.start_of_class"/>
                </div>
            </c:if>
            <p><fmt:message key="p.lecture.teacher_name"/></p>
            <input type="text" name="name" value="${name}" required>
            <p><fmt:message key="p.lecture.teacher_surname"/></p>
            <input type="text" name="surname" value="${surname}" required>
            <c:if test="${errorNameAndSurname}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.lecture.name_and_surname"/>
                </div>
            </c:if>
            <c:if test="${errorTeacherNotFound}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.lecture.teacher_not_found"/>
                </div>
            </c:if>
            <p><fmt:message key="p.lecture.start_course"/></p>
            <input type="text" name="start_course" value="${startCourse}" placeholder="2020-01-01" required pattern="^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$">
            <p><fmt:message key="p.lecture.end_course"/></p>
            <input type="text" name="end_course" value="${endCourse}" placeholder="2021-01-01" required pattern="^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$">
            <c:if test="${errorStartAndEndCourse}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.lecture.start_and_end_course"/>
                </div>
            </c:if>
            <p><fmt:message key="p.lecture.cost"/></p>
            <input type="text" name="cost" value="${cost}" required pattern="\d+">
            <c:if test="${errorCost}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.lecture.cost"/>
                </div>
            </c:if>
            <br>
            <input type="submit" value="<fmt:message key="h1.lecture.add_course_details"/>">
            <c:if test="${errorCourseNotFound}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.lecture.course_not_found"/>
                </div>
            </c:if>
            <c:if test="${errorIsHaveDetails}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.lecture.is_have_details"/>
                </div>
            </c:if>
        </form>
    </c:if>
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
                    <input type="text" name="message" value="${lecture.getLecture()}" required>
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

<c:if test="${user.getRole().toString() eq 'USER'}">
    <c:if test="${courseDetails.getCourse().getEnrollmentActive() == true}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="enroll_course">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="submit" value="<fmt:message key="button.submit.lecture"/>">
        </form>
    </c:if>
</c:if>

<c:if test="${lectures == null}">
    <h1><fmt:message key="h1.no_lecture"/></h1>
</c:if>
<c:if test="${courseDetails == null}">
    <h1><fmt:message key="h1.no_course_details"/></h1>
</c:if>

<c:if test="${errorMessage}">
    <script>
        $(document).ready(function () {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorUserHaveLittleMoney}">
    <script>
        $(document).ready(function () {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorUserHaveCourse}">
    <script>
        $(document).ready(function () {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorCost}">
    <script>
        $(document).ready(function () {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorDescription}">
    <script>
        $(document).ready(function () {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorHours}">
    <script>
        $(document).ready(function () {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorStartOfClass}">
    <script>
        $(document).ready(function () {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorTeacherNotFound}">
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
                <c:if test="${errorMessage}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.message"/>
                    </div>
                </c:if>
                <c:if test="${errorCost}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.lecture.update_cost"/>
                    </div>
                </c:if>
                <c:if test="${errorDescription}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.lecture.update_description"/>
                    </div>
                </c:if>
                <c:if test="${errorHours}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.lecture.update_hours"/>
                    </div>
                </c:if>
                <c:if test="${errorStartOfClass}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.lecture.update_start_of_class"/>
                    </div>
                </c:if>
                <c:if test="${errorTeacherNotFound}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.lecture.teacher_not_found"/>
                    </div>
                </c:if>
            </div>
            <!-- Футер модального окна -->
            <div class="modal-footer">
                <c:if test="${errorUserHaveLittleMoney}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorUserHaveLittleMoney" scope="session"/>">Ок
                    </button>
                </c:if>
                <c:if test="${errorUserHaveCourse}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorUserHaveCourse" scope="session"/>">Ок
                    </button>
                </c:if>
                <c:if test="${errorMessage}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorMessage" scope="session"/>">Ок
                    </button>
                </c:if>
                <c:if test="${errorCost}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorCost" scope="session"/>">Ок
                    </button>
                </c:if>
                <c:if test="${errorDescription}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorDescription" scope="session"/>">Ок
                    </button>
                </c:if>
                <c:if test="${errorHours}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorHours" scope="session"/>">Ок
                    </button>
                </c:if>
                <c:if test="${errorStartOfClass}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorStartOfClass" scope="session"/>">Ок
                    </button>
                </c:if>
                <c:if test="${errorTeacherNotFound}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorTeacherNotFound" scope="session"/>">Ок
                    </button>
                </c:if>
            </div>
        </div>
    </div>
</div>

<c:import url="fragment/footer.jsp"/>
</body>
</html>
