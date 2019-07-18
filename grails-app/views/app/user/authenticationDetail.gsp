<%--
  Created by IntelliJ IDEA.
  User: if-dev1
  Date: 23/02/2017
  Time: 17:53
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Résultats d'authentifications</title>
</head>

<body>
    <p>
        ${authenticationResult.user.firstname} ${authenticationResult.user.lastname}
    </p>
    <p>
        ${listObselTrust.size()} ${listObselTrust.size() > 1 ? 'obsels' : 'obsel'} (max 1000 affichés sur cette page, du plus récent au plus ancien)
    </p>
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
</body>
</html>