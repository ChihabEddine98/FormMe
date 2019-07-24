<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin">
    <title>Auth results</title>
</head>

<body>
<div class="content">
    <h1>  ${typeAuth.toString().capitalize()} authentication</h1>
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
                <td>${authenticationResult.type.name()}</td>
                <td><g:link controller="admin" action="authenticationDetail"
                            id="${authenticationResult.id}">${authenticationResult.obselTrusts.size()} ${authenticationResult.obselTrusts.size() > 1 ? 'obsels' : 'obsel'} <i class="fa fa-external-link" aria-hidden="true"></g:link></td>
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