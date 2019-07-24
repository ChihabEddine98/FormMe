<%@ page defaultCodec="HTML" %>

<!DOCTYPE html>
<html>
    <head>
        <title><g:message code="grailsAdminPlugin.dashboard.title" /></title>
        <meta name="layout" content="grailsAdmin/main" />
    </head>
    <body>
        <div class="main-container container">

            <div id="steps">

%{--                <h1>  Etapes D'insertions :</h1>--}%


%{--                <asset:image src="ordre_ins.png" style=" width: 1240px; height: 680px;"> </asset:image>--}%

%{--                <div>--}%

%{--                    <div> <h3> 1-Cours </h3>  </div>--}%
%{--                    <div> <h3> 2-Module </h3>  </div>--}%
%{--                    <div> <h3> 3-Chapitre </h3>  </div>--}%
%{--                    <div>--}%
%{--                        <h3> 4-Ressource : </h3>--}%

%{--                        <div>--}%
%{--                             <h4> 4.1 -Text,Image Ou video </h4>--}%
%{--                        </div>--}%

%{--                        <div>--}%
%{--                            <h4> 4.2 -Quiz</h4>--}%
%{--                            <h4> 4.2.1 -Questions </h4>--}%
%{--                        </div>--}%

%{--                    </div>--}%



            </div>

            <table class="table table-condensed dashboard">
                <g:each var="group" in="${config.groups}">
                    <tr>
                        <td colspan="2">
                            <h1>${group}</h1>
                        </td>
                    <tr>

                    <g:each var="domainClass" in="${config.getGroup(group)}">
                        <tr>
                            <td>${domainClass.className}</td>
                            <td>
                                <div class="btn-group">
                                    <g:link class="btn btn-link" mapping="grailsAdminList" params="[slug: domainClass.slug]">
                                        <span class="glyphicon glyphicon-list"></span>
                                        <g:message code="grailsAdminPlugin.dashboard.list" />
                                    </g:link>
                                    <g:link class="btn btn-link" mapping="grailsAdminAdd" params="[slug: domainClass.slug]">
                                        <span class="glyphicon glyphicon-plus"></span>
                                        <g:message code="grailsAdminPlugin.dashboard.add" />
                                    </g:link>
                                </div>
                            </td>
                        </tr>
                    </g:each>
                </g:each>
            </table>
        </div>
    </body>
</html>
