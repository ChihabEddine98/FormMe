<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-06-18
  Time: 12:19
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="track.DiscussionThread" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="default">
    <title>Forum</title>
</head>
<body>
<div class="pagination">
    <g:paginate total="${numberOfThreads}" params="${[topicId:topic.id]}"/>
</div>
<div class="sectionForum">
    <div class="sectionTitle">
        ${topic.title}
%{--        <span class="topicDesc">${topic.description}</span>--}%
    </div>
    <g:each in="${threads}" var="thread">
        <div class="topic">
            <g:link class="sujet" controller="forum" action="thread" params="[threadId:thread.id]" >
                ${thread.subject}
            </g:link>
            <div class="rightInfo">
                <b>replies</b>: ${thread.numberOfReplies}
            </div>
            <div class="centerInfo">
                <b>Par : </b>  ${thread.opener.firstname.toLowerCase().capitalize()} ${thread.opener.lastname.toLowerCase().capitalize()} <br>
                <b>Le  : </b> <g:formatDate date="${thread.createDate}" format="dd MMM yyyy"/>
            </div>
        </div>

%{--        <g:checkBox name="check" values="">--}%
%{--            <h1> Welcome To our site today !</h1>--}%
%{--            <h2> We are trying to test  here</h2>--}%
%{--            <p> This is a loreum ipsum paragarpah so keep it cool or die it now </p>--}%
%{--            <br>--}%
%{--        </g:checkBox>--}%

    </g:each>
</div>

<div class="postComment">
    <h2 style="color: var(--police-col);">Ajouter un sujet</h2>
    <g:form>
        <g:textField class="sectionNom" name="body" placeholder="Titre du sujet"></g:textField>
    %{--            <g:textField class="sectionNom" name="titreTopic" placeholder="Titre du Topic"></g:textField>--}%
    %{--            <g:textField class="sectionNom" name="descTopic" placeholder="Titre de Section"></g:textField>--}%
    %{--            <g:textField class="sectionNom" name="titreTopic"></g:textField>--}%


        <g:hiddenField name="topicId" value="${topic.id}"/>
        <fieldset class="btnPostContainer">
            <g:actionSubmit class="btnPost" id="btnAjoutSujet" value="Ajouter" controller ="forum" action="ajouterSujet"/>
        </fieldset>
    </g:form>
</div>


<div class="pagination">
    <g:paginate total="${numberOfThreads}" params="${[topicId:topic.id]}"/>
</div>

</body>
</html>