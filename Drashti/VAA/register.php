<?php require_once("connection.php");?><!--  -->

<!doctupe html>
<head>
	<style>
		*{margin:0px; padding: 0px;}
		#container{ width:300px; margin: 0px auto;}
		.input{width: 92%; padding: 2%;}
	</style>
</head>
<body>
	<h1 align="center">Registration Form</h1>
	<div id="container">
		<form method="post">
			<input type="text" placeholder="Username" id="user_name" name="user_name" onkeyup="check_user()" class="input" required />
			<div id="checking">Checking</div> 
			<br><br>
			
			<input type="password" placeholder="Password" name="password" class="input" required /><br><br>
			<input type="submit" id="register" name="register" value="register"/>
			<a href="login.php">Login Here</a>
		</form>
	</div>
<?php if(isset($_POST['register']))
{
	$user_name=$_POST['user_name'];
	$password=$_POST['password'];

	$q="INSERT INTO `admin_users` (`id`,`user_name`,`password`)
	VALUES ('','".$user_name."','".$password."') ";
	$r=mysqli_query($con,$q);

	if($r)
	{
		header("location:login.php");
	}
	else
	{
		echo $q;

	}
}?>
<script src ="sub_files/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	document.getElementById("register").disabled=  true;
	function check_user()
	{
		var user_name= document.getElementById("user_name").value;

		$.post("sub_files/user_check.php",
			{
				user: user_name
			},
			function(data,status)
			{
				if(data == '<p style="color:red">User already registered</p>')
				{
					document.getElementById("register").disabled=  true;
				}	else{
					document.getElementById("register").disabled= false;
				}
				document.getElementById("checking").innerHTML = data;
			}
			);
	}
</script>
</body>
</html>
	