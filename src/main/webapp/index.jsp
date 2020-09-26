<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
   fieldset{
        margin: auto 30%;
   }

    div{
        text-align: center;
    }
</style>
<body>
<script src="${path}/js/jquery-3.4.1.js"></script>
<div>
<fieldset style="border: 2px  solid deepskyblue">
    <legend><h2>欢迎登录！</h2></legend>
    <form action="/login/login" method="post">
        账号：<input type="text" name="name" id="name" value="admin"/><br/><br/>
        密码：<input type="text" name="password" id="password" value="admin"/><br/><br/>
        <img src="${path}/user/getVerifyCode" id="imageCode" /><br/>
        验证码：<input type="text" name="code" id="code"/><br/>
        记住我(七天免登陆)<input type="checkbox" value="1" name="remember"/>
        <input type="submit" value="登录"/>
    </form>
</fieldset>
</div>
<script>
    $(function () {
        $('#imageCode').click(function () {
            $(this).attr('src','${path}/user/getVerifyCode?'+new Date().getTime())
        });
    });



</script>
</body>
</html>
