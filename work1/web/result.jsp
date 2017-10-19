<%--
  Created by IntelliJ IDEA.
  User: wangz
  Date: 2017/10/13
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
    <script>

        var second = 3;

        function showTime(){



            if(second > 1){
                $("#time").text(--second);
            }else{
                location = "/index.jsp";
            }

        }

    </script>

</head>
<body onload="setInterval('showTime()', 1000)">
<h2 align="center">注册成功<font color="red" size="10"><span id="time">3</span></font>后进入主页</h2>

</body>
</html>
