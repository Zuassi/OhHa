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
                --><a href="${pageContext.request.contextPath}/harjoittelija/asetukset">Asetukset</a><!--                                                                                  
                --><a href="${pageContext.request.contextPath}/logout" class ="vika_linkki">Kirjaudu ulos</a>
                </div>	
                <div id="sisalto_laatikko">
                    <h2>Seuranta-Avain: </h2><br/>
                    ${seurantaavain}
                    <form method="POST" action="${pageContext.request.contextPath}/harjoittelija/asetukset/seurantaavain">
                        <br/>  <input type="submit" value="Uusi seuranta-avain"><br/><br/>
                        <p><b>Huom! Hakiessasi uuden avaimen vanha lakkaa toimimasta</b></p>
                        
                    </form><br/>
                         <h2>Salasana: </h2><br/>
                         <form method="POST" action="${pageContext.request.contextPath}/harjoittelija/asetukset/salasana">
                             <table>
                                 <tr><td>Vanha salasana:</td><td><input type="password" name="vanha_salasana"></td></tr>
                                 <tr><td>Uusi salanana:</td> <td><input type="password" name="uusi_salasana"></td></tr>
                                 <tr><td>Uusi salasana:</td><td><input type="password" name="uusi_salasana2"></td></tr>
                                 <tr><td></td><td><input type ="submit" value="Vaihda salasana"></td></tr>
                             </table>
                             ${message}
                </div>
      
        </div>
    </body>

</html>