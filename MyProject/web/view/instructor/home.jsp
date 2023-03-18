<%-- 
    Document   : home
    Created on : Feb 26, 2023, 9:50:16 PM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link href="../../css/style.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

        <jsp:include page="../template/header.jsp"></jsp:include>
            <section>
                <a href="instructor/weeklySchedule"/>Weekly timetable
                <a href="instructor/timetable"/>View Schedule 
                <a href="instructor/takeAttendance"/>Take Attendance 
            </section>
        <jsp:include page="../template/footer.jsp"></jsp:include>


    </body>
</html>

