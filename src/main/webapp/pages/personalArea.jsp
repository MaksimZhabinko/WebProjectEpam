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
<%--todo localization--%>
<c:if test="${user != null}">
    <h1><fmt:message key="placeholder.email"/> - ${user.getEmail()}</h1>
    <h1><fmt:message key="placeholder.name"/> - ${user.getName()}</h1>
    <h1><fmt:message key="placeholder.surname"/> - ${user.getSurname()}</h1>
    <h1><fmt:message key="h1.personal_area.role"/> - ${user.getRole()}</h1>
    <h1><fmt:message key="h1.personal_area.money"/> - ${user.getMoney()}</h1>
    <img width="120" height="120"
         src="${pageContext.request.contextPath}/upload?url=/Users/dasik/Desktop/photoUsersCourses/${user.getPhoto()}"/>
    <form action="upload" enctype="multipart/form-data" method="POST">
        <input type="hidden" name="command" value="upload_file">
        Upload File: <input type="file" name="content" height="130">
        <input type="submit" value="Upload File">
            <%--        todo localization--%>
    </form>
</c:if>

<c:if test="${user == null}">
    <h1><fmt:message key="h1.personal_area"/></h1>
</c:if>

<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="show_all_users">
        <input type="submit" value="<fmt:message key="button.personal_area.show_all_users"/>">
    </form>
</c:if>

<%--fixme добавть больше инфы--%>
<c:if test="${allUsers != null}">
    <table border="1">
        <tr>
                <%--            fixme localization--%>
            <th>id</th>
            <th>email</th>
            <th>name</th>
            <th>surname</th>
            <th>role</th>
            <th>enabled</th>
            <th>money</th>
            <th>Block</th>
            <th>Unblock</th>
        </tr>
        <c:forEach var="user" items="${allUsers}">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getEmail()}</td>
            <td>${user.getName()}</td>
            <td>${user.getSurname()}</td>
            <td>${user.getRole()}</td>
            <td>${user.isEnabled()}</td>
            <td>${user.getMoney()}</td>
            </c:forEach>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="block_user">
                <c:forEach var="user" items="${allUsers}">
                    <input type="checkbox" name="user_id" value="${user.getId()}"/>
                </c:forEach>
                <input type="submit" value="BLOCK">
            </form>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="unblock_user">
                <c:forEach var="user" items="${allUsers}">
                    <input type="checkbox" name="user_id" value="${user.getId()}"/>
                </c:forEach>
                <input type="submit" value="Unblock">
            </form>
        </tr>
    </table>
</c:if>

<c:if test="${userEnrolledByCourse != null}">
    <p>Ваши курсы</p>
    <table border="1">
        <tr>
                <%--            fixme localization--%>
            <th>course name</th>
        </tr>
        <c:forEach var="course" items="${userEnrolledByCourse}">
            <tr>
                <td>${course.getName()}</td>
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
                            <%--                        <fmt:message key="error.lecture.user_have_little_money"/>--%>
                        SOME ERROR UPLOAD FILE
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
            </div>
        </div>
    </div>
</div>

<c:import url="fragment/footer.jsp"/>
</body>
</html>
