<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-25
  Time: 17:48
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">
    <title> Questions du Quiz ${questions.first().quizR.nom} </title>
</head>

<body>

<div class="content">


    <h1> Questions du Quiz ${questions.first().quizR.nom} </h1>
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
                        <g:link controller="admin" action="editQuestionUI" params="[question:question.id]">
                            <i class="far fa-edit"></i>
                        </g:link>
                    </td>
                </tr>
            </g:each>





            </tbody>
        </table>



    </div>

</div>
</body>
</html>