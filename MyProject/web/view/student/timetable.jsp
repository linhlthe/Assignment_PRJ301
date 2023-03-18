<%-- 
    Document   : timetable
    Created on : Mar 13, 2023, 12:22:18 AM
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

        <div id="weeklytimetable">
            <table border="1px" width="1200px">
                <tr style="background-color:#00bebc">

                    <td rowspan='2'><font color="white">
                        <form action="timetable" method="POST"> 
                            <div class="enter">

                                <div id="selectedyear"><!-- comment -->
                                    YEAR <select name="year" onchange="showWeek(this.value)" > 

                                        <c:forEach items="${requestScope.listYear}" var="year">
                                            <option value="${year} "  <c:if test="${requestScope.currentyear eq year}">selected="selected"</c:if>>${year}</option> 

                                        </c:forEach>
                                    </select>
                                </div>
                                <div id="selectedweek">
                                    From-To    <select name="fromTo" onchange="showtimetable(this.value)"> 


                                        <c:forEach items="${requestScope.listWeek}" var="w">
                                            <option value = "${w.start}"
                                                    <c:if test="${w.start eq requestScope.currentweek}">
                                                        selected="selected"</c:if>>

                                                    <fmt:formatDate pattern="dd/MM" value="${w.start}" />  -  <fmt:formatDate pattern="dd/MM" value="${w.end}" /> 

                                            </option> 
                                        </c:forEach>

                                    </select>
                                </div>
                            </div>
                        </form><!-- comment -->
                    </td>
                    <td>MON</td>
                    <td>TUE</td>
                    <td>WED</td>
                    <td>THU</td>
                    <td>FRI</td>
                    <td>SAT</td>
                    <td>SUN</td>
                </tr>
                <tr style="background-color:#00bebc">
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td><fmt:formatDate pattern="dd/MM" value="${d}" /> </td>
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
                                            <a href="/group/groupDetail?group=${g.groupID}">${g.groupName}</a> - <a href="/course/courseDetail?course=${ses.group.course.courseID}">${ses.group.course.courseCode}</a><br/>
                                            ${ses.instructor.username} at ${ses.room.roomName} <br/>
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


            </table>
        </div>
       
</div>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script>
                                        function showWeek(str) {
                                            if (str == "") {
                                                document.getElementById("weeklytimetable").innerHTML = "";
                                                return;
                                            }
                                            const xhttp = new XMLHttpRequest();
                                            xhttp.onload = function () {
                                                document.getElementById("weeklytimetable").innerHTML = this.responseText;
                                            }
                                            xhttp.open("GET", "getListWeekOfYear?year=" + str);
                                            xhttp.send();
                                        }
                                        function showtimetable(str) {
                                            if (str == "") {
                                                document.getElementById("weeklytimetable").innerHTML = "";
                                                return;
                                            }
                                            const xhttp = new XMLHttpRequest();
                                            xhttp.onload = function () {
                                                document.getElementById("weeklytimetable").innerHTML = this.responseText;
                                            }
                                            xhttp.open("GET", "getTimeTableBySelectedWeek?fromTo=" + str);
                                            xhttp.send();
                                        }
        </script>  
        
    </body>
</html>
