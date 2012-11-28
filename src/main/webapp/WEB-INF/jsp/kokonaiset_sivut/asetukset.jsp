<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<article>
                <h2>Seuranta-Avaimet: </h2><br/>
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

            </article>
            <article>
                <h2>Luo uusi avain:</h2><br/>
                <p> Seuranta-avaimen avulla vieras voi seurata harjoittelujesi tilastoja.
Luo jokaiselle seuraajallesi henkilökohtainen avain jotta voit hallinnoita niitä helposti myöhemmin.</p>
                <br/>

                <table>

                    <form method="POST" id="luoAvain" action="${pageContext.request.contextPath}/harjoittelija/asetukset/luo_avain">
                        <tr><td> Kenelle:</td> <td><input type="text" name="nimi"></td></tr>


                        <tr><td/><td><input type="submit" class="input_nappula luo_avain" value="Luo avain"></td></tr>
                        <p>${avain_message}</p>
                    </form>
                </table>

            </article>
            <article id="asetukset_salasana">

                <h2>Salasana: </h2><br/>
                <form method="POST" id="vaihda_salasana" action="${pageContext.request.contextPath}/harjoittelija/asetukset/salasana">
                    <table>
                        <tr><td>Vanha salasana:</td><td><input type="password" name="vanha_salasana"></td></tr>
                        <tr><td>Uusi salanana:</td> <td><input type="password" name="uusi_salasana"></td></tr>
                        <tr><td>Uusi salasana:</td><td><input type="password" name="uusi_salasana2"></td></tr>
                        <tr><td></td><td><input class="input_nappula asetukset_salasana" type ="submit" value="Vaihda salasana"></td></tr>
                    </table>
                    ${message}
                    <br/>
            </article>