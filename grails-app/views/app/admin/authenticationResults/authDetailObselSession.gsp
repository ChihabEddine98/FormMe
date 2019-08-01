<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-24
  Time: 16:23
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin">
    <title>Résultats d'authentifications</title>
</head>

<body>
<div class="contentObsel">
    <p>
        Signature User : <br>
        ${authenticationResult.userSignature.firstname} ${authenticationResult.userSignature.lastname}
    </p>
    <p>
        Profile User : <br>
        ${authenticationResult.userProfile.firstname} ${authenticationResult.userProfile.lastname}
    </p>

    <button id="button" class="buttonObsel">Afficher le graph ObselSessionTrust.trust</button>
    <g:link controller="admin" action="calculateAuthenticationStatistics" id="${authenticationResult.id}">
        <button id="buttonStats" class="buttonObsel">Calculer les stats</button>
    </g:link>

    <h4>
        ${listObselTrust.size()} ${listObselTrust.size() > 1 ? 'obsels' : 'obsel'} (max 1000 affichés sur cette page, du plus récent au plus ancien)
    </h4>


    <div id="graph" class="graph"></div>
    <table>
        <thead>
        <tr>
            <td>ID Session</td>
            <td>idAction</td>
            <td>distances</td>
            <td>ancien barycentre</td>
            <td>nouveau barycentre</td>
            <td>score</td>
            <td>trust</td>
        </tr>
        </thead>
        <tbody>

        <g:each in="${listObselTrust}" status="i" var="obselTrust">
            <g:set var="linkGrails" value="${obselTrust.trust}"/>
        </g:each>

        <g:each in="${listObselTrust}" status="i" var="obselTrust">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>
                    <p>${obselTrust.sessionID}</p>
                </td>
                <td>
                    <g:link url="${obselTrust.idAction}">${obselTrust.idAction}</g:link>
                </td>
                <td style="min-width: 200px;">
                    <p>${distances.get(i)}</p>
                </td>
                <td style="min-width: 200px;">
                    <pre>${oldBaryCentre.get(i)}</pre>

                    <g:if test="${!oldBaryCentre.get(i).isEmpty()}">
                        <div class="collapsible">
                            <i class="fas fa-plus-circle" style="font-size: 28px;color: #0ba132;"></i>
                            <button style="font-size: 28px;color: #11a129;"> Details </button>
                        </div>

                        <div class="addContent">
%{--                            <pre>${oldBaryCentreAdd.get(i)}</pre>--}%
                            <pre> Heree : type de liste   null</pre>
                        </div>
                    </g:if>


                </td>
                <td style="min-width: 200px;">
                    <pre>${newBaryCentre.get(i)}</pre>
%{--                    <g:if test="${!newBaryCentre.get(i).isEmpty()}">--}%
%{--                        <div class="collapsible">--}%
%{--                            <i class="fas fa-plus-circle" style="font-size: 28px;color: #37a14b;"></i>--}%
%{--                            <button style="font-size: 28px;color: #37a14b;"> Details </button>--}%
%{--                        </div>--}%

%{--                        <div class="addContent">--}%
%{--                            <pre>${newBaryCentreAdd.get(i)}</pre>--}%
%{--                        </div>--}%
%{--                    </g:if>--}%

                </td>
                <td style="min-width: 50px;">
                    <p>${obselTrust.score}</p>
                </td>
                <td style="min-width: 50px;">
                    <p>${obselTrust.trust}</p>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
<script>

    var classActive = function () {
        $('.resultsLi').addClass('activeLi');
        $('.resultsA').addClass('activeA');
    };
    $( document ).ready(function() {
        classActive();
        $("#pageName").text("Authentification results");
    });

    $('#button').click(function() {
        $('.graph').toggle();
    });

    var array = ${listTrusts}

        array.reverse();


    GRAPH = document.getElementById('graph');

    // deprecated: calling plot again will add new trace(s) to the plot,
    // but will ignore new layout.
    var data2 = [{
        y: array,
        type: 'scatter'
    }];

    var layout2 = {
        title: 'ObselSessionTrust.trust'
    };

    Plotly.newPlot(GRAPH, data2, layout2, {showLink: false});


    var coll = document.getElementsByClassName("collapsible");
    var i;

    for (i = 0; i < coll.length; i++) {
        coll[i].addEventListener("click", function() {


            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "block") {
                content.style.display = "none";
            } else {
                content.style.display = "block";
            }
        });
    }

</script>
</body>
</html>