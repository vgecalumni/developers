<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Csv_import extends CI_Controller {
 
 public function __construct()
 {
  parent::__construct();
  $this->load->model('csv_import_model');
  //$this->load->library('csvimport');
 }

 function index()
 {
  $this->load->view('csv_import');
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
           <th>e_id</th>
           <th>event</th>
           <th>firstName</th>
           <th>lastName</th>
           <th>company</th>
           <th>designation</th>
           <th>passingYear</th>
           <th>branch</th>
           <th>programme</th>
           <th>mobile</th>
           <th>email</th>
           <th>award</th>
           <th>cultural</th>
           <th>project</th>
           <th>status</th>
           <th>refcode</th>
           <th>refname</th>
           <th>refcount</th>
           <th>photo</th>
           <th>food</th>
           <th>hostelier</th>
           <th>gender</th>
           <th>enrollNo</th>
           <th>roomNo</th>
           <th>foodoption</th>
           <th>amount</th>
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
     <td>'.$count.'</td>
     <td>'.$row->id.'</td>
     <td>'.$row->e_id.'</td>
     <td>'.$row->event.'</td>
     <td>'.$row->firstName.'</td>
     <td>'.$row->lastName.'</td>
     <td>'.$row->company.'</td>
     <td>'.$row->designation.'</td>
     <td>'.$row->passingYear.'</td>
     <td>'.$row->branch.'</td>
     <td>'.$row->programme.'</td>
     <td>'.$row->mobile.'</td>
     <td>'.$row->email.'</td>
     <td>'.$row->award.'</td>
     <td>'.$row->cultural.'</td>
     <td>'.$row->project.'</td>
     <td>'.$row->status.'</td>
     <td>'.$row->refcode.'</td>
     <td>'.$row->refname.'</td>
     <td>'.$row->refcount.'</td>
     <td>'.$row->photo.'</td>
     <td>'.$row->food.'</td>
     <td>'.$row->hostelier.'</td>
     <td>'.$row->gender.'</td>
     <td>'.$row->enrollNo.'</td>
     <td>'.$row->roomNo.'</td>
     <td>'.$row->foodoption.'</td>
     <td>'.$row->amount.'</td>
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
          'e_id'  => $row["e_id"],
          'event'   => $row["event"],
          'firstName'   => $row["firstName"],
          'lastName' => $row["lastName"],
          'company'  => $row["company"],
          'designation'   => $row["designation"],
          'passingYear'   => $row["passingYear"],
          'branch' => $row["branch"],
          'programme'  => $row["programme"],
          'mobile'   => $row["mobile"],
          'email'   => $row["email"],
          'award' => $row["award"],
          'cultural'  => $row["cultural"],
          'project'   => $row["project"],
          'status'   => $row["status"],
          'refcode' => $row["refcode"],
          'refname'  => $row["refname"],
          'refcount'   => $row["refcount"],
          'photo'   => $row["photo"],
          'food' => $row["food"],
          'hostelier'  => $row["hostelier"],
          'gender'   => $row["gender"],
          'enrollNo'   => $row["enrollNo"],
          'roomNo' => $row["roomNo"],
          'foodoption'  => $row["foodoption"],
          'amount'   => $row["amount"]
   );
    $this->csv_import_model->insert($data);
  }
 }
 
  
}

