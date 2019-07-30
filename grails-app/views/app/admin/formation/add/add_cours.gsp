<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-25
  Time: 12:16
--%>




<%@ page import="track.User" contentType="text/html;charset=UTF-8" %>
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
    <g:hasErrors bean="${coursInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${coursInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
%{--<g:form url="[resource: coursInstance,controller:'admin',action:'addCours']">--}%

    <g:form>



        <h1>
            Ajouter une nouvelle formation :
        </h1>


        <h2> Informations générales de la formation :</h2>



        <g:textField  class="form-input" type="text" name="nom"
                      placeholder="Nom de la formation *"/>


        <g:textField  class="form-input" type="text" name="imgUrl"
                      placeholder="Image de la formation"/>


        <g:textField  class="form-input" type="text" name="domaine"
                      placeholder="Quel Domaine ? *"/>

        <g:textArea class="form-input message" name="competences" placeholder="Quelles compétences Apres ? *"></g:textArea>

        <g:textArea class="form-input message" name="requis" placeholder="Quels Pré-requis ?"></g:textArea>

        <p>
        <h2> La durée du cours (Heures) * :</h2> <br>

        <g:field class="form-input" type="number"  name="duree" required="" value="${15}" />

        </p>



        <div class="champ col-md-4 col-sm-4">

            <g:actionSubmit value="Sauvegarder" type="button" class="button btn-success"
                            controller="admin" action="addCours"/>

        </div>




%{--        <% def nbModules=1%>--}%
%{--        <g:form>--}%
%{--            <h2> Détails de modules :</h2>--}%
%{--            <p>--}%
%{--            <h2> Combien de modules ? </h2> <br>--}%
%{--            <g:field class="form-input" type="number" name="nbModules" required="" value="${nbModules}" />--}%

%{--            </p>--}%

%{--            <br>--}%

%{--            <g:actionSubmit value="Next" type="button" class="button"--}%
%{--                            controller= "admin" action="addModuleUI" />--}%
%{--        </g:form>--}%


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
</html>