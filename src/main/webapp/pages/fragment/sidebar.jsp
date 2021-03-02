<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="property.text"/>
<div class="col-md-3 col-xl-2 bd-sidebar bg-light">
    <nav class="nav flex-column bd-links" style="float: left;">
        <a class="nav-link bd-link" href="${pageContext.request.contextPath}/controller?command=personal_area_page">
            <fmt:message key="sidebar.path.personalArea"/> </a>

        <a class="nav-link bd-link" href="${pageContext.request.contextPath}/controller?command=about_us_page">
            <fmt:message key="sidebar.path.aboutUs"/> </a>

        <a class="nav-link bd-link" href="${pageContext.request.contextPath}/controller?command=review_page">
            <fmt:message key="sidebar.path.review"/> </a>

        <a class="nav-link bd-link" href="${pageContext.request.contextPath}/controller?command=main_page"> <fmt:message
                key="sidebar.path.main"/> </a>
    </nav>
</div>
