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
		<form method="post" action="<?php echo base_url(); ?>export_csv/export">
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
								<th>enroll No</th>
								<th>first Name</th>
								<th>middle Name</th>
								<th>last Name</th>
								<th>Gender</th>
								<th>Bdate</th>
								<th>Mobile</th>
								<th>email ID</th>
								<th>Address</th>
								<th>City</th>
								<th>District</th>
								<th>State</th>
								<th>postal code</th>
								<th>Branch</th>
								<th>passing Year</th>
								<th>Pic</th>
								<th>Registered</th>
								<th>Introduction</th>
								<th>Account</th>
							</tr>
							<?php
							foreach ($student_data->result_array() as $row)
							{
								# code...
								echo' 
									<tr>
										<td>'.$row["id"].'</td>
										<td>'.$row["enrollNo"].'</td>
										<td>'.$row["firstName"].'</td>
										<td>'.$row["middleName"].'</td>
										<td>'.$row["lastName"].'</td>
										<td>'.$row["gender"].'</td>
										<td>'.$row["dob"].'</td>
										<td>'.$row["mobile"].'</td>
										<td>'.$row["emailID"].'</td>
										<td>'.$row["address"].'</td>
										<td>'.$row["city"].'</td>
										<td>'.$row["district"].'</td>
										<td>'.$row["state"].'</td>
										<td>'.$row["postalcode"].'</td>
										<td>'.$row["branch"].'</td>
										<td>'.$row["passingYear"].'</td>
										<td>'.$row["pic"].'</td>
										<td>'.$row["registered"].'</td>
										<td>'.$row["introduction"].'</td>
										<td>'.$row["account"].'</td>

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