<%-- 
    Document   : muokkaa
    Created on : 20.10.2012, 16:01:55
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

                    <form:form commandName="harjoitus" class="muokkaus" action="${pageContext.request.contextPath}/harjoittelija/harjoitus" method="POST" >
                        <table>
                        <tr>
                            <td>Alkamisaika (pv.kk.vvvv hh.mm):</td> <td><form:input path="alkamisaika" /></td><form:errors path="alkamisaika"/>
                        </tr><tr>
                            <td> Kesto (minuuttia):</td> <td><form:input path="kesto" /></td><form:errors path="kesto"/>
                        </tr><tr>
                            <td>Teho (1-5):</td> <td><form:input path="teho" /></td><form:errors path="teho" />
                        </tr><tr>
                            <td>Paikka:</td><td><form:input path="paikka" /></td><form:errors path="paikka"/>
                        </tr><tr>
                            <td>Tyyppi:</td><td><form:select path="tyyppi" items="${sallitutTyypit}"/></td>
                            <form:errors path="tyyppi" />  
                        </tr><tr>
                            <td>Sisältö:</td> <td><form:textarea path="sisalto" /></td><form:errors path="sisalto"/>
                        </tr><tr>
                            <td></td><td><input class="input_nappula" type="submit" value="Muokkaa"></td></tr>
                    </table>
                    <form:hidden path="id"  value="${harjoitus.id}" />
                </form:form>
        
