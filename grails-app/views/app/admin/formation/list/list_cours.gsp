<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-25
  Time: 12:51
--%>

<%@ page import="track.Cours" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">
    <title> Formations Disponibles </title>
</head>

<body>

<div class="content">


    <h1> Cours Disponibles </h1>
    <hr>

    <div class="champ">
        <h4>
            Ajouter une nouvelle formation ?
        </h4>
    </div>
    <div class="champ col-md-3 col-sm-4">
        <g:link class="button btn-primary" controller="admin" action="addCoursUI" params="[type:'chapitre']">
            <i class="fas fa-plus" > <span style="font-family: Futura;">Ajouter</span> </i>
        </g:link>
    </div>

    <hr>

    <div class="models">

        <table>
            <thead>
            <tr>
                <td></td>
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
                    <td>
                        <g:if test="${coursD.imgUrl!=null}">
                            <g:if test="${coursD.imgUrl.isEmpty() || coursD.imgUrl.equals(" ")}">
                                <asset:image class="mediaImgList" src="defaultCoursImg.jpg" alt="photo du cours"/>
                            </g:if>
                            <g:else>
                                <img class="mediaImgList" src="${coursD.imgUrl}" alt="photo de profil"/>
                            </g:else>
                        </g:if>
                        <g:else>
                            <asset:image class="mediaImgList" src="defaultCoursImg.jpg" alt="photo du cours"/>
                        </g:else>

                    </td>
                    <td> ${coursD.nom} </td>
                    <td> ${coursD.domaine} </td>
                    <td> ${coursD.duree} </td>
                    <td> ${coursD.competences} </td>
                    <td>
                        <g:link class="btn btn-warning" controller="admin" action="editCoursUI" params="[cours:coursD.id]">
                            <i class="fas fa-cogs" style="font-size: 27px; "></i>
                        </g:link>
                    </td>
                </tr>
            </g:each>





            </tbody>
        </table>

        <hr>

        <div class="pagination">
            <g:paginate total="${track.Cours.list().size()}"/>

        </div>

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