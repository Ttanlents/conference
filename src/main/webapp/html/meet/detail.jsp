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
    <div>

    </div>
    <div id="detail_meeting">
        标题：${meet.title}<br/><br/>id${meet.id}
        部门名称：${meet.deptName}<br/>
        应到：${map.should}人<br/><br/>
        实到：${map.actually}人<br/><br/>
        未到：${map.notJoin}人<br/><br/>
        发布时间：${meet.publishDate}<br/><br/>
        开始时间：${meet.startTime}<br/><br/>
        结束时间：${meet.endTime}<br/><br/>
        会议内容：${meet.content}<br/><br/>
        <c:if test="${map.flag!=1}">
            <c:if test="${map.flag==2}">
                <button class="btn btn-danger" id="but1">参加</button>
            </c:if>
            <c:if test="${map.flag==3}">
               <button  class="btn btn-danger" id="but2">取消</button>
            </c:if>
        </c:if>
        <c:if test="${map.flag==1}"><font color="red">亲，您不需要参加本次会议哦！！</font></c:if>
    </div>

</div>
    <script>
        $(function () {
            $('#but1').click(function () {
                confirm("确认要参加会议吗？");
                $.ajax({
                    url:'/meet/joinMeeting',
                    type:'post',
                    data:{"meetId":${meet.id}},
                    dataType:'json',
                    success:function (data) {
                        console.log(data.code)
                        if (data.code==200){
                            alert("参加会议成功!")
                            window.location.href="/meet/detail?id=${meet.id}";
                        }else {
                            alert("参加会议失败，会议进行中或者已经结束")
                        }
                    }
                });
            });

            $('#but2').click(function () {
                confirm("确认要取消吗？");
                $.ajax({
                    url:'/meet/cancelMeeting',
                    type:'post',
                    data:{"meetId":${meet.id}},
                    dataType:'json',
                    success:function (data) {
                        if (data.code == 200){
                            alert("取消成功！")
                            window.location.href="/meet/detail?id=${meet.id}";
                        }else {
                            alert("你不能取消该会议！")
                        }
                    }
                });
            });
        });
    </script>
</body>
</html>
