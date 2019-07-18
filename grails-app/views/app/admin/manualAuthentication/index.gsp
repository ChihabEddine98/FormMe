<%@ page import="track.User" %>
<!DOCTYPE html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title>Manual Authentication</title>
</head>

<body>
<div class="content">
    <g:form controller="admin">

        <p>
        <label>Signature to use</label><br>
        <g:select name="signatureUser"
                  from="${User.list()}"
                  optionValue="email"
                  optionKey="id"/>
        </p>
        <p>
        <label>Profile to use</label><br>
        <g:select name="profileUser"
                  from="${User.list()}"
                  optionValue="email"
                  optionKey="id"/>
        </p>

        
         <g:actionSubmit value="Manually Authenticate Dynamic Users" type="button" class="button"
                        action="manuallyAuthenticateDynamicUser">Manually Authenticate Dynamic Users</g:actionSubmit>
         <g:actionSubmit value="Manually Authenticate Classic Users" type="button" class="button"
                        action="manuallyAuthenticateUser">Manually Authenticate Classic Users</g:actionSubmit>
    </g:form>
</div>

<script>

    var classActive = function () {
        $('.manualLi').addClass('activeLi');
        $('.manualA').addClass('activeA');
    };
    $(document).ready(function () {
        classActive();
        $("#pageName").text("Manual authentification");
    });
</script>
</body>
</html>
