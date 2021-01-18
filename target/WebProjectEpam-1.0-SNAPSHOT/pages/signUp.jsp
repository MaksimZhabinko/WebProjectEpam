
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/singInAndSignUp.css">
    <title>sign up</title>
</head>
<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="sign_up">
            <input type="text" id="email" class="fadeIn second" name="email" placeholder="email">
            <input type="text" id="name" class="fadeIn second" name="name" placeholder="name">
            <input type="text" id="surname" class="fadeIn second" name="surname" placeholder="surname">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
            <input type="password" id="repeat_password" class="fadeIn third" name="repeat_password" placeholder="repeat password">
            <input type="submit" class="fadeIn fourth" value="sign up">
        </form>

        <div id="formFooter">
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="sign_in_page">
                <input type="submit" value="sign in">
            </form>
        </div>
    </div>
</div>
</body>
</body>
</html>
