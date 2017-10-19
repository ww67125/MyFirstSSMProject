<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangz
  Date: 2017/10/19
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">

    <tr>
        <td>新闻类型</td><td>所属用户<td>主题</td><td>内容</td><td>发布时间</td><td>下载</td>
    </tr>

    <c:set var="flag" value="true"/>

    <c:forEach items="${requestScope.newsPage.newsList}" var="news">

        <tr bgcolor="${flag==true ? '#ffffff' : '#d0d0d0'}">

            <td>${news.newstype.typename}</td>
            <td>${news.user.username}</td>
            <td>${news.title}</td>
            <td>${news.content}</td>
            <td><fmt:formatDate value="${news.pubtime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"/></td>

            <td>
                <c:if test="${news.attachment!=''}">
                    <a href="${pageContext.request.contextPath }/download?attachment=${news.attachment}&truename=${news.truename}&downloadscore=${news.downloadscore}&newsid=${news.newsid}"
                       onclick="return checkScore(${sessionScope.user.score}, ${news.downloadscore})">下载</a>
                    扣${news.downloadscore }分 已${news.downloadcount }次
                </c:if>
            </td>
        </tr>

        <c:set var="flag" value="${!flag}" />

    </c:forEach>

    <tr>
        <td colspan="6" align="center">

            每页 ${newsPage.pageSize}条记录
            共${newsPage.totalCount}条记录
            第 ${newsPage.currentPage}页
            共${newsPage.totalPage}页

            <br/>

            <c:if test="${newsPage.currentPage==1}">
                首页 上一页
            </c:if>
            <c:if test="${newsPage.currentPage!=1}">
                <a href="${pageContext.request.contextPath }/query?currentPage=1">首页</a>
                <a href="${pageContext.request.contextPath }/query?currentPage=${newsPage.currentPage-1}">上一页</a>
            </c:if>

            <c:if test="${newsPage.currentPage==newsPage.totalPage}">
                下一页 尾页
            </c:if>
            <c:if test="${newsPage.currentPage!=newsPage.totalPage}">
                <a href="${pageContext.request.contextPath }/query?currentPage=${newsPage.currentPage+1}">下一页 </a>
                <a href="${pageContext.request.contextPath }/query?currentPage=${newsPage.totalPage}">尾页</a>
            </c:if>

            <br/>

            <c:forEach var="i" begin="${newsPage.beginPage}" end="${newsPage.endPage}">

                <c:if test="${newsPage.currentPage!=i}">
                    <a href="${pageContext.request.contextPath }/query?currentPage=${i}">${i}</a>
                </c:if>

                <c:if test="${newsPage.currentPage==i}">
                    ${i}
                </c:if>

            </c:forEach>

            <select onchange="location='query?currentPage=' + this.value">

                <c:forEach var="i" begin="1" end="${newsPage.totalPage}">

                    <c:if test="${newsPage.currentPage==i}">
                        <option value="${i}" selected>${i}</option>
                    </c:if>

                    <c:if test="${newsPage.currentPage!=i}">
                        <option value="${i}">${i}</option>
                    </c:if>

                </c:forEach>

            </select>

        </td>
    </tr>
</table>

<a href="${pageContext.request.contextPath }/index.jsp">返回主页</a>

</body>
</html>
