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

<c:if test="${user != null}">
    <img width="200" height="200"
         src="${pageContext.request.contextPath}/upload?url=/Users/dasik/Desktop/photoUsersCourses/${user.getPhoto()}"/>
    <form action="upload" enctype="multipart/form-data" method="POST">
        <input type="hidden" name="command" value="upload_file">
        <fmt:message key="button.personal_area.upload_file"/>: <input type="file" name="content" height="130">
        <input type="submit" value="<fmt:message key="button.personal_area.upload_file"/>">
    </form>
    <h1><fmt:message key="placeholder.email"/> - ${user.getEmail()}</h1>
    <h1><fmt:message key="placeholder.name"/> - ${user.getName()}</h1>
    <h1><fmt:message key="placeholder.surname"/> - ${user.getSurname()}</h1>
    <h1><fmt:message key="h1.personal_area.role"/> - ${user.getRole()}</h1>
    <h1><fmt:message key="h1.personal_area.money"/> - ${user.getMoney()}</h1>
</c:if>

<c:if test="${user == null}">
    <h1><fmt:message key="h1.personal_area"/></h1>
</c:if>

<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <c:if test="${allUsers == null}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="show_all_users">
            <input type="hidden" name="page" value="1">
            <input type="submit" value="<fmt:message key="button.personal_area.show_all_users"/>">
        </form>
    </c:if>
</c:if>

<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <c:if test="${allUsers != null}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="hide_all_users">
            <input type="submit" value="<fmt:message key="button.personal_area.hide_all_users"/>">
        </form>
    </c:if>
</c:if>

<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <c:if test="${allTeachers == null}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="show_all_teachers">
            <input type="submit" value="<fmt:message key="button.personal_area.show_all_teachers"/>">
        </form>
    </c:if>
</c:if>

<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <c:if test="${allTeachers != null}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="hide_all_teachers">
            <input type="submit" value="<fmt:message key="button.personal_area.hide_all_teachers"/>">
        </form>
    </c:if>
</c:if>

<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <c:if test="${allTeachers != null}">
        <table border="1">
            <tr>
                <th><fmt:message key="table.personal_area.th_id"/></th>
                <th><fmt:message key="table.personal_area.th_photo"/></th>
                <th><fmt:message key="table.personal_area.th_name"/></th>
                <th><fmt:message key="table.personal_area.th_surname"/></th>
            </tr>
            <c:forEach items="${allTeachers}" var="teacher">
                <tr>
                    <td>${teacher.getId()}</td>
                    <td>
                        <img width="120" height="120"
                             src="${pageContext.request.contextPath}/upload?url=/Users/dasik/Desktop/photoUsersCourses/${teacher.getPhoto()}"/>
                        <form action="upload" enctype="multipart/form-data" method="POST">
                            <input type="hidden" name="command" value="upload_file_teacher">
                            <input type="hidden" name="teacher_id" value="${teacher.getId()}">
                            <input type="hidden" name="teacher_photo" value="${teacher.getPhoto()}">
                            <fmt:message key="button.personal_area.upload_file"/>: <input type="file" name="content"
                                                                                          height="130">
                            <input type="submit" value="<fmt:message key="button.personal_area.upload_file"/>">
                        </form>
                    </td>
                    <td>${teacher.getName()}</td>
                    <td>${teacher.getSurname()}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</c:if>

<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <c:if test="${allTeachers != null}">
        <h1>DELETE TEACHER</h1>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="teacher_delete">
            <c:forEach var="teacher" items="${allTeachers}">
                <input type="checkbox" name="teacher_id" value="${teacher.getId()}"/>${teacher.getId()}
            </c:forEach>
            <input type="submit" value="<fmt:message key="button.personal_area.teacher_delete"/>">
        </form>
    </c:if>
</c:if>

<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <h1><fmt:message key="h1.personal_area.add_teacher"/></h1>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="teacher_add">
        <input type="text" name="name" required>
        <input type="text" name="surname" required>
        <input type="submit" value="<fmt:message key="button.personal_area.add_teacher"/>">
    </form>
</c:if>

