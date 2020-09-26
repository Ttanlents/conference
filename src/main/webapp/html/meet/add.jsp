<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<meta charset='UTF-8'>
<head>
    <title>欢迎光临！</title>
</head>
<body>
<%@include file="/html/common/top.jsp" %>
<%@include file="/html/common/menu.jsp" %>
<div id="right">
    <div style="text-align: left;margin-left: 26%;margin-top: 30px">
        <form action="${path}/meet/addMeeting" method="post">
            标题：<input type="text" name="title"/><br/><br/>
            部门：<select name="deptId" id="deptId">
            <option>请选择</option>
            </select>
            <br/><br/>
            抄送人：<span id="makeUser"></span><br/><br/>
            开始时间：<input type="datetime-local" name="startTime"/><br/><br/>
            结束时间：<input type="datetime-local" name="endTime" /><br/><br/>
            会议内容: <input type="textarea" name="content" cols="30" rows="10"></textarea><br/><br/>
            <input type="submit" value="添加" class="btn btn-danger"/>
        </form>

    </div>
</div>
<script>
    $(function () {
        $.ajax({
            url:'/meet/getDept',
            type:'post',
            data:'',
            dataType:'json',
            success:function (data) {
                var html="";
                for (index in data) {
                    html+="<option value='"+data[index].id+"'>"+data[index].name+"</option>"
                }
                $('#deptId').append(html)
            }
        });
    });

    $(function () {
        $('#deptId').change(function () {
            var deptId=$('#deptId').val();
            $.ajax({
                url:'/meet/getUserByDeptId',
                type:'post',
                data:{"deptId":deptId},
                dataType:'json',
                success:function (data) {
                    $('#makeUser').empty()
                    var html="";
                    for (index in data) {
                        html+=data[index].username+"<input type='checkbox' name='makeUsers' value='"+data[index].id+"'/>"
                    }
                    $('#makeUser').append(html)
                }
            });
        });
    });
</script>

</body>
</html>
