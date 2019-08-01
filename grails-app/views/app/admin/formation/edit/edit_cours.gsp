<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-25
  Time: 13:09
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

    <g:form>



        <h1>
            Modifier une  formation :
        </h1>


%{--        <h2> Informations générales de la formation :</h2>--}%


        <div class="champ">
            <label> Nom de la formation  </label>
            <g:textField  class="form-input" type="text" name="nom" value="${coursInstance.nom}"/>
        </div>

        <div class="champ">
            <label> Image de la formation  </label>
            <g:textField  class="form-input" type="text" name="imgUrl" value="${coursInstance.imgUrl}"/>

%{--            <g:uploadForm controller="admin" action="uploadImg">--}%
%{--                <input type="file" name="imgFile" />--}%
%{--                <input type="submit" />--}%
%{--            </g:uploadForm>--}%

        </div>

        <div class="champ">
            <label> Quel Domaine ?  </label>
            <g:textField  class="form-input" type="text" name="domaine" value="${coursInstance.domaine}"
            />
        </div>

        <div class="champ">
            <label> Quelles compétences Apres ?   </label>
            <g:textArea class="form-input message" name="competences" value="${coursInstance.competences}"></g:textArea>
        </div>

        <div class="champ">
            <label> Quels Pré-requis ?  </label>
            <g:textArea class="form-input message" name="requis" value="${coursInstance.requis}"></g:textArea>
        </div>

        <div class="champ">
        <label> La durée (Heures)  </label>
        <g:field class="form-input" type="number"  name="duree" required="" value="${coursInstance.duree}" />
        </div>

%{--        <div class="champ">--}%
%{--            <label> Modules  </label>--}%
%{--            <g:each in="${coursInstance.modules}" var="module">--}%
%{--                <div class="champ_module">--}%
%{--                    <h3> ${module.toString()}</h3>--}%
%{--                    <g:link class="twitter" onclick="return confirm(' Etes Vous Sur De vouloir Supprimer ce module ?');" controller="admin" action="editCoursUI" params="[coursInstance:coursInstance.id,module:module.id]">--}%
%{--                        <i class="far fa-trash-alt"></i>--}%
%{--                    </g:link>--}%
%{--                </div>--}%
%{--            </g:each>--}%
%{--        </div>--}%

        <g:hiddenField name="coursId" value="${coursId}"></g:hiddenField>


        <div class="champ col-md-5 col-sm-4">
            <g:link class="button btn-danger" controller="admin" action="listCours" params="[type:'quiz']">
                <i class="fas fa-backward" > <span style="font-family: Futura;">Formations</span> </i>
            </g:link>
        </div>

        <div class="champ col-md-5 col-sm-4">
                <g:actionSubmit value="Sauvegarder" type="button" class="button btn-primary"
                                controller= "admin" action="editCours"/>

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