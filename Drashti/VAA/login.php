<?php
session_start();  
require_once("connection.php");
?>
<!doctupe html>
<head>
	<style>
		*{margin:0px; padding: 0px;}
		#container{ width:300px; margin: 0px auto;}
		.input{width: 92%; padding: 2%;}
		</style>
</head>
<body>
	<h1 align="center">Login Form</h1>
	<div id="container">
		<form method="post">
			<input type="text" placeholder="Username" id="user_name" name="user_name" class="input"/><br><br>
			<input type="password" placeholder="Password" id="password" name="password" class="input"/><br><br>
			<input type="submit" id="login" name="login" value="login"/>
						<a href="register.php">Registration Here</a>
					</form>
				</div>



		<?php if(isset($_POST['login']))
		{
		$user_name=$_POST['user_name'];
		$password=$_POST['password'];
		$login = 'NOW()';


		$q='SELECT *FROM admin_users WHERE  `user_name`="'.$user_name.'" AND `password`= "'.$password.'"	';
		$query = "UPDATE admin_users SET last_logged = '$login' WHERE user_name ='$user_name'";
	$r=mysqli_query($con,$q);
	$r1=mysqli_query($con,$query);
	if($r1)
	{
		echo 'logged in';
	}
	if($r)
	{
			if(mysqli_num_rows($r)>0)
			{
				$_SESSION['username']=$user_name;
				header("location:index.php");
			}
				else
				{
					echo"your password and username do not matched";
				}
			}
	else
	{
		echo $q;

	}
}
?>


</body>
</html>