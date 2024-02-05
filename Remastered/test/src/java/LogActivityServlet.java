
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

@WebServlet(urlPatterns = {"/LogActivityServlet"})
public class LogActivityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve username from the session
        HttpSession session = request.getSession();
        String uname = (String) session.getAttribute("username");

        // Retrieve activity from the request parameters
        String activity = request.getParameter("activity");

        // Log the activity
        logActivity(uname, activity);

        // Redirect back to the activity log page
        response.sendRedirect(request.getContextPath() + "/activityLog.jsp");
    }

    private void logActivity(String uname, String activity) {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        // Create a log entry
        String logEntry = "[" + formattedDateTime + "] User: " + uname + ", Activity: " + activity;

        // Store the log entry in a persistent storage (e.g., database)

        // Alternatively, you can store the log entry in an in-memory list (for demonstration purposes)
        ServletContext servletContext = getServletContext();
        List<String> activityLog = (List<String>) servletContext.getAttribute("activityLog");
        if (activityLog == null) {
            activityLog = new ArrayList<>();
        }
        activityLog.add(logEntry);
        servletContext.setAttribute("activityLog", activityLog);
    }
}