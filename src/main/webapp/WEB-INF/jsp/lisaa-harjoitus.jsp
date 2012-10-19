<%-- 
    Document   : lisaa_harjoitus
    Created on : 19.10.2012, 14:58:34
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
    <form:form commandName="harjoitus" action="${pageContext.request.contextPath}/harjoittelija/harjoitus" method="POST" >
        Alkamisaika (pv.kk.v hh.mm): <form:input path="alkamisaika" /><form:errors path="alkamisaika"/><br/>
        Kesto (minuuttia): <form:input path="kesto" /><form:errors path="kesto"/><br/>
        Teho (1-5): <form:input path="teho" /><form:errors path="teho" /><br/>
        Paikka:  <form:input path="paikka" /><form:errors path="paikka"/><br/>
        Tyyppi: <form:input path="tyyppi" /><form:errors path="tyyppi"/><br/>
        Sisältö: <form:input path="sisalto" /><form:errors path="sisalto"/><br/>
        <input type="submit">
    </form:form>
          
</body>
</html>
