<%@ page import="track.User" %>
<%@ page import="track.AuthenticationResult" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin">
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title>Administration</title>
    <asset:stylesheet src="multi-select.css"/>
    <asset:javascript src="jquery.multi-select.js"/>

</head>

<body>



<div class="content">
    <h1> Admin Home  </h1>
    <h2> Welcome ${msg}  !</h2>

</div>
<script>
    var classActive = function () {
        $('.homeLi').addClass('activeLi');
        $('.homeA').addClass('activeA');
    };
    $(document).ready(function () {
        classActive();
        $("#pageName").text("Dashboard");
    });

    $('#multiselect').multiSelect({
        selectableHeader: "<div class='select-header'>Show</div>",
        selectionHeader: "<div class='select-header'>Hide</div>",
        afterSelect: function(values){
            var strUser = values;
            var getClass = "." + strUser;
            $( getClass ).toggle();
        },
        afterDeselect: function(values){
            var strUser = values;
            var getClass = "." + strUser;
            $( getClass ).toggle();
        }
    });

    $('.user1').hide();
    $('.user2').hide();


</script>
</body>
</html>
