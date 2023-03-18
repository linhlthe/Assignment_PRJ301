<%-- 
    Document   : reportAttendance
    Created on : Mar 15, 2023, 9:27:05 PM
    Author     : DELL
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
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
                <table style="margin-left: 10vw; margin-bottom: 70px ">
                    <tr>
                        <td valign='top'><table style="border : 1px solid #d9dddc">
                                <tr>
                                    <th width="120px">TERM</th>
                                    <th width="400px">COURSE</th>
                                </tr>
                                <tr>
                                    <td valign='top'>
                                        <table>
                                        <c:forEach items="${requestScope.semesters}" var="s">
                                            <tr>
                                                <td><a href="?term=${s.termID}&group=-1"><c:if test="${requestScope.selectedTerm eq s.termID}">
                                                            <font color="black"><u><b> ${s.termName}</b></u></font>    
                                                            </c:if>
                                                            <c:if test="${requestScope.selectedTerm ne s.termID}">
                                                                ${s.termName}
                                                            </c:if>
                                                    </a> </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                                <td valign='top'>
                                    <table>
                                        <c:forEach items="${requestScope.groups}" var="g">
                                            <tr>
                                                <td><a href="?term=${g.semester.termID}&group=${g.groupID}">
                                                        <c:if test="${requestScope.selectedGroup eq g.groupID}">
                                                            <font color="black"><u><b>${g.groupName} - ${g.course.courseName} (${g.course.courseCode})</b></u></font>    
                                                            </c:if>
                                                            <c:if test="${requestScope.selectedGroup ne g.groupID}">
                                                                ${g.groupName} - ${g.course.courseName} (${g.course.courseCode})
                                                        </c:if>
                                                    </a> </td>

                                            </tr>

                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </table></td>



                    <td valign='top'>
                        <c:if test="${requestScope.x ne 0}">
                            <table border =1px style="text-align: center">
                                <tr><th width="50px">NO</th>
                                    <th width="150px">SLOT</th>
                                    <th width="80px">DATE</th><!-- comment -->
                                    <th width="80px">ROOM</th><!-- comment -->
                                    <th width="60px">LECTURER</th><!-- comment -->
                                    <th width="70px">GROUP NAME</th><!-- comment -->
                                    <th width="70px">STATUS</th><!-- comment -->
                                    <th width="100px">COMMENT</th> </tr>

                                <c:forEach items="${requestScope.status}" var="a" varStatus="loop">
                                    <tr heigh="20px">
                                        <td>${loop.index +1}</td>
                                        <td>${a.session.slot.slotNum}<br/><!-- comment -->
                                            ${a.session.slot.startTime} - ${a.session.slot.endTime}</td>
                                        <td>${a.session.date}</td><!--<!-- comment -->
                                        <td>${a.session.room.roomName}</td><!-- comment -->
                                        <td>${a.session.instructor.username}</td><!-- comment -->
                                        <td>${a.session.group.groupName}</td><!-- comment -->
                                        <td>
                                            <c:if test="${a.session.taken}">
                                                <c:if test="${a.status}">
                                                    <font color="green">attended</font>    
                                                </c:if>
                                                <c:if test="${a.status eq false}">
                                                    <font color="red">absent</font>    
                                                </c:if>
                                            </c:if>
                                            <c:if test="${a.session.taken eq false}">
                                                <font color="black">Future</font>    

                                            </c:if>
                                        </td>
                                        <td>${a.comment}</td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan='8'>ABSENT: ${requestScope.percent}% ABSENT SO FAR (${requestScope.numOfAbsent} ABSENT ON ${requestScope.numOfSlot} TOTAL).</td>
                                    
                                </tr>

                            </table>
                        </c:if>
                    </td>

                </tr>
            </table>



        </div>

    </body>
</html>


