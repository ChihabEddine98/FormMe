<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-06-07
  Time: 10:45
--%>

<%@ page import="track.Cours; track.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>

    <meta name="layout" content="default">
   <script type="text/javascript" src="../assets/TraceMe_CS/collector.js"></script>

    <title> home </title>
</head>

<body>










    <div class="mainPane" id="main">
        <div class="coursDispo">

            <div class="titres">
                <h1>
                    Cours Disponibles

                </h1>
            </div>




            <!-- Tab content -->
            <div  class="container">
                <g:each class="${(i % 2) == 0 ? 'even' : 'odd'}" var="cours" in="${listCours.sort({a,b-> a.id.compareTo(b.id)} )}" status="i" >


                    <div class="coursDispo_row">
                        <g:if test="${cours.imgUrl==null}">
                            <asset:image  src="defaultCoursImg.jpg" alt="pic_cours"/>
                        </g:if>
                        <g:else>
                            <img  src="${cours.imgUrl}" alt="pic_cours">
                        </g:else>

                    %{--                        <h2 class="indexCours">${(i+1).toString()+"/"+coursDispo.size()}</h2>--}%

                    %{--                        <asset:image class="userImg" src="${cours.imgUrl}" alt="cours"/>--}%


                        <div class="collapsible">
                            <i class="fas fa-plus-circle"></i>
                            <button>Detail du cours </button>
                        </div>

                        <div class="content">


                            <h1>Cours de ${cours.nom.capitalize()}</h1>

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

                        <g:link  class="btnLink" controller="homeFormation" action="inscriptionCours" params="[indice: cours.id]">
                            <div class="btnQ">
                                S'inscrire
                            </div>
                        </g:link>
                    </div>





                </g:each>
            </div>

            <div class="pagination">
                <g:paginate total="${Cours.list().size()}"/>

            </div>



        </div>


%{--        <div id="mesCours">--}%

%{--            <div class="titres">--}%
%{--                <h1 >--}%
%{--                    Mes Cours--}%
%{--                </h1>--}%
%{--            </div>--}%

%{--            <div class="container">--}%



%{--                <%  desc=null %>--}%



%{--                <!-- Tab content -->--}%
%{--                <div id="cours_tab" class="tabcontent">--}%
%{--                    <g:each class="${(i % 2) == 0 ? 'even' : 'odd'}" var="cours" in="${userInstance.cours.sort({a,b-> a.id.compareTo(b.id)} )}" status="i" >--}%




%{--                        <g:if test = "${i==0}" >--}%
%{--                            <input id="${"rad"+(i+1).toString()}" type="radio" name="rad" checked>--}%
%{--                        </g:if>--}%


%{--                        <g:else>--}%
%{--                            <input id="${"rad"+(i+1).toString()}" type="radio" name="rad">--}%
%{--                        </g:else>--}%

%{--                        <section>--}%

%{--                            <div class="rowCours">--}%
%{--                                <h2 class="indexCours">${(i+1).toString()+"/"+userInstance.cours.size()}</h2>--}%

%{--                                <g:link class="imgCours" controller="homeFormation" action="accederModules" params="[indice: cours.id]"> <img  src="${cours.imgUrl}" alt="pic"> </g:link>--}%
%{--                                <h1>Cours de ${cours.nom.capitalize()}</h1>--}%
%{--                                <g:link  class="btnLink" controller="homeFormation" action="accederModules" params="[indice: cours.id]">--}%
%{--                                    Acceder--}%
%{--                                </g:link>--}%

%{--                            </div>--}%

%{--                            <div class="seq-btn">--}%
%{--                                <g:if test = "${i==0}" >--}%
%{--                                    <label for="${"rad"+(userInstance.cours.size()).toString()}"><i class="fa fa-chevron-left"></i></label>--}%
%{--                                    <label for="${"rad"+(i+2).toString()}"><i class="fa fa-chevron-right"></i></label>--}%
%{--                                </g:if>--}%
%{--                                <g:elseif test="${i==userInstance.cours.size()-1}">--}%
%{--                                    <label for="${"rad"+(i).toString()}"><i class="fa fa-chevron-left"></i></label>--}%
%{--                                    <label for="${"rad"+(1).toString()}"><i class="fa fa-chevron-right"></i></label>--}%
%{--                                </g:elseif>--}%
%{--                                <g:else>--}%
%{--                                    <label for="${"rad"+(i).toString()}"><i class="fa fa-chevron-left"></i></label>--}%
%{--                                    <label for="${"rad"+(i+2).toString()}"><i class="fa fa-chevron-right"></i></label>--}%
%{--                                </g:else>--}%
%{--                            </div>--}%

%{--                        </section>--}%



%{--                    </g:each>--}%
%{--                </div>--}%


