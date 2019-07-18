<%@ page contentType="text/h
tml;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Home</title>
    <asset:stylesheet src="styles.css"/>

</head>

<body>
<div class="panel-body">
    <div class="container">

        <sec:ifNotLoggedIn>
            <h1 class="title top">
                Welcome to the TRACK application
            </h1>

            <p class="desc text-center">
                <g:link class="create" controller="user" action="create"><g:message code="Create an account"
                                                                                    args="[entityName]"/></g:link> or
                <g:link controller="login" action="auth"><g:message code="Log In"
                                                                    args="[entityName]"/></g:link> to the Application to test Authentication Algorithm.
            </p>

            <div class="container">
                <g:link controller="user" action="create"><button class="button signin">create an account</button></g:link>
                <g:link controller="login" action="auth"><button class="button login">login</button></g:link>
            </div>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>

            <script>
                window.location.replace("formation/forumHome");
            </script>

        </sec:ifLoggedIn>
    </div>
</div>

</body>
</html>