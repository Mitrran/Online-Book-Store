
<%@page import="java.util.Base64"%>
<%@ page import="java.util.List"%>
<%@ page import="controller.Product"%>
<%@ page import="controller.ProductDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Book List</title>
    <link rel="stylesheet" type="text/css" href="display.css">
  </head>
  <body>
    <h2 class="display-title">Book List</h2>
    <div class="book-list">
      <% ProductDAO dao = new ProductDAO();
         List<Product> products = dao.getAllProducts();
         for (Product product : products) {
      %>
      <div class="book">
        <div class="book-image">
            <div class ="book-inner">
                <img src="data:image/png;base64,<%=Base64.getEncoder().encodeToString(product.getImage())%>" alt="<%=product.getName()%> image">
            </div>
            <div class="book-details">
                <p> <b>Book ID:</b> <%=product.getId()%></p>
                <p> <b>Book Title:</b> <%=product.getName()%></p>
                <p> <b>Book Author:</b> <%=product.getAuthor()%></p>
                <p> <b>Book Genre:</b><%=product.getGenre()%></p>
                <p> <b>Year Published:</b> <%=product.getYear()%></p>
                <p> <b>Price RM:</b> <%=product.getPrice()%></p>            
                <form method="post" action="DeleteProductServlet">
                    <input type="hidden" name="id" value="<%=product.getId()%>">
                    <input type="submit" class="button" value="Delete">
                    <a href="update.jsp?id=<%=product.getId()%> " class="button" >Update</a>
                </form>
          </div>
        </div>
      </div>
      <% } %>
    </div>
               <br><br><a href="dashboard.jsp" class="button-end">Back To Dashboard</a>
               <a href="add.jsp" class="button-end">Add More Books</a><br><br>


  </body>
  
  <script>
            window.onload = function() {
                history.pushState(null, null, location.href);
                window.onpopstate = function() {
                    history.go(1);
                };
            };
        </script>
  
 
        
</html>
