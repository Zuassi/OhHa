<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css" /> 
    </head>
    <body>
        <div id ="sisalto_container">
            <div id="logo">Harjoitusseuranta</div>

            <div id="tabit">
                <!--
                --><a href="${pageContext.request.contextPath}/harjoittelija/lisaa-harjoitus" class ="eka_linkki">Lis‰‰ harjoitus</a><!--
                --><a href="${pageContext.request.contextPath}/harjoittelija/selaa">Selaa harjoituksia</a><!--
                --><a href="${pageContext.request.contextPath}/harjoittelija/tilasto">Tilastot</a><!--
                --><a href="${pageContext.request.contextPath}/logout" class ="vika_linkki">Kirjaudu ulos</a>
            </div>  
            <div id="sisalto_laatikko">

                <div id="aikavali_saato"</div>
                <h2>S‰‰d‰ oma tarkkailtava aikav‰li</h2><br/>
                <form:form commandName="AikavaliForm" action="${pageContext.request.contextPath}/harjoittelija/tilasto" method="POST" >
                    Alkamisaika (pv.kk.vvvv): <form:input path="alkamisaika" /><form:errors path="alkamisaika"/>
                    Loppumisaika (pv.kk.vvvv): <form:input path="loppumisaika" /><form:errors path="loppumisaika"/>
                    <input type="submit">
                </form:form>
            </div>
            <table>
                <tr>
                    <td>
                        <div class="tilasto_laatikko">
                            <h1>Yhteenveto</h1>

                            <b>Treenej‰ yhteens‰ t‰n‰‰n: </b>${tilasto.paiva} <br/>
                            <b>Treenej‰ yhteens‰ t‰ll‰ viikolla: </b>${tilasto.viikko}<br/>
                            <b>Treenej‰ yhteens‰ t‰ss‰ kuussa: </b>${tilasto.kuukausi}<br/>
                            <b>Treenej‰ yhteens‰ t‰n‰ vuonna: </b>${tilasto.vuosi}<br/>
                            <b>Treenej‰ asetetulla aikav‰lill‰: </b>${tilasto.oma}<br/>
                        </div>
                    </td>
                    <td>
                        <div class="tilasto_laatikko">
                            <h1>Kovat treenit (teho 4-5)</h1><br/>
                            <b>Kovia treenej‰ t‰n‰‰n: </b>${tilasto.paivaKovat}<br/>
                            <b>Kovia treenej‰ t‰ll‰ viikolla </b>${tilasto.viikkoKovat}<br/>
                            <b>Kovia treenej‰ t‰ss‰ kuussa </b>${tilasto.kuukausiKovat}<br/>
                            <b>Kovia treenej‰ t‰n‰ vuonna </b>${tilasto.vuosiKovat}<br/>
                            <b>Kovia asetetulla aikav‰lill‰: </b>${tilasto.omaKovat}<br/>
                        </div>
                    </td>
                </tr>
                <tr><td>
                        <div class="tilasto_laatikko">
                            <h1>Lajitreenit</h1></br>
                            <b>Lajitreenej‰ t‰n‰‰n:</b>${tilasto.paivaLajiharjoitus}<br/>
                            <b>Lajitreenej‰ t‰ll‰ viikolla:</b>${tilasto.viikkoLajiharjoitus}<br/>
                            <b>Lajitreenej‰ t‰ss‰ kuussa:</b>${tilasto.kuukausiLajiharjoitus}<br/>
                            <b>Lajitreenej‰ t‰n‰ vuonna:</b>${tilasto.vuosiLajiharjoitus}<br/>
                            <b>Lajitreenej‰ asetetulla aikav‰lill‰: </b>${tilasto.omaLajiharjoitus}<br/>
                        </div>
                    </td>
                    <td>
                        <div class="tilasto_laatikko">
                            <h1>Puntit</h1></br>
                            <b>Puntteja t‰n‰‰n:</b>${tilasto.paivaPuntti}<br/>
                            <b>Puntteja t‰ll‰ viikolla:</b>${tilasto.viikkoPuntti}<br/>
                            <b>Puntteja t‰ss‰ kuussa:</b>${tilasto.kuukausiPuntti}<br/>
                            <b>Puntteja t‰n‰ vuonna:</b>${tilasto.vuosiPuntti}<br/>
                            <b>Puntteja asetetulla aikav‰lill‰: </b>${tilasto.omaPuntti}<br/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="tilasto_laatikko">
                            <h1>Palauttavat</h1><br/>
                            <b>Palauttavia t‰n‰‰n:</b>${tilasto.paivaPalauttava}<br/>
                            <b>Palauttavia t‰ll‰ viikolla:</b>${tilasto.viikkoPalauttava}<br/>
                            <b>Palauttavia t‰ss‰ kuussa:</b>${tilasto.kuukausiPalauttava}<br/>
                            <b>Palauttavia t‰n‰ vuonna:</b>${tilasto.vuosiPalauttava}<br/>
                            <b>Palauttavia asetetulla aikav‰lill‰: </b>${tilasto.omaPalauttava}<br/>
                        </div>
                    </td>
                    <td>
                        <div class="tilasto_laatikko">
                            <h1>Kilpailut</h1><br/>
                            <b>Kilpailut t‰ss‰ kuussa:</b>${tilasto.kuukausiKilpailu}<br/>
                            <b>Kilpailut t‰n‰ vuonna::</b>${tilasto.vuosiKilpailu}<br/>
                            <b>Kilpailut asetetulla aikav‰lill‰: </b>${tilasto.omaKilpailu}<br/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="tilasto_laatikko">
                            <h1>Muut</h1><br/>
                            <b>Muita t‰n‰‰n:</b>${tilasto.paivaMuu}<br/>
                            <b>Muita t‰ll‰ viikolla:</b>${tilasto.viikkoMuu}<br/>
                            <b>Muita t‰ss‰ kuussa:</b>${tilasto.kuukausiMuu}<br/>
                            <b>Muita t‰n‰ vuonna:</b>${tilasto.vuosiMuu}<br/>
                            <b>Muita asetetulla aikav‰lill‰: </b>${tilasto.omaKilpailu}<br/>
                        </div>
                    </td><td></td>
                </tr>



            </table>
        </div>

    </div>
</body>

</html>