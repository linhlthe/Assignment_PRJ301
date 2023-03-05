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
        <link href="././css/login.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <header>
            <img src="././img/logo.png" alt=""width="170px" height="50px"/>
            <div class="information">
                <div class="username"> ${sessionScope.user.username}</div><div class="campus"> ${sessionScope.user.campus}</div><div class="logout"> <a href="logout">logout</a></div>
            </div>

        </header>

        <section>

            <a href=""/>Weekly timetable <br><!-- comment -->
            <a href=""/>Check attendance<br>

        </section>
        <footer>
            <div class="end">
                Powered by <a href="https://fpt.edu.vn/"> FPT Univerity </a> | <a href="https://cmshn.fpt.edu.vn/"> CMS </a> | <a href="https://cmshn.fpt.edu.vn/"> library </a> | <a href="https://cmshn.fpt.edu.vn/"> book24x7 </a> 
            </div>
        </footer>
    </body>
</html>
