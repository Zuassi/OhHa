<%-- 
    Document   : selaa
    Created on : 19.10.2012, 15:32:16
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
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
            <th>Sis�lt�</th>
            <th>Muokkaa</th>
            <th>Poista</th>



            <c:forEach var="harjoitus" items="${harjoitukset}">

            <tr class="rivi">
                <td><fmt:formatDate type="both" value="${harjoitus.alkamisaika}"/></td>
                <td>${harjoitus.kesto}min</td>
                <td>${harjoitus.teho}</td>
                <td>${harjoitus.paikka}</td>
                <td>${harjoitus.tyyppi}</td>
                  <td><a href="#" data-value="${pageContext.request.contextPath}/harjoittelija/harjoitus/${harjoitus.id}" class="nayta">N�yt�</a></td>
                <td><a href="${pageContext.request.contextPath}/harjoittelija/harjoitus/muokkaa/${harjoitus.id}">Muokkaa</a></td>
                <td><a href="${pageContext.request.contextPath}/harjoittelija/poista-harjoitus/${harjoitus.id}" class="poista">Poista</a></td>
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