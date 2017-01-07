<%--
  Created by IntelliJ IDEA.
  User: wangwei
  Date: 2017/1/5
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
hello spring mvc
${message}
<c:if test="${!empty error}">
    <font color="red"><c:out value="${error}"/></font>
</c:if>
<form action="<c:url value="/loginCheck"/>" method="post">
    账号：
    <input type="text" name="userName"/>
    <br>
    密码：
    <input type="password" name="userPwd">
    <br>
    <input type="submit" value="登录">
    <input type="reset" value="重置">
</form>

</body>
</html>
