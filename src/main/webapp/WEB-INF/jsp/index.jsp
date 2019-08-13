<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>01 - My Google Map</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://api-maps.yandex.ru/2.1/?apikey=e648a3cb-0b28-4f2e-a85e-6e0702cbf3e1&lang=ru_RU"
            type="text/javascript">
    </script>
</head>
<body>
<div class="container">

    <a href="${s:mvcUrl('PC#profile').build()}">Profile ${pageContext.request.userPrincipal.name}</a>
    <form id="logoutForm" method="POST" action="<c:url value="/logout"/>">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <a onclick="document.forms['logoutForm'].submit()">Logout</a>

</div>
<div id="filter" style="width: 100%; height: 10vh; background-color: white">
    <form:form method="POST" modelAttribute="reasonForm">
        <c:forEach var="reasonItem" items="${reasonList}">
            <form:radiobutton path="id" value="${reasonItem.id}" label="${reasonItem.name}"
                              checked="${reasonForm.id == reasonItem.id ? 'checked':''}"/><br/>
        </c:forEach>
    </form:form>
</div>

<div id="map" style="width: 100%; height: 80vh"></div>
<script type="text/javascript">
    ymaps.ready(init);

    function init() {
        var myMap = new ymaps.Map("map", {
            center: [55.76, 37.64],
            zoom: 7
        });
        myMap.events.add('click', function (e) {
            var coords = e.get('coords');
            var coordsR = [coords[1], coords[0]];
            console.log(coords);
            fetch("https://geocode-maps.yandex.ru/1.x/?apikey=e648a3cb-0b28-4f2e-a85e-6e0702cbf3e1&format=json&lang=en_US&geocode=" + coordsR.join(","), {
                method: 'GET',
                cache: 'no-cache'
            })
                .then(response => response.json())
                .then(data => data.response.GeoObjectCollection.featureMember[0].GeoObject.metaDataProperty.GeocoderMetaData.AddressDetails.Country.CountryName)
                .then(country => {
                    const reasons = document.getElementsByName("id");
                    var reasonId = -1;
                    for (var i = 0; i < reasons.length; i++) {
                        if (reasons[i].checked) {
                            reasonId = reasons[i].value;
                        }
                    }
                    return {country: country, reasonId: reasonId}
                })
                .then(({country, reasonId}) => fetch("<c:url value="/reasons"/>?country=" + country + "&reasonId=" + reasonId, {
                    method: 'GET',
                    cache: 'no-cache'
                }))
                .then(response => response.text())
                .then(htmlData => myMap.balloon.open(coords,
                    {
                        contentHeader: 'Данные о стране',
                        contentBody: htmlData,
                    }
                ))

        });
    }
</script>
</body>
</html>