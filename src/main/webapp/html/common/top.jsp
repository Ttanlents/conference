
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>头</title>
</head>
<style>
    a{
        text-decoration: underline;
    }
    #top{
        height: 10%;
        border: 1px solid red;
        margin-bottom: 2px;
    }
    #menu{
        width: 10%;
        height: 88%;
        border: 1px solid red;
        float: left;

    }

    ul li{
        list-style: none;
    }
    #right{
        margin-left: 3px;
        width: 89%;
        height: 88%;
        border: 1px solid red;
        float: left;
        text-align: center;
    }
    /*table{
        margin: 0 auto;
    }*/
    #top img{
        height: 60px;
        width: 60px;
        border-radius:50%;
        -webkit-border-radius:50%;
        -moz-border-radius:50%;
        margin-top: 9px;
    }
</style>
<body>
<div id="top">
   <c:choose>
       <c:when test="${session_login.wxOpenid!=null}">
            <c:if test="${fn:contains(session_login.pic,img)}"><img src="/user/getHeaderPic?pic=${session_login.pic}" id="detail-img"/></c:if>
            <c:if test="${!fn:contains(session_login.pic,img)}"><img src="${session_login.pic}" id="detail-img"/></c:if>
       </c:when>
       <c:when test="${session_login.pic!=null}">
           <img src="/user/getHeaderPic?pic=${session_login.pic}" id="detail-img"/>
       </c:when>
       <c:otherwise></c:otherwise>
   </c:choose>
    <c:if test="${session_login!=null}"> ${session_login.realName}</c:if>
    <c:if test="${session_login==null}">您还未登录，请先登录哦！</c:if>&nbsp;&nbsp;&nbsp; <a href="${path}/loginOut">退出登录</a>
   <%-- <form action="/user/updatePic" method="post" enctype="multipart/form-data">--%>
    <input style="display: none" type="file" id="picFile" />
        <%--<input type="submit" value="修改头像"><br>--%>
   <%-- </form>--%>
</div>
<script>
    $(function () {

        $("#detail-img").click(function () {
            // 点击图片时触发文件表单控件
            $("#picFile").click();
        });

        $("#picFile").change(function () {
            // 构造文件上传form
            var formData = new FormData();
            formData.append("iconFile", document.getElementById("picFile").files[0]);

            // 上传图片
            $.ajax({
                url: "/user/updatePic",
                processData: false,      //默认为true,对请求传递的参数(formData)不做编码处理
                contentType: false,       //不对请求头做处理
                data: formData,
                type: "post",
                dataType: "json",
                async: true,
                success: function (data) {
                    console.log(data)
                    if (data.code == '200') {
                        //成功
                        $("#detail-img").attr("src", "/user/getHeaderPic?pic=" + data.msg);
                        alert("修改成功");
                    } else {
                        alert(data.msg);
                    }
                },
                error:function () {
                    alert("修改失败，请选择合法的图片！")
                }
            });

        });
    });
</script>
</body>
</html>
