<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-06-18
  Time: 12:25
--%>

<%@ page import="track.DiscussionThread" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="default">
    <title> Forum</title>
</head>
<body>
<div class="pagination">
    <g:paginate total="${numberOfComments}" params="${[threadId:thread.id]}"/>
</div>
<div class="sectionForum">
    <div class="discussionTitle">
        ${thread.subject}
    </div>
    <g:each in="${comments}" var="comment">
        <div class="comment">
            <div>
                <g:if test="${track.User.findByEmail(comment.commentBy.email).imgUrl.isEmpty()}">
                    <g:if test="${track.User.findByEmail(comment.commentBy.email).sexe=='m'}">
                        <asset:image class="userImg" src="profil_pic.png" alt="photo de profil"/>
                    </g:if>
                    <g:else>
                        <asset:image class="userImg" src="profil_pic_female.png" alt="photo de profil"/>
                    </g:else>
                </g:if>
                <g:else>
                    <img class="userImg" src="${track.User.findByEmail(comment.commentBy.email).imgUrl}" alt=" Photo de profil">
                </g:else>

                <b>${comment.commentBy.firstname.toLowerCase().capitalize()} ${comment.commentBy.lastname.toLowerCase().capitalize()}</b>
                <span class="topicDesc">
                    <g:formatDate date="${comment.createDate}" format="dd MMM yyyy  hh:mma"/>
                </span>
            </div>
            <p> ${comment.body} </p>


        </div>
    </g:each>
    <sec:ifLoggedIn>
        <div class="postComment">
            <h2>Répondre</h2>
            <g:form>
                <g:textArea class="reponse" name="body"></g:textArea>
                <g:hiddenField name="threadId" value="${thread.id}"/>
                <fieldset class="btnPostContainer">
                    <g:actionSubmit class="btnPost" value="Envoyer" action="postReply"/>
                </fieldset>



            </g:form>
    </sec:ifLoggedIn>
</div>
<div class="pagination">
    <g:paginate  total="${numberOfComments}" params="${[threadId:thread.id]}"/>
</div>
</body>
</html>