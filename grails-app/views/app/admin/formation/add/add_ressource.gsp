<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-29
  Time: 17:00
--%>


<%@ page import="track.Chapitre; track.User" contentType="text/html;charset=UTF-8" %>
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
    <g:hasErrors bean="${ressourceInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${ressourceInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <h1>
        Ajouter une nouvelle Ressource de type ${type} :
    </h1>

    <g:form>





        <div class="champ">
            <label> Nom de la ressource  </label>
            <g:textField  class="form-input" type="text" name="nom" />
        </div>





        <div class="champ">
            <h3> Chapitre*  </h3>
            <g:select class="form-input" name="chapitre" from="${track.Chapitre.list()}"
                      optionKey="id"

            />


        </div>




        <g:if test="${type.equals("txt")}">

%{--            <% def ressource=(track.TextRessource) ressourceInstance%>--}%


            <div class="champ">
                <label> Contenu   </label>
                <g:textArea  class="form-input message" name="contenu"/>
            </div>

            <g:hiddenField name="type" value="${type}"></g:hiddenField>

        </g:if>
        <g:elseif test="${type.equals("media")}">
%{--            <% ressource=(track.AudioImgVideo) ressourceInstance %>--}%


            <div class="champ">
                <label> Url   </label>
                <g:textField  class="form-input" type="text" name="url" />
            </div>

            <div class="champ">
                <h3> Format*  </h3>
                <g:select class="form-input" name="type" from="['audio','img','video']"

                />


            </div>

        </g:elseif>
        <g:elseif test="${type.equals("quiz")}">
%{--            <% ressource=(track.Quiz) ressourceInstance %>--}%


            <div class="champ">
                <label> Nom Quiz*   </label>
                <g:textField  class="form-input" type="text" name="nomQuiz" />
            </div>

            <g:hiddenField name="type" value="${"quiz"}"></g:hiddenField>

        </g:elseif>




        <div class="champ col-md-5 col-sm-4">
            <g:link class="button btn-danger" controller="admin" action="listRessource" params="[type:type]">
                <i class="fas fa-backward" > <span style="font-family: Futura;"> ${type.toUpperCase()} Ressources</span> </i>
            </g:link>
        </div>

        <div class="champ col-md-5 col-sm-4">
            <g:actionSubmit value="Sauvegarder" type="button" class="button btn-primary"
                            controller= "admin" action="addRessource" />
        </div>






    </g:form>












%{--        <g:actionSubmit value="Manually Authenticate Classic Users" type="button" class="button"--}%
%{--                        action="manuallyAuthenticateUser">Manually Authenticate Classic Users</g:actionSubmit>--}%
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
