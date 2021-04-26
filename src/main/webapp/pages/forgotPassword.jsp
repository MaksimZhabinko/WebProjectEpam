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
    <title><ftm:message key="title.forgot_password"/></title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="forgot_password">
            <input type="text" id="email" class="fadeIn second" name="email" placeholder="<fmt:message key="placeholder.email"/>" value="${email}" required>
            <input type="submit" class="fadeIn fourth" value="<fmt:message key="button.forgotPassword"/> ">
        </form>
        <c:if test="${errorEmailMessageInvalidForForgotPassword}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="error.forgotPassword.errorEmailMessageInvalidForForgotPassword"/>
            </div>
        </c:if>
        <c:if test="${errorEmailMessageIsNotExist}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="error.forgotPassword.emailIsNotExist"/>
            </div>
        </c:if>

        <div id="formFooter">
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="sign_in_page">
                <input type="submit" value="<fmt:message key="button.signIn"/>">
            </form>
        </div>
    </div>
</div>
<c:import url="fragment/footer.jsp"/>
</body>
</html>
