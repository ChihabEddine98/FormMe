<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="game">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>jeux des formes géométriques</title>

</head>

<body>
<h2 class="title">Shapes Game</h2>
<hr class="underline">

<p class="rules-game">Place shapes on their shadows with the mouse.</p>

<div id="shapesgame" class="panel-body grey" role="main">
    <div class="container-flex flex-item">

        <div class="formes">
            <script> initFormes(); </script>
        </div>

        <div class="shadows">
            <img name="champignon" src="../assets/champignon.gif">
            <img name="chalet" src="../assets/chalet.gif">
            <img name="chien" src="../assets/chien.gif">
            <img name="chasseur" src="../assets/chasseur.gif">
            <img name="chaussette" src="../assets/chaussette.gif">
            <img name="chateau" src="../assets/chateau.gif">
        </div>
    </div>
</div>

<div style="display: none" id="textgame" class="panel-body grey" role="main">
    <div class="container container-game">

        <div class="text-chasseur">
            <p id="0">Un chasseur sachant chasser sait chasser sans son chien. <br>
                Un chasseur sachant chasser sans son chien, ça se chasse aussi, sachez-le!<br>
                Un chasseur sachant chasser chasse sans son chien.
            </p>

            <p id="1" class="hide">
                Un chasseur sachant chasser le chat sauvage sans son chien est un bon chasseur.<br>
                Un chasseur sachant chasser sans son chien est un bon chasseur qui fait sécher ses chaussettes sur une souche sèche.
            </p>

            <p id="2" class="hide">
                Un chasseur sachant chasser sait chasser sans son chien de chasse.<br>
                Un chasseur sachant chasser sans son chien est un sacré chasseur.<br>
                Sachez qu'un chasseur sachant chasser sans son chien est un bon chasseur.
            </p>

            <p id="3" class="hide">
                Un chasseur sachant chasser son chat sans son chien de chasse est un bon chasseur.<br>
                Qu'un chasseur sachant chasser sur ses échasses sache chasser sans son chien de chasse!<br>
                Un chasseur sachant chasser ne chasse jamais sans son chien.
            </p>
        </div>

        <div class="bg-progressbar">
            <div class="txt-progressbar">progress: <span id="txt-progressbar">0</span><span>%</span> </div>
            <div id="progressbar" class="progressbar"></div>
        </div>

        <textarea id="target" style="overflow:auto;resize:none" rows="13" onCopy="return false" onPaste="return false"></textarea>

        <input type="range" id="slideToUnlock" class="slideToUnlock hide" value="0" max="100">
         <input id="trust">

        <div id="output" style="display: none"></div>



    </div>
</div>

<div class="container container-buttons">
    <button id="next" onclick="next()" class="button next hide">next</button>
    <div id="12" class="hide">
        <input type="button" id="Auth" class="button next" value="Authentifier"
               onclick="authentification();">
    </div>
</div>

