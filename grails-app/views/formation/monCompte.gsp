<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-06-20
  Time: 15:58
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Mon Compte </title>
    <meta name="layout" content="default">
</head>

<body>



        <div class="userInfoContainer">
            <h1>
                Bienvenue !
            </h1>

            <ul>
                <li>
                     ${userInstance.firstname.capitalize()} ${userInstance.lastname.capitalize()}
                </li>

                <li>
                    <i class="fas fa-address-book"> ${userInstance.email}</i>
                </li>


                <g:if test="${userInstance.imgUrl.isEmpty()}">
                    <g:if test="${userInstance.sexe=='m'}">
                        <li>
                            <asset:image class="userImg" src="profil_pic.png" alt="photo de profil"/>
                        </li>
                    </g:if>
                    <g:else>
                        <li>
                            <asset:image class="userImg" src="profil_pic_female.png" alt="photo de profil"/>
                        </li>
                    </g:else>
                </g:if>
                <g:else>
                        <li>
                            <img class="userImg" src="${userInstance.imgUrl}" alt=" Photo de profil">
                        </li>
                </g:else>

            </ul>

        </div>




</body>
</html>