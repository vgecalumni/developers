	<div id="new-message">
		<p class="m-header">New Message</p>
		<p class="m-body">
			<center>
			<form method="post">
			<input type="text" list="user" onkeyup="check_in_db()" placeholder="Username" id="user_name" name="user_name" class="message-input" id="user_name" />
			<datalist id="user" name="user"></datalist>
			<br><br>	
				<textarea placeholder="WRITE YOUR MESSAGE" name="message" class="message-input"></textarea><br><br>
				<input type="submit" name="send" id="send"value="send"/>
				<button onclick="document.getElementById('new-message').style.display='none'">cancel</button></center>
</form>
		</p>
		<p class="m-footer">Click send to send</p>
	</div>

	<?php 
		require_once("connection.php");
		 if(isset($_POST['send']))
		 {
		 	$sender_name=$_SESSION['username'];
		 	$reciver_name=$_POST['user_name'];
		 	$message=$_POST['message'];
		 	$date=date("Y-m-d H:I:s");

		 	$q='INSERT INTO `admin_chat`(`id`,`sender_name`,`reciver_name`,`message_text`,`datetime`)
		 	VALUES("","'.$sender_name.'","'.$reciver_name.'","'.$message.'","'.$date.'")';
		 	$r=mysqli_query($con,$q);
		 	if($r)
		 	{
		 		header("location:index.php?user=".$reciver_name);
		 	}
		 	else
		 	{
		 		echo $q;
		 	}
		 }



	?>

	<script src ="sub_files/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">

	document.getElementById("send").disabled=true;

		function check_in_db()
		{
			var user_name=document.getElementById("user_name").value;
			$.post("sub_files/check_in_db.php",{

				user:user_name

			},
			function(data,status)
			{
				//alert(data);
				if(data == '<option value="no user">' )
				{
					document.getElementById("send").disabled=true;
				}
				else
				{
				document.getElementById("send").disabled=false;

				}
			}
			);
		}
	</script>