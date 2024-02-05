import controller.Product;
import controller.ProductDAO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/UpdateProductServlet")
@MultipartConfig(fileSizeThreshold = 0, // no threshold
                 maxFileSize = Long.MAX_VALUE, // unlimited file size
                 maxRequestSize = Long.MAX_VALUE) // unlimited request size
public class UpdateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR = "uploads";

    private ProductDAO dao;

    public void init() {
        dao = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String author = request.getParameter("author");
    String genre = request.getParameter("genre");
    int year = Integer.parseInt(request.getParameter("year"));


    float price = 0.0f;
    String priceStr = request.getParameter("price");
    if (priceStr != null && !priceStr.trim().isEmpty()) {
        price = Float.parseFloat(priceStr);
    }

    Part filePart = request.getPart("image");
    byte[] image = null;
    if (filePart != null && filePart.getSize() > 0) {
        // A new image was uploaded
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix
        InputStream fileContent = filePart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fileContent.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        image = outputStream.toByteArray();
        outputStream.close();
    } else {
        // No new image was uploaded, retrieve the existing image
        Product product = dao.getProduct(id);
        image = product.getImage();
    }

    Product product = new Product();
    product.setId(id);
    product.setName(name);
    product.setAuthor(author);
    product.setGenre(genre);
    product.setYear(year);
    product.setPrice(price);
    product.setImage(image);

    dao.updateProduct(product);

    String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) {
        uploadDir.mkdir();
    }
    
        // Log activity
        HttpSession session = request.getSession();
        String uname = (String) session.getAttribute("username");
        logActivity(uname, "Updated an Item");

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