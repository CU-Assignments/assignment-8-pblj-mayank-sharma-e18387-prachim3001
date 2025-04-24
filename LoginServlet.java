import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    private final String VALID_USERNAME = "admin";
    private final String VALID_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        if(username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
            out.println("<html><body>");
            out.println("<h2>Welcome, " + username + "!</h2>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h2 style='color:red'>Invalid credentials. Please try again.</h2>");
            out.println("<a href='login.html'>Back to login</a>");
            out.println("</body></html>");
        }
    }
}
