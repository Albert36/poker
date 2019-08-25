<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/7
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info</title>
    <link rel="stylesheet" type="text/css" href="css.css"/>
</head>
<body>

<div id="loadingInfo" name="loadingInfo"  style="left: 30%; position: absolute; top: 45%;">
    <%
        String who = String.valueOf(session.getAttribute("who"));
    %>
    <h2 style="text-align:center;">
        Your role is Player <%= who%>. The first three trials are for practice, the score won't be recorded.<br>
    </h2>
</div>

<form action="continue.login" method="post">
    <input type="submit" value="continue" class="Button" id="continue"/>
</form>

</body>
</html>
