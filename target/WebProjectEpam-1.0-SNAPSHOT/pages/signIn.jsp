
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/singInAndSignUp.css">
    <title>sign in</title>
</head>
<body>
<div class="wrapper fadeInDown">
    <div id="formContent">

        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="sign_in">
            <input type="text" id="email" class="fadeIn second" name="email" placeholder="email">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
            <input type="submit" class="fadeIn fourth" value="sign in">
        </form>
        ${errorUserMessage}

        <div id="formFooter">
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="sign_up_page">
                <input type="submit" value="sign up">
            </form>
        </div>
    </div>
</div>
</body>
</html>
