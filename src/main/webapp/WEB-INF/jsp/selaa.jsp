<%-- 
    Document   : selaa
    Created on : 19.10.2012, 15:32:16
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
     <a href="${pageContext.request.contextPath}/harjoittelija">Takaisin harjoittelijasivulle</a>
        <table border="1">
            <tr>
                <th><a href="${pageContext.request.contextPath}/harjoittelija/selaa/pvm">Alkamisaika</a></th>
                <th>Kesto</th>
                <th><a href="${pageContext.request.contextPath}/harjoittelija/selaa/teho">Teho</a></th>
                <th>Treenipaikka</th>
                <th>Tyyppi</th>
                <th>Sisältö</th>
                <th>Muokkaa</th>
                <th>Poista</th>

                <c:forEach var="harjoitus" items="${harjoitukset}">

                <tr>
                    <td>${harjoitus.alkamisaika}</td>
                    <td>${harjoitus.kesto}min</td>
                    <td>${harjoitus.teho}</td>
                    <td>${harjoitus.paikka}</td>
                    <td>${harjoitus.tyyppi}</td>
                    <td><a href="${pageContext.request.contextPath}/harjoittelija/harjoitus/${harjoitus.id}">Näytä</a></td>
                    <td><a href="${pageContext.request.contextPath}/harjoittelija/harjoitus/muokkaa/${harjoitus.id}">Muokkaa</a></td>
                    <td><a href="${pageContext.request.contextPath}/harjoittelija/poista-harjoitus/${harjoitus.id}">Poista</a></td>
                </tr>
            </c:forEach>
        </tr>
    </table>
                <p>${size}</p>
</body>
</html>
