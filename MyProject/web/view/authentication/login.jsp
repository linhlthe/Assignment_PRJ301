<%-- 
    Document   : login
    Created on : Feb 26, 2023, 8:22:28 PM
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
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link href="../../css/login.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <header>
            <img src="../../img/logo.png" alt=""style="width: 170 px; height: 50px"/>
        </header>

        <section>

            <div class="img-bg">
                <img src="../../img/backgroung.jpg" alt=""/>

            </div>

            <div class="noi-dung">
                <div class="form">
                    <form action="login" method="POST">
                        <div class="input-form">
                            <span>Username</span>
                            <input type="text" name="username">
                        </div>
                        <div class="input-form">
                            <span>Password</span>
                            <input type="password" name="password">
                        </div>

                        <div class="nho-dang-nhap">
                            <label><input type="checkbox" name="nhơ"> Nhớ Đăng Nhập</label>
                        </div>
                        <div class="dang-nhap-sai" style="color:red">

                            ${requestScope.error}

                        </div>

                        <div class="input-form">
                            <input type="submit" value="Login">
                        </div>

                    </form>

                </div>
            </div>

        </section>
        <footer>
            <div class="end">
                Powered by <a href="https://fpt.edu.vn/"> FPT Univerity <a> | <a href="https://cmshn.fpt.edu.vn/"> CMS <a> | <a href="https://cmshn.fpt.edu.vn/"> library <a> | <a href="https://cmshn.fpt.edu.vn/"> book24x7 <a> 
                                                </div>
                                                </footer>
                                                </body>
                                                </html>
