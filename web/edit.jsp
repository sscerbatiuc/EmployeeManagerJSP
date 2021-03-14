<%--
  Created by IntelliJ IDEA.
  User: sscerbatiuc
  Date: 2/23/2021
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee</title>
</head>
<body>
<div>
    Edit employees
    <form method="post" action="edit">
        <input type="hidden" name="position" value="<%= request.getAttribute("position") %>">
        <label>Name: <input type="text" name="name" value="<%= request.getAttribute("name") %>"><br/></label>
        <button type="submit">Submit</button>
    </form>

    <a href="${pageContext.request.contextPath}/">Back to main page</a>
</div>
</body>
</html>
