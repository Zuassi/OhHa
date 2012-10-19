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
        <h1>Hello World!</h1>
        <table border="1">
            <tr>
                <th><a href="${pageContext.request.contextPath}/harjoittelija/selaa/pvm">Alkamisaika</a></th>
                <th>Kesto</th>
                <th><a href="${pageContext.request.contextPath}/harjoittelija/selaa/teho">Teho</a></th>
                <th>Treenipaikka</th>
                <th>Tyyppi</th>
                <th>Sisältö</th>

                <c:forEach var="harjoitus" items="${harjoittelija.harjoitukset}">

                <tr>
                    <td>${harjoitus.alkamisaika}</td>
                    <td>${harjoitus.kesto}min</td>
                    <td>${harjoitus.teho}</td>
                    <td>${harjoitus.paikka}</td>
                    <td>${harjoitus.tyyppi}</td>
                    <td><a href="#">Näytä</a></td>
                </tr>
            </c:forEach>
        </tr>
    </table>
</body>
</html>
