<%-- 
    Document   : groupDetail
    Created on : Mar 18, 2023, 4:37:52 PM
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
            <table border =1px style="margin-bottom: 70px ;border : 1px solid #d9dddc">
                <tr>
                    <th width="120px">TERM</th>
                    <th width="300px">DEPARTMENT</th> 
                    <th width="300px">COURSE</th>   

                    <th width="120px">GROUP</th>  
                </tr>
                <tr>

                    <td valign='top'><table>

                            <tr>
                                <td valign='top'>
                                    <table>
                                        <c:forEach items="${requestScope.semesters}" var="s">
                                            <tr>
                                                <td><a href="/group/term?term=${s.termID}"><c:if test="${requestScope.selectedTerm eq s.termID}">
                                                            <font color="black"><u><b> ${s.termName}</b></u></font>    
                                                            </c:if>
                                                            <c:if test="${requestScope.selectedTerm ne s.termID}">
                                                                ${s.termName}
                                                            </c:if>
                                                    </a> </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </table></td>
                    <td valign='top'><table>

                            <tr>
                                <td valign='top'>
                                    <table>
                                        <c:forEach items="${requestScope.depts}" var="d">
                                            <tr>
                                                <td>
                                                    ${d.departmentName}

                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </table></td>
                    <td valign='top'><table>

                            <tr>
                                <td valign='top'>
                                    <table>
                                        <c:forEach items="${requestScope.courses}" var="c">
                                            <tr>
                                                <td>${c.courseName}
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </table></td>
                    <td valign='top'><table>

                            <tr>
                                <td valign='top'>
                                    <table>
                                        <c:forEach items="${requestScope.groups}" var="g">
                                            <tr>
                                                <td>${g.groupName} </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </table></td>
                </tr>
            </table>
            <table border="1px" style="text-align: center">
                <tr><th width="50px">NO</th>
                    <th width="150px">STUDENT CODE</th>
                    <th width="150px">SURNAME</th>
                    <th width="150px">MIDDLENAME</th>
                    <th width="150px">GIVENNAME</th><!-- comment -->
                    <th width="125px">IMAGE</th>
                </tr>
                <c:forEach items="${requestScope.students}" var="stu" varStatus="loop">
                    <tr>
                        <td>${loop.index +1}</td>
                        <td>${stu.studentCode}</td>
                        <td>${stu.surname}</td>
                        <td>${stu.middlename}</td><!-- comment -->
                        <td>${stu.givenname}</td><!-- comment -->
                        <td><img src="${stu.image}" alt="alt" width="120px" height="160px"/></td><!-- comment -->



                    </tr>

                </c:forEach>
            </table>


        </div>
    </body>
</html>
