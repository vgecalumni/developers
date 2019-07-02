<%@page import="org.mindrot.jbcrypt.BCrypt"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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

String email = request.getParameter("emailid");
String pwd =request.getParameter("password");
String hashpwd="";
try{  
	Class.forName("com.mysql.jdbc.Driver");  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/vaa","root","");
    PreparedStatement ps=con.prepareStatement("select * from student where emailid=?");
    ps.setString(1, email);
    ResultSet rs = ps.executeQuery();
     if(rs.next())
     {  hashpwd= rs.getString("password");
   //  out.println(hashpwd);
     
   }
     con.close();
     
}catch(Exception ex){ex.printStackTrace();}  
boolean b=BCrypt.checkpw(pwd, hashpwd);
if(b)  
{ 
  request.getRequestDispatcher("welcome.jsp").forward(request, response);
}   
else{
out.print("invaild username");
request.getRequestDispatcher("index.jsp").include(request, response);
}


%>
</body>
</html>