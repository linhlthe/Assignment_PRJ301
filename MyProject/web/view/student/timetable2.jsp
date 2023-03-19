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


                    <c:if test="${requestScope.student.groups eq null}">
                        <span> You have no activity from ${requestScope.from} to ${requestScope.to} </span>
                    </c:if>
                    <c:if test="${requestScope.student.groups ne null}">
                        <h4>Activities for ${sessionScope.user.username} from ${requestScope.from} to ${requestScope.to}: </h4>
                        <div><span>Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL...<br/>
                                Các phòng bắt đầu bằng BE thuộc tòa nhà Beta. VD: BE,..<br/>
                                Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201,...<br/>
                                Các phòng tập bằng đầu bằng R thuộc khu vực sân tập Vovinam.<br/>
                                Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE,..<br/>
                                Little UK (LUK) thuộc tầng 5 tòa nhà Delta</span></div>
                        <table border="1px" style="text-align: center"> 
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
                                                        <a href="/instructor/profile?instructor=${ses.instructor.id}">${ses.instructor.username}</a> <br/>at ${ses.room.roomName} <br/>
                                                        <c:if test="${g.eduNextURL ne null}"><button name="button" type="button" onclick="window.location.href = '${g.eduNextURL}'" style="background-color: #00bebc"><font color="white">EduNext</font></button></c:if> <button name="button" type="button" onclick="window.location.href = '${g.meetURL}'" style="background-color: grey"><font color="white">Meet URL</font></button><br/>
                                                        <fmt:formatDate type = "time" timeStyle = "short" value = "${ses.slot.startTime}" /> - <fmt:formatDate type = "time" timeStyle = "short" value = "${ses.slot.endTime}" /> <br/>
                                                        <c:if test="${ses.taken}">
                                                            <c:forEach items="${requestScope.atts}" var="att">
                                                                <c:if test="${att.session.sessionID eq ses.sessionID}">
                                                                    <c:if test="${att.status}">
                                                                        <font color="green">attended</font>    
                                                                    </c:if>
                                                                    <c:if test="${att.status eq false}">
                                                                        <font color="red">absent</font>    
                                                                    </c:if>
                                                                </c:if>
                                                            </c:forEach>
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
