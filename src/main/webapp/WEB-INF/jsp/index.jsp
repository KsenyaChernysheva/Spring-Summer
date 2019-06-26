<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Profile ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>

    </c:if>

</div>
<div id="filter" style="width: 100%; height: 10vh; background-color: white">
    <form method="post">
        <input type="checkbox" name="option1" value="a1">like nature
        <input type="checkbox" name="option2" value="a2">like architecture
        <input type="submit" value="Filter">
    </form>
</div>

<div id="profil">
    <a href="${contextPath}/registration">Register</a>
    <a href="${contextPath}/profile">Profile</a>
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
            $.get("https://geocode-maps.yandex.ru/1.x/?apikey=e648a3cb-0b28-4f2e-a85e-6e0702cbf3e1&format=json&geocode=" + coordsR.join(","), function (data) {

                var country = data.response.GeoObjectCollection.featureMember[0].GeoObject.metaDataProperty.GeocoderMetaData.AddressDetails.Country.CountryName;

                myMap.balloon.open(coords,
                    {
                        contentHeader: country,
                        contentBody: '',
                    }
                );

            });

        });
    }
</script>
</body>
</html>