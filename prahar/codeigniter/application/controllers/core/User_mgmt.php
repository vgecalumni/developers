<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class User_mgmt extends CI_Controller {

	
	public function index()
	{
		$data["title"] = "User Management | VGEC ALUMNI";
		$data["request"] = "user_mgmt";
		$data["designation"]="Admin";
		$data["user"]="Prahar Pandya";
		$this->load->library('breadcrumb');
		$this->breadcrumb->add('Home', base_url().'main/index');
		$this->breadcrumb->add('User Management', base_url().'main/user_mgmt');
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);			
	}
	public function load_data(){
		
		$data = array(
		 'user' => $this->input->post('user'),
		);
		$this->load->view('admin_panel/user_mgmt',$data);
	}

	
}
