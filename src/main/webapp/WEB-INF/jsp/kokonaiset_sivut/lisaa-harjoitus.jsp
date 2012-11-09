<%-- 
    Document   : lisaa_harjoitus
    Created on : 19.10.2012, 14:58:34
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


                    <div id="sisalto">

                        <form:form commandName="harjoitus" action="${pageContext.request.contextPath}/harjoittelija/harjoitus" method="POST" >
                            <table id="harjoitus_table">
                                <tr>
                                    <td>Alkamisaika (pv.kk.vvvv hh.mm):</td> <td><form:input class="lisaa_alkamisaika" path="alkamisaika" /></td><td><form:errors path="alkamisaika"/></td>
                                </tr><tr>
                                    <td> Kesto (minuuttia):</td> <td><form:input class="lisaa_kesto" path="kesto" /></td><td><form:errors path="kesto"/></td>
                                </tr><tr>
                                    <td>Teho (1-5):</td> <td><form:input path="teho" class="lisaa_teho" /></td><td><form:errors path="teho" /></td>
                                </tr><tr>
                                    <td>Paikka:</td><td><form:input path="paikka" class="lisaa_paikka" /></td><td><form:errors path="paikka"/></td>
                                </tr><tr>
                                    <td>Tyyppi:</td><td><form:select class="varitettava" path="tyyppi" items="${sallitutTyypit}"/></td>
                                    <td><form:errors path="tyyppi" /></td>
                                </tr><tr>
                                    <td>Sisältö:</td> <td><form:textarea path="sisalto" class="lisaa_sisalto"/></td><td><form:errors path="sisalto"/></td>
                                </tr><tr>
                                    <td></td><td><input type="submit" class="lisaa_harjoitus input_nappula"></td></tr>
                                <p>${lisatty}</p>
                            </table>
                        </form:form>
                        <center> <p id="harjoitus_lisatty"></p> </center>
                    </div>