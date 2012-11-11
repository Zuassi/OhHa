<%-- 
    Document   : asetukset_salasana
    Created on : 11.11.2012, 22:02:42
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
     

                <h2>Salasana: </h2><br/>
                <form method="POST" id="vaihda_salasana" action="${pageContext.request.contextPath}/harjoittelija/asetukset/salasana">
                    <table>
                        <tr><td>Vanha salasana:</td><td><input type="password" name="vanha_salasana"></td></tr>
                        <tr><td>Uusi salanana:</td> <td><input type="password" name="uusi_salasana"></td></tr>
                        <tr><td>Uusi salasana:</td><td><input type="password" name="uusi_salasana2"></td></tr>
                        <tr><td></td><td><input class="input_nappula asetukset_salasana" type ="submit" value="Vaihda salasana"></td></tr>
                    </table>
                    ${message}
                    <br/>
 
