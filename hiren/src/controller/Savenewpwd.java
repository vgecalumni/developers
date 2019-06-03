package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.sun.org.apache.bcel.internal.util.BCELifier;

import model.ConnectionDb;
import model.PwdEncry;

/**
 * Servlet implementation class Savenewpwd
 */
@WebServlet("/Savenewpwd")
public class Savenewpwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Savenewpwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String newpwd = request.getParameter("newpwd");
		String repwd =request.getParameter("repwd");
		//String emailid =request.getParameter("id");
		
		HttpSession session = request.getSession();
		String emailid=(String)session.getAttribute("emailid");
		
		if(newpwd.equals(repwd))
		{
			String hashpwd=PwdEncry.getEncryPwd(newpwd);
			 int status=0;  
			    try{  
			        Connection con=ConnectionDb.getConnection();  
			        PreparedStatement ps=con.prepareStatement("update student set password =?  where emailid=?");
			         
			        ps.setString(1, hashpwd);
			        ps.setString(2, emailid);
			        status=ps.executeUpdate(); 
			       
			        con.close();  
			    }catch(Exception ex){ex.printStackTrace();}  
			   
		        out.println("password change Successfully");
			    request.getRequestDispatcher("index.jsp").include(request, response);
			
		}
		else{
		out.print("not match password");
		request.getRequestDispatcher("createpassword.jsp").include(request, response);
		}
		
		
		
	}

}
