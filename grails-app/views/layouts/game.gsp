<%@ page import="track.UserAuthentification" %>
<!DOCTYPE HTML>
<head>
    <asset:stylesheet src="styles.css"/>
    <asset:javascript src="TraceMe_CS/collector.js"/>
    <asset:javascript src="dhtml.js"/>
    <asset:javascript src="jquery-3.1.1.min.js"/>
    <asset:javascript src="wz_dragdrop.js"/>
    <script type="text/javascript">
        <g:remoteFunction controller="Authentication" action="setCookies" />
        var tab = new Array();
        tab[0] = 1;
        tab[1] = 2;
        tab[2] = 3;
        tab[3] = 4;
        tab[4] = 5;
        tab[5] = 6;
        var tab2 = new Array();
        tab2[0] = "chaussette2";
        tab2[1] = "chalet2";
        tab2[2] = "chateau2";
        tab2[3] = "chien2";
        tab2[4] = "chasseur2";
        tab2[5] = "champignon2";
        var j = 0;
        var compt = 1;
        var nbImage = 6;
        function initFormes() {
            while (compt < nbImage + 1) {

                tmp = Math.floor(Math.random() * (nbImage + 1));
                j = 0;
                for (j == 0; j < nbImage; j++) {
                    if (tmp == tab[j]) {
                        document.write('<img name=' + tab2[j] + ' src=../assets/' + tab2[j] + '.gif>');
                        tab[j] = -1;
                        compt++;
                    }
                }
            }
        }
    </script>
    <g:layoutHead/>
</head>

<body>
<nav>
    <a href="${createLink(uri: '/')}"><div class="home"></div></a>
    <g:link controller="logout"><div class="disconnect"></div></g:link>
    <div class="user">Hi <sec:loggedInUserInfo field="username"/></div>
</nav>

<g:layoutBody/>
</body>
</html>
