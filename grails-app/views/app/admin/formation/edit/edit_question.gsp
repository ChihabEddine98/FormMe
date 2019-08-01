<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-07-26
  Time: 11:37
--%>

<%@ page import="track.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">

    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title>Parametres de la formation</title>
</head>

<body>
<div class="content">
    <g:form controller="admin">



        <h1>
            Modifier une Question De Quiz :
        </h1>


    %{--        <h2> Informations générales de la formation :</h2>--}%


        <div class="champ">
            <label> Question *  </label>
            <g:textField  class="form-input" type="text" name="nomModule" value="${question.question}"/>
        </div>

        <div class="champ">
            <label> Option1 *  </label>
            <g:textField  class="form-input" type="text" name="nomModule" value="${question.sugg1}"/>
        </div>

        <div class="champ">
            <label> Option2 *  </label>
            <g:textField  class="form-input" type="text" name="nomModule" value="${question.sugg2}"/>
        </div>

        <div class="champ">
            <label> Option3 *  </label>
            <g:textField  class="form-input" type="text" name="nomModule" value="${question.sugg3}"/>
        </div>

        <div class="champ">
            <label> Option4 *  </label>
            <g:textField  class="form-input" type="text" name="nomModule" value="${question.sugg4}"/>
        </div>

%{--        <% def answer=""%>--}%
%{--        <g:if test="${question.answer.equals(question.sugg1)}">--}%
%{--          ${answer="1"}--}%
%{--        </g:if>--}%
%{--        <g:if test="${question.answer.equals(question.sugg2)}">--}%
%{--            ${answer="2"}--}%
%{--        </g:if>--}%
%{--        <g:if test="${question.answer.equals(question.sugg3)}">--}%
%{--            ${answer="3"}--}%
%{--        </g:if>--}%
%{--        <g:if test="${question.answer.equals(question.sugg4)}">--}%
%{--            ${answer="4"}--}%
%{--        </g:if>--}%


        <div class="champ">
            <h3> Réponse *  </h3>
            <g:select class="form-input" name="cours" from="['1','2','3','4']" value="${answer}"
            />

        </div>

        <div class="champ">
            <h3> Quiz *  </h3>
            <g:select class="form-input" name="cours" from="${track.Quiz.findAll {}}" value="${question.quizR}"
            />

        </div>



        <div class="champ col-md-5 col-sm-4">
            <g:link class="button btn-danger" controller="admin" action="listRessource" params="[type:'quiz']">
                <i class="fas fa-backward" > <span style="font-family: Futura;">Quizes</span> </i>
            </g:link>
        </div>

        <div class="champ col-md-5 col-sm-4">
            <g:actionSubmit value="Sauvegarder" type="button" class="button btn-primary"
                            controller= "admin" action="addCours" />
        </div>



    </g:form>













%{--        <g:actionSubmit value="Manually Authenticate Classic Users" type="button" class="button"--}%
%{--                        action="manuallyAuthenticateUser">Manually Authenticate Classic Users</g:actionSubmit>--}%
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>

    var classActive = function () {
        $('.formationLi').addClass('activeLi');
        $('.formationA').addClass('activeA');
    };
    $(document).ready(function () {
        classActive();
        $("#pageName").text("parametres de la formation");
    });


    function confirmDialog() {

        return  $.confirm({
            title: 'Confirm!',
            content: 'Simple confirm!',
            buttons: {
                confirm: function () {
                    $.alert('Confirmed!');
                },
                cancel: function () {
                    $.alert('Canceled!');
                },
                somethingElse: {
                    text: 'Something else',
                    btnClass: 'btn-blue',
                    keys: ['enter', 'shift'],
                    action: function(){
                        $.alert('Something else?');
                    }
                }
            }
        });
    }

</script>
</body>
</html>