<%-- 
    Document   : header
    Created on : Mar 17, 2023, 8:59:48 PM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <img src="/img/logo.png" width="170px" height="50px"/>

        </header>
        <div class="information">
            <div class="userinfor" style="float: right; margin-right: 16px;">
                <button name="button1" type="button" onclick=""><font color="white">${sessionScope.user.username}</font> </button>
                <button name="button1" type="button" onclick=""><font color="white">Campus: ${sessionScope.user.campus} </font></button><!-- comment -->
                <button name="button1" type="button" onclick="window.location.href = '/logout'"><font color="white">logout</font></button>  <br/> 
            </div>
            <div class="homelink" style="padding-top: 8px;">

                <a href="/home">Home</a> |<span> ${requestScope.address}<!-- comment --></span>

            </div>

        </div>
    </body>
</html>
