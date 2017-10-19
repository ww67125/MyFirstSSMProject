<%--
  Created by IntelliJ IDEA.
  User: wangz
  Date: 2017/10/13
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript">
        function checkUsername(){

            /*
            var username = document.getElementById("username");
            var usernameResult = document.getElementById("usernameResult");

            if(username.value.length < 5 || username.value.length > 10){
                usernameResult.innerHTML = "<img src='../image/no.gif'/><font color='red'>用户名长度必须在5到10个字符之间</font>";
            }else{
                usernameResult.innerHTML = "<img src='../image/yes.gif'/><font color='green'>用户名合法</font>";
            }

            */

            var username = $("#username");
            var usernameResult = $("#usernameResult");

            if(username.val().length < 5 || username.val().length > 10){
                usernameResult.html("<img src='photo/no.gif'/><font color='red'>用户名长度必须在5到10个字符之间</font>");
                return;
            }

            //Ajax检查用户名是否可用
            usernameResult.html("<img src='photo/loading.gif'/><font color='blue'>正在检查用户名是否可用。。。</font>");

            $.ajax({

                url : "checkUsername",

                data : "username=" + encodeURI($("#username").val()),

                success : function(data) {

                    if(data == "yes"){
                        usernameResult.html("<img src='photo/yes.gif'/><font color='green'>恭喜，用户名可用</font>");
                    }else{
                        usernameResult.html("<img src='photo/no.gif'/><font color='red'>对不起，用户名已存在</font>");
                    }
                }

            });

        }
        function checkPassword(){

            /*
            var password = document.getElementById("password");
            var passwordResult = document.getElementById("passwordResult");

            if(password.value.length==0){
                passwordResult.innerHTML = "<img src='../image/no.gif' /><font color='red'>密码不能为空</font>";
            }else{
                passwordResult.innerHTML = "";
            }
            */

            var password = $("#password");
            var passwordResult = $("#passwordResult");

            if(password.val().length==0){
                passwordResult.html("<img src='photo/no.gif' /><font color='red'>密码不能为空</font>");
            }else{

                passwordResult.html("");
            }

        }
        function checkPassword2(){

            /*
            var password = document.getElementById("password");
            var password2 = document.getElementById("password2");
            var password2Result = document.getElementById("password2Result");

            if(password.value!=password2.value){
                password2Result.innerHTML = "<img src='../image/no.gif' /><font color='red'>两次密码必须一致</font>";
            }else{
                password2Result.innerHTML = "";
            }
            */

            var password = $("#password");
            var password2 = $("#password2");
            var passwordResult = $("#password2Result");

            if(password.val()!=password2.val()){
                passwordResult.html("<img src='photo/no.gif' /><font color='red'>两次密码必须一致</font>");
            }else{
                passwordResult.html("");
            }
        }
        function checkData(){

            var username = document.getElementById("username");

            //检查用户名
            if(username.value.length < 5 || username.value.length > 10){
                alert("form:用户名长度必须在5到10个字符之间");
                return false;
            }

            //检查密码
            var password = document.getElementById("password");

            if(password.value.length==0){
                alert("form:密码不能为空");
                return false;
            }

            var password2 = document.getElementById("password2");

            if(password.value!=password2.value){
                alert("form:两次密码必须一致");
                return false;
            }

            //检查自定义头像的选择
            var file = document.getElementById("file");
            var photo = document.getElementById("photo");

            if(photo.checked){  //选择自定义头像

                if(file.value.length == 0){
                    alert("form:必须选择自定义头像");
                    return false;
                }
            }

            return true;
        }
        function changePhoto(){

            /*
            var file = document.getElementById("file");
            var photo = document.getElementById("photo");

            if(photo.checked){			//选择了自定义头像
                file.style.visibility="visible";
            }else{
                file.style.visibility="hidden";
            }
            */

            $("#file").css("visibility", $("#photo").prop("checked") ? "visible" : "hidden");
        }

        //字段级检验
        function checkValCode(){

            //获取组件
            var valCode = $("#valCode");
            var valCodeResult = $("#valCodeResult");

            //检查验证码是否为空
            if(valCode.val().length == 0){
                valCodeResult.html("<img src='photo/no.gif' /><font color='red'>验证码不能为空</font>");
                return;
            }

            //Ajax检查验证码是否输入正确
            valCodeResult.html("<img src='photo/loading.gif' /><font color='blue'>正在检查验证码是否输入正确。。。</font>");

            $.ajax({

                url : "checkValCode",

                data : "valCode=" + encodeURI(valCode.val()),

                success : function(data) {

                    if(data == "yes"){
                        valCodeResult.html("<img src='photo/yes.gif'/><font color='green'>恭喜，验证码输入正确</font>");
                    }else{
                        valCodeResult.html("<img src='photo/no.gif'/><font color='red'>对不起，验证码输入错误</font>");
                    }
                }

            });
        }




    </script>



