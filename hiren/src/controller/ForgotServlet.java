package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MyThread;
import model.ConnectionDb;

/**
 * Servlet implementation class ForgetServlet
 */
@WebServlet("/ForgotServlet")
public class ForgotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotServlet() {
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
		String emailid = request.getParameter("emailid");
			boolean b = false;
			try{
				 Connection con=ConnectionDb.getConnection();  
		         PreparedStatement ps=con.prepareStatement("select emailid from student where emailid ='"+emailid+"'");
		         ResultSet rs = ps.executeQuery();
		         if(rs.next())
		         { b = true;}
		         con.close();
				}
			catch(Exception e){e.printStackTrace();}
			
			if(b){
				String msg = "http://localhost:8080/vaa/createpassword.jsp?id="+emailid;
				MyThread t = new MyThread();
				t.StartThread(emailid, msg , "Customer password verify link");
				HttpSession session = request.getSession();
				session.setAttribute("emailid", emailid);
				request.getRequestDispatcher("verifylink.jsp").forward(request, response);
			}
			else{
				out.println("invaild Email id ");
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
		
		
			/*Random rnd = new Random();
		    int number = rnd.nextInt(999999);
		    String msg =  new Integer(number).toString(); */
		
	}

}
