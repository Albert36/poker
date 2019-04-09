<%@ page import="java.util.Random" %>
<%@ page import="config.Global" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/5
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Done</title>
    <link rel="stylesheet" type="text/css" href="css.css"/>
</head>
<body>

<%
    int score = (Integer)session.getAttribute("score");

    String sources = "0123456789";
    Random rand = new Random();
    StringBuilder sb=new StringBuilder(Global.SECURITY_CODE);
    for (int j = 0; j < Global.SECURITY_CODE; j++)
    {
        sb.append(sources.charAt(rand.nextInt(9)) + "");
    }
    String securityCode = sb.toString();
%>

<div id="score" name="score" style="left: 40%; position: absolute; top: 50%;">
    <h2>
        You have done this game. Your score is <%= score%><br>
        Please copy this security code: <%= securityCode%> as your <br>
        service code in mTurk to get paid.
    </h2>
</div>


</body>
</html>
