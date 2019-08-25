<%@ page import="dao.OpponentDao" %>
<%@ page import="java.util.List" %>
<%@ page import="config.Global" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/1
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Player2</title>
    <link rel="stylesheet" type="text/css" href="css.css"/>
</head>
<body>

<%
    long startTime =  System.currentTimeMillis();
    session.setAttribute("time", startTime);
%>

<div id="myCard" name="myCard">
    <%
        int num = (Integer)session.getAttribute("player2_card");
        String path = "/res/" + num  + "S.png";
    %>
    <img src=<%out.println(path);%> height="180" width="120">
</div>

<div id="opponentCard" name="opponentCard">
    <img src="/res/back.png" height="180" width="120">
</div>


<form action="fold.do" method="post">
    <input type="submit" value="fold" class="Button" id="foldSubmit" />
</form>


<form action="call.do" method="post">
    <input type="submit" value="call" class="Button" id="callSubmit" />
</form>


<div id="tip" name="tip" style="left: 50%; position: absolute; top: 85%;">
    <h4>
        Current deck<br>
        2,3,4,5,6,7,8
    </h4>
</div>

<div id="score" name="score" style="left: 85%; position: absolute; top: 85%;">
    <%
        int score = Integer.parseInt(String.valueOf(session.getAttribute("score")));
    %>
    <h4>
        Your score<br>
        <%= score%>
    </h4>
</div>

<%
    response.setHeader("refresh", Global.TIME_OUT + ";URL=timeout.jsp");
%>
</body>
</html>