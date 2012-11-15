<%-- 
    Document   : rekisteroidy
    Created on : 11.11.2012, 22:11:13
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!--"fail"-->
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
