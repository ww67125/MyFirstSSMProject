<%--
  Created by IntelliJ IDEA.
  User: wangz
  Date: 2017/10/12
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<c:if test="${sessionScope.user==null}">
  未登录请先<a href="login.jsp">登陆</a>
</c:if>

<c:if test="${sessionScope.user!=null}">
  ${sessionScope.user.username}，${sessionScope.user.score}
  <img src="photo/${sessionScope.user.photo}">
  <a href="logout">注销</a>
  <a href="finduserlist">用户列表</a>
  <a href="init">添加新闻</a><br/>
  <a href="query">查询新闻列表</a><br/>

</c:if>
  </body>
</html>
