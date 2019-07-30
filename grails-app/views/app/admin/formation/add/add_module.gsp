<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-06-24
  Time: 17:49
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">

    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title>Parametres de la formation</title>
</head>

<body>

<div class="content">


    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${moduleInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${moduleInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>


    <g:form>
        <div class="saisieModule">




            <div class="moduleEntity">

                <div class="champ">
                    <g:textField  class="form-input" type="text" name="nom"
                                  placeholder="Nom du module*"/>
                </div>
                <div class="champ">
                    <h3> Durée*  </h3>
                    <g:field class="form-input" type="number" name="duree" required="" value="${20}" />

                </div>

                <div class="champ">
                    <h3> Cours*  </h3>
                    <g:select class="form-input" name="coursNom" from="${track.Cours.findAll {}}"
                              />

                </div>

            </div>


            <div class="champ col-md-4 col-sm-4">
                <g:actionSubmit value="Sauvegarder" type="button" class="button btn-success"
                                controller= "admin" action="addModule"  />
            </div>






        </div>

    </g:form>
</div>



<script>

    var classActive = function () {
        $('.formationLi').addClass('activeLi');
        $('.formationA').addClass('activeA');
    };
    $(document).ready(function () {
        classActive();
        $("#pageName").text("parametres de la formation");
    });
</script>
</body>
</html>