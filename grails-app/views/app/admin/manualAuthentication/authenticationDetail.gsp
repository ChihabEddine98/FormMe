<%--
  Created by IntelliJ IDEA.
  User: Chihab
  Date: 30/07/2019
  Time: 17:53
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

    <button id="button" class="buttonObsel">Afficher le graph Obseltrust.trust</button>
    <g:link controller="admin" action="calculateAuthenticationStatistics" id="${authenticationResult.id}">
        <button id="buttonStats" class="buttonObsel">Calculer les stats</button>
    </g:link>

    <h4>
        ${listObselTrust.size()} ${listObselTrust.size() > 1 ? 'obsels' : 'obsel'} (max 1000 affichés sur cette page, du plus récent au plus ancien)
    </h4>
    <p>
        <h4>Mean distance : ${authenticationResult?.distanceMean}</h4>
        <h4>Median distance : ${authenticationResult?.distanceMedian}</h4>
        <h4>Variance distance : ${authenticationResult?.distanceVariance}</h4>
        <h4>Standard deviation distance : ${authenticationResult?.distanceStandardDeviation}</h4>
        <h4>Trust detection treshold : ${authenticationResult?.trustTreshold}</h4>
        <h4>ANIA : ${authenticationResult?.ania}</h4>
        <h4>ANGA : ${authenticationResult?.anga}</h4>
    </p>
  
    <div id="graph" class="graph"></div>
    <table>
        <thead>
        <tr>
            <td>ID</td>
            <td>idAction</td>
            <td>typeAction</td>
            <td>begin</td>
            <td>end</td>
            <td>distance</td>
            <td>trust</td>
        </tr>
        </thead>
        <tbody>

        <g:each in="${listObselTrust}" status="i" var="obselTrust">
            <g:set var="linkGrails" value="${obselTrust.trust}"/>
        </g:each>

        <g:each in="${listObselTrust}" status="i" var="obselTrust">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${obselTrust.id}</td>
                <td>${obselTrust.idAction}</td>
                <td>${obselTrust.typeAction}</td>
                <td>${obselTrust.begin}</td>
                <td>${obselTrust.end}</td>
                <td>${obselTrust.distance}</td>
                <td>${obselTrust.trust}</td>
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
        title: 'Obseltrust.trust'
    };

    Plotly.newPlot(GRAPH, data2, layout2, {showLink: false});


</script>
</body>
</html>