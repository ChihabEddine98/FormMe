
<%@ page import="track.User" %>
<!DOCTYPE html>
<html>
	<head>
        <meta name="layout" content="admin">
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title>Auhtentication Results - User list</title>
	</head>
	<div id="content" class="content">
		<div id="list-user" class="scaffold-list" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="">
			<thead>
					<tr>

						<g:sortableColumn property="firstname" title="${message(code: 'user.firstname.label', default: 'Firstname')}" />

						<g:sortableColumn property="lastname" title="${message(code: 'user.lastname.label', default: 'Lastname')}" />

						<g:sortableColumn property="password" title="${message(code: 'user.email.label', default: 'email')}" />

						<td>Nb of signature authentications</td>

						<td>Nb of profile authentications</td>

						<td>Authentifications (details)</td>

					</tr>
				</thead>
				<tbody>
				<g:each in="${usersTuples}" status="i" var="userInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">



						<td>${fieldValue(bean: userInstance[0], field: "firstname")}</td>

						<td>${fieldValue(bean: userInstance[0], field: "lastname")}</td>

						<td>${fieldValue(bean: userInstance[0], field: "email")}</td>

						<td>${userInstance[1]}</td>

						<td>${userInstance[2]}</td>

						<td><g:link controller="admin" action="authenticationResults" id="${userInstance[0].id}">Lien Auth <i class="fa fa-external-link" aria-hidden="true"></i></g:link></td>


					</tr>
				</g:each>
				</tbody>
			</table>

			<div class="pagination">
				<g:paginate total="${userInstanceCount ?: 0}" />
			</div>
		</div>
    </div>
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
