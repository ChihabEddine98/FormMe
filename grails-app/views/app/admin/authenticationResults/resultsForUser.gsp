<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin">
    <title>Auth results</title>
</head>

<body>
<div class="content">

    <h1>  ${typeModality.toString().capitalize()} authentication</h1>
    <hr>

    <div class="fa fa-user" aria-hidden="true">
        <h2>${userInstance.firstname} ${userInstance.lastname}</h2></div>
    <table class="">
        <thead>
        <tr>
            <td>ID</td>
            <td>Date début</td>
            <td>Type</td>
            <td>Authentifications (détail)</td>
            <td>User Signature</td>
            <td>User Profile</td>
        </tr>
        </thead>
        <tbody>
        <g:each in="${authenticationResults}" status="i" var="authenticationResult">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${authenticationResult.id}</td>
                <td><g:formatDate format="dd/MM/yyyy HH:mm" date="${authenticationResult.start}"/></td>
                <td>${authenticationResult.typeModalitie}</td>
                <td><g:link controller="admin" action="authenticationDetail" params="[type:typeModality]"
                            id="${authenticationResult.id}">
                    <g:if test="${typeModality.equals("BIOMETRIC")}">
                        ${authenticationResult.obselTrusts.size()}
                    </g:if>
                    <g:elseif test="${typeModality.equals("ENVAPP")}">
                        ${authenticationResult.obselSessionTrusts.size()}
                    </g:elseif>
                        obsels
                    <i class="fa fa-external-link" aria-hidden="true"/>
                    </g:link>
                </td>
                <td>${authenticationResult.userSignature.email}</td>
                <td>${authenticationResult.userProfile.email}</td>
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
</script>
</body>
</html>