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
             <h1> Activities for ${sessionScope.user.username}</h1>
            <div><span>Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL...<br/>
                    Các phòng bắt đầu bằng BE thuộc tòa nhà Beta. VD: BE,..<br/>
                    Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201,...<br/>
                    Các phòng tập bằng đầu bằng R thuộc khu vực sân tập Vovinam.<br/>
                    Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE,..<br/>
                    Little UK (LUK) thuộc tầng 5 tòa nhà Delta</span></div>
            <div id="weeklytimetable">
                <table border="1px" width="1200px" style="text-align: center">
                    <tr>

                        <th rowspan='2'><font color="white">
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
                    </th>
                    <th>MON</th>
                    <th>TUE</th>
                    <th>WED</th>
                    <th>THU</th>
                    <th>FRI</th>
                    <th>SAT</th>
                    <th>SUN</th>
                </tr>
                <tr style="background-color:#00bebc">
                    <c:forEach items="${requestScope.dates}" var="d">
                        <th><fmt:formatDate pattern="dd/MM" value="${d}" /> </th>
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
                                        <a href="/group/groupDetail?group=${ses.group.groupID}">${ses.group.groupName}</a> - <a href="/course/courseDetail?course=${ses.group.course.courseID}">${ses.group.course.courseCode}</a><br/>
                                        at ${ses.room.roomName} <br/>
                                        <c:if test="${ses.group.eduNextURL ne null}"><button name="button" type="button" onclick="window.location.href = '${ses.group.eduNextURL}'" style="background-color: #00bebc"><font color="white">EduNext</font></button></c:if> <button name="button" type="button" onclick="window.location.href = '${ses.group.meetURL}'" style="background-color: grey"><font color="white">Meet URL</font></button><br/>
                                        
                                        <fmt:formatDate type = "time" timeStyle = "short" value = "${ses.slot.startTime}" /> - <fmt:formatDate type = "time" timeStyle = "short" value = "${ses.slot.endTime}" /> <br/>
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
