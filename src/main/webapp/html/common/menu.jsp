<%--
  Created by IntelliJ IDEA.
  User: FT
  Date: 2020/9/22
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单栏</title>
</head>
<script src="${path}/js/jquery-3.4.1.js"></script>
<body>
<div id="menu">

</div>

</body>
    <script>
       $(function () {
           $.ajax({
               url:'${path}/menu/getMenu',
               type:'post',
               data:'',
               dataType:'json',
               async:true,
               success:function (data) {
                   var parent=data.parent;
                   var son=data.son;
                   console.log(parent)
                   console.log(son)
                   var html="";
                   for (var i = 0; i <parent.length ; i++) {
                       html+=parent[i].name+'<ul>'
                       for (var j = 0; j <son.length ; j++) {
                           if ((parent[i].id+'') == (son[j].parentId+'')){
                               html+="<li><a href='"+son[j].url+"'>"+son[j].name+"</a> </li>"
                           }
                       }
                       html+="</ul>";
                   }
                   $('#menu').append(html);
               }
           });
       });
    </script>
</html>
