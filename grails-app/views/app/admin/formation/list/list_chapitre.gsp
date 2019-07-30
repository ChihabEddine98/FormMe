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
                        <g:link controller="admin" action="editChapitreUI" params="[chapitre:chapitre.id]">
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