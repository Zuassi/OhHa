<%-- 
    Document   : index
    Created on : 18.10.2012, 15:39:47
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <p>${message}</p>
        <form action="${pageContext.request.contextPath}/kirjaudu" method="POST">
            <label>Käyttäjänimi  <input type="text" name="nimi" id="nimi"/> </label><br/>
            <label>Salasana  <input type="password" name ="salasana" id="nimi"></label><br/>
    <input type="submit" value="Kirjaudu"/>
</form>
            
             <a href="${pageContext.request.contextPath}/rekisterointi">Rekisteröinti</a>
    </body>
</html>
