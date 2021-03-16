
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<html>
<head>
    <c:import url="fragment/bootstrap_style.jsp"/>
    <c:import url="fragment/bootstrap_script.jsp"/>
    <title>Title</title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
Uploads
<FORM action="upload" enctype="multipart/form-data" method="POST">
    <input type="hidden" name="command" value="upload_file">
    Upload File: <INPUT type="file" name="content" height="130">
    <INPUT type="submit" value="Upload File">

</FORM>
<img width="120" height="120" src="${pageContext.request.contextPath}/upload?url=/Users/dasik/Desktop/photo users courses/${photo}"/>
<%--<img width="120" height="120" src="${pageContext.request.contextPath}/upload?url=/Users/dasik/Desktop/photo5388829697913237096.jpg"/>--%>
</body>
</html>
