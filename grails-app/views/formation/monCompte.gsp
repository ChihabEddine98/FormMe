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

                <li>
                    <img class="userImg" src="${userInstance.imgUrl}" alt=" Photo de profil">
                </li>
            </ul>

        </div>




</body>
</html>