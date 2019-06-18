<?php
 session_start();
require_once("../connection.php");
 if(isset($_SESSION['username']) and isset($_GET['user']))
 {
 	if(isset($_POST['text']))
	{
		if($_POST['text'] !='')
		{
			$sender_name=$_SESSION['username'];
			$reciver_name=$_GET['user'];
			$message= $_POST['text'];
			$date=date("Y-m-d h:i:sa");

			$q='INSERT INTO `message`(`id`,`sender_name`,`reciver_name`,`message_text`,`datetime`)
		 	VALUES("","'.$sender_name.'","'.$reciver_name.'","'.$message.'","'.$date.'")';
		 	$r=mysqli_query($con,$q);
		 	if($r)
		 	{
		 		?>
				 		<div class="grey-message">
						<a href="#">Me</a>
						<p><?php echo $message; ?></p>
						</div>	
						<?php
		 	}
			else
		 	{
		 		echo $q;
		 	}
		}
		else
		{
			echo 'please write somethimg first';
		}
	}
	else
	{
		echo 'problem with text';
	}

 }
 else
 {
 	echo 'please login or select user to send a message';
 }
?>
