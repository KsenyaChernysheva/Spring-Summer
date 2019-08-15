<%@tag description="Default layout tag" pageEncoding="utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="title" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><c:if test="${not empty title}"> ${title}</c:if></title>
</head>
<body>
<div class="container">

    <jsp:doBody/>

</div>
</body>
</html>