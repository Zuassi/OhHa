
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form method="POST" id="seuranta-avaimet" action="${pageContext.request.contextPath}/harjoittelija/asetukset/poista_avain">
    <select size="8" name="avainId" id="avaimet" multiple="yes" onChange="tarkastaArvo();"> 
        <c:forEach var="avain" items="${avaimet}">
            <option value="${avain.id}">${avain}</option>
        </c:forEach>
    </select>

    <p id="kopioitava"></p>
    <br/>
    <input type="submit" class="input_nappula ei_float_input_nappula" value="Poista">
    <br/>
</form>
