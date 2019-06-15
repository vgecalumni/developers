<div class="row" style="margin-top:3rem;">
            <div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 grid-margin stretch-card">
              <div class="card card-statistics" style="border:1px solid #dadce0;">
                <div class="card-body">
                  <div class="clearfix">
                    <div class="float-left">
                      <i class="mdi mdi-cube text-danger icon-lg"></i>
                    </div>
                    <div class="float-right">
                      <p class="mb-0 text-right">Total Events Completed</p>
                      <div class="fluid-container">
                        <h3 class="font-weight-medium text-right mb-0">
                            25
                        </h3>
                      </div>
                    </div>
                  </div>
                  <p class="text-muted mt-3 mb-0">
                    <i class="mdi mdi-alert-octagon mr-1" aria-hidden="true"></i> Total count of all successfull event completion
                  </p>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 grid-margin stretch-card">
              <div class="card card-statistics" style="border:1px solid #dadce0;">
                <div class="card-body">
                  <div class="clearfix">
                    <div class="float-left">
                      <i class="mdi mdi-receipt text-warning icon-lg"></i>
                    </div>
                    <div class="float-right">
                      <p class="mb-0 text-right">Pending Event Proposals</p>
                      <div class="fluid-container">
                        <h3 class="font-weight-medium text-right mb-0">1</h3>
                      </div>
                    </div>
                  </div>
                  <p class="text-muted mt-3 mb-0">
                    <i class="mdi mdi-bookmark-outline mr-1" aria-hidden="true"></i> It is necessary to check pending event proposals as early as possible to activate other people's work
                  </p>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 grid-margin stretch-card">
              <div class="card card-statistics" style="border:1px solid #dadce0;">
                <div class="card-body">
                  <div class="clearfix">
                    <div class="float-left">
                      <i class="mdi mdi-poll-box text-success icon-lg"></i>
                    </div>
                    <div class="float-right">
                      <p class="mb-0 text-right">Registrations Received </p>
                      <div class="fluid-container">
                        <h3 class="font-weight-medium text-right mb-0">1500</h3>
                      </div>
                    </div>
                  </div>
                  <p class="text-muted mt-3 mb-0">
                    <i class="mdi mdi-calendar mr-1" aria-hidden="true"></i> The count is based on latest event : Farewell 2019
                  </p>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 grid-margin stretch-card">
              <div class="card card-statistics" style="border:1px solid #dadce0;">
                <div class="card-body">
                  <div class="clearfix">
                    <div class="float-left">
                      <i class="mdi mdi-account-location text-info icon-lg"></i>
                    </div>
                    <div class="float-right">
                      <p class="mb-0 text-right">Coupons Issued</p>
                      <div class="fluid-container">
                        <h3 class="font-weight-medium text-right mb-0">1500</h3>
                      </div>
                    </div>
                  </div>
                  <p class="text-muted mt-3 mb-0">
                    <i class="mdi mdi-reload mr-1" aria-hidden="true"></i> The count is based on latest event : Farewell 2019
                  </p>
                </div>
              </div>
            </div>
          </div>
          <div class="row" id="progress">
            
            <div class="col-lg-12 grid-margin stretch-card">
              <div class="card" style="border:1px solid #dadce0;">
                <div class="card-body">
                  <h2 class="card-title text-primary mb-5">Yearly Progress Tracker [<b>2019</b>]</h2>
                  <div class="wrapper">
                    <div class="d-flex justify-content-between">
                      <p class="mb-2">Events Completed</p>
                      <p class="mb-2 text-primary">5/20</p>
                    </div>
                    <div class="progress">
                      <?php $percent = (5*100)/20; ?>
                      <div class="progress-bar bg-primary progress-bar-striped progress-bar-animated" role="progressbar" style="width: <?php echo $percent ?>%;" aria-valuenow="5"
                        aria-valuemin="0" aria-valuemax="20"></div>
                    </div>
                  </div>
                  
                  <br><br>
                  <div class="wrapper d-flex justify-content-between">
                    <div class="side-left">
                      <p class="mb-2">The Best Event of the Year</p>
                      <p class="display-3 mb-4 font-weight-light" style="font-size: 1.5rem;">Farewell 2019</p>
                    </div>
                    <div class="side-right">
                      <small class="text-muted">Calcualated on the basis of user involvement & efforts applied</small>
                    </div>
                  </div>
                  <br>
                  <div class="wrapper d-flex justify-content-between">
                    <div class="side-left">
                      <p class="mb-2">The Best Departmental Event of the Year</p>
                      <p class="display-3 mb-5 font-weight-light" style="font-size: 1.5rem;"  >Ignite</p>
                    </div>
                    <div class="side-right">
                      <small class="text-muted">Calcualated on the basis of user involvement & efforts applied</small>
                    </div>
                  </div>
                  
                </div>
              </div>
            </div>
          </div>
          <div class="row" id="score">
            <div class="col-lg-12 grid-margin">
              <div class="card" style="border:1px solid #dadce0;">
                <div class="card-body">
                  <h2 class="card-title text-primary mb-5">Farewell 2019 Status</h2>
                  <div class="table-responsive">
                    <table class="table table-bordered" id="myTable">
                      <thead>
                        <tr>
                          <th>
                            #
                          </th>
                          <th>
                            Activity name
                          </th>
                          <th>
                            Progress
                          </th>
                          <th>
                            Manager
                          </th>
                          <th>
                            Reviewed
                          </th>
                          <th>
                            Contact
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                        
                       

                                <tr>
                                  <td class="font-weight-medium">
                                    1
                                  </td>
                                  <td>
                                    Proposal Approval
                                  </td>
                                  <td>
                                    <div class="progress">
                                      <div class="progress-bar bg-primary progress-bar-striped" role="progressbar" style="width:100%" aria-valuenow="100" aria-valuemin="0"
                                        aria-valuemax="100"></div>
                                    </div>
                                  </td>
                                  <td>
                                    Vice President 
                                  </td>
                                  <td class="text-danger"> Yes
                                    
                                  </td>
                                  <td>
                                    <button class="btn btn-info">Contact</button>
                                  </td>
                                </tr>

                                <tr>
                                  <td class="font-weight-medium">
                                    2
                                  </td>
                                  <td>
                                    Banner Upload
                                  </td>
                                  <td>
                                    <div class="progress">
                                      <div class="progress-bar bg-primary progress-bar-striped" role="progressbar" style="width:100%" aria-valuenow="100" aria-valuemin="0"
                                        aria-valuemax="100"></div>
                                    </div>
                                  </td>
                                  <td>
                                    Design Team
                                  </td>
                                  <td class="text-danger"> Yes
                                    
                                  </td>
                                  <td>
                                    <button class="btn btn-info">Contact</button>
                                  </td>
                                </tr>

                                <tr>
                                  <td class="font-weight-medium">
                                    3
                                  </td>
                                  <td>
                                    Details Upload
                                  </td>
                                  <td>
                                    <div class="progress">
                                      <div class="progress-bar bg-primary progress-bar-striped" role="progressbar" style="width:100%" aria-valuenow="100" aria-valuemin="0"
                                        aria-valuemax="100"></div>
                                    </div>
                                  </td>
                                  <td>
                                    Editor Team
                                  </td>
                                  <td class="text-danger"> Yes
                                    
                                  </td>
                                  <td>
                                    <button class="btn btn-info">Contact</button>
                                  </td>
                                </tr>
                          
                                <tr>
                                  <td class="font-weight-medium">
                                    4
                                  </td>
                                  <td>
                                    Activate Registrations
                                  </td>
                                  <td>
                                    <div class="progress">
                                      <div class="progress-bar bg-primary progress-bar-striped" role="progressbar" style="width:100%" aria-valuenow="100" aria-valuemin="0"
                                        aria-valuemax="100"></div>
                                    </div>
                                  </td>
                                  <td>
                                    Registration Team
                                  </td>
                                  <td class="text-danger"> Yes
                                    
                                  </td>
                                  <td>
                                    <button class="btn btn-info">Contact</button>
                                  </td>
                                </tr>
                          
                                <tr>
                                  <td class="font-weight-medium">
                                    5
                                  </td>
                                  <td>
                                    Report Upload
                                  </td>
                                  <td>
                                    <div class="progress">
                                      <div class="progress-bar bg-primary progress-bar-striped" role="progressbar" style="width:100%" aria-valuenow="100" aria-valuemin="0"
                                        aria-valuemax="100"></div>
                                    </div>
                                  </td>
                                  <td>
                                    Editor Team
                                  </td>
                                  <td class="text-danger"> Yes
                                    
                                  </td>
                                  <td>
                                    <button class="btn btn-info">Contact</button>
                                  </td>
                                </tr>
                         
                                <tr>
                                  <td class="font-weight-medium">
                                    6
                                  </td>
                                  <td>
                                    Photos Upload
                                  </td>
                                  <td>
                                    <div class="progress">
                                      <div class="progress-bar bg-primary progress-bar-striped" role="progressbar" style="width:100%" aria-valuenow="100" aria-valuemin="0"
                                        aria-valuemax="100"></div>
                                    </div>
                                  </td>
                                  <td>
                                    Design Team
                                  </td>
                                  <td class="text-danger"> Yes
                                    
                                  </td>
                                  <td>
                                    <button class="btn btn-info">Contact</button>
                                  </td>
                                </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <script type="text/javascript">
            $(document).ready( function() {
  $('.step').each(function(index, element) {
    // element == this
    $(element).not('.active').addClass('done');
    $('.done').html('<i class="icon-ok"></i>');
    if($(this).is('.active')) {
      return false;
    }
  });    
});
          </script>



          
