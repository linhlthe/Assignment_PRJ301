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
        <link href="../../css/style.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>

        <jsp:include page="../template/header.jsp"></jsp:include>

            <div class="row">
                <table border="1px" style="margin-bottom: 70px">
                    <tr>

                        <th width="20px">NO</th>
                        <th width="150px">SLOT</th>
                        <th width="80px">DATE</th>
                        <th width="300px">COURSE</th>
                        <th width="60px">GROUP</th>
                        <th width="60px">ROOM</th>
                        <th width="30px">STATUS</th>
                        <th colspan="2"> </th> 


                    </tr>
                <c:forEach items="${requestScope.instructor.sessions}" var="ses" varStatus="loop">
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
                                <a href="checkAttendance?id=${ses.sessionID}&instructor=${sessionScope.user.id}"/>Take  
                            </c:if>
                        </td>

                        <c:if test="${ses.taken}">

                            <td width="40px"> <a href="viewAttendance?id=${ses.sessionID}"/>View  </td>
                            <td width="40px"> <a href="checkAttendance?id=${ses.sessionID}&instructor=${sessionScope.user.id}"/>Edit  </td> 
                        </c:if>
                        <c:if test="${ses.taken eq false}">

                            <td colspan="2"> </td> 
                        </c:if>

                    </tr>
                </c:forEach>
            </table>
            <div style="margin-left: 150px"><b>Report Attendance:</b>
                <ul> 
                        <c:forEach items="${requestScope.groups}" var="g">
                          <li>  <a href="/instructor/reportAttendance?group=${g.groupID}">${g.groupName} - ${g.course.courseName} (${g.course.courseCode})</a> </li>
                        </c:forEach>
                   


                </ul> 
            </div>

        </div>
    </body>
</html>
