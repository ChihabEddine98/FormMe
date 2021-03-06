<!DOCTYPE HTML>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
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

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <g:layoutHead/>
</head>

<body>
    <section class="main-nav">
        <ul>

            <li class="menu-nav homeLi"><g:link controller="admin"  class="homeA"><i
                    class="fa fa-home fa-layout"
                    aria-hidden="true"></i>Home</g:link>
            </li>
%{--            <li class="menu-nav"><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home fa-layout"--}%
%{--                                                                                   aria-hidden="true"></i><g:message--}%
%{--                    code="default.home.label"/></a></li>--}%

            <li class="menu-nav usersLi"><g:link controller="admin" class="usersA" action="gestionUsers"><i class="fas fa-users-cog fa-layout"
                                                                                                    aria-hidden="true"></i><g:message
                    args="[entityName]"/> Utilisateurs</g:link></li>

            <li class="menu-nav formationLi"><g:link controller="admin" class="formationA"  action="paramFormation"><i
                    class="fas fa-graduation-cap fa-layout"
                    aria-hidden="true"></i>Formation</g:link>
            </li>

            <li class="menu-nav dashLi"><g:link controller="admin" action="dashBoard" class="dashA"><i
                    class="fas fa-chart-line fa-layout"
                    aria-hidden="true"></i>Dashboard</g:link>
            </li>
            <li class="menu-nav resultsAMLi">
                <div class="dropdown">
                    <a href="" class="dropbtn" > <g:link controller="admin" action="userList" params="[type:'BIOMETRIC',typeAuth:'Manuel']"
                                                         class="resultsAM" ><i class="fa fa-list fa-layout dropdown"
                                                                                                                 aria-hidden="true"></i>Auth results Manuel</g:link></a>

                    <div class="dropdown-content">
                        <g:link controller="admin"  action="userList" params="[type:'BIOMETRIC',typeAuth:'Manuel']">Biometric</g:link>
                        <g:link controller="admin"  action="userList" params="[type:'ENVAPP',typeAuth:'Manuel']"> Env Session App </g:link>
                        <g:link controller="admin"  action="userList" params="[type:'COMPAPP',typeAuth:'Manuel']">CompApp</g:link>
                        <a href="#">ActionApp</a>
                    </div>
                </div>
            </li>

            <li class="menu-nav resultsASLi">
                <div class="dropdown">
                    <a href="" class="dropbtn" > <g:link controller="admin" action="userList" params="[type:'BIOMETRIC',typeAuth:'System']"
                                                         class="resultsAS" ><i class="fas fa-headset fa-layout dropdown"
                                                                              aria-hidden="true"></i>Auth results System</g:link></a>

                    <div class="dropdown-content">
                        <g:link controller="admin"  action="userList" params="[type:'BIOMETRIC',typeAuth:'System']">Biometric</g:link>
                        <g:link controller="admin"  action="userList" params="[type:'ENVAPP',typeAuth:'System']"> Env Session App </g:link>
                        <g:link controller="admin"  action="userList" params="[type:'COMPAPP',typeAuth:'System']">CompApp</g:link>
                        <a href="#">ActionApp</a>
                        <p>--------------</p>
                        <g:link controller="admin"  action="authSystemParUser">Par Utilisateur</g:link>
                        <g:link controller="admin"  action="authSystemParCours" >Par Cours</g:link>
                    </div>
                </div>
            </li>
%{--            --}%
            <li class="menu-nav manualLi"><g:link controller="admin" class="manualA" action="manualAuthenticationIndex"><i
                    class="fas fa-sign-in-alt fa-layout"
                    aria-hidden="true"></i>Manual Auth</g:link>
            </li>
            <li class="menu-nav"><a class="home" href="${createLink(uri: '/')}"><i class="fas fa-graduation-cap fa-layout"
                                                                                   aria-hidden="true"></i><g:message
                    code="default.testFormation.label"/></a></li>

            <li class="menu-nav"><g:link controller="logout"><i class="fas fa-power-off fa-layout"
                                                                aria-hidden="true"></i>Log out</g:link></li>
        </ul>
    </section>
    <section class="top-nav">
        <h1 id="pageName"></h1>
        <div class="user">Logged in as <sec:loggedInUserInfo field="username"/></div>
    </section>
<script>
     $( document ).ready(function() {
         $("#pageName").text("Backoffice Trac");
     });
</script>
<g:layoutBody/>
</body>
</html>
