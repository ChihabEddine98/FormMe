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
                            <g:link controller="admin" action="listQuestions" params="[quiz:ress.id]">
                                <i class="fas fa-question"></i>
                            </g:link>
                        </td>
                    </g:elseif>

                    <td>
                        <g:link controller="admin" action="editRessourceUI" params="[ressource:ress.id,type:type]">
                            <i class="far fa-edit"></i>
                        </g:link>
                    </td>
                </tr>
            </g:each>





            </tbody>
        </table>
    </div>

</div>
</body>
</html>