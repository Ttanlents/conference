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
    <legend>修改密码</legend>
    <form action="/user/updatePassword" method="post">
        账号：<input type="text" name="username" id="username" value=""/><br/><br/>
        密码：<input type="text" name="newPassword" id="newPassword" value=""/><br/><br/>
        邮箱：<input type="text" name="email" id="email"/><br/><br/>
        <input type="button" value="发送验证码" id="but"/><br/><br/>
        验证码：<input type="text" name="emailCode" id="emailCode" value=""/><br/><br/>
        <input type="submit" value="确定"/>
   </form>
</fieldset>
</div>
</body>
<script>
    $(function () {
        $('#but').click(function () {
            var email=$('#email').val();
            console.log(email)
            $.ajax({
                url:'/user/sendEmailCode',
                type:'post',
                data:{"email":email},
                dataType:'json',
                success:function (data) {
                    if (data.code==200){
                        alert("邮件发送成功！")
                    }else {
                        alert("发送失败")
                    }
                },
                error:function () {
                    alert("无响应")
                }
            });
        });
    });

</script>
</html>
