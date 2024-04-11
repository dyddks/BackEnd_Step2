<%--
  Created by IntelliJ IDEA.
  User: bitcamp1
  Date: 2024-04-11
  Time: 오전 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form id="form1" action="/todo/update" method="post">
        <input type="text" name="tno" value="${dto.tno}">
        <input type="text" name="title" value="${dto.title}"/>
        <input type="date" name="date" value="${dto.dueDate}"/>
        <input type="checkbox" name="finished" checked="${dto.finished? "checked": ""}"/>
        <button type="submit">수정</button>
    </form>

    <form id="form2" action="/todo/remove" method="post">
        <input type="hidden" name="tno" value="${dto.tno}" readonly>
        <button type="submit">삭제</button>
    </form>
</body>
</html>
