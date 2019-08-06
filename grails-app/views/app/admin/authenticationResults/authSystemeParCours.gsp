<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-08-05
  Time: 16:40
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

    <h1> System Authentification Par Cours</h1>
    <hr>
    <table>
        <thead>

        <tr>


%{--            <th> Cours </th>--}%
%{--            <th>Firstname </th>--}%
%{--            <th>Lastname </th>--}%
%{--            <th>Auth-BIOMETRIC</th>--}%
%{--            <th>Auth-COMPAPP</th>--}%
%{--            <th>Détails Générales</th>--}%
%{--            <th>Auth-ENVAPP</th>--}%

            <th> Cours </th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>

        </tr>
        </thead>

        <tbody>


        <g:each in="${cours}" var="coursD" status="i">
            <thead>

                <tr>
                <% def x=coursD.users.size()+1%>
                <th class="tableCaseHaut" rowspan="${x}">${coursD.nom}</th>

                <td class=" tableCaseHaut" colspan="2"> <b> Users</b>   </td>
                <td class=" tableCaseHaut"> Auth-BIOMETRIC </td>
                <td class=" tableCaseHaut"> Auth-COMPAPP </td>
                <td class=" tableCaseHaut"> Détails Générales </td>
                <td class=" tableCaseHaut"> Auth-ENVAPP </td>



            </tr>
            </thead>


            <g:each in="${coursD.users}" var="user" status="j">
                <tr>
                    <td class="tableCase"> ${user.firstname} </td>
                    <td class="tableCase"> ${user.lastname} </td>
                    <% def auth=track.UserAuthentificationFormation.findByCoursAndUser(coursD,user)%>
                    <g:if test="${auth!=null}">
                        <td class="tableCase">
                            <div>
                                <div> ${auth.trustBIOMETRIC} </div>
                                <hr>
                                <g:if test="${track.UserAuthentificationFormation.findByCours(coursD)!=null}">
                                    <g:link controller="admin" action="listAuthResults" params="[userAuthFormationID:auth.id]">
                                        <i class="fas fa-plus-circle" style="font-size: 28px;color: #0fba5a;"> Details</i>
                                    </g:link>
                                </g:if>
                            </div>

                        </td>
                        <td class="tableCase"> ${auth.trustCompApp} </td>
                    </g:if>
                    <g:else>
                        <td class="tableCase">  </td>
                        <td class="tableCase"> </td>

                    </g:else>

                    <%  auth=track.UserAuthentificationFormation.findByUserAndCours(user,coursD)%>
                    <g:if test="${auth!=null}">
                        <td class="tableCase">
                            <div>
                                <div> General </div>
                                <hr>
                                <g:if test="${track.UserAuthentificationFormation.findByCours(coursD)!=null}">
                                    <g:link controller="admin" action="detailsGenerals" params="[userAuthFormationID:auth.id]">
                                        <i class="fas fa-arrow-circle-right" style="font-size: 28px;color: #d81427;"> Acceder</i>
                                    </g:link>
                                </g:if>
                            </div>
                        </td>
                    </g:if>

                    <g:else>
                        <td class="tableCase">
                            <div>
                            </div>
                        </td>
                    </g:else>


                    <% def authUserEnvApp=user.userAuthentificationEnvApp%>
                    <g:if test="${authUserEnvApp!=null}">
                        <% def authEnvApp=track.UserAuthentificationEnvApp.get(authUserEnvApp.id)%>
                        <g:if test="${authEnvApp!=null}">
                            <td class="tableCase">
                                <div>${authEnvApp.trustEnvApp}</div>
                                <hr>

                                <g:link controller="admin" action="authenticationDetail" id="${authEnvApp.authenticationResult.id}">
                                    <i class="fas fa-arrow-circle-right" style="font-size: 28px;color: #d81427;"> Details</i>
                                </g:link>

                            </td>
                        </g:if>
                        <g:else>
                            <td class="tableCase"> </td>
                        </g:else>
                    </g:if>
                    <g:else>
                        <td class="tableCase" ></td>
                    </g:else>

                </tr>

            </g:each>


        </g:each>
        </tbody>


    </table>

    <hr>

    <div class="pagination">
        <g:paginate total="${track.Cours.list().size()}"/>

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