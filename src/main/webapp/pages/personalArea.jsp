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
    <h1>Photo пока нету ничего потом добавлю</h1>
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
        </tr>
        <c:forEach var="users" items="${allUsers}">
            <tr>
                <td>${users.getId()}</td>
                <td>${users.getEmail()}</td>
                <td>${users.getName()}</td>
                <td>${users.getSurname()}</td>
                <td>${users.getRole()}</td>
                <td>${users.isEnabled()}</td>
                <td>${users.getMoney()}</td>
            </tr>
        </c:forEach>
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


<c:import url="fragment/footer.jsp"/>
</body>
</html>
