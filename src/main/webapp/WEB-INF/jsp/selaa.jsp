<%-- 
    Document   : selaa
    Created on : 19.10.2012, 15:32:16
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css" /> 
    </head>
    <body>
        <div id="tabit">

            <ul>
                <li><a href="${pageContext.request.contextPath}/harjoittelija/lisaa-harjoitus" class ="tab">Lis‰‰ harjoitus</a></li>
                <li><a href="${pageContext.request.contextPath}/harjoittelija/selaa" class="tab" >Selaa harjoituksia</a></li>
                <li><a href="${pageContext.request.contextPath}/harjoittelija/tilasto" class="tab">Tilastot</a></li>
                <li><a href="${pageContext.request.contextPath}/harjoittelija/asetukset" class="tab">Asetukset</a></li>                                                                                
                <li><a href="${pageContext.request.contextPath}/logout" class ="vika_linkki" class="tab">Kirjaudu ulos</a></li>
            </ul>
        </div>
        <div id ="sisalto_container">
            <div id="logo">Harjoitusseuranta</div>


            <div id="sisalto_laatikko">
         
            </div>

        </div>
    </body>

</html>