<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="login.css">
    <title>Login Form</title>
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
    <div class="logincontainer">
        <h2 class="login-title">User Login</h2><br>

        <form method="post" class="login-form" action="LoginServlet">
            <div>
                <label class="label">User Name</label>
                <i class="fa fa-user icon"></i><br>
                <input class="input" type="text" placeholder="Enter user name" name="uname" required><br><br>
            </div>
            <div>
                <label class="label">Password</label>
                <i class="fa fa-key icon"></i><br>
                <input class="input" type="password" placeholder="Enter password" name="password" id="pwd" required><br>
                <span style="display: none;">Enter Minimum 8 character</span>
            </div>
            <br>
            <button class="button" input type="submit" value="Login">Login</button>
            <a href="index.jsp" class="button">Back to Dashboard</a>

        </form>
    </div>
</body>
</html>
