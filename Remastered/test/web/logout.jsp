<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Logout</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-pzjw8w9Z3a5jk6Fh5yR78RnPJrTl3h6cqK8C2w1Pr0Jwau+6ZjgegR3pX1bhT1V5" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="logout.css">
</head>
    <body>
    <div class="container">
        <h2>Logout Successful</h2>
        <p>You have been logged out successfully.</p><br>
        <a href="login.jsp" class="button">Click here to login again</a>
    </div>

        <div class="container mt-4">
            <h3>User Activity Log:</h3>
            <pre>
        <%
            // Retrieve and display the user activity log
            ServletContext servletContext = request.getServletContext();
            List<String> activityLog = (List<String>) servletContext.getAttribute("activityLog");
            if (activityLog != null) {
                for (String log : activityLog) {
                    out.println(log);
                    out.println("<br>");
                }
            }
        %>
    </pre>

        </div>

    <script>
                window.onload = function() {
                    history.pushState(null, null, location.href);
                    window.onpopstate = function() {
                        history.go(1);
                    };
                };
    </script>
    
    </body>
</html>
