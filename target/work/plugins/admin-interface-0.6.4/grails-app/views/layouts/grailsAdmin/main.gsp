<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title><g:layoutTitle default="Grails"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
        <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
        <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
        <asset:stylesheet src="admin.css"/>
        <script src="https://kit.fontawesome.com/f805a2963c.js"></script>
        %{--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">--}%
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <title><g:layoutTitle/></title>

        <gap:layoutCss formType="${formType}" className="${className}"/>
    </head>
    <body>



        <div>

            <section class="top-nav">
                <h1 id="pageName"></h1>
                <div class="user">Logged in as <sec:loggedInUserInfo field="username"/></div>
            </section>
            <g:include action="menu" params="[slug: params.slug]"/>
            <g:layoutBody/>

            <g:if test="${flash.success}">
                <div id="msg" class="container" style="width: 300px;">
                    <div class="alert alert-success">
                        ${flash.success}
                    </div>
                </div>
            </g:if>

            <div id="msg-error" class="container" style="display:none;">
                <div class="alert alert-danger">
                    <p>ERROR</p>
                    <hr>
                    <div class="alert-text"></div>
                </div>
            </div>






        </div>





        <gap:layoutHandlebers formType="${formType}" className="${className}"/>
        <gap:layoutJs formType="${formType}" className="${className}"/>
        <script type="text/javascript">
            app.init();
        </script>
    </body>
</html>
