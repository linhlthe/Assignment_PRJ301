<%-- 
    Document   : courseDetail
    Created on : Mar 18, 2023, 2:48:40 AM
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
            <table border="1px" width="1200px"><tr>
                    <td>Course ID</td>
                    <td>${requestScope.course.courseID}</td>
                </tr>
                <tr>
                    <td>Course Code</td>
                    <td>${requestScope.course.courseCode}</td>
                </tr>
                <tr>
                    <td>Course Name</td>
                    <td>${requestScope.course.courseName}</td>
                </tr><!-- comment -->
                <tr>
                    <td>Department</td>
                    <td>${requestScope.course.department.departmentName}</td>
                </tr><!-- comment -->
                <tr>
                    <td>No Credit</td>
                    <td>${requestScope.course.noCredit}</td>
                </tr><!-- comment -->
                <tr>
                    <td>Time allocation</td>
                    <td>${requestScope.course.timeAllocation}</td>
                </tr><!-- comment -->
                <tr>
                    <td>Description</td>
                    <td>${requestScope.course.decription}</td>
                </tr>
                <tr>
                    <td>Student Task</td>
                    <td>${requestScope.course.studentTask}</td>
                </tr>
                <tr>
                   
                    <td>Tools</td>
                    <td>${requestScope.course.tool}</td>
                </tr>
                <tr>
                    <td>Scoring Scale</td>
                    <td>${requestScope.course.scoringScale}</td>
                </tr><!-- comment -->
                <tr>
                    <td>Decision No</td>
                    <td>${requestScope.course.decisionNo}</td>
                </tr>
                <tr>
                    <td>Is Approved</td>
                    <td>${requestScope.course.isApproved}</td>
                </tr>
                <tr>
                    <td>Note</td>
                    <td>${requestScope.course.note}</td>
                </tr><!-- comment --><tr>
                    <td>MinAvgToPass</td>
                    <td>${requestScope.course.minAvgMarkToPass}</td>
                </tr>
            </table>
        </div>
    </body>
</html>
