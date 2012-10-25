<%-- 
    Document   : muokkaa
    Created on : 20.10.2012, 16:01:55
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

                <table id="tilasto_table">

                    <form:form commandName="harjoitus" action="${pageContext.request.contextPath}/harjoittelija/harjoitus" method="POST" >
                        <tr>
                            <td>Alkamisaika (pv.kk.vvvv hh.mm):</td> <td><form:input path="alkamisaika" /></td><form:errors path="alkamisaika"/>
                        </tr><tr>
                            <td> Kesto (minuuttia):</td> <td><form:input path="kesto" /></td><form:errors path="kesto"/>
                        </tr><tr>
                            <td>Teho (1-5):</td> <td><form:input path="teho" /></td><form:errors path="teho" />
                        </tr><tr>
                            <td>Paikka:</td><td><form:input path="paikka" /></td><form:errors path="paikka"/>
                        </tr><tr>
                            <td>Tyyppi:</td><td><form:select path="tyyppi" items="${sallitutTyypit}"/></td>
                            <form:errors path="tyyppi" />  
                        </tr><tr>
                            <td>Sisältö:</td> <td><form:input path="sisalto" /></td><form:errors path="sisalto"/>
                        </tr><tr>
                            <td></td><td><input class="input" type="submit" value="Muokkaa"></td></tr>
                    </table>
                    <form:hidden path="id"  value="${harjoitus.id}" />
                </form:form>



                </table>
            </div>

        </div>
    </body>

</html>