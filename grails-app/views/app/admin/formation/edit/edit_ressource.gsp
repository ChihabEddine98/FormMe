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
    <g:form controller="admin">



        <h1>
            Modifier une  Ressource :
        </h1>

        <div class="champ">
            <label> Nom de la ressource  </label>
            <g:textField  class="form-input" type="text" name="nomRessource" value="${ressource.nom}"/>
        </div>

        <div class="champ">
            <label> Path   </label>
            <g:textField  class="form-input" type="text" name="pathRessource" value="${ressource.path}"/>
        </div>

       <% def c=ressource.chapitre %>

        <div class="champ">
            <h3> Chapitre*  </h3>
            <g:select class="form-input" name="chapitre" from="${Chapitre.findAll {}}" value="${c}"
            />

        </div>

        <g:if test="${type.equals("txt")}">

            <%  ressource=(TextRessource)ressource%>

            <div class="champ">
                <label> Contenu   </label>
                <g:textArea  class="form-input message" name="contenuRessource" value="${ressource.contenu}"/>
            </div>

        </g:if>
        <g:elseif test="${type.equals("media")}">
            <% ressource=(track.AudioImgVideo)ressource %>


            <div class="champ">
                <label> Url   </label>
                <g:textField  class="form-input" type="text" name="urlRessource" value="${ressource.url}"/>
            </div>

            <div class="champ">
                <label> Format   </label>
                <g:textField  class="form-input" type="text" name="formatRessource" value="${ressource.format}"/>
            </div>

        </g:elseif>
        <g:elseif test="${type.equals("quiz")}">
             <% ressource=(track.Quiz)ressource %>


            <div class="champ">
                <label> Nom Quiz*   </label>
                <g:textField  class="form-input" type="text" name="nomQuiz" value="${ressource.nom}"/>
            </div>

        </g:elseif>


        <div class="champ col-md-4 col-sm-4">
            <g:actionSubmit value="Sauvegarder" type="button" class="button btn-success"
                            controller= "admin" action="addCours" />
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