<script type="text/javascript">

    window.addEventListener("load", function () {
        console.log("here")

    })
    /*	document.getElementById('openModal').addEventListener('click', function () {
     this.classList.add('hide')

     });*/

    function reload() {
        console.log('here')
        window.location.reload()
    }
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
    SET_DHTML(CURSOR_HAND, "chaussette2", "chateau2", "champignon2", "chien2", "chasseur2", "chalet2", "chaussette" + NO_DRAG, "chateau" + NO_DRAG, "champignon" + NO_DRAG, "chien" + NO_DRAG, "chasseur" + NO_DRAG, "chalet" + NO_DRAG);
    function testDrag() {
        var testFin;
        testFin = 0;
        testtmp = 0;
        if (dd.elements.chaussette2.getEltBelow() != null) {
            testtmp++;
            if (dd.elements.chaussette2.getEltBelow().name == "chaussette") testFin++;
        }
        if (dd.elements.champignon2.getEltBelow() != null) {
            testtmp++;
            if (dd.elements.champignon2.getEltBelow().name == "champignon") testFin++;
        }
        if (dd.elements.chalet2.getEltBelow() != null) {
            testtmp++;
            if (dd.elements.chalet2.getEltBelow().name == "chalet") testFin++;
        }
        if (dd.elements.chateau2.getEltBelow() != null) {
            testtmp++;
            if (dd.elements.chateau2.getEltBelow().name == "chateau") testFin++;
        }
        if (dd.elements.chasseur2.getEltBelow() != null) {
            testtmp++;
            if (dd.elements.chasseur2.getEltBelow().name == "chasseur") testFin++;
        }
        if (dd.elements.chien2.getEltBelow() != null) {
            testtmp++;
            if (dd.elements.chien2.getEltBelow().name == "chien") testFin++;
        }
        if (testtmp == 6) {
            if (testFin == 6) {
                aff_image_finale();
                clearTimeout(id);
                //document.getElementById("temps").value = "REJOUER";
                dd.elements.chaussette2.setDraggable(false);
                dd.elements.champignon2.setDraggable(false);
                dd.elements.chalet2.setDraggable(false);
                dd.elements.chateau2.setDraggable(false);
                dd.elements.chasseur2.setDraggable(false);
                dd.elements.chien2.setDraggable(false);
                document.getElementById('next').classList.remove("hide");
            }
            /*else {
             nbErreur = testtmp - testFin;
             if (nbErreur == 1) {
             document.getElementById("messageErreur").firstChild.data = "il y a " + nbErreur + " erreur";
             } else {
             document.getElementById("messageErreur").firstChild.data = "il y a " + nbErreur + " erreurs";
             }
             }*/
        }
    }
    dd.elements.chaussette2.setDragFunc(testDrag);
    dd.elements.chalet2.setDragFunc(testDrag);
    dd.elements.champignon2.setDragFunc(testDrag);
    dd.elements.chateau2.setDragFunc(testDrag);
    dd.elements.chasseur2.setDragFunc(testDrag);
    dd.elements.chien2.setDragFunc(testDrag);
    window.onerror = monerreur;
    function monerreur() {
    }

    var next = function () {
        $('#shapesgame').hide()
        $('.next').hide()
        $('#textgame').show()


        $('#chaletdiv').hide()
        $('#chasseurdiv').hide()
        $('#chiendiv').hide()
        $('#champignondiv').hide()
        $('#chateaudiv').hide()
        $('#chaussettediv').hide()

        $('#chalet2div').hide()
        $('#chasseur2div').hide()
        $('#chien2div').hide()
        $('#champignon2div').hide()
        $('#chateau2div').hide()
        $('#chaussette2div').hide()

        console.log("ok")

        $('.title').text("Text entry")
        $('.rules-game').text("Type the text below et follow the instructions.")

    }


    var $input = $('textarea');
    var $output = $('#output');
    var typingTimer;                //timer identifier
    var doneTypingInterval = 100;  //time in ms

    //on keyup, start the countdown

    $input.on('keyup', function () {
        clearTimeout(typingTimer);
        typingTimer = setTimeout(doneTyping, doneTypingInterval);
    });

    //on keydown, clear the countdown
    $input.on('keydown', function () {
        clearTimeout(typingTimer);
    });

    $input.bind('input propertychange', function () {
        $output.html($(this).val());
    });


    var j = 0;
    function doneTyping() {
        if (($output.text().length > 175) && (j == 0)) {
            document.getElementById('slideToUnlock').classList.remove("hide");
            j++
        }
        else if (($output.text().length > 190) && (j == 1)) {
            document.getElementById('slideToUnlock').classList.remove("hide");
            j++
        }
        else if (($output.text().length > 200) && (j == 2)) {
            document.getElementById('slideToUnlock').classList.remove("hide");
            j++
        }
        else if (($output.text().length > 225) && (j == 3)) {
            document.getElementById('slideToUnlock').classList.remove("hide");
            j++
        }

        // uniquement pour la phase de dev
        if ($output.text() == "sésame") {
            $('#12').removeClass("hide");
        }
        if ($output.text() == "slider") {
            document.getElementById('slideToUnlock').classList.remove("hide");
        }
    }

    document.querySelector("input[type=\"range\"]").onmouseup = function () {
        var theRange = this.value;
        if (theRange == 100) {

            unlock();

            document.init = setInterval(function () {
                if (document.querySelector("input[type=\"range\"]").value != 0) {
                    document.querySelector("input[type=\"range\"]").value = theRange--;
                }
            }, 1);

        } else {
            document.init = setInterval(function () {
                if (document.querySelector("input[type=\"range\"]").value != 0) {
                    document.querySelector("input[type=\"range\"]").value = theRange--;
                }
            }, 1);
        }
    }

    document.querySelector("input[type=\"range\"]").onmousedown = function () {
        clearInterval(document.init);
    }

    var i = 0;

    function unlock() {
        if (i == 0) {
            document.getElementById('1').classList.remove("hide");
            i++;
            document.getElementById('slideToUnlock').classList.add("hide");
            document.getElementById('progressbar').style.width = "25%";
            document.getElementById('txt-progressbar').innerHTML = "25";
            document.getElementById('0').classList.add("hide");
            $input.val("");
        }
        else if (i == 1) {
            document.getElementById('2').classList.remove("hide");
            i++;
            document.getElementById('slideToUnlock').classList.add("hide");
            document.getElementById('progressbar').style.width = "50%";
            document.getElementById('txt-progressbar').innerHTML = "50";
            document.getElementById('1').classList.add("hide");
            $input.val("");
        }
        else if (i == 2) {
            document.getElementById('3').classList.remove("hide");
            i++;
            document.getElementById('slideToUnlock').classList.add("hide");
            document.getElementById('progressbar').style.width = "75%";
            document.getElementById('txt-progressbar').innerHTML = "75";
            document.getElementById('2').classList.add("hide");
            $input.val("");

        }
        else if (i == 3) {
            document.getElementById('12').classList.remove("hide");
            i++;
            document.getElementById('slideToUnlock').classList.add("hide");
            document.getElementById('progressbar').style.width = "100%";
            document.getElementById('txt-progressbar').innerHTML = "100";
            document.getElementById('3').classList.add("hide");
            document.querySelector('h3').innerHTML = "Passez à la phase d'authentification";
            $input.val("");

        }
    }

</script>
</body>
</html>