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

                <li><a href="#" data-value="harjoittelija/harjoitus" class ="tab tab_lisaa">Lisää harjoitus</a></li>
                <li><a href="#" class="tab tab_selaa" >Selaa harjoituksia</a></li>
                <li><a href="#" class="tab tab_tilasto">Tilastot</a></li>
                <li><a href="#" class="tab tab_asetukset">Asetukset</a></li>                                                                                
                <li><a href="${pageContext.request.contextPath}/logout" onclick="displayArticle(4);" class ="vika_linkki" class="tab">Kirjaudu ulos</a></li>
            </ul>
        </div>

        <div id ="sisalto_container">
            <div id="logo">Harjoitusseuranta</div>


            <div id="sisalto_laatikko">
                <div id="sisalto">

                    <form:form commandName="harjoitus" action="${pageContext.request.contextPath}/harjoittelija/harjoitus" method="POST" >
                        <table id="harjoitus_table">
                            <tr>
                                <td>Alkamisaika (pv.kk.vvvv hh.mm):</td> <td><form:input class="lisaa_alkamisaika" path="alkamisaika" /></td><td><form:errors path="alkamisaika"/></td>
                            </tr><tr>
                                <td> Kesto (minuuttia):</td> <td><form:input class="lisaa_kesto" path="kesto" /></td><td><form:errors path="kesto"/></td>
                            </tr><tr>
                                <td>Teho (1-5):</td> <td><form:input path="teho" class="lisaa_teho" /></td><td><form:errors path="teho" /></td>
                            </tr><tr>
                                <td>Paikka:</td><td><form:input path="paikka" class="lisaa_paikka" /></td><td><form:errors path="paikka"/></td>
                            </tr><tr>
                                <td>Tyyppi:</td><td><form:select class="varitettava" path="tyyppi" items="${sallitutTyypit}"/></td>
                                <td><form:errors path="tyyppi" /></td>
                            </tr><tr>
                                <td>Sisältö:</td> <td><form:textarea path="sisalto" class="lisaa_sisalto"/></td><td><form:errors path="sisalto"/></td>
                            </tr><tr>
                                <td></td><td><input type="submit" class="lisaa_harjoitus input_nappula"></td></tr>
                            <p>${lisatty}</p>
                        </table>
                    </form:form>
                    <center> <p id="harjoitus_lisatty"></p> </center>
                </div>

            </div>

        </div>



    </body>

</html>