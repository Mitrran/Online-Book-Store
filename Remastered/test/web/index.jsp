<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="index.css">
        <title>JSP Page</title>
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
        
    <div class="container">
        <div class="box-inner">
            <div class="book books-1 book-blur"></div>
        </div>
            <div class="message-box">
                <h1>21 Store</h1>
                <h2>Books and Fictions</h2>
            <div class="buttons-con">
                 <div class="action-link-wrap">
                 <a href="registration.jsp" class="link-button link-back-button">Registration</a>
                 <a href="login.jsp" class="link-button">Login</a>
            </div>
          </div>
        </div>
    </div>

    </body>
</html>
