<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Résultats d'authentifications</title>
</head>

<body>
    <p>
        ${userInstance.firstname} ${userInstance.lastname}
    </p>
    <table>
        <thead>
        <tr>
            <td>ID</td>
            <td>Date début</td>
            <td>Type</td>
            <td>Authentifications (détail)</td>
        </tr>
        </thead>
        <tbody>
        <g:each in="${authenticationResults}" status="i" var="authenticationResult">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${authenticationResult.id}</td>
                <td><g:formatDate format="dd/MM/yyyy HH:mm" date="${authenticationResult.start}"/></td>
                <td>${authenticationResult.type.name()}</td>
                <td><g:link controller="user" action="authenticationDetail" id="${authenticationResult.id}">${authenticationResult.obselTrusts.size()} ${authenticationResult.obselTrusts.size() > 1 ? 'obsels' : 'obsel' }</g:link></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</body>
</html>