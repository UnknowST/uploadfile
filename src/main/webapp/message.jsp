<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>结果页面</title>
</head>

<body>
${message}
</br>

<c:if test="${state==1}" >
    <p> UUID :${UUID}</p>
</c:if>

<c:if test="${state==2}">
    <p>查无结果！</p>
</c:if>

<c:if test="${state==3}">
    <c:out value="${file.toString()}"></c:out>
</c:if>

</body>
</html>