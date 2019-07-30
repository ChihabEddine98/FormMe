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
    <title> Modules Disponibles </title>
</head>

<body>

<div class="content">


    <h1> Modules Disponibles </h1>
    <hr>

    <div class="models">

        <table>
            <thead>
            <tr>
                <td> Nom </td>
                <td> Durée </td>
                <td></td>
            </tr>
            </thead>
            <tbody>

            <g:each in="${modules}" var="module">
                <tr>
                    <td> ${module.toString()} </td>
                    <td> ${module.duree} </td>
                    <td>
                        <g:link controller="admin" action="editModuleUI" params="[module:module.id]">
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