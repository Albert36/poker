<%@ page import="dao.OpponentDao" %>
<%@ page import="java.util.List" %>
<%@ page import="config.Global" %><%--
  Created by IntelliJ IDEA.
  User: 29256
  Date: 2019/3/27
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Player1</title>
    <link rel="stylesheet" type="text/css" href="css.css"/>
</head>
<body>

<%--clean user's score at the real test round--%>
<%
    int gameNum = (Integer)session.getAttribute("gameNum");
    if(gameNum == Global.TRIAL_GAME_NUM)
        session.setAttribute("score", "0");
%>


<%
    long startTime =  System.currentTimeMillis();
    session.setAttribute("time", startTime);
%>

<div id="myCard" name="myCard">
    <%
        int num = (int)(Math.random() * 7 + 2);
        String path = "res/" + num  + "S.png";
        session.setAttribute("player1_card", num);

        //get opponent
        int player2_card = (int)(Math.random() * 7 + 2);
        while(player2_card == num)
            player2_card = (int)(Math.random() * 7 + 2);

        OpponentDao opponentDao = new OpponentDao();
        List<String> list = opponentDao.queryRandom(2, player2_card);

        String player2_decision = list.get(1);
        session.setAttribute("player2_card", player2_card);
        session.setAttribute("player2_decision", player2_decision);
        session.setAttribute("player2_time", list.get(2));
    %>
    <img src=<%out.println(path);%> height="180" width="120">
</div>

<div id="opponentCard" name="opponentCard">
    <img src="res/back.png" height="180" width="120">
</div>


<form action="check.do" method="post">
    <div name="check">
        <input type="submit" value="check" class="Button" id="checkSubmit" />
    </div>
</form>


<form action="bet.do" method="post">
    <div name="bet">
        <input type="submit" value="bet" class="Button" id="betSubmit" />
    </div>
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
