<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<meta charset='UTF-8'>
  <head>
    <title>欢迎光临！</title>
  </head>
<style>
  #id-span{
    display: none;
    color: red;
  }
</style>
  <body>
  <div id="right">
   <div style="text-align: left;margin-left: 36%;margin-top: 20px">
     <form action="${path}/user/register" method="post" enctype="multipart/form-data">
       用户名:<input type="text" name="username" id="username" onblur="checkName()"/><br/>
       <span id="id-span">账号已存在</span><br/><br/>
       密码：<input type="text" name="password" /><br/><br/>
       姓名：<input type="text" name="realName" /><br/><br/>
       头像：<br/><input type="file" id="picFile" name="filename"/><br/>
       邮箱：<input type="text" name="email" /><br/><br/>
       性别：<input type="radio" name="gender" value="1"  />男
       <input type="radio" name="gender" value="0" />女<br/><br/>
       年龄: <input type="text" name="age" /><br/><br/>
       部门名称：<select name="deptId">
       <option>请选择</option>
       <c:forEach var="dept" items="${deptList}">
         <option value="${dept.id}" <c:if test="${dept.id==user.deptId}">selected</c:if>>${dept.name}</option>
       </c:forEach>
     </select>
       &nbsp; &nbsp; &nbsp;
       <input type="submit" value="注册" class="btn btn-danger"/>
     </form>
   </div>
  </div>
  <script>
      var flag=false;
      function  checkName(){
          var name= $('#username').val();
              $.ajax({
                  url:'/user/checkName',
                  type:'post',
                  data:{"name":name},
                  dataType:'json',
                  success:function (data) {
                      if (data==1){
                          console.log(data==1)
                          $('#id-span').css("display","inline-block");
                          flag=false;
                      } else {
                          $('#id-span').css("display","none");
                          flag=true;
                      }
                  },
                  error:function () {
                      alert("无响应")
                  }

              });

      }
  </script>

  </body>
</html>
