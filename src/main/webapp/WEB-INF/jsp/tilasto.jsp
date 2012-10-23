<%-- 
    Document   : tilasto
    Created on : 23.10.2012, 12:19:45
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tilastot</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/harjoittelija">Takaisin harjoittelijasivulle</a><br/>

        <b>S‰‰d‰ oma tarkkailtava aikav‰li</b>
        <form:form commandName="AikavaliForm" action="${pageContext.request.contextPath}/harjoittelija/tilasto" method="POST" >
            Alkamisaika (pv.kk.vvvv hh.mm): <form:input path="alkamisaika" /><form:errors path="alkamisaika"/>
            Loppumisaika (pv.kk.vvvv hh.mm): <form:input path="loppumisaika" /><form:errors path="loppumisaika"/>
            <input type="submit">
        </form:form>

        <h1>Yhteenveto</h1>

        <b>Treenej‰ yhteens‰ t‰n‰‰n: </b>${tilasto.treenitYhteensaPaivassa} <br/>
        <b>Treenej‰ yhteens‰ t‰ll‰ viikolla: </b>${tilasto.treenitYhteensaViikossa}<br/>
        <b>Treenej‰ yhteens‰ t‰ss‰ kuussa: </b>${tilasto.treenitYhteensaKuukaudessa}<br/>
        <b>Treenej‰ yhteens‰ t‰n‰ vuonna: </b>${tilasto.treenitYhteensaVuodessa}<br/>

        <h1>Kovat treenit (teho 4-5)</h1><br/>
        <b>Kovia treenej‰ t‰n‰‰n: </b>${tilasto.kovatTreenitPaivassa}<br/>
        <b>Kovia treenej‰ t‰ll‰ viikolla </b>${tilasto.kovatTreenitViikossa}<br/>
        <b>Kovia treenej‰ t‰ss‰ kuussa </b>${tilasto.kovatTreenitKuukaudessa}<br/>
        <b>Kovia treenej‰ t‰n‰ vuonna </b>${tilasto.kovatTreenitVuodessa}<br/>

        <h1>Lajitreenit</h1></br>
        <b>Lajitreenej‰ t‰n‰‰n:</b>${tilasto.lajitPaivassa}<br/>
        <b>Lajitreenej‰ t‰ll‰ viikolla:</b>${tilasto.lajitViikossa}<br/>
        <b>Lajitreenej‰ t‰ss‰ kuussa:</b>${tilasto.lajitKuukaudessa}<br/>
        <b>Lajitreenej‰ t‰n‰ vuonna:</b>${tilasto.lajitVuodessa}<br/>


        <h1>Puntit</h1></br>
        <b>Puntteja t‰n‰‰n:</b>${tilasto.puntitPaivassa}<br/>
        <b>Puntteja t‰ll‰ viikolla:</b>${tilasto.puntitViikossa}<br/>
        <b>Puntteja t‰ss‰ kuussa:</b>${tilasto.puntitKuukaudessa}<br/>
        <b>Puntteja t‰n‰ vuonna:</b>${tilasto.puntitVuodessa}<br/>

        <h1>Palauttavat</h1><br/>
        <b>Palauttavia t‰n‰‰n:</b>${tilasto.palauttavatPaivassa}<br/>
        <b>Palauttavia t‰ll‰ viikolla:</b>${tilasto.palauttavatViikossa}<br/>
        <b>Palauttavia t‰ss‰ kuussa:</b>${tilasto.palauttavatKuukaudessa}<br/>
        <b>Palauttavia t‰n‰ vuonna:</b>${tilasto.palauttavatVuodessa}<br/>

        <h1>Kilpailut</h1><br/>
        <b>Kilpailut t‰ss‰ kuussa:</b>${tilasto.kilpailujaKuukaudessa}<br/>
        <b>Kilpailut t‰n‰ vuonna::</b>${tilasto.kilpailujaVuodessa}<br/>

        <h1>Muut</h1><br/>
        <b>Muita t‰n‰‰n:</b>${tilasto.muutPaivassa}<br/>
        <b>Muita t‰ll‰ viikolla:</b>${tilasto.muutViikossa}<br/>
        <b>Muita t‰ss‰ kuussa:</b>${tilasto.muutKuukaudessa}<br/>
        <b>Muita t‰n‰ vuonna:</b>${tilasto.muutVuodessa}<br/>


    </body>
</html>
