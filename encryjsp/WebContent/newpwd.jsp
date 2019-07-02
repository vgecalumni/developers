<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.mindrot.jbcrypt.BCrypt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String newpwd = request.getParameter("newpwd");
String repwd =request.getParameter("repwd");

String emailid=(String)session.getAttribute("emailid");

if(newpwd.equals(repwd))
{
	String Hashpwd=BCrypt.hashpw(newpwd, BCrypt.gensalt(12));  // encry pwd
	int status=0;  
			try{  
				Class.forName("com.mysql.jdbc.Driver");  
	            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/vaa","root","");
			    PreparedStatement ps=con.prepareStatement("update student set password =?  where emailid=?");
			     
			    ps.setString(1, Hashpwd);
			    ps.setString(2, emailid);
			    status=ps.executeUpdate(); 
			   
			    con.close();  
			}catch(Exception ex){ex.printStackTrace();}  

		out.println("password change Successfully");
		//out.println(status);
		out.println(Hashpwd);
request.getRequestDispatcher("index.jsp").include(request, response);
}
else{
out.print("not match password");
request.getRequestDispatcher("createpwd.jsp").include(request, response);
}


%>




</body>
</html>