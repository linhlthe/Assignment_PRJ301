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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-2">
                    <table>
                        <tr style="background-color:#00bebc">
                            <td>
                                <c:forEach items="${requestScope.semesters}" var="ses">
                                    <a href="?termID=${ses.sessionID}"/>${ses.termName} <br>
                                </c:forEach>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="col-sm-4">
                    <table>
                        <tr style="background-color:#00bebc">
                            <td>
                                <c:forEach items="${requestScope.groups}" var="g">
                                    ${g.course.courseName} (${g.course.courseCode}) - ${g.groupName} <br>
                                </c:forEach>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="col-sm-4">
                    <h3>Column 3</h3>        
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
                    <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
                </div>
            </div>
        </div>

    </body>
</html>

</body>
</html>
