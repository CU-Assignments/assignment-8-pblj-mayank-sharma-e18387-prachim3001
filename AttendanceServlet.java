import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get form parameters
        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String status = request.getParameter("status");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            // Get database connection
            Connection conn = DatabaseConnection.getConnection();
            
            // Insert attendance record
            String sql = "INSERT INTO attendance (student_id, name, date, status) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studentId);
            pstmt.setString(2, name);
            pstmt.setString(3, date);
            pstmt.setString(4, status);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                response.sendRedirect("confirmation.jsp?status=success");
            } else {
                response.sendRedirect("confirmation.jsp?status=error");
            }
            
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("confirmation.jsp?status=error");
        }
    }
}
