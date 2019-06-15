<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Alumni_registrations extends CI_Controller {

	
	public function index()
	{
		$data["title"] = "Alumni Registrations | VGEC ALUMNI";
		$data["request"] = "alumni_registrations";
		$data["designation"]="Admin";
		$data["user"]="Prahar Pandya";
		$this->load->library('breadcrumb');
		$this->breadcrumb->add('Home', base_url().'main/index');
		$this->breadcrumb->add('Alumni Registrations', base_url().'main/alumni_registrations');
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);			
	}
	public function load_data(){
		
		$data = array(
		 'user' => $this->input->post('user'),
		);
		$this->load->view('admin_panel/alumni_registrations',$data);
	}

	
}
