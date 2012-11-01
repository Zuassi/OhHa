<%-- 
    Document   : lisaa_harjoitus
    Created on : 19.10.2012, 14:58:34
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>

    </head>
    <body>




        <form:form commandName="harjoitus" action="${pageContext.request.contextPath}/harjoittelija/harjoitus" method="POST" >
            <table id="harjoitus_table">

                <tr>
                    <td>Alkamisaika (pv.kk.vvvv hh.mm):</td> <td><form:input path="alkamisaika" /></td><td><form:errors path="alkamisaika"/></td>
                </tr><tr>
                    <td> Kesto (minuuttia):</td> <td><form:input path="kesto" /></td><td><form:errors path="kesto"/></td>
                </tr><tr>
                    <td>Teho (1-5):</td> <td><form:input path="teho" /></td><td><form:errors path="teho" /></td>
                </tr><tr>
                    <td>Paikka:</td><td><form:input path="paikka" /></td><td><form:errors path="paikka"/></td>
                </tr><tr>
                    <td>Tyyppi:</td><td><form:select class="varitettava" path="tyyppi" items="${sallitutTyypit}"/></td>
                    <td><form:errors path="tyyppi" /></td>
                </tr><tr>
                    <td>Sisältö:</td> <td><form:textarea path="sisalto" /></td><td><form:errors path="sisalto"/></td>
                </tr><tr>
                    <td></td><td><input type="submit" class="input_nappula"></td></tr>
                <p>${lisatty}</p>
            </table>


            <table id="selaa_table">
                <tr class="varitettava"">
                    <th><a href="${pageContext.request.contextPath}/harjoittelija/selaa?jarjestys=alkamisaika&sivuNumero=${sivuNumero}">Alkamisaika</a></th>
                    <th><a href="${pageContext.request.contextPath}/harjoittelija/selaa?jarjestys=kesto&sivuNumero=${sivuNumero}">Kesto</a></th>
                    <th><a href="${pageContext.request.contextPath}/harjoittelija/selaa?jarjestys=teho&sivuNumero=${sivuNumero}">Teho</a></th>
                    <th><a href="${pageContext.request.contextPath}/harjoittelija/selaa?jarjestys=paikka&sivuNumero=${sivuNumero}">Treenipaikka</a></th>
                    <th><a href="${pageContext.request.contextPath}/harjoittelija/selaa?jarjestys=tyyppi&sivuNumero=${sivuNumero}">Tyyppi</a></th>
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
                        <td><a href="${pageContext.request.contextPath}/harjoittelija/harjoitus/${harjoitus.id}">Näytä</a></td>
                        <td><a href="${pageContext.request.contextPath}/harjoittelija/harjoitus/muokkaa/${harjoitus.id}">Muokkaa</a></td>
                        <td><a href="${pageContext.request.contextPath}/harjoittelija/poista-harjoitus/${harjoitus.id}">Poista</a></td>
                    </tr>
                </c:forEach>
            </tr>

        </table>

    </form:form>









</body>
</html>