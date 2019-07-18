<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-06-13
  Time: 13:20
--%>

<%@ page  import="track.Exercice; track.AudioImgVideo; track.Ressource; track.TextRessource; track.Quiz; track.QuizScores; formation.ChapitresController" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="default">
    <script type="text/javascript" src="../assets/TraceMe_CS/collector.js"></script>
    <title> Chapitres </title>
</head>

<body>

<div class="mainPane">

    <div class="container">

        <div class="tab">
            <button class="tablinks active" onclick="openTab(event, 'cours_tab')"> Chapitres </button>
            <button class="tablinks" onclick="openTab(event, 'forum_tab')"> Forum </button>
            <button class="tablinks" onclick="openTab(event, 'progress_tab')"> Progression </button>
        </div>


        <div id="cours_tab" class="tabcontent">
            <g:each var="chapitre" in="${chapitres.sort({a,b-> a.id.compareTo(b.id)} )}" status="i" >


            <div class="collapsible">
                <i class="fas fa-plus-circle"></i>
                <button>${chapitre.nom.capitalize()}</button>
            </div>

            <div class="content">
                <div>



                    <% def ressourcesChap= track.Ressource.findAllByChapitre(chapitre) %>



                    <g:if test="${ressourcesChap.size()!=0}">


                        <g:each in="${ressourcesChap}" var="res" >



                            %{-- Ressources De Type txt --}%
                            <g:if test = "${ res.instanceOf(track.TextRessource)}" >

                                <div class="imgContainer">
                                    <h2>
                                        Resources ( txt ):
                                    </h2>
                                    <p> ${res.contenu}</p>
                                </div>
                            </g:if>
                            %{-- Ressources De Type Image, Video , audio --}%

                            <g:if test = "${ res.instanceOf(track.AudioImgVideo)}" >

                                <g:if test = "${ res.type.equals("img")}" >
                                    <div class="imgContainer">
                                        <h2>
                                            Resources ( Images ):
                                        </h2>
                                        <a><img  src="${res.url}" alt="detail"></a>
                                    </div>
                                </g:if>

                                <g:if test = "${ res.type.equals("video")}" >
                                    <div class="videoContainer">
                                        <h2>
                                            Resources ( Vidéos ):
                                        </h2>


                                        <video width="100%" height="420" controls>
                                            <source src="${res.url}" frameborder="0" type="video/mp4">
                                            Your browser does not support the video tag.
                                        </video>
                                    </div>
                                </g:if>



                             </g:if>

                           %{--  Le cas d'un quiz --}%
                            <g:if test = "${ res.instanceOf(track.Quiz)}" >


                                <div class="imgContainer">
                                    <h1> Quiz Time ! </h1>
                                    <h2 style="font-family: lato; font-size: 36px;"> ${res.nom.toUpperCase()} </h2>
                                    <hr style="border: var(--attirante-col) dotted 2px;">



                                <% Quiz q=track.Quiz.findByNom(res.nom) %>
                                    <% def iq=(int)q.id %>

                                    <% def questions=formation.ChapitresController.questionsOfQuiz(iq) %>

                                    <g:form controller="chapitres" action="submitQuiz">

                                        <g:each in="${questions}" status="j"
                                                var="question">


                                    <fieldset class="btnPostContainer">
                                    </fieldset>

                                    <p id="question-body"> Question #${(j+1)}</p>
                                    %{-- Question De Type audio --}%
                                        <g:if test = "${ question.getQuestion().contains("http")}" >

                                            <div id="questionAudio">

                                                <audio controls>
                                                    <source src="${question.question}" type="audio/ogg">
                                                    Your browser does not support the audio element.
                                                </audio>

                                            </div>


                                        </g:if>
                                        <g:else>
                                            <p id="question-body">${ question.question} </p>
                                        </g:else>


                                        <div class="quizOptions">

                                            <g:radioGroup name="${"question"+j}" values="[question.sugg1,question.sugg2,question.sugg3,question.sugg4]"
                                                          labels="[question.sugg1,question.sugg2,question.sugg3,question.sugg4]">

                                                <label for="${"question"+j}" class="choiceContainer"> ${it.label} <br>
                                                    ${it.radio}
                                                    <div class="checkmark">
                                                    </div>
                                                </label>

                                            </g:radioGroup>

                                        </div>


                                            <hr style="border: var(--attirante-col) dotted 2px;">


                                        </g:each>

                                        <div>
                                            <g:hiddenField name="quizId" value="${iq}"/>
                                            <g:hiddenField name="indice" value="${(int)chapitre.moduleId}"/>

                                            <g:actionSubmit class="btnQ" value="Submit"  action="submitQuiz"/>
                                        </div>

                                        <g:if test="${scoreAlert}">
                                            <div class="alert">
                                                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                                            </div>
                                        </g:if>


                                    </g:form>






%{--                                    <hr style="margin-top: 90px;">--}%


                                </div>

                            </g:if>

                        </g:each>


                    </g:if>

                </div>

            </div>


        </g:each>
        </div>



        %{--  Forum du module  --}%
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



        document.getElementById("petiteImg")

        var buttons=document.getElementsByClassName("btnQ");
        var score = 0;

        function imgClicked() {
            alert (" Mouse is in : ("+event.pageX+","+event.pageY+")");
        }

        var scoreBtn=document.getElementById("score");
        var testOutput = document.getElementById("testOutput");


        function choiceClicked(qn,an) {
            if (qn===an)
            {
                score++


            }

        }

        function validateChoices() {

            if(score>3)
                score=3






            alert(" You Scored : "+score);
        }




        var coll = document.getElementsByClassName("collapsible");
        var i;

        for (i = 0; i < coll.length; i++) {
            coll[i].addEventListener("click", function() {


                this.classList.toggle("active");
                var content = this.nextElementSibling;
                if (content.style.display === "block") {
                    content.style.display = "none";
                } else {
                    content.style.display = "block";
                }
            });
        }


        // Get all elements with class="closebtn"
        var close = document.getElementsByClassName("closebtn");
        var i;

        // Loop through all close buttons
        for (i = 0; i < close.length; i++) {
            // When someone clicks on a close button
            close[i].onclick = function(){

                // Get the parent of <span class="closebtn"> (<div class="alert">)
                var div = this.parentElement;

                // Set the opacity of div to 0 (transparent)
                div.style.opacity = "0";

                // Hide the div after 600ms (the same amount of milliseconds it takes to fade out)
                setTimeout(function(){ div.style.display = "none"; }, 600);
            }
        }





    </script>

</body>
</html>


