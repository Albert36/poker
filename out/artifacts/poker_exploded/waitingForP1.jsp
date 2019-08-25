<%@ page import="java.util.List" %>
<%@ page import="dao.OpponentDao" %>
<%@ page import="dao.GameDao" %>
<%@ page import="config.Global" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/4
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Loading</title>
    <link rel="stylesheet" type="text/css" href="css.css"/>
</head>
<body>


<%--clean user's score at the real test round--%>
<%
    int gameNum = (Integer)session.getAttribute("gameNum");
    int waitTime = Global.FORMAL_WAIT_TIME;

    if(gameNum < Global.TRIAL_GAME_NUM)
        waitTime = Global.TRIAL_WAIT_TIME;

    if(gameNum == Global.TRIAL_GAME_NUM)
        session.setAttribute("score", "0");
%>


<%
    //generate my(P2) card num
    int num = (int)(Math.random() * 7 + 2);
    session.setAttribute("player2_card", num);

    //get opponent
    int player1_card = (int)(Math.random() * 7 + 2);
    while(player1_card == num)
        player1_card = (int)(Math.random() * 7 + 2);
    OpponentDao opponentDao = new OpponentDao();
    List<String> list = opponentDao.queryRandom(1, player1_card);

    String player1_decision = list.get(1);
    session.setAttribute("player1_card", player1_card);
    session.setAttribute("player1_decision", player1_decision);
    session.setAttribute("player1_time", list.get(2));

    String loadingInfo = " ";
    if(player1_decision.equals("check"))
        loadingInfo = "Player1 choose to check, you don't need to make a decision";
    else
        loadingInfo = "Player1 choose to bet, you can press the button to make a decision";
%>




<div id="myCard" name="myCard">
    <%
        String path = "res/" + num  + "S.png";
    %>
    <img src=<%out.println(path);%> height="180" width="120">
</div>

<div id="opponentCard" name="opponentCard">
    <img src="res/back.png" height="180" width="120">
</div>

<div id="loadingInfo" name="loadingInfo"  style="left: 40%; position: absolute; top: 45%;">
    <h2 style="text-align:center;">
        <%= loadingInfo%>
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
    %>
    <h4>
        Your score<br>
        <%= score%>
    </h4>
</div>



<%
    if(player1_decision.equals("check"))
    {
        String amazonID = String.valueOf(session.getAttribute("amazonID"));
        String decision = "checked";

        GameDao gameDao = new GameDao();
        gameDao.insert(String.valueOf(player1_card), String.valueOf(num), player1_decision, decision, String.valueOf(list.get(2)), "0");

        String winInfo = " ";
        int points = 0;
        if(num > player1_card)
        {
            points = Global.CHECK_POINT;
            winInfo = "Your card is larger, yon earn " + points;
            session.setAttribute("winInfo", winInfo);
            session.setAttribute("points", points);
        }
        else
        {
            points = -Global.CHECK_POINT;
            winInfo = "Your card is smaller, yon earn " + points;
            session.setAttribute("winInfo", winInfo);
            session.setAttribute("points", points);
        }

        opponentDao.insert(2, amazonID, String.valueOf(num), decision, "0", String.valueOf(points));
        response.setHeader("refresh", waitTime + ";URL=win.jsp");
    }
    else
    {
        response.setHeader("refresh", waitTime + ";URL=player2.jsp");
    }

%>

</body>
</html>
