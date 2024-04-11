<%--
  Created by IntelliJ IDEA.
  User: bitcamp1
  Date: 2024-04-08
  Time: 오후 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/todo/register" method="post">
        <input type="text" name="title"/>
        <input type="date" name="date"/>
        <button type="submit">등록</button>
    </form>
</body>
</html>
