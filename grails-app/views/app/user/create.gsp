<!DOCTYPE html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <asset:stylesheet src="styles.css"/>

<body>


<h2 class="title top">Create an account</h2>
<hr class="underline">
<div id="create-user" class="panel-body panel-body-forms grey" role="main">
    <div class="container container-form">

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${userInstance}">
            <ul class="errors" role="alert">
                <g:eachError bean="${userInstance}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                            error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>


        <g:form url="[resource: userInstance, action: 'save']">
            <fieldset class="form">
                <g:render template="/app/user/form"/>

                    <h2 class="rules">*Password must contain an upper case, a lower case, a number and contain more than 8 characters.</h2>

                <g:link class="link-redirect register" controller="login" action="auth">already sign-up ? login here</g:link>


                    <div id="validate" class="form-input form-validate">
                        <input id="submitbutton" type="submit" name="create" value="validate"/>
                    </div>
                </div>
            </fieldset>
        </g:form>
    </div>
</div>

</body>
</html>
