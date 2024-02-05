<%-- 
    Document   : dashboard
    Created on : 9 May 2023, 9:50:57 pm
    Author     : imran
--%>

<%@page import="controller.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="controller.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="dashboard.css">
        <title>Dashboard</title>
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

            <h2 class="register-title">Dashboard</h2>

            <form method="" class="register-form" action = "">
                <a href="add.jsp" class="button">Add a Book</a>
                <a href="display.jsp" class="button">View Book List</a>
                <a href="logout.jsp" class="button">Logout</a>

            </form>
        </div>

    </body>
</html>
