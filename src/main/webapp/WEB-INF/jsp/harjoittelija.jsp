<%-- 
    Document   : harjoittelija
    Created on : 18.10.2012, 15:12:17
    Author     : Lalli
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Harjoittelija</title>
    </head>
    <body>
        <h1>${harjoittelija.nimi}</h1>
        <a href ="${pageContext.request.contextPath}/harjoittelija/lisaa-harjoitus">Lisää harjoitus</a><br/>
        <a href ="${pageContext.request.contextPath}/harjoittelija/selaa">Selaa harjoituksia</a><br/>
        <a href ="${pageContext.request.contextPath}/harjoittelija/tilasto">Tilastot</a><br/>
        <p>${message}</p><br/>


        <a href="${pageContext.request.contextPath}/logout">Kirjaudu ulos</a>

    </body>
</html>
