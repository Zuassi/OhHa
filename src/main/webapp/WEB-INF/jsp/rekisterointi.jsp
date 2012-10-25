<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
                <h2 class="otsikko">Rekisteröidy</h2>
                <table>
                    <tr>
                    <p>${message}</p>
                        <form:form commandName="harjoittelija" action="${pageContext.request.contextPath}/rekisterointi" method="POST">
                            <td> Käyttäjänimi </td><td> <form:input path="nimi" class="input"/><form:errors path="nimi" /></td></tr> <br/>
                        <tr><td>Salasana</td>  <td><form:input path="salasana" class="input" /><form:errors path="salasana"/></td></tr><br/>
                        <td/><td><input type="submit" class="input" id="kirjaudu_nappula" value="Rekisteröidy"/></td></table>
                        </form:form>

                </table>
            </div>

        </div>
    </body>

</html>