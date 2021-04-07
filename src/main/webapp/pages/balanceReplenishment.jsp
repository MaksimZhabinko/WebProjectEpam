<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <c:import url="fragment/bootstrap_style.jsp"/>
    <c:import url="fragment/bootstrap_script.jsp"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/singInAndSignUp.css">
    <title><ftm:message key="title.balance_replenishment"/></title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<c:import url="fragment/sidebar.jsp"/>
<c:if test="${user != null}">
    <div class="wrapper fadeInDown">
        <div id="formContent">
<%--            todo сообщение что можно до определенной скммы--%>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="balance_replenishment">
                <input type="text" id="money" class="fadeIn second" name="money" title="<fmt:message key="input.title.balance_replenishment"/>" required pattern="\d+(\.\d{0,2})?">
                <input type="submit" class="fadeIn fourth" value="<fmt:message key="header.balance_replenishment"/>">
            </form>
            <c:if test="${errorBalanceMessageIsValid}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.balance_replenishment"/>
                </div>
            </c:if>
            <c:if test="${errorLimitMoney}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.balance_replenishment.limit_money"/>
                </div>
            </c:if>
        </div>
    </div>
</c:if>
<c:if test="${errorBalanceUserIdIsNotExist}">
    <div class="alert alert-danger" role="alert">
        <fmt:message key="error.balance_replenishment.user_id"/>
    </div>
</c:if>
<c:import url="fragment/footer.jsp"/>
</body>
</html>

