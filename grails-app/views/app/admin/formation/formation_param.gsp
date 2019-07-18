<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-06-24
  Time: 14:39
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
    <g:form controller="admin">



        <h1>
            Ajouter une nouvelle formation :
        </h1>


        <h2> Informations générales de la formation :</h2>

        <g:textField  class="form-input" type="text" name="nomCours"
                         placeholder="Nom de la formation"/>

        <g:textField  class="form-input" type="text" name="imgCours"
                      placeholder="Image de la formation"/>

        <g:textField  class="form-input" type="text" name="domaine"
                      placeholder="Quel Domaine ?"/>

        <g:textArea class="form-input message" name="competences" placeholder="Quelles compétences Apres ?"></g:textArea>

        <g:textArea class="form-input message" name="requis" placeholder="Quels Pré-requis ?"></g:textArea>

        <p>
            <h2> La durée du cours  ? </h2> <br>
            <g:field class="form-input" type="number"  name="dureeCours" required="" value="${20}" />

        </p>




        <% int nbModules=1%>
        <h2> Détails de modules :</h2>
        <p>
            <h2> Combien de modules ? </h2> <br>
            <g:field class="form-input" type="number" name="nbModules" required="" value="${nbModules}" />

        </p>

        <br>

        <g:actionSubmit value="Next" type="button" class="button"
                        controller= "admin" action="addModule" params="nbModules:nbModules"/>

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