</head>
<body>
<form action="register" method="post" enctype="multipart/form-data" onsubmit="return checkData()">

    <table align="center">

        <tr><td colspan="2" align="center"><font color="red"><b>新用户注册</b></font></td></tr>

        <tr>
            <td>用户名</td>
            <td>
                <input type="text" name="username" id="username" onblur="checkUsername()" /><br/>
                <span id="usernameResult"></span>

            </td>
        </tr>

        <tr>
            <td>密码</td>
            <td>
                <input type="password" name="password" id="password" onblur="checkPassword()" /><br/>
                <span id="passwordResult"></span>

            </td>
        </tr>
        <tr>
            <td>确认密码</td>
            <td>
                <input type="password" name="password2" id="password2" onblur="checkPassword2()"/>
                <br/>
                <span id="password2Result"></span>
            </td>
        </tr>

        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="gender" value="男" checked />男
                <input type="radio" name="gender" value="女" />女
            </td>
        </tr>

        <tr>
            <td>职业</td>
            <td>
                <select name="job">
                    <option value="程序员" selected >程序员</option>
                    <option value="美工" >美工</option>
                    <option value="项目经理" >项目经理</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>兴趣爱好</td>
            <td>
                <input type="checkbox" name="interest" value="唱歌" />唱歌
                <input type="checkbox" name="interest" value="跳舞" />跳舞
                <input type="checkbox" name="interest" value="跑步" />跑步
                <input type="checkbox" name="interest" value="游泳" />游泳
            </td>
        </tr>

        <tr>
            <td>头像</td>
            <td>
                <input type="radio" name="photo" value="1.gif" checked /> <img src="${pageContext.request.contextPath}/photo/1.gif"/>
                <input type="radio" name="photo" value="2.gif" /> <img src="${pageContext.request.contextPath}/photo/2.gif"/>
                <input type="radio" name="photo" value="3.gif" /> <img src="${pageContext.request.contextPath}/photo/3.gif"/>
                <input type="radio" name="photo" value="4.gif" /> <img src="${pageContext.request.contextPath}/photo/4.gif"/>
                <input type="radio" name="photo" value="5.gif" /> <img src="${pageContext.request.contextPath}/photo/5.gif"/>
                <br/>
                <input type="radio" name="photo" value="0.gif" id="photo" onclick="changePhoto()" />自定义头像
                <input type="file" name="file" id="file" style="visibility:hidden"/>

            </td>

        </tr>


        <tr>
            <td>验证码</td>
            <td>
                <input type="text" name="valCode" id="valCode" onblur="checkValCode()"/>

                <img src="valCode" id="imgValCode" onclick="this.src = this.src + '?'" />
                <input type="button" value="看不清，换一张" onclick="document.getElementById('imgValCode').src = document.getElementById('imgValCode').src + '?'" />

                <br/>
                <span id="valCodeResult"></span>

            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="立即注册" />
                <input type="reset" value="重新填写" />
            </td>
        </tr>

    </table>

</form>


</body>
</html>
