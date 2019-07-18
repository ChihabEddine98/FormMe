<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="default">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Home</title>

    <script type="text/javascript" src="../assets/TraceMe_CS/collector.js"></script>
     <script type="text/javascript" src="../assets/dhtml.js"></script>
    <script type="text/javascript" src="../assets/jquery-3.1.1.min.js"></script>
    <asset:stylesheet src="styles.css"/>


</head>
<body>

<p class="legal">By clicking "Start experience" you agree that this site will record your typing habits and mouse movements that will be used for research, development and analysis purposes.</p>

<div class="container-flex">
<g:if test="${Signature}">
    
   <div class="small-container">
        <h2 class="title-experience">Create trace</h2>
        <div class="first-bloc">
            <g:link controller="experience" action="createTrace" params="['type':'Signature']" class = "notactive"><button class="button init notactive">initialization</button></g:link>
            <div class="infos">Click here to create the signature prerequisites.</div>
        </div>
        <div class="second-bloc">
        <g:link class="" controller="experience" action="createSignature"><button class="button start active">start experience</button></g:link>
            <div class="infos">Click here to create your signature.</div>
        </div>
    </div> 
    
</g:if>
<g:else>
      <div class="small-container">
        <h2 class="title-experience">Create trace</h2>
        <div class="first-bloc">
            <g:link controller="experience" action="createTrace" params="['type':'Signature']"><button class="button init active">initialization</button></g:link>
            <div class="infos">Click here to create the signature prerequisites.</div>
        </div>
        <div class="second-bloc">
        <g:link class="" controller="experience" action="createSignature"><button class="button start notactive">start experience</button></g:link>
            <div class="infos">Click here to create your signature.</div>
        </div>
    </div> 
     
</g:else>

 <g:if test="${Classic}">   
    <div class="small-container">
        <h2 class="title-experience">Classic auth</h2>
        <div class="first-bloc">
            <g:link class="notactive" controller="experience" action="createTrace" params="['type':'Classic']"><button class="button init">initialization</button></g:link>
            <div class="infos">Click here to initialize the static trace.</div>
        </div>
        <div class="second-bloc">
            <g:link class="active" controller="experience" action="experienceAuthClasic"><button class="button start active">start experience</button></g:link>
            <div class="infos">Click here to do an authentication using the static algorithm.</div>
        </div>
    </div>
 </g:if>
 <g:else>
 <div class="small-container">
        <h2 class="title-experience">Classic auth</h2>
        <div class="first-bloc">
            <g:link class="active" controller="experience" action="createTrace" params="['type':'Classic']"><button class="button init active">initialization</button></g:link>
            <div class="infos">Click here to initialize the static trace.</div>
        </div>
        <div class="second-bloc">
            <g:link class="notactive" controller="experience" action="experienceAuthClasic"><button class="button start">start experience</button></g:link>
            <div class="infos">Click here to do an authentication using the static algorithm.</div>
        </div>
    </div>
 </g:else>
 
 <g:if test="${Dynamic==true}">   
    <div class="small-container">
        <h2 class="title-experience">Dynamic auth</h2>
        <div class="first-bloc">
            <g:link class="notactive" controller="experience" action="createTrace" params="['type':'Dynamic']"><button class="button init notactive">initialization</button></g:link>
            <div class="infos">Click here to initialize the dynamic trace.</div>
        </div>
        <div class="second-bloc">
            <g:link class="active" controller="experience" action="experienceAuthDynamic" ><button class="button start active">start experience</button></g:link>
            <div class="infos">Click here to do an authentication using the dynamic algorithm.</div>
        </div>
    </div>
</g:if>
<g:else>
 <div class="small-container">
        <h2 class="title-experience">Dynamic auth</h2>
        <div class="first-bloc">
            <g:link class="active" controller="experience" action="createTrace" params="['type':'Dynamic']"><button class="button init active">initialization</button></g:link>
            <div class="infos">Click here to initialize the dynamic trace.</div>
        </div>
        <div class="second-bloc">
            <g:link class="notactive" controller="experience" action="experienceAuthDynamic"><button class="button start notactive">start experience</button></g:link>
            <div class="infos">Click here to do an authentication using the dynamic algorithm.</div>
        </div>
    </div>
</g:else>
</div>

</body>
</html>

