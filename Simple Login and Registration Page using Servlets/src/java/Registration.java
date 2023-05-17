
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;


@WebServlet(urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        
        
            String Name = request.getParameter("name");
            String Surname = request.getParameter("surname");
            String Username = request.getParameter("username");
            String Password = request.getParameter("password");
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); //
             Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_and_registration","root","");
 
             String sql= "INSERT INTO information(Name, Surname, Username, Password) VALUES(?,?,?,?)";
             String sql1= "INSERT INTO login_details(Username, Password) VALUES(?,?)";
             
             PreparedStatement pst = connection.prepareStatement(sql);
             PreparedStatement pst1 = connection.prepareStatement(sql1);
             
             pst.setString(1,Name);
             pst.setString(2,Surname);
             pst.setString(3,Username);
             pst.setString(4,Password);
 
            pst1.setString(1,Username);
            pst1.setString(2,Password); 
           
            if(Name.isEmpty() || Surname.isEmpty() || Username.isEmpty() || Password.isEmpty()){
                  out.println("Details Have Not Been Captured !!!");         
            }else{
                pst.executeUpdate(); 
                pst1.executeUpdate();
             out.println("Details Have Been Captured !!!");
            } 
            } 
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            }   
        
        
    }

}
