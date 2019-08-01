<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-26
  Time: 10:46
--%>


<%@ page import="track.TextRessource; track.Chapitre; track.User" contentType="text/html;charset=UTF-8" %>
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
            <g:textField  class="form-input" type="text" name="nom" value="${ressourceInstance.nom}"/>
        </div>





        <div class="champ">
            <h3> Chapitre*  </h3>
            <g:select class="form-input" name="chapitre" from="${Chapitre.list()}"
                      value="${ressourceInstance.chapitre.id}"
                      optionKey="id"


            />


        </div>




        <g:if test="${type.equals("txt")}">

            <%  ressourceInstance=(track.TextRessource) ressourceInstance%>


            <div class="champ">
                <label> Contenu   </label>
                <g:textArea  class="form-input message" name="contenu" value="${ressourceInstance.contenu}"/>
            </div>

            <g:hiddenField name="type" value="${type}"></g:hiddenField>

        </g:if>
        <g:elseif test="${type.equals("media")}">
            <% ressourceInstance=(track.AudioImgVideo) ressourceInstance %>


            <div class="champ">
                <label> Url   </label>
                <g:textField  class="form-input" type="text" name="url" value="${ressourceInstance.url}" />
            </div>

            <div class="champ">
                <h3> Format*  </h3>
                <g:select class="form-input" name="type" from="['audio','img','video']"
                          value="${ressourceInstance.type}"
                />


            </div>

        </g:elseif>
        <g:elseif test="${type.equals("quiz")}">
            <% ressourceInstance=(track.Quiz) ressourceInstance %>


            <div class="champ">
                <label> Nom Quiz*   </label>
                <g:textField  class="form-input" type="text" name="nomQuiz"
                              value="${ressourceInstance.nom}"/>
            </div>

            <g:hiddenField name="type" value="${"quiz"}"></g:hiddenField>

        </g:elseif>

        <g:hiddenField name="resId" value="${resId}"></g:hiddenField>



        <div class="champ col-md-5 col-sm-4">
            <g:link class="button btn-danger" controller="admin" action="listRessource" params="[type:type]">
                <i class="fas fa-backward" > <span style="font-family: Futura;"> ${type.toUpperCase()} Ressources</span> </i>
            </g:link>
        </div>

        <div class="champ col-md-5 col-sm-4">
            <g:actionSubmit value="Sauvegarder" type="button" class="button btn-primary"
                            controller= "admin" action="editRessource" />
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
<