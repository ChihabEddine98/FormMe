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


        <h1> Gestion Des Formations </h1>
        <hr>

        <div class="models">

            <table>
                <thead>
                <tr>
                    <td>Tables </td>
                    <td></td>
                    <td></td>
                </tr>
                </thead>
                <tbody>

                <tr>
                    <td> Cours </td>
                    <td>
                        <g:link controller="admin" action="listCours">
                            <i class="fas fa-list-ul"></i>
                        </g:link>
                    <td>
                        <g:link controller="admin" action="addCoursUI">
                            <i class="fas fa-plus"></i>
                        </g:link>
                    </td>
                </tr>

                <tr>
                    <td> Module </td>
                    <td>
                        <g:link controller="admin" action="listModule">
                            <i class="fas fa-list-ul"></i>
                        </g:link>
                    <td>
                        <g:link controller="admin" action="addModuleUI">
                            <i class="fas fa-plus"></i>
                        </g:link>
                    </td>
                </tr>

                <tr>
                    <td> Chapitre </td>
                    <td>
                        <g:link controller="admin" action="listChapitre">
                            <i class="fas fa-list-ul"></i>
                        </g:link>
                    <td>
                        <g:link controller="admin" action="addChapitreUI">
                            <i class="fas fa-plus"></i>
                        </g:link>
                    </td>
                </tr>

                <tr>
                    <td> Text Ressource </td>
                    <td>
                        <g:link controller="admin" action="listRessource" params="[type:'txt']">
                            <i class="fas fa-list-ul"></i>
                        </g:link>
                    <td>
                        <g:link controller="admin" action="addRessourceUI" params="[type:'txt']">
                            <i class="fas fa-plus"></i>
                        </g:link>
                    </td>
                </tr>

                <tr>
                    <td> Media Ressource </td>
                    <td>
                        <g:link controller="admin" action="listRessource" params="[type:'media']">
                            <i class="fas fa-list-ul"></i>
                        </g:link>
                    <td>
                        <g:link controller="admin" action="addRessourceUI" params="[type:'media']">
                            <i class="fas fa-plus"></i>
                        </g:link>
                    </td>
                </tr>

                <tr>
                    <td> Quiz Ressource </td>
                    <td>
                        <g:link controller="admin" action="listRessource" params="[type:'quiz']">
                            <i class="fas fa-list-ul"></i>
                        </g:link>
                    <td>
                        <g:link controller="admin" action="addRessourceUI" params="[type:'quiz']">
                            <i class="fas fa-plus"></i>
                        </g:link>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
%{--    <g:form controller="admin">--}%



%{--        <h1>--}%
%{--            Ajouter une nouvelle formation :--}%
%{--        </h1>--}%


%{--        <h2> Informations générales de la formation :</h2>--}%

%{--        <g:textField  class="form-input" type="text" name="nomCours"--}%
%{--                         placeholder="Nom de la formation"/>--}%

%{--        <g:textField  class="form-input" type="text" name="imgCours"--}%
%{--                      placeholder="Image de la formation"/>--}%

%{--        <g:textField  class="form-input" type="text" name="domaine"--}%
%{--                      placeholder="Quel Domaine ?"/>--}%

%{--        <g:textArea class="form-input message" name="competences" placeholder="Quelles compétences Apres ?"></g:textArea>--}%

%{--        <g:textArea class="form-input message" name="requis" placeholder="Quels Pré-requis ?"></g:textArea>--}%

%{--        <p>--}%
%{--            <h2> La durée du cours  ? </h2> <br>--}%
%{--            <g:field class="form-input" type="number"  name="dureeCours" required="" value="${20}" />--}%

%{--        </p>--}%




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


%{--    </g:form>--}%













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