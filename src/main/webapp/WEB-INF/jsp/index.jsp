<%-- 
    Document   : index
    Created on : 18.10.2012, 15:39:47
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css" /> 
    </head>
    <body>
        <div id ="login_container">

            <div id="logo">Harjoitusseuranta</div>
                 
            <div id="tabit">
                <a href="${pageContext.request.contextPath}" class="eka_linkki"> Kirjaudu</a><a href="${pageContext.request.contextPath}/rekisterointi" class="vika_linkki"> Rekisteröidy </a>
            </div>	
            <div id="kirjaudu_laatikko">

                <h2 class="otsikko">Kirjaudu</h2>
                <table>
                    <tr>
                    <form action="${pageContext.request.contextPath}/kirjaudu" method="POST">
                        <td> Käyttäjänimi </td><td> <input type="text" name="nimi" id="nimi" class="input"/></td></tr> <br/>
                        <tr><td>Salasana</td>  <td><input type="password" name ="salasana" class="input" id="nimi"></td></tr><br/>
                        <td/><td><input type="submit" class="input" id="kirjaudu_nappula" value="Kirjaudu"/></td></table>
                </form>

                </table>
            </div>
        </div>

    </body>

</html>