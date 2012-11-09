<%-- 
    Document   : selaa
    Created on : 9.11.2012, 18:51:22
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<div id ="selaa">

    <table id="selaa_table">
        <tr class="varitettava"">
            <th><a href="${pageContext.request.contextPath}/home?jarjestys=alkamisaika&sivuNumero=${sivuNumero}">Alkamisaika</a></th>
            <th><a href="${pageContext.request.contextPath}/home?jarjestys=kesto&sivuNumero=${sivuNumero}">Kesto</a></th>
            <th><a href="${pageContext.request.contextPath}/home?selaa?jarjestys=teho&sivuNumero=${sivuNumero}">Teho</a></th>
            <th><a href="${pageContext.request.contextPath}/home?selaa?jarjestys=paikka&sivuNumero=${sivuNumero}">Treenipaikka</a></th>
            <th><a href="${pageContext.request.contextPath}/home?selaa?jarjestys=tyyppi&sivuNumero=${sivuNumero}">Tyyppi</a></th>
            <th>Sisältö</th>
            <th>Muokkaa</th>
            <th>Poista</th>



            <c:forEach var="harjoitus" items="${harjoitukset}">

            <tr class="rivi">
                <td><fmt:formatDate type="both" value="${harjoitus.alkamisaika}"/></td>
                <td>${harjoitus.kesto}min</td>
                <td>${harjoitus.teho}</td>
                <td>${harjoitus.paikka}</td>
                <td>${harjoitus.tyyppi}</td>
                <td><a href="#" data-value="${pageContext.request.contextPath}/harjoittelija/harjoitus/${harjoitus.id}" class="nayta">Näytä</a></td>
                <td><a href="#" data-value="${pageContext.request.contextPath}/harjoittelija/harjoitus/muokkaa/${harjoitus.id}" class="muokkaa">Muokkaa</a></td>
                <td><a href="#" data-value="${pageContext.request.contextPath}/harjoittelija/poista-harjoitus/${harjoitus.id}" class="poista">Poista</a></td>
            </tr>
        </c:forEach>
        </tr>

    </table>


    <c:if test="${sivutus}">
        <p>Sivut:  <c:forEach var="i" begin="0" end="${sivumaara-1}">
                <a href="${pageContext.request.contextPath}/home?jarjestys=${jarjestys}&sivuNumero=${i+1}">${i+1}</a>     
            </c:forEach></p>
        </c:if>
</div>
<p id="treenisisalto" class="hidden"></p>  
<div id="muokkaa">

</div>
