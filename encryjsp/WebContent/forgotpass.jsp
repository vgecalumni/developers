<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="javax.websocket.SendResult"%>
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
String emailid = request.getParameter("emailid");
boolean b = false;
try{
	Class.forName("com.mysql.jdbc.Driver");  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/vaa","root","");
     PreparedStatement ps=con.prepareStatement("select emailid from student where emailid ='"+emailid+"'");
     ResultSet rs = ps.executeQuery();
     if(rs.next())
     { b = true;}
     con.close();
	}
catch(Exception e){e.printStackTrace();}

if(b){
	//String msg = "http://localhost:8080/vaa/createpassword.jsp?id="+emailid;
	//MyThread t = new MyThread();
	//t.StartThread(emailid, msg , "Customer password verify link");
	// code of mail  and verify link
	//So redirect create newpassword createpwd.jsp
	session.setAttribute("emailid", emailid);
	request.getRequestDispatcher("createpwd.jsp").forward(request, response);
}
else{
	out.println("invaild Email id ");
	request.getRequestDispatcher("index.jsp").include(request, response);
}


%>
</body>
</html>