<%--
  Created by IntelliJ IDEA.
  User: sscerbatiuc
  Date: 2/23/2021
  Time: 9:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="edu.step.entity.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Employees</title>
    <%@ include file="lib/bootstrap.html" %>
</head>
<body>
<%@include file="lib/navbar.html" %>
<div class="container">
    <div class="row">
        <c:if test="${not empty requestScope.wrongPageMsg}">
            <div class="alert alert-danger" role="alert">
               <c:out value="${requestScope.wrongPageMsg}"/>
            </div>
        </c:if>

    </div>


    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Employee list</li>
            </ol>
        </nav>

        <c:if test="${not empty param.q}">
            <div class="alert alert-primary" role="alert">
                Active filter: <c:out value="${param.q}"/> <a href="list">Clear</a>
            </div>
        </c:if>

        Employees list:<br/>

        <c:if test="${requestScope.employees != null && !requestScope.employees.isEmpty()}">

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.employees}" var="emp">
                    <tr>
                        <td><c:out value="${emp.name}"/></td>
                        <td><a class="btn btn-primary" href="/edit?id=${emp.id}">Update</a></td>
                        <td><a class="btn btn-danger" href="/delete?id=${emp.id}">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                        <c:forEach var="i" begin="1" end="${requestScope.totalPages}">

                            <c:choose>
                                <c:when test="${requestScope.currentPage == i}">
                                    <li class="page-item active">
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${not empty param.q}">
                                    <a class="page-link" href="list?q=${param.q}&p=${i}"><c:out value="${i}"/> </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="page-link" href="list?p=${i}"><c:out value="${i}"/> </a>
                                </c:otherwise>
                            </c:choose>

                            </li>



                        </c:forEach>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
            </nav>
        </c:if>
        <c:if test="${requestScope.employees == null || requestScope.employees.isEmpty()}">
            <c:out value="No employees"/>
        </c:if>
    </div>
</div>
</body>
</html>
