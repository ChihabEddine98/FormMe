<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-08-05
  Time: 14:31
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

    <h1> Auth-Results List </h1>
    <hr>

    <table>
        <thead>
        <tr>
            <td>Session ID</td>
            <td>Trust</td>
            <td></td>
        </tr>
        </thead>
        <tbody>


        <g:each in="${authResults}" status="i" var="authenticationResult">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${authenticationResult.sessionId}</td>
                <td>${authenticationResult.lastTrust()}</td>
                <td>
                    <g:link controller="admin" action="authenticationDetail" id="${authenticationResult.id}">
                        <i class="fas fa-plus-circle" style="font-size: 28px;color: #37a14b;"> Details</i>
                    </g:link>
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





</script>
</body>
</html>