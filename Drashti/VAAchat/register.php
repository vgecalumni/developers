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
			Role:  <select name="role">
			    <option value="principal">principal</option>
			    <option value="faculty">faculty</option>
			    <option value="core Member">Core Member</option>
			    <option value="Volunteer">Volunteer</option>
			  </select><br><br>
			<input type="name" placeholder="Full Name" name="name" class="input" required /><br><br>
			<input type="email" placeholder="Email" name="email" class="input" required /><br><br>
			<input type="number" placeholder="Mobile No." name="mobile" class="input" required /><br><br>
			upload profile picture:<input type="file" placeholder="Upload Profile Picture" name="profile" class="input" required /><br><br>

			<input type="submit" id="register" name="register" value="register"/>
			<a href="login.php">Login Here</a>
		</form>
	</div>
<?php if(isset($_POST['register']))
{
	$user_name=$_POST['user_name'];
	$password=$_POST['password'];
	$role=$_POST['role'];
	$name=$_POST['name'];
	$email=$_POST['email'];
	$mobile=$_POST['mobile'];
	$profile=$_POST['profile'];




	$q="INSERT INTO `users` (`id`,`user_name`,`password`,`role`,`status`,`name`,`email`,`mobile`,`profile_pic`,`last_logged`,`role_id`)
	VALUES ('','".$user_name."','".$password."','".$role."','','".$name."','".$email."','".$mobile."','".$profile."','','') ";
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
	