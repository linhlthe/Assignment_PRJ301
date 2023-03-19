<%-- 
    Document   : profile
    Created on : Mar 19, 2023, 1:55:43 PM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../../css/style.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../template/header.jsp"></jsp:include>
            <div class="row">
                <table border="1px" width="1000px">
                    <tr height="50px"><td>
                            Full Name
                        </td>
                        <td>${requestScope.i.surname} ${requestScope.i.middlename} ${requestScope.i.givenname}</td>
                </tr>
                <tr height="50px">
                    <td>Department</td>
                    <td>${requestScope.i.department.departmentName}</td>
                </tr><tr height="50px">
                    <td>Gender</td>
                    <td ><c:if test="${requestScope.i.gender}">
                            Female
                        </c:if>
                        <c:if test="${!requestScope.i.gender}">
                            Male
                        </c:if></td>
                </tr>
                <tr height="50px">
                    <td>Campus</td>
                    <td>${requestScope.i.campus}</td>
                </tr>
                <tr height="50px">
                    <td>Email</td>
                    <td>${requestScope.i.email}</td></tr>


            </table>
        </div>
    </body>
</html>
