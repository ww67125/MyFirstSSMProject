<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangz
  Date: 2017/10/19
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">

        function checkData(){

            var attachment = document.getElementById("attachment");
            var downloadscore = document.getElementById("downloadscore");

            if(attachment.value!=""){   //选择了附件

                if(downloadscore.value=="" || isNaN(downloadscore.value)){
                    alert("必须输入该附件扣除的数字积分");
                    return false;
                }

            }else{
                downloadscore.value = 0;
            }

            return true;
        }

    </script>

</head>
<body>
<form action="${pageContext.request.contextPath }/add" method="post" onsubmit="return checkData()" enctype="multipart/form-data">
    <table align="center">

        <tr><td colspan="2" align="center"><font color="red"><b>添加新闻</b></font></td></tr>

        <tr>
            <td>新闻类型</td>
            <td>
                <select name="newstype.typeid">

                    <c:forEach items="${requestScope.newsTypeList}" var="newstype">
                        <option value="${newstype.typeid }">${newstype.typename }</option>
                    </c:forEach>

                </select>
            </td>
        </tr>

        <tr>
            <td>新闻主题</td>
            <td>
                <input type="text" name="title"/>
            </td>
        </tr>

        <tr>
            <td>新闻内容</td>
            <td>
                <textarea rows="3" cols="30" name="content"></textarea>
            </td>
        </tr>

        <tr>
            <td>新闻附件</td>
            <td>
                <input type="file" name="file" id="attachment"/>
            </td>
        </tr>

        <tr>
            <td>附件所需扣除</td>
            <td>
                <input type="text" name="downloadscore" id="downloadscore"/>分
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="添加新闻" />
                <input type="reset" value="重新填写" />
            </td>
        </tr>

    </table>

</form>

</body>
</html>
