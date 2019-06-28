<!-- <!DOCTYPE html>
<html>
<head>
	<title></title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>


<h3 align="center"><?php
	echo $title;
?></h3>
<form method="post" action="http://127.0.0.1/codeigniter/test/resetlink">
	<div class="form-group">
		
		<input type="text" name="username" class="form-control" placeholder="Username"/>
		
	</div>
	
	<div class="form-group">
		<input type="submit" value="Send Reset Link"/>
	</div>
</form>
</body>
</body>
</body>
</html>
 -->


 <!-- <!DOCTYPE html>
<html>
<head>
	<title></title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>


<h3 align="center"><?php
	echo $title;
?></h3>
<form method="post" action="http://127.0.0.1/codeigniter/test/login_validation">
	<div class="form-group">
		<label>Enter Username</label>
		<input type="text" name="username" class="form-control"/>
		
	</div>
	<div class="form-group">
		<label>Enter Password</label>
		<input type="password" name="password" class="form-control"/>
			
	</div>
	<a href="http://127.0.0.1/codeigniter/test/ForgetPassword">Forget password?</a>
	<div class="form-group">
		<input type="submit" name="insert" value="Login"/>
		<?php
			echo $this->session->flashdata("error");
		?>
	</div>
</form>
</body>
</body>
</body>
</html>
 -->

<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="assets/plugins/sticky/css/slick.css">
    <link rel="stylesheet" href="assets/plugins/sticky/css/slick-theme.css">
    <link rel="stylesheet" href="assets/plugins/testimonial/css/owl.carousel.min.css">
    <link rel="stylesheet" href="assets/plugins/testimonial/css/owl.theme.min.css">
    <link rel="stylesheet" href="../assets/css/style2.css">

    <title>Forget Password</title>
</head>



<body>

    <div id="home" class="container-fluid">

        <!--  ************************* Header Starts Here ************************** -->
                                                                             
                                                                                                                                              
          <!-- ######## Header End ####### --> 
         <div class="container">
             <div class="row">
                 <div class="col-sm-4 login-box">
                     <div class="title-box">
                         <h4>Enter Username:</h4>
                         <!-- <p>Please enter  your Login details !</p> -->
                     </div>
                     <form method="post" action="http://127.0.0.1/codeigniter/test/resetlink">
                     <div class="row">
                         <input type="text" name="username" placeholder="Enter Username" class="form-control inpt-sm">
                     </div>
                     <!-- <div class="row">
                         <input type="password" name="password" placeholder="Enter Password" class="form-control inpt-sm">
                     </div> -->
                     <div class="row chk-lab">
                        <div class="col-sm-6">
                            <!-- <input type="checkbox"> <label>Remember me</label> -->
                        </div>
                        <div class="col-sm-6 colkd">
                            <!-- <a href="http://127.0.0.1/codeigniter/test/ForgetPassword">Forget Password ?</a> -->
                        </div>
                     </div>
                     <div class=" submot-row">
                         <input type="submit" class="btn btn-sm btn-success">
                     </div>
                 </form>
                 </div>
             </div>
         </div>
         
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
        
        </div>
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="../assets/js/jquery-3.2.1.min.js"></script>
        <script src="../assets/js/popper.js"></script>
        <script src="../assets/js/bootstrap.min.js"></script>
        <script src="assets/plugins/sticky/js/slick.min.js"></script>
        <script src="assets/plugins/scroll-fixed/jquery-scrolltofixed-min.js"></script>
        <script src="assets/plugins/testimonial/js/owl.carousel.min.js"></script>
        <script src="../assets/js/script.js"></script>
</body>

</html>