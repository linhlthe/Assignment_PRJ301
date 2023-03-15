<%-- 
    Document   : checkAttendance
    Created on : Mar 15, 2023, 3:10:26 AM
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
    </head>
    <body>
        <table border="1px">
            <tr style="background-color:#00bebc">

                <th width="20px">NO</th>
                <th width="150px">SLOT</th>
                <th width="80px">DATE</th>
                <th width="300px">COURSE</th>
                <th width="60px">GROUP</th>
                <th width="60px">ROOM</th>
                <th width="30px">STATUS</th>
                <th colspan="2"> </th> 
                

            </tr>
            <c:forEach items="${requestScope.sessions}" var="ses" varStatus="loop">
                <tr>

                    <td>${loop.index +1}</td><!-- comment -->
                    <td>
                        <fmt:formatDate type = "time" timeStyle = "short" value = "${ses.slot.startTime}" /> - <fmt:formatDate type = "time" timeStyle = "short" value = "${ses.slot.endTime}" /></td>
                    <td><fmt:formatDate pattern="dd/MM/YYYY" value="${ses.date}" /></td>
                    <td>${ses.group.course.courseName} (${ses.group.course.courseCode})</td>
                    <td>${ses.group.groupName}</td>
                    <td>${ses.room.roomName}</td>
                    <td>
                        <c:if test="${ses.taken}">
                             Taken   
                        </c:if>
                        <c:if test="${ses.taken eq false}">
                            <a href="checkAttendance?id=${ses.sessionID}"/>Take  
                        </c:if>
                    </td>
                    
                        <c:if test="${ses.taken}">

                           <td width="40px"> <a href="viewAttendance?id=${ses.sessionID}"/>View  </td>
                           <td width="40px"> <a href="checkAttendance?id=${ses.sessionID}"/>Edit  </td> 
                        </c:if>
                    <c:if test="${ses.taken eq false}">

                           <td colspan="2"> </td> 
                        </c:if>
                    
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
