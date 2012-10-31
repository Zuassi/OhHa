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
        <script src="${pageContext.request.contextPath}/resources/code.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
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
                
                    <table id="tilasto_table">

                        <form:form commandName="harjoitus" action="${pageContext.request.contextPath}/harjoittelija/harjoitus" method="POST" >
                            <tr>
                                <td>Alkamisaika (pv.kk.vvvv hh.mm):</td> <td><form:input path="alkamisaika" /></td><td><form:errors path="alkamisaika"/></td>
                            </tr><tr>
                                <td> Kesto (minuuttia):</td> <td><form:input path="kesto" /></td><td><form:errors path="kesto"/></td>
                            </tr><tr>
                                <td>Teho (1-5):</td> <td><form:input path="teho" /></td><td><form:errors path="teho" /></td>
                            </tr><tr>
                                <td>Paikka:</td><td><form:input path="paikka" /></td><td><form:errors path="paikka"/></td>
                            </tr><tr>
                                <td>Tyyppi:</td><td><form:select class="varitettava" path="tyyppi" items="${sallitutTyypit}"/></td>
                                <td><form:errors path="tyyppi" /></td>
                            </tr><tr>
                                <td>Sisältö:</td> <td><form:textarea path="sisalto" /></td><td><form:errors path="sisalto"/></td>
                            </tr><tr>
                                <td></td><td><input type="submit" class="input_nappula"></td></tr>
                        </table>
                    </form:form>

          

            </div>

        </div>



    </body>

</html>