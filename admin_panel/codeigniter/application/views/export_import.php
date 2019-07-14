<!DOCTYPE html>
<html>
<head>
  <title>IMPORT CSV TO MYSQL</title>
  

  <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script> -->
  <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" /> -->
  <script src="../assets/js/jquery-3.2.1.min.js"></script>
  <script src="../assets/js/popper.js"></script>
  <script src="../assets/js/script.js"></script>
   <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
  <!-- <script  src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
  <script src="../assets/js/bootstrap.min.js"></script>
</head>
<body>
  <div class="container-fluid box">
    <h3 align="center">Import CSV File Data into MySQL Database in CodeIgniter</h3>
    <br />
    <form method="post" id="import_csv" enctype="multipart/form-data">
      <div class="form-group">
        <label>SELECT CSV FILE</label>
        <input type="file" name="csv_file" id="csv_file" required accept=".csv" />
      </div>
      <br />
      
      
      <button type="submit" name="import_csv" class="btn btn-info" id="import_csv_btn">Import</button>
       
     </form>

    <br />
    <div id="imported_csv_data"></div>
  </div>
</body>
</html>
<script>
  $(document).ready(function()
  {
    load_data();
    
    function load_data()
    {
      $.ajax({
        url:"<?php echo base_url(); ?>alumni/Alumni_registrations/load_data",
        method:"POST",
        success:function(data)
        {
          $('#imported_csv_data').html(data);
        }
      })
    }
    $('#import_csv').on('submit',function(event)
    {

      event.preventDefault();
      $.ajax({
        url:"<?php echo base_url(); ?>alumni/Alumni_registrations/import",
        method:"POST",
        data:new FormData(this),
        contentType:false,
        cache:false,
        processData:false,
        beforesend:function()
        {
          $('#import_csv_btn').html('Importing..');
        },
        success:function(data)
        {
          $('#import_csv')[0].reset();
          $('#import_csv_btn').attr('disabled',false);
          $('#import_csv_btn').html('Import Done');
          load_data();
        }
      })
    });
  });
</script>