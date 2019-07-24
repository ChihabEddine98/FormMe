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
        <li class="menu-nav resultsLi"><g:link controller="admin" class="resultsA" action="userList"><i class="fa fa-list fa-layout"
                                                                                                        aria-hidden="true"></i>Auth results</g:link>
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

