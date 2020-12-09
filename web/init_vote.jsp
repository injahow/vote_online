<%--
  Created by IntelliJ IDEA.
  User: injah
  Date: 2020/11/30
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<table>
    <tr>
        <td>编号</td><td>姓名</td><td>性别</td><td>得票数</td><td>得票数可视化</td><td>操作</td>
    </tr>
    <c:forEach items="${electorList}" var="i">
        <tr>
            <td>${i.getId()}</td>
            <td>${i.getName()}</td>
            <td>${i.getSex()}</td>
            <td>${i.getElectedNum()}</td>
            <td>
                <div style="background-color:red;width:${i.getElectedNum()}px">&nbsp;</div>
            </td>
            <td>
                <a href="javascript:void(0)" onclick="vote('${i.getId()}','0')">投票</a>
                <input type="checkbox" name="sel" value="${i.getId()}">
                <a href="javascript:void(0)" onclick="vote('${i.getId()}','1')">匿名投票</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
