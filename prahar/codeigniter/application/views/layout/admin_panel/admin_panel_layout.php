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
    <nav class="navbar default-layout col-lg-12 col-12 p-0 fixed-top d-flex flex-row" style="z-index: 1000;background: #fff;">
      <div class="text-center navbar-brand-wrapper d-flex align-items-top justify-content-center">
        <a class="navbar-brand brand-logo" href="{{route('welcome'; ?>">
          <img src="<?php echo base_url().'assets/images/logo.png'; ?>" alt="logo" />
          
        </a>
        <a class="navbar-brand brand-logo-mini" href="{{route('welcome'; ?>">
          <img src="<?php echo base_url().'assets/images/logo.png'; ?>" alt="logo" />
          
        </a>
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center">
        <ul class="navbar-nav navbar-nav-left header-links d-none d-md-flex">
          <li class="nav-item">
            <!-- Search form -->
<input class="form-control" type="text" id="search" placeholder="Search" aria-label="Search" style="border: 5px solid transparent;min-width: 500px;color:#000!important;background: #F1F3F4;border-radius: 8px;">
          </li>
        </ul>
        <ul class="navbar-nav navbar-nav-right">
          <li class="nav-item dropdown">
            <a class="nav-link count-indicator dropdown-toggle" id="messageDropdown" href="#" data-toggle="dropdown" aria-expanded="false" style="padding: 5px;border-radius: 10px;">
              <i class="mdi mdi-file-document-box"></i>
              <span class="count">7</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="messageDropdown">
              <div class="dropdown-item">
                <p class="mb-0 font-weight-normal float-left">You have 7 unread mails
                </p>
                <span class="badge badge-info badge-pill float-right">View all</span>
              </div>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <img src="images/faces/face4.jpg" alt="image" class="profile-pic">
                </div>
                <div class="preview-item-content flex-grow">
                  <h6 class="preview-subject ellipsis font-weight-medium text-dark">David Grey
                    <span class="float-right font-weight-light small-text">1 Minutes ago</span>
                  </h6>
                  <p class="font-weight-light small-text">
                    The meeting is cancelled
                  </p>
                </div>
              </a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <img src="images/faces/face2.jpg" alt="image" class="profile-pic">
                </div>
                <div class="preview-item-content flex-grow">
                  <h6 class="preview-subject ellipsis font-weight-medium text-dark">Tim Cook
                    <span class="float-right font-weight-light small-text">15 Minutes ago</span>
                  </h6>
                  <p class="font-weight-light small-text">
                    New product launch
                  </p>
                </div>
              </a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <img src="images/faces/face3.jpg" alt="image" class="profile-pic">
                </div>
                <div class="preview-item-content flex-grow">
                  <h6 class="preview-subject ellipsis font-weight-medium text-dark"> Johnson
                    <span class="float-right font-weight-light small-text">18 Minutes ago</span>
                  </h6>
                  <p class="font-weight-light small-text">
                    Upcoming board meeting
                  </p>
                </div>
              </a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link count-indicator dropdown-toggle" id="notificationDropdown" href="#" data-toggle="dropdown" style="padding: 5px;border-radius: 10px;">
              <i class="mdi mdi-bell"></i>
              <span class="count">4</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="notificationDropdown">
              <a class="dropdown-item">
                <p class="mb-0 font-weight-normal float-left">You have 4 new notifications
                </p>
                <span class="badge badge-pill badge-warning float-right">View all</span>
              </a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-success">
                    <i class="mdi mdi-alert-circle-outline mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <h6 class="preview-subject font-weight-medium text-dark">Application Error</h6>
                  <p class="font-weight-light small-text">
                    Just now
                  </p>
                </div>
              </a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-warning">
                    <i class="mdi mdi-comment-text-outline mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <h6 class="preview-subject font-weight-medium text-dark">Settings</h6>
                  <p class="font-weight-light small-text">
                    Private message
                  </p>
                </div>
              </a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-info">
                    <i class="mdi mdi-email-outline mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <h6 class="preview-subject font-weight-medium text-dark">New user registration</h6>
                  <p class="font-weight-light small-text">
                    2 days ago
                  </p>
                </div>
              </a>
            </div>
          </li>
          <li class="nav-item dropdown d-none d-xl-inline-block">
            <a class="nav-link dropdown-toggle" id="UserDropdown" href="#" data-toggle="dropdown" aria-expanded="false" style="padding: 5px;border-radius: 10px;">
              <span class="profile-text">Hello, <?php echo $user; ?></span>
              <img class="img-xs rounded-circle" src="<?php echo base_url().'assets/images/prahar.jpg'; ?>" alt="Profile image">
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="UserDropdown">
              
              <a class="dropdown-item mt-2" href="#">
                Manage Profile
              </a>
              <a class="dropdown-item" href="#">
                Change Password
              </a>
              <a class="dropdown-item" href="#">
                Sign Out
              </a>
            </div>
          </li>
        </ul>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
          <span class="mdi mdi-menu"></span>
        </button>
      </div>
    </nav>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_sidebar.html -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar" style="font-weight: bold;color:#5F6368;position: fixed;border-right:1px solid #dadce0;">
        <ul class="nav">
          <li class="nav-item nav-profile">
            <div class="nav-link">
              <div class="user-wrapper">
                <div class="profile-image">
                  <img src="<?php echo base_url().'assets/images/prahar.jpg'; ?>" alt="profile image">
                </div>
                <div class="text-wrapper">
                  <p class="profile-name"><?php echo $user; ?></p>
                  <div>
                    <small class="designation text-muted"><?php echo $designation; ?></small>
                    <span class="status-indicator online"></span>
                  </div>
                </div>
              </div>
              
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<?php echo base_url().'main/index'; ?>">
              <i class="menu-icon mdi mdi-television"></i>
              <span class="menu-title">Dashboard</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
              <i class="menu-icon mdi mdi-content-copy"></i>
              <span class="menu-title">Events</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="ui-basic">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item">
                  <a class="nav-link" href="<?php echo base_url().'main/events/get/event_details'; ?>">Details</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="<?php echo base_url().'main/events/get/event_proposals'; ?>">Proposals</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="<?php echo base_url().'main/events/get/event_reports'; ?>">Reports</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="<?php echo base_url().'main/events/get/event_photos'; ?>" >Photos</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="<?php echo base_url().'main/events/get/event_registrations'; ?>" >Registration</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="<?php echo base_url().'main/events/get/event_coupons'; ?>" >Coupons</a>
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<?php echo base_url().'main/alumni_registrations'; ?>">
              <i class="menu-icon mdi mdi-sticker"></i>
              <span class="menu-title">Alumni Registrations</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<?php echo base_url().'main/messages'; ?>">
              <i class="menu-icon mdi mdi-sticker"></i>
              <span class="menu-title">Messages <i class="badge badge-danger">7</i></span>
            </a>
          </li>
          <li class="nav-item" >
            <a class="nav-link" href="<?php echo base_url().'main/user_mgmt'; ?>">
              <i class="menu-icon mdi mdi-sticker"></i>
              <span class="menu-title">User Management <i class="badge badge-info">20</i></span>
            </a>
          </li>
          <li class="nav-item" >
            <a class="nav-link" href="<?php echo base_url().'main/activity'; ?>">
              <i class="menu-icon mdi mdi-sticker"></i>
              <span class="menu-title">Activities Tracker</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<?php echo base_url().'main/contact'; ?>">
              <i class="menu-icon mdi mdi-sticker"></i>
              <span class="menu-title">Contact Us</span>
            </a>
          </li>
          <li class="nav-item" >
            <a class="nav-link" href="#">
              <i class="menu-icon mdi mdi-sticker"></i>
              <span class="menu-title">Logout</span>
            </a>
          </li>
          </ul>
      </nav>


      <!-- partial -->
      <div class="main-panel" style="margin-left: 255px;">
        <div class="content-wrapper">
          <div class="row" style="position: fixed;width:100%;z-index: 2;margin-top: -1.5rem;margin-left:-1.7rem;margin-right:-1.7rem;z-index: 500;background: #fff">

            <div class="col-12" style="padding:0;">
              <span class="d-block d-md-flex align-items-center" style="padding:0;border-bottom: 1px solid #dadce0;">
                <nav aria-label="breadcrumb">
                <ol class="breadcrumb">

                  <?php  echo $this->breadcrumb->output(); ?>
                </ol>
                </nav>
              </span>
            </div>
          </div>
          <div id="loader">
            <div class="row" style="margin-top:3rem;height: 75vh;">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 grid-margin stretch-card">
           <img id="loading" class="" src="<?php echo base_url().'assets/images/loading.gif'; ?>" alt="Loading...">
         </div></div>
          </div>
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