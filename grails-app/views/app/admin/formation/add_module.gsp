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

    <div class="saisieModule">



        <g:each in="${(1..nbModules).toList()}" var="module" >
            <div class="moduleEntity">

                <g:textField  class="form-input" type="text" name="domaine"
                              placeholder="Nom du module"/>
                <label> Durée  </label>
                <g:field class="form-input" type="number" name="dureeModule" required="" value="${20}" />

            </div>

        </g:each>


    </div>

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