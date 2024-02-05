

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
      <link rel="stylesheet" type="text/css" href="add.css">

<head>
      <title>Add Product</title>
</head>
<body>
   
  <div class="bookcontainer">
    <h1>Add Product</h1>
        <form method="post" action="AddProductServlet" enctype="multipart/form-data">
            
            <div class="book-form">
      
                <label class = "label">Book Title:</label>
                <input type="text" name="name" placeholder="Enter Book Title" required><br>

                <label class = "label">Author</label>
                <input type="text" name="author" placeholder="Enter Book Author" required><br>

                <label class = "label">Genre</label>
                <input type="text" name="genre" placeholder="Enter Book Genre" required><br>

                <label class = "label">Year</label>
                <input type="text" name="year" placeholder="Enter Year Published" required><br>

                <label class = "label">Price:</label>
                <input type="text" name="price" placeholder="Enter Book Price" required><br>

                <label class = "label">Image:</label>
                <input type="file" name="image" required><br>

                <button class = "button" button type="submit">Add Books</button>
                <a href="display.jsp" class="button">View Products</a>
                <a href="dashboard.jsp" class="button">Back To Dashboard</a>

        
            </form>
        </div>
 </div>
  

      
      <%
          String message = request.getParameter("message");
          if (message != null && !message.isEmpty()) {
      %>
      <p><%= message %></p>
      <% } %>
      
      <% String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
        %>
        <script>
            alert("<%=successMessage%>");
        </script>
        <% 
               session.removeAttribute("successMessage"); // remove the attribute after showing it
           }
        %>
      
      <script>
          var inputs = document.querySelectorAll('input, textarea, select');
          for (var i = 0; i < inputs.length; i++) {
              inputs[i].addEventListener('invalid', function(e) {
                  e.target.setCustomValidity("");
                  if (!e.target.validity.valid) {
                      e.target.setCustomValidity("All fields are required.");
                  }
              });
          }
      </script>
      
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
