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
    <div id="content" class="content">
    <h1>  Dashboard</h1>
    </div>>
%{--<div class="content">--}%
%{--    <h2>Classic Authentication </h2>--}%

%{--    <div class="legende">--}%
%{--        <h5><i class="fa fa-superpowers" aria-hidden="true"> : Start authentication </i></h5>--}%
%{--        <h5><i class="fa fa-table" aria-hidden="true"> : Show stats if auth done</i></h5>--}%
%{--        <h5><i class="fa fa-plus" aria-hidden="true"> : Show more details if auth done (in development)</i></h5>--}%
%{--    </div>--}%

%{--    <div class="container">--}%
%{--        <select id='multiselect' multiple='multiple'>--}%
%{--            <% anothercount = 0 %>--}%
%{--            <g:while test="${anothercount < userInstanceList.size()}">--}%
%{--                <g:while test="${anothercount < 2}">--}%
%{--                    <optgroup label="Admins">--}%
%{--                        <option value="user${userInstanceList.get(anothercount).id}" selected>${userInstanceList.get(anothercount).firstname}</option>--}%
%{--                        <% anothercount++ %>--}%
%{--                    </optgroup>--}%
%{--                </g:while>--}%
%{--                <optgroup label="Users">--}%
%{--                    <option value="user${userInstanceList.get(anothercount).id}">${userInstanceList.get(anothercount).firstname}</option>--}%
%{--                    <% anothercount++ %>--}%
%{--                </optgroup>--}%
%{--            </g:while>--}%
%{--        </select>--}%
%{--    </div>--}%

%{--    <table class="table-dashboard">--}%
%{--    <tr>--}%
%{--        <th></th>--}%
%{--        <% test = 0 %>--}%
%{--        <% iteration = 0 %>--}%
%{--        <% count = 0 %>--}%
%{--        <g:while test="${test < userInstanceList.size()}">--}%
%{--            <th class="user${userInstanceList.get(test).id}" scope="col">${userInstanceList.get(test).firstname}</th>--}%
%{--            <% test++ %>--}%
%{--        </g:while>--}%
%{--        <g:while test="${iteration < userInstanceList.size()}">--}%
%{--            <tr class="user${userInstanceList.get(iteration).id}">--}%
%{--                <th scope="row">${userInstanceList.get(iteration).firstname}</th>--}%
%{--                <% colonne = 0 %>--}%
%{--                <g:if test="${iteration == count}">--}%
%{--                        <g:while test="${colonne < userInstanceList.size()}">--}%
%{--                                    <th class="user${userInstanceList.get(colonne).id}" id= C${User.list().get(iteration).id }|${User.list().get(colonne).id}>--}%
%{--                                        <g:link controller="admin" action="manuallyAuthenticateUserDash" params="[signatureUser: "${User.list().get(iteration).id}",profileUser:"${User.list().get(colonne).id}"]"  ><div class="empty"> <i id="buttonStats" class="fa fa-superpowers" aria-hidden="true"></i> </g:link> <i id="buttonStats" class="fa fa-table" aria-hidden="true"></i><i class="fa fa-plus" aria-hidden="true"></i></div>--}%

%{--                                    </th>--}%
%{--                            		<% colonne++ %>--}%

%{--                    </g:while>--}%
%{--                    <% count++ %>--}%
%{--                </g:if>--}%
%{--            </tr>--}%
%{--            <% iteration++ %>--}%
%{--        </g:while>--}%
%{--    </table>--}%

%{--    <h2>Dynamic authentication</h2>--}%

