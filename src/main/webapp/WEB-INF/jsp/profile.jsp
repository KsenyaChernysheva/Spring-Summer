<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<t:base_layout title="Profile">

    <a href="${s:mvcUrl('PC#profile').build()}">Profile ${pageContext.request.userPrincipal.name}</a>
    <form id="logoutForm" method="POST" action="<c:url value="/logout"/>">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <a onclick="document.forms['logoutForm'].submit()">Logout</a>

    <c:forEach var="userReasonItem" items="${userReason}">
        <p>${userReasonItem.country} ${userReasonItem.reason.name} ${userReasonItem.comment}</p>
        <form:form modelAttribute="deleteForm" method="post">
            <button type="submit" name="delete">Delete</button>
            <form:hidden path="reasonId" value="${userReasonItem.id}"/>
        </form:form>
    </c:forEach>

</t:base_layout>
