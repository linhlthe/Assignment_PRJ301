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




                        <input type="hidden" name="studentID" value="${sessionScope.user.id}">

                    <input type="submit" value="Search"><br><!-- comment -->
                </form>
            </div>
            <div>
                <c:if test="${requestScope.inform ne null}">
                    <h4>Activities for ${sessionScope.user.username} from ${requestScope.from} to ${requestScope.to}: </h4>

                    <c:if test="${requestScope.student.groups eq null}">
                        <span> You have no activity from ${requestScope.from} to ${requestScope.to} </span>
                    </c:if>
                    <c:if test="${requestScope.student.groups ne null}">
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
                                            <c:forEach items="${requestScope.student.groups}" var="g">
                                                <c:forEach items="${g.sessions}" var="ses">

                                                    <c:if test="${ses.date eq d and ses.slot.slotNum eq i}">
                                                        <a href="/group/groupDetail?group=${g.groupID}">${g.groupName}</a> <a href="/course/courseDetail?course=${ses.group.course.courseID}">${ses.group.course.courseCode}</a><br/>
                                                        ${ses.instructor.username} <br/> ${ses.room.roomName} <br/>

                                                        <c:if test="${att.session.sessionID eq ses.sessionID}">
                                                            <c:if test="${att.status}">
                                                                <font color="green">attended</font>    
                                                            </c:if>
                                                            <c:if test="${att.status eq false}">
                                                                <font color="red">absent</font>    
                                                            </c:if>
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
                    </c:if>
                </table>
            </div>
    </body>
</html>
