<nav class="sidebar sidebar-offcanvas" id="sidebar" style="font-weight: bold;color:#5F6368;position: fixed;border-right:1px solid #dadce0;">
        <ul class="nav">
          <li class="nav-item nav-profile">
            <div class="nav-link">
              <div class="user-wrapper">
                <div class="profile-image">
                  <img src="<?php echo $pic; ?>" alt="profile image">
                </div>
                <div class="text-wrapper">
                  <p class="profile-name"><?php echo $user_name; ?></p>
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
            <a class="nav-link" href="<?php echo base_url().'main/logout';?>">
              <i class="menu-icon mdi mdi-sticker"></i>
              <span class="menu-title">Logout</span>
            </a>
          </li>
          </ul>
      </nav>