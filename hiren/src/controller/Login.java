package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ConnectionDb;
import model.PwdEncry;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
		String email = request.getParameter("emailid");
		String pwd =request.getParameter("password");
		String hashpwd="";
		out.println(hashpwd);
		
			    try{  
			        Connection con=ConnectionDb.getConnection();  
			        PreparedStatement ps=con.prepareStatement("select * from student where emailid=?");
			        ps.setString(1, email);
			        ResultSet rs = ps.executeQuery();
			         if(rs.next())
			         { hashpwd= rs.getString("password");
			         }
			        // out.println(hashpwd);
			         con.close();
			    }catch(Exception ex){ex.printStackTrace();}  
		
			    
			    boolean b=PwdEncry.checkEncryPwd(pwd, hashpwd);
			  if(b)  
			  { 
				  request.getRequestDispatcher("welcome.jsp").forward(request, response);
			  }   
		else{
		out.print("invaild username");
		request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}

}
