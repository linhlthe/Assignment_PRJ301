<%-- 
    Document   : timetable
    Created on : Mar 9, 2023, 8:29:04 AM
    Author     : sonnt
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
        <form action="timetable" method="POST"> 
            
            From : <input type="date" name="from"/> <br/>
            To : <input type="date" name="to"/> <br/>
            <input type="hidden" name="studentID" value="${sessionScope.user.id}">
            
            <input type="submit" value="Search"/>
        </form>
            <c:if test="${requestScope.student ne null}">
        <table border="1px"> 
            <tr>
                <td></td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td><fmt:formatDate pattern="dd/MM" value="${d}" /><br/>
                    </td>
                </c:forEach>

            </tr>
            <c:forEach var = "i" begin = "1" end = "6">
                        <tr>
                            <td>Slot ${i} 
                            </td>
                            <c:forEach items="${requestScope.dates}" var="d">
                                <td>
                                    <c:forEach items="${requestScope.student.groups}" var="g">
                                        <c:forEach items="${g.sessions}" var="ses">

                                            <c:if test="${ses.date eq d and ses.slot.slotNum eq i}">
                                                ${g.groupName} - ${g.course.courseCode}<br/>
                                                ${ses.instructor.username} at ${ses.room.roomName} <br/>
                                                <fmt:formatDate type = "time" timeStyle = "short" value = "${ses.slot.startTime}" /> - <fmt:formatDate type = "time" timeStyle = "short" value = "${ses.slot.endTime}" /> <br/>
                                                <c:if test="${ses.taken}">
                                                    <font color="green">attended</font>   
                                                </c:if>
                                                <c:if test="${ses.taken eq false}">
                                                    <font color="red">Not yet</font>   
                                                </c:if>

                                            </c:if>

                                        </c:forEach>
                                    </c:forEach>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
 </c:if>
        </table>
    </body>
</html>
