
package controller;

import static controller.ConnectionFactory.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
  private Connection conn;
  
  public ProductDAO() {
   conn = ConnectionFactory.getConnection();
  }
  
  public void addProduct(Product product) {
    try {
      PreparedStatement preparedStatement = conn.prepareStatement(

        "INSERT INTO products (name, author, genre, year, price, image) VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getAuthor());
        preparedStatement.setString(3, product.getGenre());
        preparedStatement.setInt(4, product.getYear());
        preparedStatement.setFloat(5, product.getPrice());
        preparedStatement.setBytes(6, product.getImage());
        preparedStatement.executeUpdate();
        
      } catch (SQLException e) {
        e.printStackTrace();
      }
  }
  
  public boolean updateProduct(Product product) {
    Connection connection = getConnection();
    PreparedStatement statement = null;
    boolean success = false;
    try {
        statement = connection.prepareStatement("UPDATE products SET name=?, author=?, genre=?, year=?, price=?, image=? WHERE id=?");
        statement.setString(1, product.getName());
        statement.setString(2, product.getAuthor());
        statement.setString(3, product.getGenre());
        statement.setInt(4, product.getYear());
        statement.setFloat(5, product.getPrice());
        statement.setBytes(6, product.getImage());
        statement.setInt(7, product.getId());
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            success = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    return success;
}
  
  public void deleteProduct(int productId) {
    try {
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM products WHERE id = ?");
        preparedStatement.setInt(1, productId);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
  
  public Product getProduct(int productId) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Product product = null;

    try {
        // get a connection to the database
        conn = ConnectionFactory.getConnection();
        
        // prepare SQL statement
        String sql = "SELECT * FROM products WHERE id=?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, productId);
        
        // execute query and get result set
        rs = stmt.executeQuery();

        // if product found, create Product object and set values
        if (rs.next()) {
            product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setAuthor(rs.getString("author"));
            product.setGenre(rs.getString("genre"));
            product.setYear(rs.getInt("year"));
            product.setPrice(rs.getFloat("price"));
            product.setImage(rs.getBytes("image"));
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    } 

    return product;
}
  
  public List<Product> getAllProducts() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Product> productList = new ArrayList<>();

    try {
        // get a connection to the database
        conn = ConnectionFactory.getConnection();

        
        // prepare SQL statement
        String sql = "SELECT * FROM products";
        stmt = conn.prepareStatement(sql);
        
        // execute query and get result set
        rs = stmt.executeQuery();

        // loop through result set and create Product objects
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setAuthor(rs.getString("author"));
            product.setGenre(rs.getString("genre"));
            product.setYear(rs.getInt("year"));
            product.setPrice(rs.getFloat("price"));
            product.setImage(rs.getBytes("image"));
            productList.add(product);
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    } 

    return productList;
    }
}
