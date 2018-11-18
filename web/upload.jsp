<%--
  Created by IntelliJ IDEA.
  User: LiFen
  Date: 2018/8/9
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
    <form action="/upload" method="post" enctype="multipart/form-data">
        用户名：<input type="text" name="username"><br>
        照片：<input type="file" name="image"><br>
        <input type="submit" value="上传">
    </form>
</body>
</html>
