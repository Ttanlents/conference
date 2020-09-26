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
    <form action="${path}/user/updateUser2" method="post">
      <input type="hidden" name="id" value="${user.id}"/>
      用户名：<input type="text" name="username" value="${user.username}"/><br/><br/>
      真实姓名：<input type="text" name="realName" value="${user.realName}"/><br/><br/>
      邮箱：<input type="text" name="email" value="${user.email}"/><br/><br/>
      性别：<input type="radio" name="gender" value="1"  <c:if test="${user.gender==1}">checked</c:if>/>男
      <input type="radio" name="gender" value="0" <c:if test="${user.gender==0}">checked</c:if>/>女<br/><br/>
      年龄: <input type="text" name="age" value="${user.age}"/><br/><br/>
      注册时间:<input type="datetime-local" name="registerTime" value="${user.registerTime}"/><br/><br/>
      部门名称：<select name="deptId">
      <c:forEach var="dept" items="${deptList}">
        <option value="${dept.id}" <c:if test="${dept.id==user.deptId}">selected</c:if>>${dept.name}</option>
      </c:forEach>

    </select>
      <br/><br/>
      <input type="submit" value="修改"/>
    </form>
  </div>

  </body>
</html>
