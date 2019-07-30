<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-25
  Time: 16:16
--%>



<%@ page import="track.Cours; track.Module; track.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">

    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title>Parametres de la formation</title>
</head>

<body>
<div class="content">


    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${moduleInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${moduleInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>


    <g:form controller= "admin" action="editModule" >


        <h1>
            Modifier un Module :
        </h1>


    %{--        <h2> Informations générales de la formation :</h2>--}%


        <div class="champ">
            <label> Nom du module *  </label>
            <g:textField  class="form-input" type="text" name="nom" value="${moduleInstance.nom}"/>
        </div>


        <h1> ${Module.findById(moduleId).cours}</h1>
        <div class="champ">
            <h3> Cours *  </h3>
            <g:select class="form-input" name="cours" from="${Cours.list()}"
                      value="${moduleInstance?.cours.toString()}"
                      optionKey="nom"
            />

        </div>


        <div class="champ">
            <label> La durée (Heures) * </label>
            <g:field class="form-input" type="number"  name="duree" required="true" value="${moduleInstance.duree}" />
        </div>


        <g:hiddenField name="moduleId" value="${moduleId}"></g:hiddenField>


        <div class="champ col-md-4 col-sm-4">
            <g:actionSubmit value="Sauvegarder" type="button" class="button btn-success"
                            controller= "admin" action="editModule" />
        </div>






    </g:form>













%{--        <g:actionSubmit value="Manually Authenticate Classic Users" type="button" class="button"--}%
%{--                        action="manuallyAuthenticateUser">Manually Authenticate Classic Users</g:actionSubmit>--}%
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

    var classActive = function () {
        $('.formationLi').addClass('activeLi');
        $('.formationA').addClass('activeA');
    };
    $(document).ready(function () {
        classActive();
        $("#pageName").text("parametres de la formation");
    });


    function confirmDialog() {

        return  $.confirm({
            title: 'Confirm!',
            content: 'Simple confirm!',
            buttons: {
                confirm: function () {
                    $.alert('Confirmed!');
                },
                cancel: function () {
                    $.alert('Canceled!');
                },
                somethingElse: {
                    text: 'Something else',
                    btnClass: 'btn-blue',
                    keys: ['enter', 'shift'],
                    action: function(){
                        $.alert('Something else?');
                    }
                }
            }
        });
    }

</script>
</body>
</html>