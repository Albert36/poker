<%@ page import="config.Global" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/1
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Win</title>
    <link rel="stylesheet" type="text/css" href="css.css"/>
</head>
<body>


<div id="myCard" name="myCard">
    <%
        int num = 0;
        String who = String.valueOf(session.getAttribute("who"));
        if(who.equals("1"))
            num = (Integer)session.getAttribute("player1_card");
        else
            num = (Integer)session.getAttribute("player2_card");
        String path = "res/" + num  + "S.png";
    %>
    <img src=<%out.println(path);%> height="180" width="120">
</div>

<div id="opponentCard" name="opponentCard">
    <img src="res/back.png" height="180" width="120">
</div>

<div id="winInfo" name="winInfo"  style="left: 40%; position: absolute; top: 45%;">
    <%
        String a = String.valueOf(session.getAttribute("winInfo"));
    %>
    <h2 style="text-align:center;">
        <%= a%>
    </h2>
</div>


<div id="tip" name="tip" style="left: 50%; position: absolute; top: 85%;">
    <h4>
        Current deck<br>
        2,3,4,5,6,7,8
    </h4>
</div>

<div id="score" name="score" style="left: 85%; position: absolute; top: 85%;">
    <%
        int score = Integer.parseInt(String.valueOf(session.getAttribute("score")));
        String points = String.valueOf(session.getAttribute("points"));
        score = score + Integer.parseInt(points);
        session.setAttribute("score", score);
    %>
    <h4>
        Your score<br>
        <%= score%>
    </h4>
</div>


<form action="next.do" method="post">
    <input type="submit" value="next" class="Button" id="nextSubmit" />
</form>


<%
    int gameNum = (Integer)session.getAttribute("gameNum");
    gameNum++;
    if(gameNum == Global.FORMAL_GAME_NUM + Global.TRIAL_GAME_NUM)
    {
        response.setHeader("refresh",Global.FINAL_WAIT_TIME +";URL=Done.jsp");
    }
    session.setAttribute("gameNum", gameNum);
%>
</body>
</html>
