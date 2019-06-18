<!DOCTYPE html>
<html lang="en">

<head>
  
  <title><?php echo $title; ?></title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  
  <?php $this->load->view('layout/admin_panel/styles'); ?>
  
</head>

<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <?php $this->load->view('layout/admin_panel/navbar'); ?>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_sidebar.html -->
      
      <?php $this->load->view('layout/admin_panel/sidebar'); ?>

      <!-- partial -->
      <div class="main-panel" style="margin-left: 255px;">
        <div class="content-wrapper">
          
          <?php $this->load->view('layout/admin_panel/breadcrumb'); ?>
          <?php $this->load->view('layout/admin_panel/loader'); ?>
          <div id="data">
                      
          </div>
        </div>
      </div>

    </div>
  </div>

 <?php $this->load->view('layout/admin_panel/script'); ?>
 <?php $this->load->view('admin_panel/request_handler',$request); ?>
  
</body>

</html>