<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="register.css">
    <title>Registration Form</title>
    <script>
            window.onload = function() {
                history.pushState(null, null, location.href);
                window.onpopstate = function() {
                    history.go(1);
                };
            };
        </script>
</head>
<body>
    <div class="registercontainer">
    <h2 class="register-title">Registration</h2><br>

    <form method="post" class="register-form" action = "RegisterServlet">

        <div>
            <label class="label">Full Name</label>
            <i class="fa fa-address-card"></i><br>
            <input type="text" placeholder="Enter full name" name="fname"><br><br>
        </div>


        <div>
            <label class="label">User Name</label>
            <i class="fa fa-user icon"></i><br>
            <input type="text" placeholder="Enter user name" name="uname"><br><br>          
        </div>


        <div>
            <label class="label">Email</label>
            <i class="fa fa-envelope"></i><br>
            <input type="text" placeholder="Enter email" name="email"><br><br> 
        </div>


        <div>
            <label class="label">Password</label>
            <i class="fa fa-key icon"></i><br>
            <input type="password" placeholder="Enter password" name="password" minlength="8" required><br><br> 
            <span style="display: none;">Enter Minimum 8 character</span>          
        </div>

        <button class="button" input type="submit" value="Register">Register</button>
        <a href="index.jsp" class="button">Back to Dashboard</a>

    </form>
    
</div>


    
</body>
</html>
