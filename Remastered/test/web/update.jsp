<%@page import="java.util.Base64"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="controller.Product" %>
<%@ page import="controller.ProductDAO" %>

<%
// retrieve the product ID from the request parameter
int productId = Integer.parseInt(request.getParameter("id"));

// retrieve the product from the database
ProductDAO dao = new ProductDAO();
Product product = dao.getProduct(productId);
%>

<html>
<head>
    <title>Update Product</title>
    <link rel="stylesheet" type="text/css" href="update.css">
</head>
<body>
    
    <div class="bookcontainer">
        <h1>Update Product</h1>
   
    <div class ="book-form">
                        
        <form method="post" action="UpdateProductServlet" enctype="multipart/form-data">

            <input type="hidden" name="id" value="<%= product.getId() %>">
            <input type="hidden" name="currentImage" value="<%= Base64.getEncoder().encodeToString(product.getImage()) %>">

            <label class="label">Name:</label>
            <input type="text" name="name" value="<%= product.getName() %>"><br><br>

            <label class="label">Author:</label>
            <input type="text" name="author" value="<%= product.getAuthor() %>"><br><br>

            <label class="label">Genre:</label>
            <input type="text" name="genre" value="<%= product.getGenre() %>"><br><br>

            <label class="label">Year:</label>
            <input type="text" name="year" value="<%= product.getYear() %>"><br><br>

            <label class="label">Price:</label>
            <input type="number" name="price" value="<%= product.getPrice() %>"><br><br>

            <label class="label">Current Image:</label>
            <% if (product.getImage() != null) { %>
            <img src="data:image/png;base64,<%=Base64.getEncoder().encodeToString(product.getImage())%>" alt="<%=product.getName()%> image" height="100" style="text-align: center;" >
            <br><br>
            <% } %>
            <div class="submit-file">
                <input type="file" class="button" name="image"><br><br>
            </div>
            <input type="submit" class="button" value="Update"><br><br>
            <input type="submit" href="display.jsp" class="button" value="View Book List!">

         </form>
                                       
     </div>
        
 
    </div>
    
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