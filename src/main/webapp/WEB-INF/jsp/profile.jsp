<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
<div class="container">

    <a href="${s:mvcUrl('PC#profile').build()}">Profile ${pageContext.request.userPrincipal.name}</a>
    | <a href="${s:mvcUrl('UC#login').build()}">Logout</a>

    <c:forEach var="userReasonItem" items="${userReason}">
        <p>${userReasonItem.country} ${userReasonItem.reason.name} ${userReasonItem.comment}</p>
        <form:form modelAttribute="deleteForm" method="post">
            <button type="submit" name="delete">Delete</button>
            <form:hidden path="reasonId" value="${userReasonItem.id}"/>
        </form:form>
    </c:forEach>

</div>
</body>
</html>

