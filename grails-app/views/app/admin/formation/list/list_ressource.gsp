<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-25
  Time: 17:09
--%>

<%@ page import="track.TextRessource" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">
    <title> ${type} Ressources  </title>
</head>

<body>

<div class="content">


    <h1> ${type} Ressources </h1>
    <hr>

    <div class="models">


        <hr>

        <div class="champ">
            <h4>
                Ajouter une nouvelle Ressource ?
            </h4>
        </div>
        <div class="champ col-md-3 col-sm-4">
            <g:link class="button btn-primary" controller="admin" action="addRessourceUI" params="[type:type]">
                <i class="fas fa-plus" > <span style="font-family: Futura;">Ajouter</span> </i>
            </g:link>
        </div>

        <hr>
        <table>
            <thead>
            <tr>
                <td> Nom </td>
                <td> Path </td>
                <g:if test="${type.equals("txt")}">
                    <td> Contenu </td>
                </g:if>
                <g:elseif test="${type.equals("media")}">
                    <td> Url </td>
                    <td> Format </td>
                </g:elseif>
                <g:elseif test="${type.equals("quiz")}">
                    <td> Nom Quiz </td>
                    <td></td>
                    <td></td>
                </g:elseif>

                <td></td>
            </tr>
            </thead>
            <tbody>

            <g:each in="${ressources}" var="ress">
                <tr>
                    <td> ${ress.nom} </td>
                    <td> ${ress.path} </td>
                    <g:if test="${type.equals("txt")}">
                        <td> ${((track.TextRessource)ress).contenu}  </td>
                    </g:if>
                    <g:elseif test="${type.equals("media")}">
                        <td> ${((track.AudioImgVideo)ress).url}  </td>
                        <td> ${((track.AudioImgVideo)ress).format}  </td>
                    </g:elseif>
                    <g:elseif test="${type.equals("quiz")}">
                        <td> ${((track.Quiz)ress).nom} </td>
                        <td>
                            <g:link class="btn btn-primary" controller="admin" action="listQuestions" params="[quiz:ress.id]">
                                <i class="fas fa-question"style="font-size: 24px;"></i>
                            </g:link>
                        </td>
                        <td>
                            <g:link class="btn btn-success" controller="admin" action="addQuestionUI" params="[quizId:ress.id]">
                                <i class="fas fa-plus"style="font-size: 24px; "></i>
                            </g:link>
                        </td>
                    </g:elseif>

                    <td>
                        <g:link class="btn btn-warning" controller="admin" action="editRessourceUI" params="[ressource:ress.id,type:type]">
                            <i class="fas fa-cogs" style="font-size: 24px; "></i>
                        </g:link>
                    </td>

                </tr>
            </g:each>





            </tbody>
        </table>
    </div>


    <hr>

    <div class="champ col-md-3 col-sm-4">
        <g:link class="button btn-danger" controller="admin" action="paramFormation" params="[type:'quiz']">
            <i class="fas fa-backward" > <span style="font-family: Futura;">Home</span> </i>
        </g:link>
    </div>

    <hr>

</div>
</body>
</html>