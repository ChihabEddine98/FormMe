<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-06-07
  Time: 18:07
--%>

<%@ page import="track.Section" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="default">
    <script type="text/javascript" src="../assets/TraceMe_CS/collector.js"></script>
    <title> Modules </title>
</head>

<body>



<div class="mainPane">

    <div class="container">


        <div class="tab">
            <button class="tablinks active" onclick="openTab(event, 'cours_tab')"> Cours </button>
            <button class="tablinks" onclick="openTab(event, 'desc_tab')"> Description </button>
            <button class="tablinks" onclick="openTab(event, 'forum_tab')"> Forum </button>
            <button class="tablinks" onclick="openTab(event, 'progress_tab')"> Progression </button>
        </div>




        %{--  Nos modules  --}%
        <div id="cours_tab" class="tabcontent">
            <g:each var="module" in="${cours.modules.sort({a,b-> a.id.compareTo(b.id)} )}" status="i" >


                <div class="rowCours">

                    <g:link class="imgCours" controller="modules" action="accederChapitres" params="[indice : module.id]"> <img  src="${cours.imgUrl}" alt="pic"> </g:link>
                    <h1>${module.nom.capitalize()}</h1>
                    <g:link  class="btnLink" controller="modules" action="accederChapitres" params="[indice : module.id]">
                        Acceder
                    </g:link>

                    <div class="duree">
                        <i class="far fa-clock"> ${module.duree} heures</i>
                    </div>

                </div>

            </g:each>

        </div>


        %{--  Description du cours  --}%
        <div id="desc_tab" class="tabcontent">


                <div class="competences">
                    <h1>${cours.competences.capitalize()} </h1>
                </div>

                <div class="requis">
                    <h2> Pré-requis :</h2> <br>
                    <p>
                        ${cours.requis.capitalize()}
                    </p>
                </div>


                <div class="duree">
                    <i class="far fa-clock"> ${cours.duree} heures</i>

                </div>

        </div>




        %{--  Forum du cours  --}%
        <div id="forum_tab" class="tabcontent">


        <% def sections=track.Section.findAllByTitle(cours.nom)%>
            <g:each in="${sections}" var="section">
                <div class="sectionForum">
                    <div class="sectionTitle">${section.title}</div>
                    <g:each in="${section.topics.sort{it.title}}" var="topic">
                        <div class="topic">
                            <g:link class="topicTitle" controller="forum" action="topic" params="[topicId:topic.id]" >
                                <h2>${topic.title}</h2>
                            </g:link>
                            <span class="topicDesc">${topic.description}</span>
                            <div class="rightInfo">
                                <b>replies</b>: ${topic.numberOfReplies}
                            </div>

                            <div class="centerInfo">
                                <b>discussions</b>: ${topic.numberOfThreads}
                            </div>
                        </div>
                    </g:each>
                </div>
             </g:each>

        </div>

    </div>

</div>



    <script>


        function openTab(evt, tab_id) {
            // Declare all variables
            var i, tabcontent, tablinks;

            // Get all elements with class="tabcontent" and hide them
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }

            // Get all elements with class="tablinks" and remove the class "active"
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }

            // Show the current tab, and add an "active" class to the button that opened the tab
            document.getElementById(tab_id).style.display = "block";
            evt.currentTarget.className += " active";
        }


    </script>


</body>
</html>