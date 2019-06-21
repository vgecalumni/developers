<html>
<head>

	<title> export my sql data to csv file </title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
	<script src=" https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	
	<div class="container box">
		<h3 align="center">Export Mysql Data To CSV File In Codeigniter </h3>
		<br />
		<form method="post" action="<?php echo base_url(); ?>export_event/export">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-6">
							<h3 class="panel-title">Student Data </h3>
						</div>
						<div class="col-md-6" align="right">
							<input type="submit" name="export" class="btn btn-success btn-xs" value="Export to CSV" />
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-bordered table-striped">
							<tr>
								<th>Id</th>
								<th>E_id</th>
								<th>Event</th>
								<th>first Name</th>
								<th>last Name</th>
								<th>Company</th>
								<th>Designation</th>
								<th>passing Year</th>
								<th>Branch</th>
								<th>Programme</th>
								<th>Mobile</th>
								<th>Email</th>
								<th>Award</th>
								<th>Cultural</th>
								<th>Project</th>
								<th>Status</th>
								<th>Refcode</th>
								<th>Refname</th>
								<th>Refcount</th>
								<th>Photo</th>
								<th>Food</th>
								<th>Hostelier</th>
								<th>Gender</th>
								<th>EnrollNo</th>
								<th>RoomNo</th>
								<th>Foodoption</th>
								<th>Amount</th>
							</tr>
							<?php
							foreach ($student_data->result_array() as $row)
							{
								# code...
								echo' 
									<tr>
										<td>'.$row["id"].'</td>
										<td>'.$row["e_id"].'</td>
										<td>'.$row["event"].'</td>
										<td>'.$row["firstName"].'</td>
										<td>'.$row["lastName"].'</td>
										<td>'.$row["company"].'</td>
										<td>'.$row["designation"].'</td>
										<td>'.$row["passingYear"].'</td>
										<td>'.$row["branch"].'</td>
										<td>'.$row["programme"].'</td>
										<td>'.$row["mobile"].'</td>
										<td>'.$row["email"].'</td>
										<td>'.$row["award"].'</td>
										<td>'.$row["cultural"].'</td>
										<td>'.$row["project"].'</td>
										<td>'.$row["status"].'</td>
										<td>'.$row["refcode"].'</td>
										<td>'.$row["refname"].'</td>
										<td>'.$row["refcount"].'</td>
										<td>'.$row["photo"].'</td>
										<td>'.$row["food"].'</td>
										<td>'.$row["hostelier"].'</td>
										<td>'.$row["gender"].'</td>
										<td>'.$row["enrollNo"].'</td>
										<td>'.$row["roomNo"].'</td>
										<td>'.$row["foodoption"].'</td>
										<td>'.$row["amount"].'</td>

									</tr>
								';
							}
							?>
						</table>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>

