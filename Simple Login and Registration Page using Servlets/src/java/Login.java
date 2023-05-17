import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String Username = request.getParameter("Username");
        String Password = request.getParameter("Password");
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); //
             Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_and_registration","root","");
 
             String sql= "SELECT * from login_details where Username='"+Username+"' and Password='"+Password+"'"; 
             PreparedStatement pst = connection.prepareStatement(sql);
 
              ResultSet rs=pst.executeQuery(); 
           
            if(rs.next()){
              out.println("Correct Credentials!!! You have been logged in.")  ;            
            }else{
            out.println("Invalid Credentials");
            } 
            } 
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            }   
            
       
            
        
    }

}
