<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<div class="container" style="text-align: center">
    <br/>
    <h2>Status code: ${pageContext.errorData.statusCode}</h2>
    <br/>
    <h2>Exception: ${pageContext.exception}</h2>
    <br/>
    <h2>Message from exception: ${pageContext.exception.message}</h2>
</div>
</body>
</html>
