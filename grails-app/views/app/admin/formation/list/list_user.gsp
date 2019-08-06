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
    <title> Utilisateurs Disponibles </title>
</head>

<body>

<div class="content">



    <g:if test="${filtre==null}">
        <h1 style="font-family: Futura;"> Tout Les Utilisateurs  </h1>
    </g:if>
    <g:else>
        <g:if test="${filtre.equals("cours")}">
            <h1 style="font-family: Futura;"> Les Utilisateurs Inscrits à un cours (au moins) </h1>
        </g:if>
        <g:if test="${filtre.equals("admin")}">
            <h1 style="font-family: Futura;"> Les Admins </h1>
        </g:if>
        <g:if test="${filtre.equals("simple")}">
            <h1 style="font-family: Futura;"> Les Utilisateurs Simples</h1>
        </g:if>
    </g:else>

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

    <hr>
    <div class="champ">
        <h4 style="font-family: Futura; font-size: 32px;color: #3e4154;"> ${count} Résultats trouvés</h4>
    </div>
    <hr>
    <div class="">
        <h4 style="font-family: Futura; font-size: 32px">
            Filtrer les résultats ?
        </h4>
    </div>
    <div class="champ col-md-2 col-sm-4">
        <g:link class="button btn-success" controller="admin" action="gestionUsers">
            <i class="fas fa-users" > <span style="font-family: Futura;">All Users</span> </i>
        </g:link>
    </div>
    <div class="champ col-md-3 col-sm-4">
        <g:link class="button btn-primary" controller="admin" action="gestionUsers" params="[filtre:'cours']">
            <i class="fas fa-user-graduate" > <span style="font-family: Futura;">Inscrits à un cours</span> </i>
        </g:link>
    </div>
    <div class="champ col-md-3 col-sm-4">
        <g:link class="button btn-warning" controller="admin" action="gestionUsers" params="[filtre:'admin']">
            <i class="fas fa-user-shield" > <span style="font-family: Futura;">Admin Role</span> </i>
        </g:link>
    </div>
    <div class="champ col-md-3 col-sm-4">
        <g:link class="button btn-danger" controller="admin" action="gestionUsers" params="[filtre:'simple']">
            <i class="fas fa-user" style="color: white;"> <span style="font-family: Futura; color: white"> Simple User</span> </i>
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

        <hr>


        <div class="pagination">

            <g:if test="${filtre!=null}">
                <g:paginate total="${count}" params="[filtre: filtre]"/>
            </g:if>
            <g:else>
                <g:paginate total="${User.list().size()}"/>
            </g:else>

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