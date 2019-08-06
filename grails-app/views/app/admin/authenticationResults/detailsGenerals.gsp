<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-08-05
  Time: 16:47
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

    <h1> Auth-Results List Trusts </h1>
    <hr>

    <table>
        <thead>
        <tr>
            <td>Session ID</td>
            <td>Trust Biometric</td>
            <td>Trust ENVAPP</td>
        </tr>
        </thead>
        <tbody>


        <g:each in="${authResults}" status="i" var="authenticationResult">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${authenticationResult.sessionId}</td>
                <td>${authenticationResult.trustBiometric}</td>
                <td>
                    <% def obsSession=track.ObselSessionTrust.findBySessionID(authenticationResult.sessionId)%>
                    <g:if test="${obsSession!=null}">
                        ${obsSession.trust}
                    </g:if>
                    <g:else>
                        Pas de trust
                    </g:else>
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