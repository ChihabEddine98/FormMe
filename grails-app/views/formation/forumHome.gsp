<%--
  Created by IntelliJ IDEA.
  User: aamiot
  Date: 2019-06-18
  Time: 11:55
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="track.DiscussionThread" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="default">
    <script type="text/javascript" src="../assets/TraceMe_CS/collector.js"></script>
    <title> Forum </title>
</head>
<body>



<g:each in="${sections}" var="section">
    <div class="sectionForum">
        <div class="sectionTitle">${section.title}</div>
        <g:each in="${section.topics.sort{it.title}}" var="topic">
            <div class="topic">
                <g:link class="topicTitle" controller="forum" action="topic" params="[topicId:topic.id]" >
                    <h2>${topic.title}</h2>
                </g:link>
                <span class="topicDesc">${topic.description}</span>
                <div class="rightInfo">
                    <b>replies</b>: ${topic.numberOfReplies}
                </div>

                <div class="centerInfo">
                    <b>discussions</b>: ${topic.numberOfThreads}
                </div>
            </div>
        </g:each>
    </div>
</g:each>





</body>
</html>