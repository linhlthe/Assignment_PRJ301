<%-- 
    Document   : att
    Created on : Mar 15, 2023, 3:55:02 AM
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
        <form action="checkAttendance" method="POST"> 
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
                </tr>
                <c:forEach items="${requestScope.atts}" var="a" varStatus="loop">
                    <tr>

                        <td>${loop.index +1}</td>
                        <td>${a.student.studentCode}</td>
                        <td>${a.student.surname}</td>
                        <td>${a.student.middlename}</td><!-- comment -->
                        <td>${a.student.givenname}</td><!-- comment -->

                        <td>
                            <input type="radio" 
                                   <c:if test="${!a.status}">
                                       checked="checked" 
                                   </c:if>
                                   name="status${a.student.id}" value="absent"/> Absent
                            <input type="radio" 
                                   <c:if test="${a.status}">
                                       checked="checked" 
                                   </c:if>
                                   name="status${a.student.id}" value="present" /> Present
                        </td>
                    
                        <td><img src="../../img_student/4.png" alt=""/></td>
                        <td>

                            <input type="hidden" name="sid" value="${a.student.id}"/>
                            <input type="hidden" name="aid${a.student.id}" value="${a.aid}"/>
                            <input type="text" name="comment${a.student.id}" value="${a.comment}"/></td>
                    </tr>    

                </c:forEach>
            </table>
            <img src="../../img_student/5.png" alt=""/>
            <input type="hidden" name="sessionid" value="${requestScope.id}"/>
            <input type="submit" value="Save"/>
        </form>

    </body>
</html>
