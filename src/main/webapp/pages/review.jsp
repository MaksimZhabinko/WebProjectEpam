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
        <h1>Добавить отзыв</h1>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="review_add">
            <textarea name="message" required></textarea>
            <input type="submit" value="<fmt:message key="button.review.sendReview"/>">
        </form>
    </div>
</c:if>

<c:if test="${errorReviewMessage}">
    <div class="alert alert-danger" role="alert">
        <fmt:message key="error.review.message"/>
    </div>
</c:if>
<c:import url="fragment/footer.jsp"/>
</body>
</html>
