<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>jeux des formes g&eacute;om&eacute;triques</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script type="text/javascript" src="../assets/TraceMe_CS/collector.js"></script>
    <script type="text/javascript" src="../assets/dhtml.js"></script>
    <script type="text/javascript" src="../assets/jquery-3.1.1.min.js"></script>

    <script src="../assets/wz_dragdrop.js"></script>
    <script type="text/javascript">
        <g:remoteFunction controller="Game" action="setCookies" />
        var tab = new Array();
        tab[0] = 1;
        tab[1] = 2;
        tab[2] = 3;
        tab[3] = 4;
        tab[4] = 5;
        tab[5] = 6;
        var tab2 = new Array();
        tab2[0] = "rond2";
        tab2[1] = "etoile2";
        tab2[2] = "carre2";
        tab2[3] = "pentagone2";
        tab2[4] = "hexagone2";
        tab2[5] = "triangle2";
        var j = 0;
        var compt = 1;
        var nbImage = 6;
        function initFormes() {
            while (compt < nbImage + 1) {

                tmp = Math.floor(Math.random() * (nbImage + 1));
                j = 0;
                for (j == 0; j < nbImage; j++) {
                    if (tmp == tab[j]) {
                        document.write('<td width="90" align=center>');
                        document.write('<img name=' + tab2[j] + ' src=../assets/' + tab2[j] + '.gif width=72 height=72>');
                        document.write('</td>');
                        tab[j] = -1;
                        compt++;
                    }
                }
            }
        }
    </script>




    <!-- >script type="text/javascript" src="../ktbs.js"></script>
<script type="text/javascript" src="../connect.js"></script>
<script type="text/javascript" src="../auth.js"></script-->
</head>

<body>
<div class="body">
    <g:link controller="logout"><input type="button" value="logout" class="button"/></g:link>
    <div id="centercol">
        <center>
            <h1>JEUX DES FORMES</h1>

            <p class="titre3">Remplace les formes sur leurs ombres avec la souris et remplis le nom de chaque forme</p>

            <p>[chateau,chien,chaussettes,champignon,chalet,chasseur]</p>

            <table bgcolor="#FFFFCC" id="jeuforme" style="width: 90%;">
                <tr height=200>
                    <script language="JavaScript"> initFormes(); </script>
                </tr>


                <tr height=100></tr>

                <tr height=200>
                    <td align=center name="triangle"><img name="triangle" src="../assets/triangle.gif" width=80
                                                          height=80></td>
                    <td align=center><img name="etoile" src="../assets/etoile.gif" width=80 height=80></td>
                    <td align=center><img name="pentagone" src="../assets/pentagone.gif" width=80 height=80></td>
                    <td align=center><img name="hexagone" src="../assets/hexagone.gif" width=80 height=80></td>
                    <td align=center><img name="rond" src="../assets/rond.gif" width=80 height=80></td>
                    <td align=center><img name="carre" src="../assets/carre.gif" width=80 height=80></td>
                </tr>

                <p id="messageErreur" class="tresdifficile">&nbsp;</p>
            </table>


            <br>
            <table style="width: 90%;">
                <tr>
                    <td name="triangle"><input type="text" style="border-color: #1a1919; height: 30px; width: 220px;">
                    </td>
                    <td><input type="text" style="border-color: #1a1919; height: 30px; width: 220px;"></td>
                    <td><input type="text" style="border-color: #1a1919; height: 30px; width: 220px;"></td>
                    <td><input type="text" style="border-color: #1a1919; height: 30px; width: 220px;"></td>
                    <td><input type="text" style="border-color: #1a1919; height: 30px; width: 220px;"></td>
                    <td><input type="text" style="border-color: #1a1919; height: 30px; width: 220px;"></td>
                </tr>
            </table>
            <br>
            <input type="button" id="Auth" value="Authentifier" onclick="authentification();"></input>

            <p>

            </p>


            <script type="text/javascript">
                function aff_image_finale() {
//document.getElementById("imageBravo").src = "images/bravo.gif";
                }
                var ticker, sec, min, id;
                // function initTime()
                // {
                // min = Math.floor(ticker/60);
                // sec = (ticker-(min*60))+'';
                // if(sec.length == 1) {sec = "0"+sec};
                // ticker++;
                // document.getElementById("temps").value = min+" mn "+sec+ " s";
                // id = setTimeout("initTime()", 1000);
                // }
                testTmp = 0;
                ticker = 0;
                min = 0;
                sec = 0;
                //initTime();
                SET_DHTML(CURSOR_HAND, "rond2", "carre2", "triangle2", "pentagone2", "hexagone2", "etoile2", "rond" + NO_DRAG, "carre" + NO_DRAG, "triangle" + NO_DRAG, "pentagone" + NO_DRAG, "hexagone" + NO_DRAG, "etoile" + NO_DRAG);
                function testDrag() {
                    var testFin;
                    testFin = 0;
                    testtmp = 0;
                    if (dd.elements.rond2.getEltBelow() != null) {
                        testtmp++;
                        if (dd.elements.rond2.getEltBelow().name == "rond") testFin++;
                    }
                    if (dd.elements.triangle2.getEltBelow() != null) {
                        testtmp++;
                        if (dd.elements.triangle2.getEltBelow().name == "triangle") testFin++;
                    }
                    if (dd.elements.etoile2.getEltBelow() != null) {
                        testtmp++;
                        if (dd.elements.etoile2.getEltBelow().name == "etoile") testFin++;
                    }
                    if (dd.elements.carre2.getEltBelow() != null) {
                        testtmp++;
                        if (dd.elements.carre2.getEltBelow().name == "carre") testFin++;
                    }
                    if (dd.elements.hexagone2.getEltBelow() != null) {
                        testtmp++;
                        if (dd.elements.hexagone2.getEltBelow().name == "hexagone") testFin++;
                    }
                    if (dd.elements.pentagone2.getEltBelow() != null) {
                        testtmp++;
                        if (dd.elements.pentagone2.getEltBelow().name == "pentagone") testFin++;
                    }
                    document.getElementById("messageErreur").firstChild.data = "Remplace les formes 8!";
                    if (testtmp == 6) {
                        if (testFin == 6) {
                            aff_image_finale();
                            clearTimeout(id);
//document.getElementById("temps").value = "REJOUER";
                            document.getElementById("messageErreur").firstChild.data = "Rentre le nom de chaque objet dans le champ prévu à cet effet !";
                            dd.elements.rond2.setDraggable(false);
                            dd.elements.triangle2.setDraggable(false);
                            dd.elements.etoile2.setDraggable(false);
                            dd.elements.carre2.setDraggable(false);
                            dd.elements.hexagone2.setDraggable(false);
                            dd.elements.pentagone2.setDraggable(false);
                        } else {
                            nbErreur = testtmp - testFin;
                            if (nbErreur == 1) {
                                document.getElementById("messageErreur").firstChild.data = "il y a " + nbErreur + " erreur";
                            } else {
                                document.getElementById("messageErreur").firstChild.data = "il y a " + nbErreur + " erreurs";
                            }
                        }
                    }
                }
                dd.elements.rond2.setDragFunc(testDrag);
                dd.elements.etoile2.setDragFunc(testDrag);
                dd.elements.triangle2.setDragFunc(testDrag);
                dd.elements.carre2.setDragFunc(testDrag);
                dd.elements.hexagone2.setDragFunc(testDrag);
                dd.elements.pentagone2.setDragFunc(testDrag);
                window.onerror = monerreur;
                function monerreur() {
                }
            </script>
    </div>
</body>
</html>