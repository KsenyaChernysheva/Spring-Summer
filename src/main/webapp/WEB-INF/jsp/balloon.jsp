<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Balloon</title>
</head>
<body>
<form:form method="POST" modelAttribute="reasonForm">
    <c:forEach var="reasonItem" items="${reasonList}">
        <form:radiobutton path="reason" value="${reasonItem.id}" label="${reasonItem.name}"
                          checked="${reasonForm.reason.id == reasonItem.id ? 'checked':''}"/><br/>
    </c:forEach>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
</form:form>

</body>
</html>
