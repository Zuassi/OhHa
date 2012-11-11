<%-- 
    Document   : kirjaudu
    Created on : 11.11.2012, 22:21:37
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 class="otsikko">Kirjaudu</h2>
<p class="keskitettava_message">${login_message}</p>
<table>
    <tr>
    <form action="${pageContext.request.contextPath}/kirjaudu" method="POST">
        <td> Käyttäjänimi </td><td> <input type="text" name="nimi" id="nimi" class="input kirjaudu_nimi"/></td></tr> <br/>
        <tr><td>Salasana</td>  <td><input type="password" name ="salasana" class="input kirjaudu_salasana" id="nimi"></td></tr><br/>
        <td/><td><input type="submit" class="input_nappula kirjaudu_nappula" id="kirjaudu_nappula" value="Kirjaudu"/></td>
    </form></table>


