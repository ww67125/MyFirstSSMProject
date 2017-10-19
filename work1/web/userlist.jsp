<%--
  Created by IntelliJ IDEA.
  User: wangz
  Date: 2017/10/16
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
    <script src="js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        //显示和隐藏注册时间的区间端
        function showTimeRange(timerange){

            /* var spantime = document.getElementById("spantime");

            if(timerange=="全部时间"){
                spantime.style.visibility = "hidden";
            }else{
                spantime.style.visibility = "visible";
            }
            */

            $("#spantime").css("visibility", timerange=="全部时间" ? "hidden" : "visible");

        }
        function confirmDelete(element){
            var username = $(element).parents("tr").find("td:eq(2)").text();
            return confirm('是否确定删除用户【' + username + "】");
        }


    </script>
</head>
<body>

<form action="finduserlist" method="post">
    用户名<input type="text" name="username" value="${requestScope.user.username }"/>

    用户性别
    <select name="gender">
        <option value="" ${""==requestScope.user.gender ? "selected" : ""}>全部</option>
        <option value="男" ${"男"==requestScope.user.gender ? "selected" : ""}>男</option>
        <option value="女" ${"女"==requestScope.user.gender ? "selected" : ""}>女</option>
    </select>
    职业
    <select name="job">
        <option value="" ${""==requestScope.user.job ? "selected" : ""}>全部</option>
        <option value="程序员" ${"程序员"==requestScope.user.job ? "selected" : ""}>程序员</option>
        <option value="美工" ${"美工"==requestScope.user.job ? "selected" : ""}>美工</option>
        <option value="项目经理" ${"项目经理"==requestScope.user.job ? "selected" : ""}>项目经理</option>
    </select>

    <input type="submit" value="查询"/><br/>
    注册时间
    <select name="regdatetime" onchange="showTimeRange(this.value)">
        <option value="全部时间" ${ "全部时间"==requestScope.user.regdatetime ? "selected" : ""}>全部时间</option>
        <option value="指定时间" ${ "指定时间"==requestScope.user.regdatetime ? "selected" : ""}>指定时间</option>
    </select>

    <span id="spantime" style="visibility:${ '全部时间'==requestScope.user.regdatetime ? 'hidden' : 'visible'}">
	 	从<input type="text" name="begintime" value="${requestScope.user.begintime}" readonly="readonly" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	 	到<input type="text" name="endtime" value="${requestScope.user.endtime}"  readonly="readonly" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	 </span>

</form>

<table>
    <c:set var="flag" value="false"></c:set>
    <c:forEach items="${requestScope.userlist}" var="u">

        <tr bgcolor="${flag ? '#ffaaaa':'#aaaaff'}">
            <!-- 加红显示用户名的关键字 -->
            <c:set var="redusername" value="<font color='red'><b>${requestScope.user.username}</b></font>"/>
            <td>${fn:replace(u.username, requestScope.user.username, redusername) }</td>

            <td>${u.gender}</td>
            <td>${u.job}</td>
            <td>${u.score}</td>

            <td><fmt:formatDate value="${u.regdatetime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/></td>
            <td><img src="photo/${u.photo}"></td>
            <td><a href="delete?userid=${u.userid}" onclick="">删除</a> </td>
        </tr>
        <c:set var="flag" value="${!flag}"></c:set>
    </c:forEach>
</table>


</body>
</html>
