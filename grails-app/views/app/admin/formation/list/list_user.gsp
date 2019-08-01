<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-08-01
  Time: 10:50
--%>

<%@ page import="track.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">
    <title> Formations Disponibles </title>
</head>

<body>

<div class="content">


    <h1> Users  </h1>
    <hr>

    <div class="champ">
        <h4>
            Ajouter un nouveau utilisateur ?
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
                <td> Firstname </td>
                <td> Lastname </td>
                <td> Sexe </td>
                <td> Cours</td>
                <td></td>
            </tr>
            </thead>
            <tbody>

            <g:each in="${users}" var="user">
                <tr>
                    <td>
                        <g:if test="${user.imgUrl.isEmpty() || user.imgUrl.equals(" ")}">
                            <g:if test="${user.sexe=='m'}">
                                <asset:image class="userImgList" src="profil_pic.png" alt="photo de profil"/>
                            </g:if>
                            <g:else>
                                <asset:image class="userImgList" src="profil_pic_female.png" alt="photo de profil"/>
                            </g:else>
                        </g:if>
                        <g:else>
                            <img class="userImgList" src="${user.imgUrl}" alt="photo de profil"/>
                        </g:else>

                    </td>
                    <td> ${user.firstname} </td>
                    <td> ${user.lastname} </td>
                    <td>
                       <g:if test="${user.sexe=='m'}">
                           <i class="fas fa-male " style="font-size: 38px; color: #00b3ee;"></i>
                       </g:if>
                       <g:else>
                           <i class="fas fa-female" style="font-size: 38px; color: #ee84d6;"></i>
                       </g:else>

                    </td>
                    <td> ${user.cours} </td>
                    <td>
                        <g:link class="btn btn-warning" controller="admin" action="editCoursUI" params="[cours:user.id]">
                            <i class="fas fa-cogs" style="font-size: 27px; "></i>
                        </g:link>
                    </td>
                </tr>
            </g:each>





            </tbody>
        </table>

        <div class="pagination">
            <g:paginate total="${User.list().size()}"/>

        </div>

        <hr>

        <div class="champ col-md-5 col-sm-4">
            <g:link class="button btn-danger" controller="admin" action="paramFormation" params="[type:'quiz']">
                <i class="fas fa-graduation-cap fa-layout" > <span style="font-family: Futura; font-size: 32px;">formations</span> </i>
            </g:link>
        </div>

        <hr>

    </div>

</div>
</body>
</html>