%{--    <table class="table-dashboard">--}%
%{--    <tr>--}%
%{--        <th></th>--}%
%{--        <% test = 0 %>--}%
%{--        <% iteration = 0 %>--}%
%{--        <% count = 0 %>--}%
%{--        <g:while test="${test < userInstanceList.size()}">--}%
%{--            <th class="user${userInstanceList.get(test).id}" scope="col">${userInstanceList.get(test).firstname}</th>--}%
%{--            <% test++ %>--}%
%{--        </g:while>--}%
%{--        <g:while test="${iteration < userInstanceList.size()}">--}%
%{--            <tr class="user${userInstanceList.get(iteration).id}">--}%
%{--                <th scope="row">${userInstanceList.get(iteration).firstname}</th>--}%
%{--                <% colonne = 0 %>--}%
%{--                <g:if test="${iteration == count}">--}%
%{--                    <g:while test="${colonne < userInstanceList.size()}">--}%
%{--                        <th class="user${userInstanceList.get(colonne).id}" id= D${User.list().get(iteration).id }${User.list().get(colonne).id}>--}%
%{--                            <g:link controller="admin" action="manuallyAuthenticateUserDash" params="[signatureUser: "${User.list().get(iteration).id}",profileUser:"${User.list().get(colonne).id}"]"  ><div class="empty"> <i id="buttonStats" class="fa fa-superpowers" aria-hidden="true"></i> </g:link> <i id="buttonStats" class="fa fa-table" aria-hidden="true"></i><i class="fa fa-plus" aria-hidden="true"></i></div>--}%

%{--                        </th>--}%
%{--                        <% colonne++ %>--}%

%{--                    </g:while>--}%
%{--                    <% count++ %>--}%
%{--                </g:if>--}%
%{--            </tr>--}%
%{--            <% iteration++ %>--}%
%{--        </g:while>--}%
%{--    </table>--}%

%{--</div>--}%

