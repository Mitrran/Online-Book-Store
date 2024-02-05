
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  private static Connection connection = null;

  public static Connection getConnection() {
    if (connection == null) {
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/crud?serverTimezone=UTC";
        String username = "root";
        String password = "Mitrran@27";
        connection = DriverManager.getConnection(url, username, password);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return connection;
  }
}
