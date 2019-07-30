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
                  optionKey="email"/>
        </p>
        <p>
        <label>Profile to use</label><br>
        <g:select name="profileUser"
                  from="${User.list()}"
                  optionValue="email"
                  optionKey="email"/>
        </p>

        <br/>
         <g:actionSubmit value="Manually Authenticate Biometric Users" type="button" class="button"  action="manuallyAuthenticateBiometricUser"> Manually Authenticate Biometric Users </g:actionSubmit>
        <br/> 
          <g:actionSubmit value="Manually Authenticate Environnement Apprentissage Users" type="button" class="button"  action="manuallyAuthenticateEnvAppUser"> Manually Authenticate Environnement Apprentissage Users </g:actionSubmit>
        <br/>
           <g:actionSubmit value="Manually Authenticate Comportement Apprentissage Users" type="button" class="button"  action="manuallyAuthenticateBiometricUser"> Manually Authenticate Comportement Apprentissage Users </g:actionSubmit>
         <br/>
         <g:actionSubmit value="Manually Authenticate Degre Final Users" type="button" class="button"  action="manuallyAuthenticateBiometricUser"> Manually Authenticate Degré Final Users </g:actionSubmit>
         
<%--         <g:actionSubmit value="Manually Authenticate Classic Users" type="button" class="button"--%>
<%--                        action="manuallyAuthenticateUser">Manually Authenticate Classic Users</g:actionSubmit>--%>
    
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
