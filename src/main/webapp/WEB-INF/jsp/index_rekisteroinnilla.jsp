<%-- 
    Document   : index
    Created on : 18.10.2012, 15:39:47
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Harjoitusseuranta</title>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.1.min.js"></script>
        <script src="http://malsup.github.com/jquery.form.js"></script> 
        <script src="${pageContext.request.contextPath}/resources/code.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css" /> 
    </head>
    <body>

        <div id="tabit">
            <ul>
                <div id="valikko">HARJOITUSSEURANTA</div>
                <li> <a href="#" class="tab tab_kirjaudu"> Kirjaudu</a></li>
                <li> <a href="#" class="tab_rekisteroidy" onclick="displayArticle(1);" class="tab"> Rekisteröidy</a></li>

                <li><a href="#" class="tab_seuranta" onclick="displayArticle(2);" class="tab"> Seuranta</a></li>
            </ul>
        </div>

        <div id="logo">Harjoitusseuranta</div>
        <div id ="sisalto_container">




            <div id="sisalto_laatikko">

                <div class="keskitettava">
                    <h2 class="otsikko">Rekisteröidy</h2>
                    <p class="keskitettava_message">${register_message}</p>

                    <table>
                        <tr>

                            <form:form commandName="harjoittelija" action="${pageContext.request.contextPath}/rekisterointi" method="POST">
                                <td> Käyttäjänimi </td><td> <form:input path="nimi" class="input rekisteroidy_nimi"/><form:errors path="nimi" /></td></tr> <br/>
                            <tr><td>Salasana</td>  <td><form:password path="salasana" class="input rekisteroidy_salasana" /><form:errors path="salasana"/></td></tr><br/>
                            <td/><td><input type="submit" class="input_nappula rekisteroidy_nappula" id="kirjaudu_nappula" value="Rekisteröidy"/></td></table>
                            </form:form>


                </div>
            </div>


        </div>




    </body>

</html>