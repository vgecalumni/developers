<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Dashboard extends CI_Controller {

	
	public function index()
	{
		$this->load->library('session');
		$data["title"] = "Dashboard | VGEC ALUMNI";
		$data["request"] = "dashboard";
		$data["designation"]="Admin";
		$data["user"]="Prahar Pandya";
		$this->load->library('breadcrumb');
		$this->breadcrumb->add('Home', base_url().'main/index');
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);			
	}
	public function load_data(){
		
		$this->load->library('session');
		$data = array(
		 'user' => $this->input->post('user'),
		);
		$this->load->view('admin_panel/dashboard',$data);
	}

	
}
