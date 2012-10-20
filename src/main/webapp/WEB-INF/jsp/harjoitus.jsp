<%-- 
    Document   : harjoitus
    Created on : 20.10.2012, 15:45:14
    Author     : Lalli
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Harjoitus</title>
    </head>
    <body>
        <b>Alkamisaika (pv.kk.v hh.mm):</b> <p>${harjoitus.alkamisaika}</p><br/>
        <b>Kesto (minuuttia): </b> <p>${harjoitus.kesto}</p><br/>
        <b>Teho (1-5): </b> <p>${harjoitus.teho}</p><br/>
        <b>Paikka: </b><p>${harjoitus.paikka}</p><br/>
        <b>Tyyppi: </b> <p>${harjoitus.tyyppi}</p><br/>
        <b>Sisältö: </b><p>${harjoitus.sisalto}</p><br/>
    </body>
</html>