%{--                <div id="desc_tab" class="tabcontent">--}%



%{--                    <g:each class="${(i % 2) == 0 ? 'even' : 'odd'}" var="cours" in="${userInstance.cours.sort({a,b-> a.id.compareTo(b.id)} )}" status="i" >--}%
%{--                        <g:if test = "${i==0}" >--}%
%{--                            <input id="${"radDesc"+(i+1).toString()}" type="radio" name="radDesc" checked>--}%
%{--                        </g:if>--}%


%{--                        <g:else>--}%
%{--                            <input id="${"radDesc"+(i+1).toString()}" type="radio" name="radDesc">--}%
%{--                        </g:else>--}%


%{--                        <section>--}%
%{--                            <% desc=track.InfoCours.findAllById(i+1)%>--}%


%{--                            <div class="competences">--}%
%{--                                <h2 class="indexCours">${(i+1).toString()+"/"+userInstance.cours.size()}</h2>--}%
%{--                                <h1>${desc.first().competences.capitalize()} </h1>--}%
%{--                            </div>--}%

%{--                            <div class="requis">--}%
%{--                                <h2> Pré-requis :</h2> <br>--}%
%{--                                <p>--}%
%{--                                    ${desc.first().requis.capitalize()}--}%
%{--                                </p>--}%
%{--                            </div>--}%


%{--                            <div class="duree">--}%
%{--                                <i class="far fa-clock"> ${desc.first().duree} heures</i>--}%

%{--                            </div>--}%


%{--                            <div class="seq-btn">--}%
%{--                                <g:if test = "${i==0}" >--}%
%{--                                    <label for="${"radDesc"+(userInstance.cours.size()).toString()}"><i class="fa fa-chevron-left"></i></label>--}%
%{--                                    <label for="${"radDesc"+(i+2).toString()}"><i class="fa fa-chevron-right"></i></label>--}%
%{--                                </g:if>--}%
%{--                                <g:elseif test="${i==userInstance.cours.size()-1}">--}%
%{--                                    <label for="${"radDesc"+(i).toString()}"><i class="fa fa-chevron-left"></i></label>--}%
%{--                                    <label for="${"radDesc"+(1).toString()}"><i class="fa fa-chevron-right"></i></label>--}%
%{--                                </g:elseif>--}%
%{--                                <g:else>--}%
%{--                                    <label for="${"radDesc"+(i).toString()}"><i class="fa fa-chevron-left"></i></label>--}%
%{--                                    <label for="${"radDesc"+(i+2).toString()}"><i class="fa fa-chevron-right"></i></label>--}%
%{--                                </g:else>--}%
%{--                            </div>--}%

%{--                        </section>--}%





%{--                    </g:each>--}%

%{--                </div>--}%






%{--            </div>--}%

%{--        </div>--}%


    </div>

    <div id="contact">



        <h1 class="contact_titre"> Contact Us </h1>

        <div class="contact_form">


            <g:form>
                <div class="fieldcontain">
                    <div id="nomForm" class="fieldcontain">
                        <g:textField     class="form-input" type="text" name="firstname"
                                         placeholder="Nom"/>
                    </div>

                    <div id="prenomForm" class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastname', 'error')}">
                        <g:textField class="form-input" type="text" name="lastname"
                                     placeholder="Prénoms"/>
                    </div>

                    <div id="emailForm" class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')}">
                        <g:textField class="form-input" type="email" name="email"    placeholder="Email"/>
                    </div>

                    <div id="sujetForm" class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastname', 'error')}">
                        <g:textField class="form-input" type="text" name="sujet"
                                     placeholder="Sujet"/>
                    </div>
                    <g:textArea class="form-input message" name="body" placeholder="Votre Message"></g:textArea>
                    <fieldset class="btnPostContainer">
                        <g:actionSubmit class="btnPost" value="Envoyer" controller="homeFormation" action="sendEmail"/>
                    </fieldset>
                </div>

            </g:form>

        </div>


        <div class="social">

            <a id="github_ico">
                <i class="fab fa-github"></i>
            </a>

            <a id="linked_ico">
                <i class="fab fa-linkedin"></i>
            </a>

            <a id="google_ico">
                <i class="fab fa-google-plus"></i>
            </a>

            <a id="twit_ico">
                <i class="fab fa-twitter"></i>
            </a>

            <a id="hash_ico">
                <i class="fas fa-hashtag"></i>
            </a>

            <a id="branch_ico">
                <i class="fas fa-code-branch"></i>
            </a>

        </div>



    </div>



<script>
        function openTab(evt, tabName) {
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
            document.getElementById(tabName).style.display = "block";
            evt.currentTarget.className += " active";
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





</script>

</body>
</html>