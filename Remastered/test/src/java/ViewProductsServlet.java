
import controller.Product;
import controller.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ViewProductsServlet"})
public class ViewProductsServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private ProductDAO dao;

  public void init() {
    dao = new ProductDAO();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Product> product = dao.getAllProducts();
    request.setAttribute("products", product);
    request.getRequestDispatcher("display.jsp").forward(request, response);
  }
}