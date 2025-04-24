import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employeedb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String empId = request.getParameter("empId");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // Create prepared statement
            String sql = "SELECT * FROM Employee WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, empId);
            
            // Execute query
            rs = pstmt.executeQuery();
            
            out.println("<html><head><title>Employee Details</title>");
            out.println("<style>");
            out.println("table { border-collapse: collapse; width: 50%; margin: 20px auto; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("h2 { text-align: center; }");
            out.println("</style></head>");
            out.println("<body>");
            out.println("<h2>Employee Details</h2>");
            
            if (rs.next()) {
                out.println("<table>");
                out.println("<tr><th>ID</th><td>" + rs.getString("id") + "</td></tr>");
                out.println("<tr><th>Name</th><td>" + rs.getString("name") + "</td></tr>");
                out.println("<tr><th>Department</th><td>" + rs.getString("designation") + "</td></tr>");
                out.println("<tr><th>Salary</th><td>" + rs.getDouble("salary") + "</td></tr>");
                out.println("</table>");
            } else {
                out.println("<p style='text-align:center;color:red;'>No employee found with ID: " + empId + "</p>");
            }
            
            out.println("<div style='text-align:center;'><a href='employee.html'>Search Again</a></div>");
            out.println("</body></html>");
            
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<html><body>");
            out.println("<h2 style='color:red'>Error: " + e.getMessage() + "</h2>");
            out.println("</body></html>");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
