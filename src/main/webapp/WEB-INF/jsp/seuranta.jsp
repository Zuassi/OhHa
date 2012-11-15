<%-- 
    Document   : index
    Created on : 18.10.2012, 15:39:47
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>




    <center>
        <h2 class="otsikko">Seuranta</h2>
        <p>${seuranta_message}</p>
        <table>
            <tr>
            <form action="${pageContext.request.contextPath}/seuranta" method="POST" id="seuranta_avain">
                <td>Harjoitusseuranta avain </td><td> <input type="text" name="avain" class="input"/></td></tr> <br/>

                <td/><td><input type="submit" class="input seuranta_nappula" id="kirjaudu_nappula" value="Seuranta"/></td>
            </form>
        </table>
    </center>

    <c:if test="${seurantaAsetettu}">
        <div id="aikavali_saato"</div>
        <h2>Säädä oma tarkkailtava aikaväli</h2><br/>
        <table>
            <form:form commandName="aikavali" action="${pageContext.request.contextPath}/harjoittelija/tilasto" method="POST" >
                <tr><td>Alkamisaika (pv.kk.vvvv):</td><td><form:input class="aseta_alkamisaika" path="alkamisaika" /></td></tr>
                <tr><td>Loppumisaika (pv.kk.vvvv):</td><td><form:input path="loppumisaika" class="aseta_loppumisaika"/></td></tr>
                <tr><td/><td><input type="submit" class="aseta_submit input_nappula"></td></tr>
                    </form:form>
        </table>

    </div>
    <table id="tilasto_table">
        <p>${seuranta_error}</p>
        <tr>
            <td>
                <div class="tilasto_laatikko">
                    <h1>Yhteenveto</h1>

                    <b>Treenejä yhteensä tänään: </b>${tilasto.paiva} <br/>
                    <b>Treenejä yhteensä tällä viikolla: </b>${tilasto.viikko}<br/>
                    <b>Treenejä yhteensä tässä kuussa: </b>${tilasto.kuukausi}<br/>
                    <b>Treenejä yhteensä tänä vuonna: </b>${tilasto.vuosi}<br/>
                    <b>Treenejä asetetulla aikavälillä: </b>${tilasto.oma}<br/>
                </div>
            </td>
            <td>
                <div class="tilasto_laatikko">
                    <h1>Kovat treenit (teho 4-5)</h1><br/>
                    <b>Kovia treenejä tänään: </b>${tilasto.paivaKovat}<br/>
                    <b>Kovia treenejä tällä viikolla </b>${tilasto.viikkoKovat}<br/>
                    <b>Kovia treenejä tässä kuussa </b>${tilasto.kuukausiKovat}<br/>
                    <b>Kovia treenejä tänä vuonna </b>${tilasto.vuosiKovat}<br/>
                    <b>Kovia asetetulla aikavälillä: </b>${tilasto.omaKovat}<br/>
                </div>
            </td>
        </tr>
        <tr><td>
                <div class="tilasto_laatikko">
                    <h1>Lajitreenit</h1></br>
                    <b>Lajitreenejä tänään:</b>${tilasto.paivaLajiharjoitus}<br/>
                    <b>Lajitreenejä tällä viikolla:</b>${tilasto.viikkoLajiharjoitus}<br/>
                    <b>Lajitreenejä tässä kuussa:</b>${tilasto.kuukausiLajiharjoitus}<br/>
                    <b>Lajitreenejä tänä vuonna:</b>${tilasto.vuosiLajiharjoitus}<br/>
                    <b>Lajitreenejä asetetulla aikavälillä: </b>${tilasto.omaLajiharjoitus}<br/>
                </div>
            </td>
            <td>
                <div class="tilasto_laatikko">
                    <h1>Puntit</h1></br>
                    <b>Puntteja tänään:</b>${tilasto.paivaPuntti}<br/>
                    <b>Puntteja tällä viikolla:</b>${tilasto.viikkoPuntti}<br/>
                    <b>Puntteja tässä kuussa:</b>${tilasto.kuukausiPuntti}<br/>
                    <b>Puntteja tänä vuonna:</b>${tilasto.vuosiPuntti}<br/>
                    <b>Puntteja asetetulla aikavälillä: </b>${tilasto.omaPuntti}<br/>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="tilasto_laatikko">
                    <h1>Palauttavat</h1><br/>
                    <b>Palauttavia tänään:</b>${tilasto.paivaPalauttava}<br/>
                    <b>Palauttavia tällä viikolla:</b>${tilasto.viikkoPalauttava}<br/>
                    <b>Palauttavia tässä kuussa:</b>${tilasto.kuukausiPalauttava}<br/>
                    <b>Palauttavia tänä vuonna:</b>${tilasto.vuosiPalauttava}<br/>
                    <b>Palauttavia asetetulla aikavälillä: </b>${tilasto.omaPalauttava}<br/>
                </div>
            </td>
            <td>
                <div class="tilasto_laatikko">
                    <h1>Kilpailut</h1><br/>
                    <b>Kilpailut tässä kuussa:</b>${tilasto.kuukausiKilpailu}<br/>
                    <b>Kilpailut tänä vuonna::</b>${tilasto.vuosiKilpailu}<br/>
                    <b>Kilpailut asetetulla aikavälillä: </b>${tilasto.omaKilpailu}<br/>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="tilasto_laatikko">
                    <h1>Muut</h1><br/>
                    <b>Muita tänään:</b>${tilasto.paivaMuu}<br/>
                    <b>Muita tällä viikolla:</b>${tilasto.viikkoMuu}<br/>
                    <b>Muita tässä kuussa:</b>${tilasto.kuukausiMuu}<br/>
                    <b>Muita tänä vuonna:</b>${tilasto.vuosiMuu}<br/>
                    <b>Muita asetetulla aikavälillä: </b>${tilasto.omaKilpailu}<br/>
                </div>


            </td><td></td>
        </tr>



    </table>
</c:if>


