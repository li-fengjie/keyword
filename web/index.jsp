<%--
  Created by IntelliJ IDEA.
  User: LiFen
  Date: 2018/11/15
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
    <script type="text/javascript" src="ajax-lib/ajaxutils.js">
        window.onload = function () {
            ajax({
                url: "",
                type: "json",
                callback: function (data) {
//                    data 服务器返回数据
                }
            })
        }
    </script>
</head>
<body>
<form action="/BServlet" method="post" enctype="multipart/form-data" multiple>
    用户名：<input type="text" name="username"><br>
    照片：<input type="file" name="image"><br>
    <input type="submit" value="上传">
</form>
</body>
</html>