<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <c:if test="${allUsers != null}">
        <table border="1">
            <tr>
                <th><fmt:message key="table.personal_area.th_id"/></th>
                <th><fmt:message key="table.personal_area.th_photo"/></th>
                <th><fmt:message key="table.personal_area.th_email"/></th>
                <th><fmt:message key="table.personal_area.th_name"/></th>
                <th><fmt:message key="table.personal_area.th_surname"/></th>
                <th><fmt:message key="table.personal_area.th_role"/></th>
                <th><fmt:message key="table.personal_area.th_status"/></th>
                <th><fmt:message key="table.personal_area.th_money"/></th>
            </tr>
            <c:forEach var="user" items="${allUsers}">
            <tr>
                <td>${user.getId()}</td>
                <td>
                    <img width="120" height="120"
                         src="${pageContext.request.contextPath}/upload?url=/Users/dasik/Desktop/photoUsersCourses/${user.getPhoto()}"/>
                </td>
                <td>${user.getEmail()}</td>
                <td>${user.getName()}</td>
                <td>${user.getSurname()}</td>
                <td>${user.getRole()}</td>
                <td>
                    <c:if test="${user.isEnabled() == true}">
                        <fmt:message key="table.personal_area.td_status_active"/>
                    </c:if>
                    <c:if test="${user.isEnabled() == false}">
                        <fmt:message key="table.personal_area.td_status_inactive"/>
                    </c:if>
                </td>
                <td>${user.getMoney()}</td>
                </c:forEach>
            </tr>
        </table>
        <c:if test="${pages != null}">
            <c:forEach items="${pages}" var="page">
                <a href='${pageContext.request.contextPath}/controller?command=show_all_users&page=${page}'>${page}</a>
            </c:forEach>
        </c:if>
    </c:if>
    <c:if test="${allUsers != null}">
        <h1><fmt:message key="h1.personal_area.block_user"/></h1>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="block_user">
            <input type="hidden" name="page" value="${page}">
            <c:forEach var="user" items="${allUsers}">
                <input type="checkbox" name="user_id" value="${user.getId()}"/>${user.getId()}
            </c:forEach>
            <input type="submit" value="<fmt:message key="h1.personal_area.block_user"/>">
        </form>
    </c:if>
    <c:if test="${allUsers != null}">
        <h1><fmt:message key="h1.personal_area.unblock_user"/></h1>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="unblock_user">
            <input type="hidden" name="page" value="${page}">
            <c:forEach var="user" items="${allUsers}">
                <input type="checkbox" name="user_id" value="${user.getId()}"/>${user.getId()}
            </c:forEach>
            <input type="submit" value="<fmt:message key="h1.personal_area.unblock_user"/>">
        </form>
    </c:if>
</c:if>

<c:if test="${userEnrolledByCourse != null}">
    <p><fmt:message key="p.personal_area.your_courses"/></p>
    <table border="1">
        <tr>
            <th><fmt:message key="table.personal_area.th_course_name"/></th>
        </tr>
        <c:forEach var="course" items="${userEnrolledByCourse}">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/controller?command=lecture_page&course_id=${course.getId()}">
                            ${course.getName()}
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${uploadFileError}">
    <script>
        $(document).ready(function () {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorBLockYourself}">
    <script>
        $(document).ready(function () {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorTeacherAdd}">
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
                <c:if test="${uploadFileError}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.personal_area.upload_file"/>
                    </div>
                </c:if>
                <c:if test="${errorBLockYourself}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.personal_area.block_yourself"/>
                    </div>
                </c:if>

                <c:if test="${errorTeacherAdd}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.personal_area.add_teacher"/>
                    </div>
                </c:if>
            </div>
            <!-- Футер модального окна -->
            <div class="modal-footer">
                <c:if test="${uploadFileError}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="uploadFileError" scope="session"/>">Ок
                    </button>
                </c:if>
                <c:if test="${errorBLockYourself}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorBLockYourself" scope="session"/>">Ок
                    </button>
                </c:if>

                <c:if test="${errorTeacherAdd}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorTeacherAdd" scope="session"/>">Ок
                    </button>
                </c:if>
            </div>
        </div>
    </div>
</div>

<c:import url="fragment/footer.jsp"/>
</body>
</html>
