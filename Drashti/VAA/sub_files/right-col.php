<div id="right-col-container">
	<div id="message-container">

		<?php
		$no_message=false;
		if(isset($_GET['user']))
		{
			$_GET['user']=$_GET['user'];
		}
		else
		{
			$q='SELECT `sender_name`,`reciver_name` FROM `admin_chat` WHERE `sender_name`="'.$_SESSION['username'].'" OR `reciver_name`="'.$_SESSION['username'].'" ORDER BY `datetime` DESC LIMIT 1';
 			$r=mysqli_query($con,$q);

				if($r)
				{
						if(mysqli_num_rows($r)>0)
						{
							while ($row=mysqli_fetch_assoc($r))
							 {
							 	$sender_name=$row['sender_name'];
		 						$reciver_name=$row['reciver_name'];

		 						if($_SESSION['username'] == $sender_name){
		 							$_GET['user'] = $reciver_name;
		 						}else{
		 						$_GET['user'] = $sender_name;

		 						}
				 			}

						}
						else
						{
							echo'no message form you';
							$no_message= true;
						}
				}
				else
				{
							echo $q;
				}
		}
		if($no_message == false){
		$q='SELECT *FROM `admin_chat` WHERE
		`sender_name`="'.$_SESSION['username'].'" AND `reciver_name`="'.$_GET['user'].'"
		OR 
		`sender_name`="'.$_GET['user'].'" AND `reciver_name`="'.$_SESSION['username'].'"';
				 	$r=mysqli_query($con,$q);
		 	if($r)
		 	{
		 		while ($row=mysqli_fetch_assoc($r))
				 {
				 	$sender_name=$row['sender_name'];
				 	$reciver_name=$row['reciver_name'];
				 	$message=$row['message_text'];
				 	if($sender_name == $_SESSION['username'])
				 	{
				 		?>
				 		<div class="grey-message">
						<a href="#">Me</a>
						<p><?php echo $message; ?></p>
						</div>	
						<?php
					}else{ ?>

						<div class="white-message">
						<a href="#"><?php echo $sender_name; ?></a>
						<p><?php echo $message; ?></p>
						</div>
						<?php 
					}

				 	}

			}else
			{
				echo $q;
			}
		}

		?>
	</div>
	<form method="post" id="message-form">
	<textarea placeholder="WRITE YOUR MESSAGE" id="message_text" class="textarea"></textarea>
	</form>
</div>

	<script src ="sub_files/jquery-3.4.1.min.js"></script>
	<script>
		$("document").ready(function(event)
		{
			$("#right-col-container").on('submit','#message-form',function()
			{
				var message_text = $("#message_text").val();
				$.post("sub_files/sending_process.php?user=<?php echo $_GET['user'];?>",
				{
					text: message_text,
				},
				function(data,status)
				{
					//	alert(data);
				$("#message_text").val("");
				document.getElementById("message-container").innerHTML += data;
				});
			});
			$("#right-col-container").keypress(function(e)
			{
				if(e.keyCode == 13 && !e.shiftKey)
				{
					$("#message-form").submit();
				}
			})

		}); 
	</script>