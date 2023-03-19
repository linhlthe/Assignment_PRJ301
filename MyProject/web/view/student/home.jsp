<%-- 
    Document   : home
    Created on : Feb 26, 2023, 9:50:16 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="../../css/style.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <jsp:include page="../template/header.jsp"></jsp:include>
            <section>
                <div style="margin-left: 50px; margin-top: 20px">
                    <table>
                        <tr><a href="student/WeeklySchedule"/>Weekly timetable</a><br/></tr>
                        <tr><a href="student/timetable"/>View Schedule</a><br/></tr>
                        <tr><a href="student/reportAttendance?term=-1&group=-1"/>Attendance Report</a><br/></tr>
                        <tr><a href="student/profile"/>Student Profile</a><br/></tr>
                        <tr><a href="/changePassword"/>Change Password</a></tr>

                    </table>
                </div>



            </section>
        <jsp:include page="../template/footer.jsp"></jsp:include>
    </body>
</html>
