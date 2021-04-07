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
                <input type="submit" value="<fmt:message key="button.update_about_us"/>">
            </form>
        </c:if>
    </c:forEach>
</div>

<c:if test="${errorMessage}">
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
                <c:if test="${errorMessage}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.message"/>
                    </div>
                </c:if>
            </div>
            <!-- Футер модального окна -->
            <div class="modal-footer">
                <c:if test="${errorMessage}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorMessage" scope="session"/>">Ок
                    </button>
                </c:if>
            </div>
        </div>
    </div>
</div>


<c:import url="fragment/footer.jsp"/>
</body>
</html>
