<?php
session_start();

 if(isset($_SESSION['username']))
 {
//echo 'how are you??'.$_SESSION['username'];
 	?>
<!doctupe html>
<html>
	<style>
		<?php require_once("sub_files/style.php");?>

	</style>
<body>
		<?php require_once("sub_files/new-message.php");?>

		<div id="container">
			<div id="menu">
				<?php

				echo $_SESSION['username'];
				echo '<a style="float: right;color: white;" href="logout.php">log out</a>';
?>
			</div>

<div id="left-col">
<?php
require_once("sub_files/left-col.php"); 
?>
</div>


<div id="right-col">
<?php
require_once("sub_files/right-col.php"); 
?>
</div>
</div>
</body>
</html>
 	<?php
}
else
{
	header("location:login.php");
}
?>
