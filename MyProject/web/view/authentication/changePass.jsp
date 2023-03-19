<%-- 
    Document   : changePass
    Created on : Mar 19, 2023, 9:07:24 PM
    Author     : DELL
--%>

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
        <div class="form">
            <table>
                <tr>
                    <td>
                        <form action="changePassword" method="POST">

                            <div class="input-form">
                                <span>Old Password</span>
                                <input type="password" name="oldpassword">
                            </div>
                            <div class="input-form">
                                <span>New Password</span>
                                <input type="password" name="newpassword">
                            </div>
                            <div class="input-form">
                                <span>Re-Enter Password</span>
                                <input type="password" name="repassword">
                            </div>

                            <div class="input-form">
                                <input type="submit" value="Change">
                            </div>



                        </form>
                    </td>
                    <td width="50px"></td>
                    <td style="background-color: yellow ; margin-left: 150px" > <b>Note:</b>
                        Password must meet complexity requirements:
                        <ul>
                            <li>Be at least eight characters in length</li>
                            <li>Contain english uppercase characters (A through Z)</li>
                            <li>Contain english lowercase characters (a through z)</li>
                            <li>Contain base 10 digits (0 through 9)</li>
                            <li>Non-alphabetic characters (for example, !, $, #, %)</li>
                        </ul> 
                    </td>
                </tr>
            </table>
            <font color="red">${requestScope.error}</font>  
        </div>
    </body>
</html>
