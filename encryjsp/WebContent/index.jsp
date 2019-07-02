<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <form action="login.jsp" class="my-login-form" method="POST">
                        <div class="my-login-header">
                            <center>
                                <h4 class="">Login</h4>
                            </center>
                        </div>
                        <div class="my-login-content">
                            <div class="container-95 row">
                                <div class="col-md"></div>                                
                                <div class="col-md-8">
                                    <div class="row">
                                        <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label for="username">Username</label>
                                        </div>
                                        <div class="col-md-8">
                                            <input type="text" name="emailid" id="username" >
                                        </div>
                                    </div>                                    
                                </div>
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label for="password">Password</label>
                                        </div>
                                        <div class="col-md-8">
                                            <input type="password" name="password" id="password">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-md">
                                            <center>
                                                <input type="submit" name="login" id="login" value="login">
                                            </center>
                                        </div>
                                    </div>
                                </div>
                                             <div class="col-md">
                                            <center>
                                               <a href="forgot.jsp">Forgot Password</a>
                                            </center>
                                        </div>                      
                                    </div>
                                </div>
                                <div class="col-md"></div>
                            </div>
                        </div>
                    </form>
                </div>        
</body>
</html>