<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-09
  Time: 11:51
--%>

<%@ page import="track.Cours; track.User" contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="default">
    <script type="text/javascript" src="../assets/TraceMe_CS/collector.js"></script>

    <title> Mes Cours </title>
</head>

<body>





    <div class="mainPane" >

        <div class="mesCours">

        <div class="titres">
            <h1 >
                Mes Cours
            </h1>
        </div>


        <div class="container">



            <!-- Tab content -->
            <div id="cours_tab" class="tabcontent">
                <g:each class="${(i % 2) == 0 ? 'even' : 'odd'}" var="cours" in="${coursList.sort({a,b-> a.id.compareTo(b.id)} )}" status="i" >

                    <div class="rowCours">

                        <g:if test="${cours.imgUrl==null}">
                            <g:link class="imgCours" controller="homeFormation" action="accederModules" params="[indice: cours.id]">
                                <asset:image  src="defaultCoursImg.jpg" alt="pic_cours"/>
                            </g:link>

                        </g:if>
                        <g:else>
                            <g:link class="imgCours" controller="homeFormation" action="accederModules" params="[indice: cours.id]">
                                <img  src="${cours.imgUrl}" alt="pic_cours">
                            </g:link>
                        </g:else>
%{--                        <g:link class="imgCours" controller="homeFormation" action="accederModules" params="[indice: cours.id]"> <img  src="${cours.imgUrl}" alt="pic"> </g:link>--}%
                        <h1>Cours de ${cours.nom.capitalize()}</h1>
                        <g:link  class="btnLink" controller="homeFormation" action="accederModules" params="[indice: cours.id]">
                            Acceder
                        </g:link>

                    </div>

                </g:each>
            </div>

            <div class="pagination">
                <g:paginate total="${userInstance.cours.size()}"/>

            </div>


        </div>



    </div>
    </div>





</body>
</html>