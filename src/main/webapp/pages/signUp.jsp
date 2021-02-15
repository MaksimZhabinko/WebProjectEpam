<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>sign up</title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="sign_up">
            <input type="text" id="email" class="fadeIn second" name="email" placeholder="<fmt:message key="placeholder.email"/>" value="${email}" required pattern="^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,4}">
            <c:if test="${errorEmailMessageInvalid}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.signUp.emailInvalid"/>
                </div>
            </c:if>
            <c:if test="${errorEmailMessageIsExist}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.signUp.emailIsExist"/>
                </div>
            </c:if>
            <input type="text" id="name" class="fadeIn second" name="name" value="${name}" placeholder="<fmt:message key="placeholder.name"/>" required>
            <input type="text" id="surname" class="fadeIn second" name="surname" value="${surname}" placeholder="<fmt:message key="placeholder.surname"/>" required>
            <c:if test="${errorNameAndSurnameMessage}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.signUp.nameAndSurname"/>
                </div>
            </c:if>
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="<fmt:message key="placeholder.password"/>" required pattern="^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$"
                   title='<fmt:message key="input.title.password"/>'>
            <input type="password" id="repeat_password" class="fadeIn third" name="repeat_password" placeholder="<fmt:message key="placeholder.passwordRepeat"/>" required pattern="^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$"
                   title='<fmt:message key="input.title.password"/>'>
            <c:if test="${errorPasswordMessage}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="error.signUp.password"/>
                </div>
            </c:if>
            <input type="submit" class="fadeIn fourth" value="<fmt:message key="button.signUp"/>">
        </form>

        <div id="formFooter">
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="sign_in_page">
                <input type="submit" value="<fmt:message key="button.signIn"/>">
            </form>
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="main_page">
                <input type="submit" value="Главная страница">
            </form>
        </div>
    </div>
</div>
</body>
</body>
</html>
