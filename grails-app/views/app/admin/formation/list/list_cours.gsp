<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-25
  Time: 12:51
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">
    <title> Formations Disponibles </title>
</head>

<body>

<div class="content">


    <h1> Cours Disponibles </h1>
    <hr>

    <div class="models">

        <table>
            <thead>
            <tr>
                <td> Nom </td>
                <td> Domaine </td>
                <td> Durée </td>
                <td> Compétences </td>
                <td></td>
            </tr>
            </thead>
            <tbody>

            <g:each in="${coursDispo}" var="coursD">
                <tr>
                    <td> ${coursD.nom} </td>
                    <td> ${coursD.domaine} </td>
                    <td> ${coursD.duree} </td>
                    <td> ${coursD.competences} </td>
                    <td>
                        <g:link controller="admin" action="editCoursUI" params="[cours:coursD.id]">
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