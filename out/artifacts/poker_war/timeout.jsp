<%@ page import="config.Global" %>
<%@ page import="dao.OpponentDao" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/8
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Timeout</title>
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
        String path = "/res/" + num  + "S.png";
    %>
    <img src=<%out.println(path);%> height="180" width="120">
</div>

<div id="opponentCard" name="opponentCard">
    <img src="/res/back.png" height="180" width="120">
</div>

<div id="winInfo" name="winInfo"  style="left: 40%; position: absolute; top: 45%;">
    <h2>
        Time out! You lose 2 points<br>
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
        int points = Global.TIMEOUT_POINT;
        score = score - points;
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


<%
    //save player's info
    String amazonID = String.valueOf(session.getAttribute("amazonID"));
    String card = String.valueOf(num);
    String decision = "timeout";
    String time = "8";
    OpponentDao opponentDao = new OpponentDao();
    opponentDao.insert(1, amazonID, card, decision, time);


%>

</body>
</html>
