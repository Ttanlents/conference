<%--
  Created by IntelliJ IDEA.
  User: FT
  Date: 2020/9/22
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
<%@include file="/html/common/top.jsp"%>
<%@include file="/html/common/menu.jsp"%>
<style>
    td img{
        height: 60px;
        width: 60px;
        border-radius:50%;
        -webkit-border-radius:50%;
        -moz-border-radius:50%;
        margin-top: 9px;
    }
</style>
<div id="right">
    <form action="${path}/user/list" method="post" style="margin-left: 160px">
        <input type="text" name="username" placeholder="请输入用户名"/>&nbsp;
        <input class="btn btn-success" type="submit" value="查询"/> &nbsp;<a href="${path}/user/addUser1" class="btn btn-success">添加</a>&nbsp;&nbsp;&nbsp;
    </form>

   <table class="table table-bordered">
       <tr>
           <th>序号</th>
           <th>头像</th>
           <th>用户名</th>
           <th>邮箱</th>
           <th>真实姓名</th>
           <th>年龄</th>
           <th>性别</th>
           <th>注册时间</th>
           <th>部门</th>
           <th>操作</th>
       </tr>
      <c:forEach var="user" items="${userList}" varStatus="status">
          <tr>
          <td>${status.count+(page.pageCurrent-1)*page.pageSize}</td>
          <td>
              <c:choose>
                  <c:when test="${user.wxOpenid!=null}">
                      <c:if test="${fn:contains(user.pic,img)}"><img src="/user/getHeaderPic?pic=${user.pic}" id="detail-img"/></c:if>
                      <c:if test="${!fn:contains(user.pic,img)}"><img src="${user.pic}" id="detail-img"/></c:if>
                  </c:when>
                  <c:when test="${user.pic!=null}">
                      <img src="/user/getHeaderPic?pic=${user.pic}" id="detail-img"/>
                  </c:when>
                  <c:otherwise></c:otherwise>
              </c:choose>
          </td>
          <td>${user.username}</td>
          <td>${user.email}</td>
          <td>${user.realName}</td>
          <td>${user.age}</td>
          <td>${user.gender}</td>
          <td>
              <fmt:parseDate var="abc" value="${user.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
              <fmt:formatDate value="${abc}" pattern="yyyy年MM月dd日 HH时mm分ss秒"></fmt:formatDate>
          </td>
              <td>${user.deptName}</td>
              <td>
                  <a class="btn btn-primary" href="${path}/user/updateUser?id=${user.id}" onclick="return confirm('确认要修改吗？')">修改</a>
                  <a  class="btn btn-danger" href="${path}/user/deleteUser?id=${user.id}&username=${username}&pageCurrent=${page.pageCurrent}" onclick="return confirm('确认要删除吗？')">删除</a>
              </td>
          </tr>
      </c:forEach>

   </table>
    <br/>
    <div id="div4" style="">
        <a href="${path}/user/list?pageCurrent=1&username=${username}">首页</a>
        <a href="s${path}/user/list?pageCurrent=${page.pageCurrent-1}&username=${username}">上一页</a>
        <a href="${path}/user/list?pageCurrent=${page.pageCurrent+1}&username=${username}">下一页</a>
        <a href="${path}/user/list?pageCurrent=${page.pageTotal}&username=${username}">尾页</a>
        <br/>
        当前${page.pageCurrent}页，每页${page.pageSize}条，共${page.count}条记录，共${page.pageTotal}页
    </div>
</div>
</body>
</html>
