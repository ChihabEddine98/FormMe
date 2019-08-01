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
    <title> Modules Disponibles </title>
</head>

<body>

<div class="content">






    <h1> Chapitres Disponibles </h1>

    <hr>

    <div class="champ">
        <h4>
            Ajouter un nouveau Chapitre ?
        </h4>
    </div>
    <div class="champ col-md-3 col-sm-4">
        <g:link class="button btn-primary" controller="admin" action="addChapitreUI" params="[type:'chapitre']">
            <i class="fas fa-plus" > <span style="font-family: Futura;">Ajouter</span> </i>
        </g:link>
    </div>

    <hr>

    <div class="models">

        <table>
            <thead>
            <tr>
                <td> Nom </td>
                <td></td>
            </tr>
            </thead>
            <tbody>

            <g:each in="${chapitres}" var="chapitre">
                <tr>
                    <td> ${chapitre.toString()} </td>
                    <td>
                        <g:link class="btn btn-warning" controller="admin" action="editChapitreUI" params="[chapitre:chapitre.id]">
                            <i class="fas fa-cogs" style="font-size: 27px; "></i>
                        </g:link>
                    </td>
                </tr>
            </g:each>





            </tbody>
        </table>

        <hr>

        <div class="champ col-md-3 col-sm-4">
            <g:link class="button btn-danger" controller="admin" action="paramFormation" params="[type:'quiz']">
                <i class="fas fa-backward" > <span style="font-family: Futura;">Home</span> </i>
            </g:link>
        </div>

        <hr>

    </div>

</div>
</body>
</html>