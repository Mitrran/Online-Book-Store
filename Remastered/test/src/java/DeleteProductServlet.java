
import controller.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/DeleteProductServlet"})
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ProductDAO dao = new ProductDAO();
        dao.deleteProduct(id);

        // Log activity
        HttpSession session = request.getSession();
        String uname = (String) session.getAttribute("username");
        logActivity(uname, "Deleted an Item");
        
        response.sendRedirect("display.jsp");
    }
    
    private void logActivity(String uname, String activity) {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        // Retrieve the existing activity log from the application scope
        ServletContext servletContext = getServletContext();
        List<String> activityLog = (List<String>) servletContext.getAttribute("activityLog");

        // If the activity log doesn't exist, create a new one
        if (activityLog == null) {
            activityLog = new ArrayList<>();
        }

        // Add the log entry to the activity log
        String logEntry = "[" + formattedDateTime + "] User: " + uname + ", Activity: " + activity;
        activityLog.add(logEntry);

        // Update the activity log in the application scope
        servletContext.setAttribute("activityLog", activityLog);
    }
}