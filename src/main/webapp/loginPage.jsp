<%--
  Created by IntelliJ IDEA.
  User: benjamin
  Date: 9/17/14
  Time: 11:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8"/>
  <title>Todo Login</title>
</head>
<body>
<form METHOD="post" action="oauth2/authorize">
  <input type="text" id="username" name="username" placeholder="username"/>
  <input type="password" id="password" name="password" placeholder="Password"/>
  <input type="hidden" name="client_id" value="<%=request.getParameter("client_id")%>"/>
  <input type="hidden" name="response_type" value="<%=request.getParameter("response_type")%>"/>
  <input type="hidden" name="redirect_uri" value="<%=request.getParameter("redirect_uri")%>"/>
  <input type="hidden" name="state" value="<%=request.getParameter("state")%>"/>
  <input type="submit" value="submit"/>
</form>
</body>
</html>