%{--    <g:each in="${authResult}" var="userResult" status="i">--}%
%{--                 <g:javascript>--}%
%{--                 --}%
%{--                 if ("${userResult.type}" == "CLASSIC" )--}%
%{--                 {--}%
%{--                 console.log ("${userResult.type}")--}%
%{--                  el = document.getElementById("C${userResult.userSignature.id}|${userResult.userProfile.id}");--}%
%{--	              ania = "${userResult.ania}"--}%
%{--	              angia = "${userResult.anga}"--}%
%{--	              size = "${userResult.sizeObselTrust()}" --}%
%{--	              lastTrust = "${userResult.lastTrust ()}"--}%
%{--	              ratio =  "${userResult.ratio()}"--}%
%{--	                if (${userResult.userSignature.id} == ${userResult.userProfile.id} && lastTrust > 0){--}%
%{--	                 --}%
%{--                    --}%
%{--                        {document.getElementById("C${userResult.userSignature.id}|${userResult.userProfile.id}").style.backgroundColor = "#0e762b";}--}%
%{--                      --}%
%{--	              }--}%
%{--	                else if (${userResult.userSignature.id} == ${userResult.userProfile.id} && lastTrust < 0) {--}%
%{--                      --}%
%{--                        {document.getElementById("C${userResult.userSignature.id}|${userResult.userProfile.id}").style.backgroundColor = "#dd0918";}--}%
%{--                      --}%
%{--	              }--}%
%{--	                if (${userResult.userSignature.id} != ${userResult.userProfile.id} && lastTrust < 0) {--}%
%{--                     --}%
%{--                        document.getElementById("C${userResult.userSignature.id}|${userResult.userProfile.id}").style.backgroundColor = "#0e762b";--}%
%{--                       --}%
%{--	              }--}%
%{--	                else if (${userResult.userSignature.id} != ${userResult.userProfile.id} && lastTrust > 0) {--}%
%{--                        --}%
%{--                        document.getElementById("C${userResult.userSignature.id}|${userResult.userProfile.id}").style.backgroundColor = "#dd0918";--}%
%{--                       --}%
%{--	              }--}%
%{--	              el.innerHTML = '<g:link controller="admin" action="manuallyAuthenticateUserDash" params="[signatureUser: "${userResult.userSignature.id}",profileUser:"${userResult.userProfile.id}"]"><div class="notempty"> <i id="buttonStats" class="fa fa-superpowers" aria-hidden="true"></i></g:link> '+--}%
%{--	              '<g:link controller="admin" action="calculateAuthenticationStatisticsDash" id="${userResult.id}"> <i id="buttonStats" class="fa fa-table" aria-hidden="true"></i></g:link>'+ '<g:link controller="admin" action ="authenticationDetail" id="${userResult.id}"><i class="fa fa-plus" aria-hidden="true"></i></g:link></div>' + "<div class='resultsAuth'> NB Obsel = "+size +"<br> lastTrust= " +lastTrust + "<br> ratio ="+ ratio +'</div>'--}%
%{--	             } --}%
%{--	             --}%
%{--	             else if ("${userResult.type}" == "DYNAMIC"){--}%
%{--	             el = document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}");--}%
%{--	             ania = "${userResult.ania}"--}%
%{--	             angia = "${userResult.anga}"--}%
%{--	              size = "${userResult.sizeObselTrust()}" --}%
%{--	              lastTrust = "${userResult.lastTrust ()}"--}%
%{--                    	                if (${userResult.userSignature.id} == ${userResult.userProfile.id} && lastTrust > 0) {--}%
%{--                      if (lastTrust > 0 && lastTrust <= 20)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#ebe702";--}%
%{--	                  else if (lastTrust > 20 && lastTrust <= 40)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#99c731";--}%
%{--	                  else if (lastTrust > 40 && lastTrust <= 60)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#5bab0e";--}%
%{--	                  else if (lastTrust > 60 && lastTrust <= 80)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#1a9427";--}%
%{--	                  else if (lastTrust > 80)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#0e762b";--}%
%{--	              }--}%
%{--	                else if (${userResult.userSignature.id} == ${userResult.userProfile.id} && lastTrust < 0) {--}%
%{--                      if (lastTrust < 0 && lastTrust >= -20)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#ffd900";--}%
%{--	                  else if (lastTrust < -20 && lastTrust >= -40)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#ffac04";--}%
%{--	                  else if (lastTrust < -40 && lastTrust >= -60)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#ff7c0a";--}%
%{--	                  else if (lastTrust < -60 && lastTrust >= -80)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#f44f0f";--}%
%{--	                  else if (lastTrust < -80)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#dd0918";--}%
%{--	              }--}%
%{--	                if (${userResult.userSignature.id} != ${userResult.userProfile.id} && lastTrust < 0) {--}%
%{--                      if (lastTrust < 0 && lastTrust >= -20)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#ebe702";--}%
%{--	                  else if (lastTrust < -20 && lastTrust >= -40)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#99c731";--}%
%{--	                  else if (lastTrust < -40 && lastTrust >= -60)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#5bab0e";--}%
%{--	                  else if (lastTrust < -60 && lastTrust >= -80)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#1a9427";--}%
%{--	                  else if (lastTrust < -80)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#0e762b";--}%
%{--	              }--}%
%{--	                else if (${userResult.userSignature.id} != ${userResult.userProfile.id} && lastTrust > 0) {--}%
%{--              if (lastTrust > 0 && lastTrust <= 20)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#ffd900";--}%
%{--	                  else if (lastTrust > 20 && lastTrust <= 40)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#ffac04";--}%
%{--	                  else if (lastTrust > 40 && lastTrust <= 60)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#ff7c0a";--}%
%{--	                  else if (lastTrust > 60 && lastTrust <= 80)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#f44f0f";--}%
%{--	                  else if (lastTrust > 80)--}%
%{--                        document.getElementById("D${userResult.userSignature.id}${userResult.userProfile.id}").style.backgroundColor = "#dd0918";--}%
%{--	              }--}%
%{--	            el.innerHTML = '<g:link controller="admin" action="manuallyAuthenticateUserDash" params="[signatureUser: "${userResult.userSignature.id}",profileUser:"${userResult.userProfile.id}"]"><div class="notempty"> <i id="buttonStats" class="fa fa-superpowers" aria-hidden="true"></i></g:link> '+--}%
%{--	              '<g:link controller="admin" action="calculateAuthenticationStatisticsDash" id="${userResult.id}"> <i id="buttonStats" class="fa fa-table" aria-hidden="true"></i></g:link> '+ '<g:link controller="admin"><i class="fa fa-plus" aria-hidden="true"></i></g:link></div>' + "<div class='resultsAuth'><br> ANIA = " +ania + "<br> First action =" +angia +"<br> NB Obsel = "+size +"<br> lastTrust= " +lastTrust +'</div>'--}%
%{--	             }--}%

%{--                 </g:javascript>--}%

%{--    </g:each>--}%

<script>
    var classActive = function () {
        $('.dashLi').addClass('activeLi');
        $('.dashA').addClass('activeA');
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
