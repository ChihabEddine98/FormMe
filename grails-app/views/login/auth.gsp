<html>
<head>
    <g:set var="entityName" value="${message(code: 'users.label', default: 'Users')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <asset:stylesheet src="styles.css"/>

</head>

<body>

<h2 class="title top">Sign in</h2>
<hr class="underline">

<div id="create-user" class="panel-body panel-body-forms grey" role="main">
    <div class="container container-form">
        <fieldset>
            <form role="form" action='${postUrl}' method="POST" id="loginForm" autocomplete="off">

                <div class="form-input form-signin-email">
                    <input type="text" name="j_username" id="username"
                           placeholder="Email Address">
                </div>

                <div class="form-input form-signin-password">
                    <input type="password" name="j_password" id="password" placeholder="Password">
                </div>

                <input type='checkbox' class="remember-me" class='' name='${rememberMeParameter}'
                       id='remember_me'
                       <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                <label class="remember-me-txt" for='remember_me'>se souvenir de moi</label>
                <g:link class="link-redirect createacc" controller="user" action="create">create an account</g:link>

                <input type="submit" class="form-input form-signin" value="sign in">

            </form>
        </fieldset>
    </div>
</div>
</div>
</div>

</body>

</html>


