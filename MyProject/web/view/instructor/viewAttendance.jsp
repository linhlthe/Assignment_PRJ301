<%-- 
    Document   : viewAttendance
    Created on : Mar 15, 2023, 8:06:51 AM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <table border="1px">
            <tr style="background-color:#00bebc">
                <td>No</td>
                <td>Student Code</td>
                <td>SurName</td>
                <td>MiddleName</td>
                <td>GivenName</td>
                <td>Status</td>
                <td>Image</td>
                <td>Comment</td>
                <td>Recorded Time</td>
            </tr>
            <c:forEach items="${requestScope.atts}" var="a" varStatus="loop">
                <tr>

                    <td>${loop.index +1}</td>
                    <td>${a.student.studentCode}</td>
                    <td>${a.student.surname}</td>
                    <td>${a.student.middlename}</td><!-- comment -->
                    <td>${a.student.givenname}</td><!-- comment -->

                    <td>

                        <c:if test="${a.status}">
                            <font color="green">attended</font>
                        </c:if>


                        <c:if test="${a.status eq false}">
                            <font color="red">absent</font>
                        </c:if>

                    </td>
                    <td><img src="${a.student.image}" width="120px" height="160px" alt=""/></td>
                    <td>${a.comment}</td>
                    <td>${a.recordedTime}</td>
                </tr>    

            </c:forEach>
        </table>


    </body>
</html>