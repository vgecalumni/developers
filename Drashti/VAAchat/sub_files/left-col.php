<div id="left-col-container">
	<div style="cursor: pointer;" onclick="document.getElementById('new-message').style.display='block'" class="white-back">
		<p align="center">New Message</p>
	</div>
	<?php
$q='SELECT DISTINCT `reciver_name`,`sender_name`FROM `message` WHERE `sender_name`="'.$_SESSION['username'].'" OR `reciver_name`="'.$_SESSION['username'].'" ORDER BY `datetime` DESC';
	$r=mysqli_query($con,$q);

	if($r)
	{
		if(mysqli_num_rows($r)>0)
			{
				$counter =0;
				$added_user = array();
				while ($row=mysqli_fetch_assoc($r))
				 {
				 	$sender_name=$row['sender_name'];
				 	$reciver_name=$row['reciver_name'];

				 	if($_SESSION['username']==$sender_name)
				 	{
				 		if(in_array($reciver_name,$added_user))
				 		{
				 	}
				 	else
				 	{
				 	?>
				 	<div class="grey-back">
					<img src="images/profile.jpg" class="images" />

					<?php echo '<a href="?user='.$reciver_name.'">'.$reciver_name.'</a>'; ?></div>
				 	<?php
				 	$added_user =array($counter => $reciver_name);  
				 	$counter++;
				 	}
				 }elseif($_SESSION['username']==$reciver_name){
				 		if(in_array($sender_name,$added_user)){	
				 	}else
				 {
				 	?>
				 	<div class="grey-back">
		<img src="images/profile.jpg" class="images" />

					<?php echo'<a href="?user='.$sender_name.'">'.$sender_name.'</a>'; ?>
				 	<?php
				 	$added_user =array($counter => $sender_name);  
				 	$counter++;
				 }
			}
		}
	}
		else
		{
			echo 'no user';
		}
	}
	else
	{
		echo $q;
	}

?>

		

</div>