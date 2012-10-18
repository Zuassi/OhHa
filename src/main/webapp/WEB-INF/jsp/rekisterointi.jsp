<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rekisteröinti</title>
    </head>
    <body>
         <form:form commandName="harjoittelija" action="${pageContext.request.contextPath}/rekisterointi" method="POST">
          
            <form:input path="nimi" /><form:errors path="nimi" /><br/>
            <form:input path="salasana" /><form:errors path="salasana"/><br/>
            <input type="submit">
        </form:form>
    </body>
</html>
