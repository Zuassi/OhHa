<%-- 
    Document   : lisaa_harjoitus
    Created on : 19.10.2012, 14:58:34
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.1.min.js"></script>
        <script src="http://malsup.github.com/jquery.form.js"></script> 
        <script src="${pageContext.request.contextPath}/resources/code.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css" /> 
        <title>Harjoitusseuranta - Kirjautunut</title>
    </head>
    <body>
        <div id="tabit">

            <ul>
                <div id="valikko">HARJOITUSSEURANTA</div>

                <li><a href="#" class ="tab tab_lisaa">Lis‰‰ harjoitus</a></li>
                <li><a href="#" class="tab tab_selaa" >Selaa harjoituksia</a></li>
                <li><a href="#" class="tab tab_tilasto">Tilastot</a></li>
                <li><a href="#" class="tab tab_asetukset">Asetukset</a></li>                                                                                
                <li><a href="${pageContext.request.contextPath}/logout" onclick="displayArticle(4);" class ="vika_linkki" class="tab">Kirjaudu ulos</a></li>
            </ul>
        </div>

        <div id ="sisalto_container">
            <div id="logo">Harjoitusseuranta</div>


            <div id="sisalto_laatikko">
               

            </div>

        </div>



    </body>

</html>