<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-25
  Time: 17:48
--%>

<%@ page import="track.Quiz" contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">
    <title> Questions du Quiz ${track.Quiz.get((long)Integer.parseInt(qID)).nom} </title>
</head>

<body>

<div class="content">

    <g:if test="${!questions.isEmpty()}">
        <h1> Questions du Quiz ${questions.first().quizR.nom} </h1>
        <hr>

        <div class="champ">
            <h4>
                Ajouter une nouvelle Question ?
            </h4>
        </div>
        <div class="champ col-md-3 col-sm-4">
            <g:link class="button btn-primary" controller="admin" action="addQuestionUI" params="[quizId:qID]">
                <i class="fas fa-plus" > <span style="font-family: Futura;">Ajouter</span> </i>
            </g:link>
        </div>

        <hr>

        <div class="models">

            <table>
                <thead>
                <tr>
                    <td> Question </td>
                    <td> option 1 </td>
                    <td> option 2 </td>
                    <td> option 3 </td>
                    <td> option 4 </td>
                    <td></td>
                </tr>
                </thead>
                <tbody>

        <g:each in="${questions}" var="question">
            <tr>
                <td> ${question.question} </td>
                <td> ${question.sugg1} </td>
                <td> ${question.sugg2} </td>
                <td> ${question.sugg3} </td>
                <td> ${question.sugg4} </td>

                <td>
                    <g:link class="btn btn-warning" controller="admin" action="editQuestionUI" params="[question:question.id]">
                        <i class="fas fa-cogs" style="font-size: 27px; "></i>
                    </g:link>
                </td>


            </tr>
        </g:each>





        </tbody>
    </table>

        <hr>


        <div class="pagination">
            <g:paginate total="${countQ?:0}" params="[quiz:qID]"/>
        </div>

        <hr>
        </div>


    </g:if>







    <g:else>

        <div class="champ">
            <h1> Oops !!</h1>
        </div>
        <hr>

        <br>
        <div class="champ">
            <h2> Le Quiz n'a aucune question enregistée </h2>
            <hr>
        </div>
        <br>


        <hr>
        <div class="champ">
            <h4>
                Ajouter une nouvelle Question ?
            </h4>
        </div>
        <div class="champ col-md-4 col-sm-4">
            <g:link class="button btn-primary" controller="admin" action="addQuestionUI" params="[quizId:qID]">
                <i class="fas fa-plus" > <span style="font-family: Futura;">Ajouter</span> </i>
            </g:link>
        </div>



    </g:else>



    <hr>

    <div class="champ col-md-3 col-sm-4">
        <g:link class="button btn-danger" controller="admin" action="listRessource" params="[type:'quiz']">
            <i class="fas fa-backward" > <span style="font-family: Futura;">Quizes</span> </i>
        </g:link>
    </div>

    <hr>

</div>
</body>
</html>