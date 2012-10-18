<%-- 
    Document   : harjoittelija
    Created on : 18.10.2012, 15:12:17
    Author     : Lalli
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Harjoittelija</title>
    </head>
    <body>
        <h1>${harjoittelija.nimi}</h1>

        <form:form commandName="harjoitus" action="${pageContext.request.contextPath}/harjoittelija/${harjoittelija.id}/harjoitus" method="POST" >
            Alkamisaika (pv.kk.v hh.mm): <form:input path="alkamisaika" /><form:errors path="alkamisaika"/><br/>
            Kesto (minuuttia): <form:input path="kesto" /><form:errors path="kesto"/><br/>
            Teho (1-5): <form:input path="teho" /><form:errors path="teho" /><br/>
            Paikka:  <form:input path="paikka" /><form:errors path="paikka"/><br/>
            Tyyppi: <form:input path="tyyppi" /><form:errors path="tyyppi"/><br/>
            Sisältö: <form:input path="sisalto" /><form:errors path="sisalto"/><br/>
            <input type="submit">
        </form:form>
            <p>${message}</p><br/>

        <a href="${pageContext.request.contextPath}/logout">Kirjaudu ulos</a>

    </body>
</html>
