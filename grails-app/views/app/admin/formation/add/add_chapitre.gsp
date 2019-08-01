<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-25
  Time: 16:29
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
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


    <g:form controller="admin">
        <div class="saisieModule">




            <div class="moduleEntity">

                <div class="champ">
                    <g:textField  class="form-input" type="text" name="nom"
                                  placeholder="Nom du Chapitre*"/>
                </div>


                <div class="champ">
                    <h3> Module*  </h3>
                    <g:select class="form-input" name="moduleNom" from="${track.Module.findAll {}}"
                    />

                </div>

            </div>


            <div class="champ col-md-4 col-sm-4">
                <g:link class="button btn-danger" controller="admin" action="listChapitre" params="[type:'quiz']">
                    <i class="fas fa-backward" > <span style="font-family: Futura;">Chapitres</span> </i>
                </g:link>
            </div>

            <div class="champ col-md-4 col-sm-4">

                <g:actionSubmit value="Sauvegarder" type="button" class="button btn-primary"
                                controller= "admin" action="addChapitre" />

            </div>





        </div>

    </g:form>
</div>



<script>

    var classActive = function () {
        $('.formationLi').addClass('activeLi');
        $('.formationA').addClass('activeA');
    };
    $(document).ready(function () {
        classActive();
        $("#pageName").text("parametres de la formation");
    });
</script>
</body>
</html>