
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>通过uuid查看文件元数据</title>
</head>
<body>
<form method="get" action="servlet/GetinfoServlet" enctype="text/plain ">
<p>请输入uuid:</p>
<input type="text" name="uuid" id="uuid">
<input type="submit" name="but" value="查询">
</form>





</body>
</html>
