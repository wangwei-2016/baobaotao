<%--
  Created by IntelliJ IDEA.
  User: wangwei
  Date: 2017/1/5
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>宝宝淘论坛</title>
</head>
<body>
sdfs
<%--${user.userName} 欢迎进入宝宝淘，当前积分为：${user.credits};--%>
</body>
</html>

function CreateGrade(type) {
var num = 0;
var num2 = 0;
switch (type) {
case "1":
num = 1;
num2 = 6;
break;
case "2":
num = 7;
num2 = 9;
break;
case "3":
num = 10;
num2 = 12;
break;
case "4":
num = 1;
num2 = 9;
break;
case "5":
num = 7;
num2 = 12;
break;
case "6":
num = 1;
num2 = 12;
break;
default:
num = 0;
num2 = 0;
break;
}
var ghtml = '<option value="0" style="width: 100px">所有年级</option>';
if (num > 0) {
for (var i = num; i <= num2; i++) {
ghtml += "<option value='" + i + "'>" + i + "年级</option>";
}
}

$("#grade").html(ghtml);
}