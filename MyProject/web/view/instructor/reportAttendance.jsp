<%-- 
    Document   : reportAttendance
    Created on : Mar 18, 2023, 10:59:46 PM
    Author     : DELL
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dal.StudentDBContext"%>
<%@page import="model.Student"%>
<%@page import="model.Group"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../../css/style.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../template/header.jsp"></jsp:include>
            <div class="row">
                <table border="1px"><tr><td></td>
                        <c:forEach items="${requestScope.dates}" var="d">
                        <th><fmt:formatDate pattern="dd/MM" value="${d}" /></th>
                    </c:forEach></tr>
                    <c:forEach items="${requestScope.students}" var="s">
                    <tr <c:if test="${s.attendanceExemption}"> style="background-color: hsl(0, 0%, 75%,0.5)" </c:if>><td>${s.surname} ${s.middlename} ${s.givenname}<br/> (${s.studentCode})<br/><!-- comment -->
                            
                        </td>
                            <c:forEach items="${requestScope.dates}" var="d">
                            <td style="text-align: center" width="50px">

                                <c:forEach items="${requestScope.attends}" var="a">
                                    <c:if test="${a.session.date eq d && a.student.id eq s.id}">
                                        <c:if test="${a.session.taken eq false}">
                                            -
                                        </c:if>
                                        <c:if test="${a.session.taken}">

                                            <c:if test="${a.status}">

                                                <font color="green">P</font> 

                                            </c:if>
                                            <c:if test="${a.status eq false}">
                                                <font color="red">A</font> 

                                            </c:if>
                                        </c:if>
                                    </c:if>


                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </body>
</html>
