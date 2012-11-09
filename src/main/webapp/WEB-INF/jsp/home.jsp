<%-- 
    Document   : lisaa_harjoitus
    Created on : 19.10.2012, 14:58:34
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.1.min.js"></script>
        <script src="http://malsup.github.com/jquery.form.js"></script> 
        <script src="${pageContext.request.contextPath}/resources/code.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css" /> 
        <title>Harjoitusseuranta - Kirjautunut</title>
    </head>
    <body onload="init(${page});">
        <div id="tabit">

            <ul>
                <div id="valikko">HARJOITUSSEURANTA</div>

                <li><a href="#" onclick="displayArticle(0);" class ="tab">Lis‰‰ harjoitus</a></li>
                <li><a href="#" onclick="displayArticle(1);" class="tab" >Selaa harjoituksia</a></li>
                <li><a href="#" onclick="displayArticle(2);" class="tab">Tilastot</a></li>
                <li><a href="#" onclick="displayArticle(3);" class="tab">Asetukset</a></li>                                                                                
                <li><a href="${pageContext.request.contextPath}/logout" onclick="displayArticle(4);" class ="vika_linkki" class="tab">Kirjaudu ulos</a></li>
            </ul>
        </div>

        <div id ="sisalto_container">
            <div id="logo">Harjoitusseuranta</div>


            <div id="sisalto_laatikko">
                <section class="hidden">

                    <div id="sisalto">

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
                                    <td>Sis‰ltˆ:</td> <td><form:textarea path="sisalto" /></td><td><form:errors path="sisalto"/></td>
                                </tr><tr>
                                    <td></td><td><input type="submit" class="input_nappula"></td></tr>
                                <p>${lisatty}</p>
                            </table>
                        </form:form>
                    </div>


                </section>
                <section class="hidden">


                    <table id="selaa_table">
                        <tr class="varitettava"">
                            <th><a href="${pageContext.request.contextPath}/home?jarjestys=alkamisaika&sivuNumero=${sivuNumero}">Alkamisaika</a></th>
                            <th><a href="${pageContext.request.contextPath}/home?jarjestys=kesto&sivuNumero=${sivuNumero}">Kesto</a></th>
                            <th><a href="${pageContext.request.contextPath}/home?selaa?jarjestys=teho&sivuNumero=${sivuNumero}">Teho</a></th>
                            <th><a href="${pageContext.request.contextPath}/home?selaa?jarjestys=paikka&sivuNumero=${sivuNumero}">Treenipaikka</a></th>
                            <th><a href="${pageContext.request.contextPath}/home?selaa?jarjestys=tyyppi&sivuNumero=${sivuNumero}">Tyyppi</a></th>
                            <th>Sis‰ltˆ</th>
                            <th>Muokkaa</th>
                            <th>Poista</th>



                            <c:forEach var="harjoitus" items="${harjoitukset}">

                            <tr class="rivi">
                                <td><fmt:formatDate type="both" value="${harjoitus.alkamisaika}"/></td>
                                <td>${harjoitus.kesto}min</td>
                                <td>${harjoitus.teho}</td>
                                <td>${harjoitus.paikka}</td>
                                <td>${harjoitus.tyyppi}</td>
                                <td><a href="${pageContext.request.contextPath}/harjoittelija/harjoitus/${harjoitus.id}">N‰yt‰</a></td>
                                <td><a href="${pageContext.request.contextPath}/harjoittelija/harjoitus/muokkaa/${harjoitus.id}">Muokkaa</a></td>
                                <td><a href="${pageContext.request.contextPath}/harjoittelija/poista-harjoitus/${harjoitus.id}">Poista</a></td>
                            </tr>
                        </c:forEach>
                        </tr>

                    </table>


                    <c:if test="${sivutus}">
                        <p>Sivut:  <c:forEach var="i" begin="0" end="${sivumaara-1}">
                                <a href="${pageContext.request.contextPath}/home?jarjestys=${jarjestys}&sivuNumero=${i+1}">${i+1}</a>     
                            </c:forEach></p>
                        </c:if>

                </section>
                <section class="hidden">

                    <div id="aikavali_saato"</div>
                    <h2>S‰‰d‰ oma tarkkailtava aikav‰li</h2><br/>
                    <table>
                        <form:form commandName="AikavaliForm" action="${pageContext.request.contextPath}/harjoittelija/tilasto" method="POST" >
                            <tr><td>Alkamisaika (pv.kk.vvvv):</td><td><form:input path="alkamisaika" /></td></tr>
                            <tr><td>Loppumisaika (pv.kk.vvvv):</td><td><form:input path="loppumisaika" /></td></tr>
                            <tr><td/><td><input type="submit" class="input_nappula"></td></tr>
                                </form:form>
                    </table>
                    <p>${seuranta_error}</p>
            </div>
            <table id="tilasto_table">
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
            <br/>

        </section>
        <section class="hidden">
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

                <table>

                    <form method="POST" id="luoAvain" action="${pageContext.request.contextPath}/harjoittelija/asetukset/luo_avain">
                        <tr><td> Kenelle:</td> <td><input type="text" name="nimi"></td></tr>


                        <tr><td/><td><input type="submit" class="input_nappula" value="Luo avain"></td></tr>
                        <p>${avain_message}</p>
                    </form>
                </table>

            </article>
            <article>

                <h2>Salasana: </h2><br/>
                <form method="POST" action="${pageContext.request.contextPath}/harjoittelija/asetukset/salasana">
                    <table>
                        <tr><td>Vanha salasana:</td><td><input type="password" name="vanha_salasana"></td></tr>
                        <tr><td>Uusi salanana:</td> <td><input type="password" name="uusi_salasana"></td></tr>
                        <tr><td>Uusi salasana:</td><td><input type="password" name="uusi_salasana2"></td></tr>
                        <tr><td></td><td><input class="input_nappula" type ="submit" value="Vaihda salasana"></td></tr>
                    </table>
                    ${message}
                    <br/>
            </article>
        </section>
        <section class="hidden">
            <table id="tilasto_table">

                <form:form commandName="harjoitus" action="${pageContext.request.contextPath}/harjoittelija/harjoitus" method="POST" >
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
                        <td>Sis‰ltˆ:</td> <td><form:textarea path="sisalto" /></td><form:errors path="sisalto"/>
                    </tr><tr>
                        <td></td><td><input class="input_nappula" type="submit" value="Muokkaa"></td></tr>
                </table>
                <form:hidden path="id"  value="${harjoitus.id}" />
            </form:form>

            <br/>


        </section>




    </div>

</div>



</body>

</html>