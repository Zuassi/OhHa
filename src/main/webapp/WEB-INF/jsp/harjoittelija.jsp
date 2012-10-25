<%-- 
    Document   : harjoittelija
    Created on : 18.10.2012, 15:12:17
    Author     : Lalli
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css" /> 
    </head>
    <body>
        <div id ="sisalto_container">

              <div id="logo">Harjoitusseuranta</div>
                <div id="tabit">
                    <!--
                --><a href="${pageContext.request.contextPath}/harjoittelija/lisaa-harjoitus" class ="eka_linkki">Lisää harjoitus</a><!--
                --><a href="${pageContext.request.contextPath}/harjoittelija/selaa">Selaa harjoituksia</a><!--
                --><a href="${pageContext.request.contextPath}/harjoittelija/tilasto">Tilastot</a><!--
                --><a href="${pageContext.request.contextPath}/logout" class ="vika_linkki">Kirjaudu ulos</a>
                </div>	
                <div id="sisalto_laatikko">
                	
                    
                    <h1>${harjoittelija.nimi}</h1>
        
                   

                   
                </div>
      
        </div>
    </body>

</html>