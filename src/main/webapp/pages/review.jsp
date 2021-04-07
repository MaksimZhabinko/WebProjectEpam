<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <c:import url="fragment/bootstrap_style.jsp"/>
    <c:import url="fragment/bootstrap_script.jsp"/>
    <title><fmt:message key="title.review"/></title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<c:import url="fragment/sidebar.jsp"/>

<h1 style="text-align: center"><fmt:message key="title.review"/></h1>
<div>
    <c:forEach var="review" items="${reviews}">
        <p>${review.getUser().getName()} ${review.getUser().getSurname()} ${review.getDateMessage().toString()}</p>
        <p>${review.getMessage()}</p>
        <c:if test="${user.getRole().toString() eq 'ADMIN'}">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="review_delete">
                <input type="hidden" name="review_id" value="${review.getId()}">
                <input type="submit" value="<fmt:message key="button.review.deleteReview"/>">
            </form>
        </c:if>
        <c:if test="${review.getUser().getEmail() eq user.getEmail()}">
            <c:if test="${user.getRole().toString() eq 'USER'}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="review_delete">
                    <input type="hidden" name="review_id" value="${review.getId()}">
                    <input type="submit" value="<fmt:message key="button.review.deleteReview"/>">
                </form>
            </c:if>
        </c:if>
    </c:forEach>
</div>

<c:if test="${user == null}">
    <h1><fmt:message key="text.review"/></h1>
</c:if>
<c:if test="${user.getRole().toString() eq 'USER'}">
    <div>
        <h1><fmt:message key="h1.review.add"/></h1>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="review_add">
            <textarea name="message" required></textarea>
            <input type="submit" value="<fmt:message key="button.review.sendReview"/>">
        </form>
    </div>
</c:if>

<c:if test="${errorMessage}">
    <script>
        $(document).ready(function () {
            $("#myModalBox").modal('show');
        });
    </script>
</c:if>
<c:if test="${errorDeleteNotYourReview}">
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
                <c:if test="${errorDeleteNotYourReview}">
                    <div class="alert alert-danger" role="alert">
                        Вы не можете удалить чужой комментарий
                        <fmt:message key="error.review.delete_another_review"/>
                    </div>
                </c:if>
                <c:if test="${errorMessage}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="error.message"/>
                    </div>
                </c:if>
            </div>
            <!-- Футер модального окна -->
            <div class="modal-footer">
                <c:if test="${errorDeleteNotYourReview}">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                            onclick="<c:remove var="errorDeleteNotYourReview" scope="session"/>">Ок
                    </button>
                </c:if>
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
