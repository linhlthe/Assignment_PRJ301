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
    </head>
    <body>

        <div id="weeklytimetable">
            <table border="1px">
                <tr style="background-color:#00bebc">

                    <td rowspan='2'><font color="white">
                        <form action="timetable" method="POST"> 
                            <div class="enter">

                                <div id="selectedyear"><!-- comment -->
                                    YEAR <select name="txt" onchange="showWeek(this.value)" > 

                                        <c:forEach items="${requestScope.listYear}" var="year">
                                            <option value="${year} "  <c:if test="${requestScope.currentyear eq year}">selected="selected"</c:if>>${year}</option> 

                                        </c:forEach>
                                    </select>
                                </div>
                                <div id="selectedweek">
                                    From-To    <select name="fromTo" onchange="showtimetable(this.value)"> 


                                        <c:forEach items="${requestScope.listWeek}" var="w">
                                            <option value = "${w}"
                                                    <c:if test="${w.start eq requestScope.currentweek}">
                                                        selected="selected"</c:if>>

                                                    ${w.start} - ${w.end}
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
                <tr>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>${d}</td>
                    </c:forEach>

                </tr>
                <c:forEach items="${requestScope.slots}" var="slot"> 
                    <tr>
                        <td>${slot.slotNum}</td>
                        <c:forEach items="${requestScope.dates}" var="d">
                            <td>
                                <c:forEach items="${requestScope.s.groups}" var="g">
                                    <c:forEach items="${g.sessions}" var="ses">
                                        <c:if test="${ses.date eq d and ses.slot.slotNum eq slot.slotNum}">
                                            ${g.groupName}(${g.course.code}) <br/>
                                            ${ses.instructor.username}-${ses.room.roomName} <br/>
                                            <c:if test="${ses.taken}">
                                                attended
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

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script>
                                        function showWeek(str) {
                                            if (str == "") {
                                                document.getElementById("selectedweek").innerHTML = "";
                                                return;
                                            }
                                            const xhttp = new XMLHttpRequest();
                                            xhttp.onload = function () {
                                                document.getElementById("selectedweek").innerHTML = this.responseText;
                                            }
                                            xhttp.open("GET", "getweek?txt=" + str);
                                            xhttp.send();
                                        }
                                        function showtimetable(str) {
                                            if (str == "") {
                                                document.getElementById("fromTo").innerHTML = "";
                                                return;
                                            }
                                            const xhttp = new XMLHttpRequest();
                                            xhttp.onload = function () {
                                                document.getElementById("selectedweek").innerHTML = this.responseText;
                                            }
                                            xhttp.open("GET", "getweek?txt=" + str);
                                            xhttp.send();
                                        }
        </script>  
    </body>
</html>
