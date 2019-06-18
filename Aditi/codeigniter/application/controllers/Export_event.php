<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Export_event extends CI_Controller {

  public function __construct()
  {
    parent::__construct();
    $this->load->model('export_event_model');

  }
  function index()
  {
    $data['student_data'] = $this->export_event_model->fetch_all();
    $this->load->view('export_event', $data);
  }
  function export()
  {
    $file_name = 'student_details_on_'.date('Ymd').'.csv';
    header("Content-Description: File Transfer");
    header("Content-Disposition: attachment; filename=$file_name");
    header("Content-Type: application/csv;");
    $student_data = $this->export_event_model->fetch_all();

    $file = fopen('php://output', 'w');

    $header = array("Id", "E_id",  "Event", "first Name", "last Name",  "Company", "Designation", "passing Year", "Branch",  "Programme" ,"Mobile"  ,"Email", "Award", "Cultural",  "Project",         "Status"  ,"Refcode", "Refname", "Refcount",  "Photo", "Food",  "Hostelier", "Gender",  "EnrollNo", "RoomNo",  "Foodoption",  "Amount" );

    fputcsv($file, $header);

    foreach ($student_data->result_array() as $key => $value) {
      # code...
      fputcsv($file, $value);

    }
    fclose($file);
    exit;
  }
}
