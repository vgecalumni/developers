<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Export_csv extends CI_Controller {

	public function __construct()
	{
		parent::__construct();
		$this->load->model('export_csv_model');

	}
	function index()
	{
		$data['student_data'] = $this->export_csv_model->fetch_all();
		$this->load->view('export_csv', $data);
	}
	function export()
	{
		$file_name = 'student_details_on_'.date('Ymd').'.csv';
		header("Content-Description: File Transfer");
		header("Content-Disposition: attachment; filename=$file_name");
		header("Content-Type: application/csv;");
		$student_data = $this->export_csv_model->fetch_all();

		$file = fopen('php://output', 'w');

		$header = array("Id",	"enroll No",	"first Name",	"middle Name",	"last Name",	"Gender",	"Bdate",	"Mobile"	,"email ID"	,"Address"	,"City"	,"District"	,"State"	,"postal code"	,"Branch",	"passing Year",	"Pic"	,"Registered"	,"Introduction"	,"Account" );

		fputcsv($file, $header);

		foreach ($student_data->result_array() as $key => $value) {
			# code...
			fputcsv($file, $value);

		}
		fclose($file);
		exit;
	}
}
