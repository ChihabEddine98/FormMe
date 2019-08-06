<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-08-05
  Time: 11:00
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin">
    <title>Résultats d'authentifications</title>
</head>

<body>
<div class="contentObsel">


    <h1> System Authentification Par Utilisateur</h1>
    <hr>
    <table>
        <thead>

        <tr>


            <th>Firstname </th>
            <th>Lastname </th>
            <th> </th>
            <th>Auth-BIOMETRIC</th>
            <th>Auth-COMPAPP</th>
            <th>Détails Générales</th>
            <th>Auth-ENVAPP</th>

        </tr>
        </thead>

        <tbody>


        <g:each in="${users}" var="user" status="i">
            <tr>
                <% def x=user.cours.size()+1%>
                <th class="tableCaseHaut" rowspan="${x}">${user.firstname}</th>
                <th class="tableCaseHaut" rowspan="${x}">${user.lastname}</th>

                <td class=" tableCaseHaut" > <b> Cours</b>   </td>
                <td class=" tableCaseHaut">  </td>
                <td class=" tableCaseHaut">  </td>
                <td class=" tableCaseHaut">  </td>

                <% def authUserEnvApp=user.userAuthentificationEnvApp%>
                <g:if test="${authUserEnvApp!=null}">
                    <% def authEnvApp=track.UserAuthentificationEnvApp.get(authUserEnvApp.id)%>
                    <g:if test="${authEnvApp!=null}">
                        <td class="tableCase" rowspan="${x}">
                        <div>${authEnvApp.trustEnvApp}</div>
                        <hr>

                        <g:link controller="admin" action="authenticationDetail" id="${authEnvApp.authenticationResult.id}">
                            <i class="fas fa-arrow-circle-right" style="font-size: 28px;color: #0fba5a;"> Details</i>
                        </g:link>

                        </td>
                    </g:if>

                </g:if>
                <g:else>
                    <td class="tableCase" rowspan="${x}"> ENVAPP NULL </td>
                </g:else>



            </tr>

            <g:each in="${user.cours}" var="cours" status="j">
                <tr>
                     <td class="tableCase"> ${cours.nom} </td>
                    <% def auth=track.UserAuthentificationFormation.findByUserAndCours(user,cours)%>
                    <g:if test="${auth!=null}">
                        <td class="tableCase">
                            <div>
                                <div> ${auth.trustBIOMETRIC} </div>
                                <hr>
                                <g:if test="${track.UserAuthentificationFormation.findByCours(cours)!=null}">
                                    <g:link controller="admin" action="listAuthResults" params="[userAuthFormationID:auth.id]">
                                      <i class="fas fa-plus-circle" style="font-size: 28px;color: #0fba5a;"> Details</i>
                                    </g:link>
                                </g:if>
                            </div>

                        </td>
                        <td class="tableCase"> ${auth.trustCompApp} </td>
                    </g:if>
                    <g:else>
                        <td class="tableCase"> BIO NULL </td>
                        <td class="tableCase"> COMPAPP  NULL</td>

                    </g:else>
                     <td class="tableCase">
                    <%  auth=track.UserAuthentificationFormation.findByUserAndCours(user,cours)%>
                    <g:if test="${auth!=null}">
                        <div>
                            <div> General </div>
                            <hr>
                            <g:if test="${track.UserAuthentificationFormation.findByCours(cours)!=null}">
                                <g:link controller="admin" action="detailsGenerals" params="[userAuthFormationID:auth.id]">
                                    <i class="fas fa-arrow-circle-right" style="font-size: 28px;color: #ba1c3a;"> Acceder</i>
                                </g:link>
                            </g:if>
                        </div>
                    </g:if>
                     </td>


                </tr>

            </g:each>


        </g:each>
        </tbody>


    </table>

    <hr>

    <div class="pagination">
        <g:paginate total="${track.User.list().size()}"/>

    </div>

    <hr>
</div>
<script>
    var classActive = function () {
        $('.resultsLi').addClass('activeLi');
        $('.resultsA').addClass('activeA');
    };
    $( document ).ready(function() {
        classActive();
        $("#pageName").text("Authentification results");
    });

    $('#button').click(function() {
        $('.graph').toggle();
    });

    var array = ${listTrusts}

        array.reverse();


    GRAPH = document.getElementById('graph');

    // deprecated: calling plot again will add new trace(s) to the plot,
    // but will ignore new layout.
    var data2 = [{
        y: array,
        type: 'scatter'
    }];

    var layout2 = {
        title: 'Obseltrust.trust'
    };

    Plotly.newPlot(GRAPH, data2, layout2, {showLink: false});


</script>
</body>
</html>