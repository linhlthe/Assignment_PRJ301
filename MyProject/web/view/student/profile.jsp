<%-- 
    Document   : profile
    Created on : Feb 27, 2023, 11:26:47 PM
    Author     : DELL
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../../css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    
    <body>
         <jsp:include page="../template/header.jsp"></jsp:include>
       <div class="row">
            <table border="1px" width="1000px">
                <tr height="50px"><td>Student Code</td>
                    <td>${requestScope.s.studentCode}</td></tr>
                <tr height="50px"><td>Surname</td>
                    <td>${requestScope.s.surname}</td></tr><!-- comment -->
                <tr height="50px"><td>Middlename</td>
                    <td>${requestScope.s.middlename}</td></tr><!-- comment -->
                <tr height="50px"><td>Givenname</td>
                    <td>${requestScope.s.givenname}</td></tr><!-- comment -->
                <tr height="50px"><td>Email</td>
                    <td>${requestScope.s.email}</td></tr><!-- comment -->
                <tr height="50px"><td>Gender</td>
                    <td><c:if test="${requestScope.s.gender}">
                            Female
                        </c:if>
                        <c:if test="${!requestScope.s.gender}">
                            Male
                        </c:if></td></tr><!-- comment -->
                <tr height="50px"><td>Date Of Birth</td>
                    <td>${requestScope.s.dob}</td></tr>
                <tr height="50px"><td>Nationality</td>
                    <td>${requestScope.s.nationality}</td></tr><!-- comment -->
                <tr height="50px"><td>Campus</td>
                    <td>${requestScope.s.campus}</td></tr><!-- comment -->
                <tr height="50px"><td>Address</td>
                    <td>${requestScope.s.address}</td></tr>
                <tr height="50px"><td>Phone</td>
                    <td>${requestScope.s.phoneNumber}</td></tr><!-- comment -->
                <tr height="50px"><td>ID Card</td>
                    <td>${requestScope.s.idCard}</td></tr>
                <tr height="50px"><td>Curriculum</td>
                    <td>${requestScope.s.curriculum}</td></tr><!-- comment -->
                <tr height="50px"><td>Place Of Birth</td>
                    <td>${requestScope.s.placeOfBirth}</td></tr>
                <tr height="50px"><td>Major</td>
                    <td>${requestScope.s.major}</td></tr><!-- comment -->
                <tr height="50px"><td>Peoples</td>
                    <td>${requestScope.s.peoples}</td></tr>
                <tr height="50px"><td>Father Name</td>
                    <td>${requestScope.s.fatherName}</td></tr>
                <tr height="50px">
                    <td>Mother Name</td>
                    <td>${requestScope.s.motherName}</td>
                </tr>
                <tr height="50px"><td>Father Job</td>
                    <td>${requestScope.s.fatherJob}</td></tr>
                <tr height="50px">
                    <td>Mother Job</td>
                    <td>${requestScope.s.motherJob}</td>
                </tr><!-- comment -->
                <tr height="50px"><td>Father Phone</td>
                    <td>${requestScope.s.fatherPhone}</td></tr>
                <tr height="50px">
                    <td>Mother Phone</td>
                    <td>${requestScope.s.motherPhone}</td>
                </tr>





            </table>
        </div>
    </body>
</html>
