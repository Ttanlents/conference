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
    <title>会议管理</title>
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
    <a class="btn btn-success" href="/html/meet/add.jsp" >发布会议</a>
   <table class="table table-bordered">
       <tr>
           <th>序号</th>
           <th>主题</th>
           <th>部门名称</th>
           <th>状态</th>
           <th>发布时间</th>
           <th>会议内容</th>
           <th>操作</th>
       </tr>
      <c:forEach var="meet" items="${list}" varStatus="status">
          <tr>
          <td>${status.count+(page.pageCurrent-1)*page.pageSize}</td>
          <td><a href="meet/detail?id=${meet.id}">${meet.title}</a></td>
          <td>${meet.deptName}</td>
          <td>
              <c:choose>
                  <c:when test="${meet.status==0}">未开始</c:when>
                  <c:when test="${meet.status==1}">进行中</c:when>
                  <c:when test="${meet.status==2}">已结束</c:when>
                  <c:otherwise></c:otherwise>
              </c:choose>
          </td>
              <td>
                  <fmt:parseDate var="abc" value="${meet.publishDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                  <fmt:formatDate value="${abc}" pattern="yyyy年MM月dd分 HH时mm分ss秒"></fmt:formatDate>
              </td>
          <td>${meet.content}</td>
              <td>
                  <a class="btn btn-primary" href="${path}/meet/toupdate?id=${meet.id}&pageCurrent=${page.pageCurrent}" onclick="return confirm('确认要修改吗？')">修改</a>
                  <a  class="btn btn-danger" href="${path}/meet/delete?id=${meet.id}&pageCurrent=${page.pageCurrent}" onclick="return confirm('确认要删除吗？')">删除</a>
              </td>
          </tr>
      </c:forEach>

   </table>
    <br/>
    <div id="div4" style="">
        <a href="${path}/meet/list?pageCurrent=1&username=${username}">首页</a>
        <a href="s${path}/meet/list?pageCurrent=${page.pageCurrent-1}">上一页</a>
        <a href="${path}/meet/list?pageCurrent=${page.pageCurrent+1}">下一页</a>
        <a href="${path}/meet/list?pageCurrent=${page.pageTotal}">尾页</a>
        <br/>
        当前${page.pageCurrent}页，每页${page.pageSize}条，共${page.count}条记录，共${page.pageTotal}页
    </div>
</div>
</body>
</html>
