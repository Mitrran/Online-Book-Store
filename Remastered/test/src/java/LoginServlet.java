
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Establish database connection
        String url = "jdbc:mysql://localhost:3306/crud?serverTimezone=UTC";
        String username = "root";
        String password = "Mitrran@27";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);

            // Get form parameters
            String uname = request.getParameter("uname");
            String pwd = request.getParameter("password");
            System.out.println("Username: " + uname);
            System.out.println("Password: " + pwd);

            // Query the database for user data
            String querySql = "SELECT * FROM users WHERE user_name = ? AND password = ?";
            pstmt = con.prepareStatement(querySql);
            pstmt.setString(1, uname);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();
            
            // Store the username in the session
            HttpSession session = request.getSession();
            session.setAttribute("username", uname);

            // Check if user exists and redirect to dashboard page
            if (rs.next()) {
                response.sendRedirect("dashboard.jsp");
            } else {
                // User not found, display error message
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<p>Incorrect username or password. Please try again.</p>");
                out.println("<p>Redirecting back to the login page in 3 seconds...</p>");
                out.println("</body></html>");
                response.setHeader("Refresh", "3; URL=login.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
