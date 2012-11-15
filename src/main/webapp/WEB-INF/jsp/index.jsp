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
                <li> <a href="#" class="tab tab_kirjaudu" data-value="${pageContext.request.contextPath}/kirjaudu"> Kirjaudu</a></li>
               <li> <a href="#" class="tab tab_rekisteroidy" data-value="${pageContext.request.contextPath}/rekisterointi"> Rekisteroidy</a></li>
               <li> <a href="#" class="tab tab_seuranta" data-value="${pageContext.request.contextPath}/vierasseuranta"> Seuranta</a></li>

            </ul>
        </div>

        <div id="logo">Harjoitusseuranta</div>
        <div id ="sisalto_container">




            <div id="sisalto_laatikko">

                <div class="keskitettava">
                    <h2 class="otsikko">Kirjaudu</h2>
<p class="keskitettava_message">${login_message}</p>
                    <table>
                        <tr>
                        <form action="${pageContext.request.contextPath}/kirjaudu" method="POST">
                            <td> Käyttäjänimi </td><td> <input type="text" name="nimi" id="nimi" class="input kirjaudu_nimi"/></td></tr> <br/>
                            <tr><td>Salasana</td>  <td><input type="password" name ="salasana" class="input kirjaudu_salasana" id="nimi"></td></tr><br/>
                            <td/><td><input type="submit" class="input_nappula kirjaudu_nappula" id="kirjaudu_nappula" value="Kirjaudu"/></td>
                        </form></table>

                </div>
            </div>


        </div>



        <div/>
    </body>

</html>