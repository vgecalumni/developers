<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Reports extends CI_Controller {

	
	public function index()
	{
		$data["title"] = "Event Reports | VGEC ALUMNI";
		$data["request"] = "event_reports";
		$data["designation"]="Admin";
		$data["user"]="Prahar Pandya";
		$this->load->library('breadcrumb');
		$this->breadcrumb->add('Home', base_url().'main/index');
		$this->breadcrumb->add('Event Reports', base_url().'main/event_reports');
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);			
	}
	public function load_data(){
		
		$data = array(
		 'user' => $this->input->post('user'),
		);
		$this->load->view('admin_panel/event_reports',$data);
	}

	
}
