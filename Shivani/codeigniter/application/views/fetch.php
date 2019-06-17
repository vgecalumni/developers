<!DOCTYPE html>
<html>
<head>
	<title></title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>

<!-- <?php
// if($fetch_data->num_rows()>0)
// {
// 	foreach($fetch_data->result() as $row)
// 	{
// 		echo $row->username;
// 		echo $row->password;
// 	}
// }
// else
// {

// }
?> -->
<h3 align="center"><?php
	echo $title;
?></h3>
<form method="post" id="upload_form" enctype="multipart/form-data">
	<input type="file" name="image_file" id="image_file">
	<input type="submit" name="upload" id="upload" value="Upload"/>
	<div id="uploaded_image">
		<?php
			echo $image_data;
		?>
	</div>
</form>
</body>
</body>
</body>
</html>
<script type="text/javascript">
	$(document).ready(function(){
		$('#upload_form').on('submit',function(e){
			e.preventDefault();
			if($('#image_file').val() == '')
			{
				alert("please upload the image");
			}
			else
			{
				$.ajax({
					url:"http://127.0.0.1/codeigniter/test/ajax_upload",
					method : "POST",
					data:new FormData(this),
					contentType : false,
					cache : false,
					processData : false,
					success : function(data)
					{
						$('#uploaded_image').html(data);
					}
				});
			}

		});
	});
</script>