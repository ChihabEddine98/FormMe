<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-25
  Time: 16:29
--%>


<%@ page import="track.Module; track.User" contentType="text/html;charset=UTF-8" %>
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
    <g:hasErrors bean="${chapitreInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${chapitreInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>


    <g:form>



        <h1>
            Modifier un Chapitre :
        </h1>


    %{--        <h2> Informations générales de la formation :</h2>--}%


        <div class="champ">
            <label> Nom du Chapitre*  </label>
            <g:textField  class="form-input" type="text" name="nom" value="${chapitreInstance.nom}"/>
        </div>




        <div class="champ">
            <h3> Module*  </h3>
            <g:select class="form-input" name="module" from="${Module.list()}"
                      value="${chapitreInstance?.module.toString()}"
                      optionKey="id"

            />

        </div>

        <g:hiddenField name="chapitreId" value="${chapitreInstance.id}"></g:hiddenField>


        <div class="champ col-md-5 col-sm-4">
            <g:link class="button btn-danger" controller="admin" action="listChapitre" params="[type:'quiz']">
                <i class="fas fa-backward" > <span style="font-family: Futura;">Chapitres</span> </i>
            </g:link>
        </div>

        <div class="champ col-md-5 col-sm-4">
            <g:actionSubmit value="Sauvegarder" type="button" class="button btn-primary"
                            controller= "admin" action="editChapitre" />
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
