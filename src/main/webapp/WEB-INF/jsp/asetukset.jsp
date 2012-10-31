<%-- 
    Document   : harjoittelija
    Created on : 18.10.2012, 15:12:17
    Author     : Lalli
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.request.contextPath}/resources/code.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.1.min.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css" /> 
    </head>
    <body>
         <div id="tabit">

            <ul>
                <li><a href="${pageContext.request.contextPath}/harjoittelija/lisaa-harjoitus" class ="tab">Lisää harjoitus</a></li>
                <li><a href="${pageContext.request.contextPath}/harjoittelija/selaa" class="tab" >Selaa harjoituksia</a></li>
                <li><a href="${pageContext.request.contextPath}/harjoittelija/tilasto" class="tab">Tilastot</a></li>
                <li><a href="${pageContext.request.contextPath}/harjoittelija/asetukset" class="tab">Asetukset</a></li>                                                                                
                <li><a href="${pageContext.request.contextPath}/logout" class ="vika_linkki" class="tab">Kirjaudu ulos</a></li>
            </ul>
        </div>

        <div id ="sisalto_container">

            <div id="logo">Harjoitusseuranta</div>
         
            <div id="sisalto_laatikko">
                <article>
                    <h2>Seuranta-Avaimet: </h2><br/>
                    <form method="POST" action="${pageContext.request.contextPath}/harjoittelija/asetukset/poista_avain">
                        <select size="8" name="avainId" id="avaimet" multiple="yes" onChange="tarkastaArvo();"> 
                            <c:forEach var="avain" items="${avaimet}">
                                <option value="${avain.id}">${avain}</option>
                            </c:forEach>
                        </select>
                            
                        <p id="kopioitava"></p>
                        <br/>
                        <input type="submit" class="input_nappula ei_float_input_nappula" value="Poista">
                        <br/>
                    </form>
                        
                </article>
                <article>
                    <h2>Luo uusi avain:</h2><br/>
                        
                    <table>
                        
                        <form method="POST" action="${pageContext.request.contextPath}/harjoittelija/asetukset/luo_avain">
                            <tr><td> Kenelle:</td> <td><input type="text" name="nimi"></td></tr>
                                
                                
                            <tr><td/><td><input type="submit" class="input_nappula" value="Luo avain"></td></tr>
                            <p>${avain_message}</p>
                        </form>
                    </table>
                        
                </article>
                <article>
                    
                    <h2>Salasana: </h2><br/>
                    <form method="POST" action="${pageContext.request.contextPath}/harjoittelija/asetukset/salasana">
                        <table>
                            <tr><td>Vanha salasana:</td><td><input type="password" name="vanha_salasana"></td></tr>
                            <tr><td>Uusi salanana:</td> <td><input type="password" name="uusi_salasana"></td></tr>
                            <tr><td>Uusi salasana:</td><td><input type="password" name="uusi_salasana2"></td></tr>
                            <tr><td></td><td><input class="input_nappula" type ="submit" value="Vaihda salasana"></td></tr>
                        </table>
                        ${message}
                        <br/>
                </article>
                        </div>

                        </div>
                        </body>

                        </html>