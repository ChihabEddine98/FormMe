<%@ page import="track.UserAuthentification" %>
<!DOCTYPE HTML>
<head>

        <asset:stylesheet src="formation_style.css"/>
        <script src="https://kit.fontawesome.com/f805a2963c.js"></script>



    <g:layoutHead/>
</head>

<body>


<nav>

    <div class="topnav" id="myTopnav">

        <div class="logo">
            <g:link controller="homeFormation"><i class="fas fa-graduation-cap logo" style="font-size:40px;color:var(--icons-col);;"></i></g:link>
        </div>


        <div class="">
            <g:link controller="homeFormation"><i class="fas fa-home home" style="font-size:40px;color:var(--icons-col);"></i></g:link>
        </div>

        <div class="dropdown">
            <a href="" class="dropbtn"><i class="far fa-user-circle dropdown" style="font-size:40px;color:var(--icons-col);"></i></a>

            <div class="dropdown-content">
                <g:link controller="homeFormation" action="monCompte">Mon Compte</g:link>
                <g:link controller="homeFormation" action="mesCours"> Mes Cours </g:link>
                <g:link controller="forum">Forum</g:link>
                <a href="#">Aide</a>
            </div>
        </div>

        <div>
            <a href="#contact"><i class="fas fa-info-circle" style="font-size:40px;color:var(--icons-col);"></i></a>
        </div>

        <div>

            <g:link controller="logout"><i class="fas fa-power-off" style="font-size:40px;color:var(--icons-col);"></i></g:link>
        </div>

        <div class="icon">
            <a href="javascript:void(0);"  onclick="responsiveNavBar()">
                <i class="fa fa-bars" style="font-size:40px;color:var(--icons-col);"></i></a>
        </div>





    </div>







</nav>



<g:layoutBody/>




    <script>

    // When the user scrolls the page, execute fixedNavBar
    window.onscroll = function() {fixedNavBar()};

    // Get the navbar
    var navbar = document.getElementById("myTopnav");

    // Get the offset position of the navbar
    var sticky = navbar.offsetTop;

    // Add the sticky class to the navbar when you reach its scroll position. Remove "sticky" when you leave the scroll position
    function fixedNavBar() {
        if (window.pageYOffset >= sticky) {
            navbar.classList.add("sticky");

        } else {
            navbar.classList.remove("sticky");
        }
    }


    function responsiveNavBar() {
        var x = document.getElementById("myTopnav");
        if (x.className === "topnav") {
            x.className += " responsive";
        } else {
            x.className = "topnav";
        }
    }

    </script>
</body>
</html>
