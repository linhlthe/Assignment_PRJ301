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
        <link href="../../css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <jsp:include page="../template/header.jsp"></jsp:include>
        <div class="row">
            <form action="checkAttendance" method="POST"> 
                <table border="1px" width="1200px" style="text-align: center">
                    <tr>
                        <th>NO</th>
                        <th>STUDENT CODE</th>
                        <th>SURNAME</th>
                        <th>MIDDLENAME</th>
                        <th>GIVENAME</th>
                        <th>STATUS</th>
                        <th width="120px">IMAGE</th>
                        <th>COMMENT</th>
                    </tr>
                <c:forEach items="${requestScope.atts}" var="a" varStatus="loop">
                    <tr <c:if test="${a.student.attendanceExemption}"> style="background-color: hsl(0, 0%, 75%,0.5)" </c:if>>

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

                        <td><img src="${a.student.image}" width="120px" height="160px" alt=""/></td>
                        <td>

                            <input type="hidden" name="sid" value="${a.student.id}"/>
                            <input type="hidden" name="aid${a.student.id}" value="${a.aid}"/>
                            <input type="text" name="comment${a.student.id}" value="${a.comment}"/></td>
                    </tr>    

                </c:forEach>
            </table>

            <input type="hidden" name="sessionid" value="${requestScope.id}"/>
            <input type="submit" value="Save"/>
        </form>
        </div>

    </body>
</html>
