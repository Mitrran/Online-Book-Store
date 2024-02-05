
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String fname = request.getParameter("fname");
        String uname = request.getParameter("uname");
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");

        // Check if all fields are filled in
        if (fname.isEmpty() || uname.isEmpty() || email.isEmpty() || pwd.isEmpty()) {
            out.println("<p>All fields are required. Please fill in all fields.</p>");
        } else {
            Connection con = null;
            PreparedStatement insertStmt = null;
            PreparedStatement selectStmt = null;
            ResultSet rs = null;

            try {
                // Establish database connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/crud?serverTimezone=UTC";
                String username = "root";
                String password = "Mitrran@27";
                con = DriverManager.getConnection(url, username, password);

                // Check if the user already exists
                String selectSql = "SELECT * FROM users WHERE user_name = ?";
                selectStmt = con.prepareStatement(selectSql);
                selectStmt.setString(1, uname);
                rs = selectStmt.executeQuery();

                if (rs.next()) {
                    // User already exists, prompt the user to choose a different username
                    String UserTakenMessage = "Username is already taken, please choose a different username! Redirecting back to the register page in 3 seconds...";
                    out.println("<p>" + UserTakenMessage + "</p>");
                    response.setHeader("Refresh", "3; URL=registration.jsp");

                    
                } else {
                    // User does not exist, insert user data into database
                    String insertSql = "INSERT INTO users (full_name, user_name, email, password) VALUES (?, ?, ?, ?)";
                    insertStmt = con.prepareStatement(insertSql);
                    insertStmt.setString(1, fname);
                    insertStmt.setString(2, uname);
                    insertStmt.setString(3, email);
                    insertStmt.setString(4, pwd);
                    insertStmt.executeUpdate();

                    // Store the username in the session
                    HttpSession session = request.getSession();
                    session.setAttribute("username", uname);

                    // Registration successful
                String successMessage = "Registration successful! Redirecting back to the register page in 3 seconds...";
                out.println("<p>" + successMessage + "</p>");
                response.setHeader("Refresh", "3; URL=registration.jsp");
                }
            } catch (Exception e) {
                    out.println("<p>Error: " + e.getMessage() + "</p>");
                    out.println("<p>Redirecting back to the register page in 3 seconds...</p>");
                    response.setHeader("Refresh", "3; URL=registration.jsp");
                }
                 finally {
                try {
                    if (rs != null) rs.close();
                    if (selectStmt != null) selectStmt.close();
                    if (insertStmt != null) insertStmt.close();
                    if (con != null) con.close();
                } catch (Exception e) {
                    out.println("<p>Error: " + e.getMessage() + "</p>");
                }
            }
        }
    }
}