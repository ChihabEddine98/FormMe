
<section class="main-nav">
    <ul>


        <li class="menu-nav"><g:link controller="admin" class="create" action="gestionUsers"><i class="fa fa-user-plus fa-layout"
                                                                                                aria-hidden="true"></i><g:message
                code="default.new.label" args="[entityName]"/></g:link></li>

        <li class="menu-nav formationLi"><g:link controller="admin" class="formationA"  action="paramFormation"><i
                class="fas fa-graduation-cap fa-layout"
                aria-hidden="true"></i>Formation</g:link>
        </li>

        <li class="menu-nav dashLi"><g:link controller="admin" class="dashA"><i
                class="fas fa-chart-line fa-layout"
                aria-hidden="true"></i>Dashboard</g:link>
        </li>
        <li class="menu-nav resultsLi">
            <div class="dropdown">
                <a href="" class="dropbtn" > <g:link controller="admin" action="userList" params="[type:'biometric']"
                                                     class="resultsA" ><i class="fa fa-list fa-layout dropdown"
                                                                          aria-hidden="true"></i>Auth results Manuel</g:link></a>

                <div class="dropdown-content">
                    <g:link controller="admin"  action="userList" params="[type:'biometric']">Biometric</g:link>
                    <g:link controller="admin"  action="userList" params="[type:'envApp']"> Env Session App </g:link>
                    <g:link controller="admin"  action="userList" params="[type:'compApp']">CompApp</g:link>
                    <a href="#">ActionApp</a>
                </div>
            </div>
        </li>
        <li class="menu-nav manualLi"><g:link controller="admin" class="manualA" action="manualAuthenticationIndex"><i
                class="fas fa-sign-in-alt fa-layout"
                aria-hidden="true"></i>Manual Auth</g:link>
        </li>
        <li class="menu-nav"><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home fa-layout"
                                                                               aria-hidden="true"></i><g:message
                code="default.home.label"/></a></li>

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

