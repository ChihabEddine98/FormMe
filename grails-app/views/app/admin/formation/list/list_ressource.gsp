<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-25
  Time: 17:09
--%>

<%@ page import="track.TextRessource" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">
    <title> ${type} Ressources  </title>
</head>

<body>

<div class="contentObsel">


    <h1 style="font-family: Futura;font-size: 55px"> ${type.capitalize()} Ressources </h1>
    <hr>

    <div class="models">


        <hr>

        <div class="champ">
            <h4>
                Ajouter une nouvelle Ressource ?
            </h4>
        </div>
        <div class="champ col-md-2 col-sm-4">
            <g:link class="button btn-primary" controller="admin" action="addRessourceUI" params="[type:type]">
                <i class="fas fa-plus" > <span style="font-family: Futura;">Ajouter</span> </i>
            </g:link>
        </div>

        <hr>

        <g:if test="${type.equals("media")}">
            <hr>

            <div class="">
                <h4 style="font-family: Futura; font-size: 32px">
                    Filtrer les résultats :
                </h4>
            </div>
            <div class="champ col-md-1 col-sm-4">
                <g:link class="button btn-success" controller="admin" action="listRessource" params="[type:'media']">
                    <i class="fas fa-icons" > <span style="font-family: Futura;">Tout formats</span> </i>
                </g:link>
            </div>
            <div class="champ col-md-1 col-sm-4">
                <g:link class="button btn-primary" controller="admin" action="listRessource" params="[type:'media',typeFiltre:'audio']">
                    <i class="fas fa-file-audio" > <span style="font-family: Futura;">Audio</span> </i>
                </g:link>
            </div>
            <div class="champ col-md-1 col-sm-4">
                <g:link class="button btn-warning" controller="admin" action="listRessource" params="[type:'media',typeFiltre:'img']">
                    <i class="far fa-images" > <span style="font-family: Futura;">Images</span> </i>
                </g:link>
            </div>
            <div class="champ col-md-1 col-sm-4">
                <g:link class="button btn-danger" controller="admin" action="listRessource" params="[type:'media',typeFiltre:'video']">
                    <i class="fab fa-youtube" > <span style="font-family: Futura;">Videos</span> </i>
                </g:link>
            </div>

            <hr>
        </g:if>

        <table>
            <thead>
            <tr>
                <td> Nom </td>
                <td> Path </td>
                <g:if test="${type.equals("txt")}">
                    <td> Contenu </td>
                </g:if>
                <g:elseif test="${type.equals("media")}">
                    <td> Url </td>
                    <td> Aperçu </td>
                    <td> Format </td>
                </g:elseif>
                <g:elseif test="${type.equals("quiz")}">
                    <td> Nom Quiz </td>
                    <td></td>
                    <td></td>
                </g:elseif>

                <td></td>
            </tr>
            </thead>
            <tbody>

            <g:each in="${ressources}" var="ress">
                <tr>
                    <td> ${ress.nom} </td>
                    <td> ${ress.path} </td>
                    <g:if test="${type.equals("txt")}">
                        <td> ${((track.TextRessource)ress).contenu}  </td>
                    </g:if>
                    <g:elseif test="${type.equals("media")}">
                        <td>
                            ${((track.AudioImgVideo)ress).url}
                        </td>
                        <td>
                            <% track.AudioImgVideo res=(track.AudioImgVideo)ress%>
                            <g:if test="${res.url!=null}">
                                <g:if test="${res.format.equals("img")}">
                                    <g:if test="${res.url.isEmpty() || res.url.equals(" ")}">
                                        <asset:image class="mediaImgList" src="defaultCoursImg.jpg" alt="photo du cours"/>
                                    </g:if>
                                    <g:else>
                                        <img class="mediaImgList" src="${res.url}" alt="photo de profil"/>
                                    </g:else>
                                </g:if>
                                <g:if test="${res.format.equals("video")}">
                                    <g:if test="${res.url.isEmpty() || res.url.equals(" ")}">
                                        <asset:image class="mediaImgList" src="defaultCoursImg.jpg" alt="photo du cours"/>
                                    </g:if>
                                    <g:else>
                                        <video class="mediaImgList"  controls>
                                            <source src="${res.url}" frameborder="0" type="video/mp4">
                                            Your browser does not support the video tag.
                                        </video>
                                    </g:else>
                                </g:if>
                                <g:if test="${res.format.equals("audio")}">
                                    <g:if test="${res.url.isEmpty() || res.url.equals(" ")}">
                                        <asset:image class="mediaImgList" src="defaultCoursImg.jpg" alt="photo du cours"/>
                                    </g:if>
                                    <g:else>
                                        <audio style="margin: 10px" controls>
                                            <source src="${res.url}" type="audio/ogg">
                                            Your browser does not support the audio element.
                                        </audio>
                                    </g:else>
                                </g:if>

                            </g:if>
                            <g:else>
                                <asset:image class="coursImgList" src="defaultCoursImg.jpg" alt="photo du cours"/>
                            </g:else>
                        </td>
                        <td> ${((track.AudioImgVideo)ress).format}  </td>
                    </g:elseif>
                    <g:elseif test="${type.equals("quiz")}">
                        <td> ${((track.Quiz)ress).nom} </td>
                        <td>
                            <g:link class="btn btn-primary" controller="admin" action="listQuestions" params="[quiz:ress.id]">
                                <i class="fas fa-question"style="font-size: 24px;"></i>
                            </g:link>
                        </td>
                        <td>
                            <g:link class="btn btn-success" controller="admin" action="addQuestionUI" params="[quizId:ress.id]">
                                <i class="fas fa-plus"style="font-size: 24px; "></i>
                            </g:link>
                        </td>
                    </g:elseif>

                    <td>
                        <g:link class="btn btn-warning" controller="admin" action="editRessourceUI" params="[ressource:ress.id,type:type]">
                            <i class="fas fa-cogs" style="font-size: 24px; "></i>
                        </g:link>
                    </td>

                </tr>
            </g:each>





            </tbody>
        </table>
    </div>


    <hr>

    <div class="pagination">
%{--        <g:paginate total="${track.Ressource.countByType(type)}" params="[type:type]"/>--}%
        <g:if test="${type.equals("txt")}">
            <g:paginate total="${track.TextRessource.list().size()}" params="[type:type]"/>
        </g:if>
        <g:elseif test="${type.equals("media")}">
            <g:if test="${typeFiltre!=null}">
                <g:paginate total="${track.AudioImgVideo.countByType(typeFiltre)}" params="[type:type,typeFiltre: typeFiltre]"/>
            </g:if>
            <g:else>
                <g:paginate total="${track.AudioImgVideo.count}" params="[type:type]"/>
            </g:else>
        </g:elseif>
        <g:else>
            <g:paginate total="${track.Quiz.list().size()}" params="[type:type]"/>
        </g:else>
    </div>

    <hr>

    <div class="champ col-md-3 col-sm-4">
        <g:link class="button btn-danger" controller="admin" action="paramFormation" params="[type:'quiz']">
            <i class="fas fa-backward" > <span style="font-family: Futura;">Home</span> </i>
        </g:link>
    </div>

    <hr>

</div>
</body>
</html>