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
                        <th width="400px">COURSE</th>   

                        <th width="120px">GROUP</th>  
                    </tr>
                    <tr>

                        <td valign='top'>
                            <table width="118px">
                            <c:forEach items="${requestScope.semesters}" var="s">
                                <tr height="23px">
                                    <td><a href="/group/groupDetail?term=${s.termID}"><c:if test="${requestScope.selectedTerm.termID eq s.termID}">
                                                <font color="black"><u><b> ${s.termName}</b></u></font>    
                                                </c:if>
                                                <c:if test="${requestScope.selectedTerm.termID ne s.termID}">
                                                    ${s.termName}
                                                </c:if>
                                        </a> </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                    <td valign='top'>
                        <table width="298px">
                            <c:forEach items="${requestScope.depts}" var="d">
                                <tr height="23px">
                                    <td>
                                        <a href="/group/groupDetail?term=${requestScope.selectedTerm.termID}&dept=${d.departmentID}"><c:if test="${requestScope.selectedDept.departmentID eq d.departmentID}">
                                                <font color="black"><u><b> ${d.departmentName}</b></u></font>    
                                                </c:if>
                                                <c:if test="${requestScope.selectedDept.departmentID ne d.departmentID}">
                                                    ${d.departmentName}
                                                </c:if>
                                        </a> 

                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                    <td valign='top'><table  width="400px">


                            <c:forEach items="${requestScope.courses}" var="c">
                                <tr height="23px">
                                    <td><a href="/group/groupDetail?term=${requestScope.selectedTerm.termID}&dept=${requestScope.selectedDept.departmentID}&course=${c.courseID}"><c:if test="${requestScope.selectedCourse.courseID eq c.courseID}">
                                                <font color="black"><u><b> ${c.courseName} (${c.courseCode})</b></u></font>    
                                                </c:if>
                                                <c:if test="${requestScope.selectedCourse.courseID ne c.courseID}">
                                                    ${c.courseName} (${c.courseCode})
                                            </c:if>
                                        </a> 
                                    </td>
                                </tr>
                            </c:forEach>

                        </table></td>
                    <td valign='top'>

                        <table style="text-align: center">
                            <c:forEach items="${requestScope.groups}" var="g">
                                <tr height="23px">
                                <a href="/group/groupDetail?group=${g.groupID}"><c:if test="${requestScope.selectedGroup.groupID eq g.groupID}">
                                        <font color="black"><u><b> ${g.groupName}</b></u></font>    
                                        </c:if>
                                        <c:if test="${requestScope.selectedGroup.groupID ne g.groupID}">
                                            ${g.groupName}
                                        </c:if>
                                </a> 
                    </tr>
                </c:forEach>
            </table>
        </td>

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
