<%-- 
    Document   : timetable2
    Created on : Mar 15, 2023, 1:01:24 AM
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
                <div>
                    <form action="timetable" method="POST"> 


                        From :
                        <input type="date" name="from">


                        To   :
                        <input type="date" name="to"><br/>




                        <input type="hidden" name="instructorID" value="${sessionScope.user.id}">

                    <input type="submit" value="Search"><br><!-- comment -->
                </form>
            </div>
            <div>
                <c:if test="${requestScope.inform ne null}">
                    <h4>Activities for ${sessionScope.user.username} from ${requestScope.from} to ${requestScope.to}: </h4>

                    <c:if test="${requestScope.instructor.sessions eq null}">
                        <span> You have no activity from ${requestScope.from} to ${requestScope.to} </span>
                    </c:if>
                    <c:if test="${requestScope.instructor.sessions ne null}">


                        <table border="1px"> 
                            <tr>
                                <th></th>
                                    <c:forEach items="${requestScope.dates}" var="d">
                                    <th><fmt:formatDate pattern="dd/MM" value="${d}" /><br/>
                                    </th>
                                </c:forEach>

                            </tr>
                            <c:forEach var = "i" begin = "1" end = "6">
                                <tr>
                                    <td>Slot ${i} 
                                    </td>
                                    <c:forEach items="${requestScope.dates}" var="d">
                                        <td>
                                            <c:forEach items="${requestScope.instructor.sessions}" var="ses">



                                                <c:if test="${ses.date eq d and ses.slot.slotNum eq i}">
                                                    <a href="/group/groupDetail?group=${ses.group.groupID}">${ses.group.groupName}</a>  <a href="/course/courseDetail?course=${ses.group.course.courseID}">${ses.group.course.courseCode}</a><br/>
                                                    ${ses.room.roomName} <br/>

                                                    <c:if test="${ses.taken}">
                                                        <font color="green">attended</font>   
                                                    </c:if>
                                                    <c:if test="${ses.taken eq false}">
                                                        <a href="checkAttendance?id=${ses.sessionID}&instructor=${sessionScope.user.id}"/>Take attendance  
                                                    </c:if>

                                                </c:if>


                                            </c:forEach>
                                        </td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                </c:if>
            </div>

        </div>
    </body>
</html>


