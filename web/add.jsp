<%--
  Created by IntelliJ IDEA.
  User: sscerbatiuc
  Date: 2/25/2021
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add employee</title>
    <%@ include file="lib/bootstrap.html"%>
</head>
<body>
<div>

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Add employee</li>
        </ol>
    </nav>

    <form method="post">
        <label>Name: <input type="text" name="name"></label>
        <button type="submit">Save</button>
    </form>

</div>
</body>
</html>
