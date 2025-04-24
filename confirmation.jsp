<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Attendance Submission</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }
        .success { color: green; }
        .error { color: red; }
        a { display: inline-block; margin-top: 20px; }
    </style>
</head>
<body>
    <% String status = request.getParameter("status"); %>
    <% if ("success".equals(status)) { %>
        <h1 class="success">Attendance Recorded Successfully!</h1>
    <% } else { %>
        <h1 class="error">Error Recording Attendance</h1>
        <p>Please try again or contact support.</p>
    <% } %>
    <a href="attendance.jsp">Record Another Attendance</a>
</body>
</html>
