<%--
  Created by IntelliJ IDEA.
  User: 29256
  Date: 2019/3/27
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8"/>
  <title>Login</title>

  <link rel="stylesheet" type="text/css" href="css.css"/>

</head>
<body>


<%--login with amazon ID--%>
<form action="login.login" method="post">

  <input id="amazonIDInput" name="amazonID" value="please input your amazon ID" class="TextInput"
         onfocus="javascript:if(this.value=='please input your amazon ID'){this.value='';};"
         onblur="javascript:if(this.value==''){this.value='please input your amazon ID';};">
  <input type="submit" value="submit" class="Button" id="amazonIDSubmit"/>

</form>


</body>
</html>
