<%-- 
    Document   : index
    Created on : 18.10.2012, 15:39:47
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css" /> 
    </head>
    <body>
        <div id ="sisalto_container">

            <div id="logo">Harjoitusseuranta</div>

            <div id="tabit">
                <a href="${pageContext.request.contextPath}" class="eka_linkki"> Kirjaudu</a><!--
                --><a href="${pageContext.request.contextPath}/rekisterointi"> Rekisteröidy </a><!--
                --><a href="${pageContext.request.contextPath}/seuranta" class="vika_linkki"> Seuranta</a>
            </div>	

            <div id="sisalto_laatikko">
                <center>
                    <h2 class="otsikko">Seuranta</h2>
                    <p>${message}</p>
                    <table>
                        <tr>
                        <form action="${pageContext.request.contextPath}/seuranta" method="POST">
                            <td>Harjoitusseuranta avain </td><td> <input type="text" name="avain" class="input"/></td></tr> <br/>

                            <td/><td><input type="submit" class="input" id="kirjaudu_nappula" value="Seuranta"/></td>
                        </form>
                    </table>
                </center>
                    <c:if test="${seurantaAsetettu}">


             
                <table id="tilasto_table">
                    <tr>
                        <td>
                            <div class="tilasto_laatikko">
                                <h1>Yhteenveto</h1>

                                <b>Treenejä yhteensä tänään: </b>${tilasto.paiva} <br/>
                                <b>Treenejä yhteensä tällä viikolla: </b>${tilasto.viikko}<br/>
                                <b>Treenejä yhteensä tässä kuussa: </b>${tilasto.kuukausi}<br/>
                                <b>Treenejä yhteensä tänä vuonna: </b>${tilasto.vuosi}<br/>
                            </div>
                        </td>
                        <td>
                            <div class="tilasto_laatikko">
                                <h1>Kovat treenit (teho 4-5)</h1><br/>
                                <b>Kovia treenejä tänään: </b>${tilasto.paivaKovat}<br/>
                                <b>Kovia treenejä tällä viikolla </b>${tilasto.viikkoKovat}<br/>
                                <b>Kovia treenejä tässä kuussa </b>${tilasto.kuukausiKovat}<br/>
                                <b>Kovia treenejä tänä vuonna </b>${tilasto.vuosiKovat}<br/>
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
                            </div>
                        </td>
                        <td>
                            <div class="tilasto_laatikko">
                                <h1>Puntit</h1></br>
                                <b>Puntteja tänään:</b>${tilasto.paivaPuntti}<br/>
                                <b>Puntteja tällä viikolla:</b>${tilasto.viikkoPuntti}<br/>
                                <b>Puntteja tässä kuussa:</b>${tilasto.kuukausiPuntti}<br/>
                                <b>Puntteja tänä vuonna:</b>${tilasto.vuosiPuntti}<br/>
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
                            </div>
                        </td>
                        <td>
                            <div class="tilasto_laatikko">
                                <h1>Kilpailut</h1><br/>
                                <b>Kilpailut tässä kuussa:</b>${tilasto.kuukausiKilpailu}<br/>
                                <b>Kilpailut tänä vuonna::</b>${tilasto.vuosiKilpailu}<br/>
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
                            </div>
                        </td><td></td>
                    </tr>



                </table>

                </c:if>

        
        </div>
    </div>

</body>

</html>