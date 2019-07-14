<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Alumni_registrations extends CI_Controller {

	
	
	// public function load_data(){
		
	// 	$data = array(
	// 	 'user' => $this->input->post('user'),
	// 	);
	// 	$this->load->view('admin_panel/alumni_registrations',$data);
	// }
	 public function __construct()
 {
  parent::__construct();
  $this->load->model('csv_import_model');
  //$this->load->library('csvimport');
 }

 function index()
 {
  $this->load->view('export_import');
 }
 
function load_data()
 {
  $result = $this->csv_import_model->select();
  $output = '
   <h3 align="center">Imported User Details from CSV File</h3>
        <div class="table-responsive">
         <table class="table table-bordered table-striped">
          <tr>
           <th>id</th>
           <th>enrollNo</th>
           
           <th>firstName</th>
           <th>MiddleName</th>
           <th>lastName</th>
           <th>gender</th>
           <th>dob</th>
           <th>mobile</th>
           <th>emailID</th>
           <th>address</th>
           <th>city</th>
           <th>district</th>
           <th>state</th>
           <th>postalcode</th>
           <th>branch</th>
           <th>passingYear</th>
           <th>pic</th>
           <th>registered</th>
           <th>introduction</th>
           <th>account</th>
           
          </tr>
  ';
  $count = 0;
  if($result->num_rows() > 0)
  {
   foreach($result->result() as $row)
   {
    $count = $count + 1;
    $output .= '
    <tr>
     <td>'.$row->id.'</td>
     <td>'.$row->enrollNo.'</td>
     <td>'.$row->firstName.'</td>
     <td>'.$row->middleName.'</td>
     <td>'.$row->lastName.'</td>
     <td>'.$row->gender.'</td>
     <td>'.$row->dob.'</td>
     <td>'.$row->mobile.'</td>
     <td>'.$row->emailID.'</td>
     <td>'.$row->address.'</td>
     <td>'.$row->city.'</td>
     <td>'.$row->district.'</td>
     <td>'.$row->state.'</td>
     <td>'.$row->postalcode.'</td>
     <td>'.$row->branch.'</td>
     <td>'.$row->passingYear.'</td>
     <td>'.$row->pic.'</td>
     <td>'.$row->registered.'</td>
     <td>'.$row->introduction.'</td>
     <td>'.$row->account.'</td>
    </tr>
    ';
   }
  }
  else
  {
   $output .= '
   <tr>
       <td colspan="27" align="center">Data not Available</td>
      </tr>
   ';
  }
  $output .= '</table></div>';
  echo $output;
 }

 function import()
 {
  $file_data = $this->csvimport->get_array($_FILES["csv_file"]["tmp_name"]);
  foreach($file_data as $row)
  {
   $data[] = array(
          'id' => $row["id"],
          'enrollNo'  => $row["enrollNo"],
          'firstName'   => $row["firstName"],
          'middleName'   => $row["middleName"],
          'lastName' => $row["lastName"],
          'gender'  => $row["gender"],
          'dob'   => $row["dob"],
          'mobile'   => $row["mobile"],
          'emailID' => $row["emailID"],
          'address'  => $row["address"],
          'city'   => $row["city"],
          'district'   => $row["district"],
          'state' => $row["state"],
          'postalcode'  => $row["postalcode"],
          'branch'   => $row["branch"],
          'passingYear'   => $row["passingYear"],
          'pic' => $row["pic"],
          'registered'  => $row["registered"],
          'introduction'   => $row["introduction"],
          'account'   => $row["account"],
          
   );
    $this->csv_import_model->insert($data);
  }
 }


	
}
