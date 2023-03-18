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
                <tr><td>Student Code</td>
                    <td>${requestScope.s.studentCode}</td></tr>
                <tr><td>Surname</td>
                    <td>${requestScope.s.surname}</td></tr><!-- comment -->
                <tr><td>Middlename</td>
                    <td>${requestScope.s.middlename}</td></tr><!-- comment -->
                <tr><td>Givenname</td>
                    <td>${requestScope.s.givenname}</td></tr><!-- comment -->
                <tr><td>Email</td>
                    <td>${requestScope.s.email}</td></tr><!-- comment -->
                <tr><td>Gender</td>
                    <td><c:if test="${requestScope.s.gender}">
                            Female
                        </c:if>
                        <c:if test="${!requestScope.s.gender}">
                            Male
                        </c:if></td></tr><!-- comment -->
                <tr><td>Date Of Birth</td>
                    <td>${requestScope.s.dob}</td></tr>
                <tr><td>Nationality</td>
                    <td>${requestScope.s.nationality}</td></tr><!-- comment -->
                <tr><td>Campus</td>
                    <td>${requestScope.s.campus}</td></tr><!-- comment -->
                <tr><td>Address</td>
                    <td>${requestScope.s.address}</td></tr>
                <tr><td>Phone</td>
                    <td>${requestScope.s.phoneNumber}</td></tr><!-- comment -->
                <tr><td>ID Card</td>
                    <td>${requestScope.s.idCard}</td></tr>
                <tr><td>Curriculum</td>
                    <td>${requestScope.s.curriculum}</td></tr><!-- comment -->
                <tr><td>Place Of Birth</td>
                    <td>${requestScope.s.placeOfBirth}</td></tr>
                <tr><td>Major</td>
                    <td>${requestScope.s.major}</td></tr><!-- comment -->
                <tr><td>Peoples</td>
                    <td>${requestScope.s.peoples}</td></tr>
                <tr><td>Father Name</td>
                    <td>${requestScope.s.fatherName}</td></tr>
                <tr>
                    <td>Mother Name</td>
                    <td>${requestScope.s.motherName}</td>
                </tr>
                <tr><td>Father Job</td>
                    <td>${requestScope.s.fatherJob}</td></tr>
                <tr>
                    <td>Mother Job</td>
                    <td>${requestScope.s.motherJob}</td>
                </tr><!-- comment -->
                <tr><td>Father Phone</td>
                    <td>${requestScope.s.fatherPhone}</td></tr>
                <tr>
                    <td>Mother Phone</td>
                    <td>${requestScope.s.motherPhone}</td>
                </tr>





            </table>
        </div>
    </body>
</html>
