<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<meta charset='UTF-8'>
  <head>
    <title>欢迎光临！</title>
  </head>
  <body>
  <%@include file="/html/common/top.jsp"%>
  <%@include file="/html/common/menu.jsp"%>
  <div id="right">
   <div style="text-align: left;margin-left: 36%;margin-top: 20px">
     <form action="${path}/user/addUser" method="post" enctype="multipart/form-data">
       用户名：${user.id}<input type="text" name="username"/><br/><br/>
       姓名：<input type="text" name="realName" /><br/><br/>
       头像：<br/><input type="file" id="picFile" name="filename"/><br/>
       邮箱：<input type="text" name="email" /><br/><br/>
       性别：<input type="radio" name="gender" value="1"  />男
       <input type="radio" name="gender" value="0" />女<br/><br/>
       年龄: <input type="text" name="age" /><br/><br/>
       部门名称：<select name="deptId">
       <c:forEach var="dept" items="${deptList}">
         <option value="${dept.id}" <c:if test="${dept.id==user.deptId}">selected</c:if>>${dept.name}</option>
       </c:forEach>
     </select>
       &nbsp; &nbsp; &nbsp;
       <input type="submit" value="添加" class="btn btn-danger"/>
     </form>
   </div>
  </div>

  </body>
</html>
