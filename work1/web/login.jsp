<%--
  Created by IntelliJ IDEA.
  User: wangz
  Date: 2017/10/12
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--${pageContext.request.contextPath }/user/login--%>
<form action="login" method="post">
    用户名<input type="text" name="username" value="user1"/><br/>
    密码<input type="password" name="password" value="1"/><br/>
    <input type="submit"/>
    <a href="register.jsp">注册</a>

</form>


</body>
</html>
