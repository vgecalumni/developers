<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Contact extends CI_Controller {

	
	public function index()
	{
		$data["title"] = "Contact | VGEC ALUMNI";
		$data["request"] = "contact";
		$data["designation"]="Admin";
		$data["user"]="Prahar Pandya";
		$this->load->library('breadcrumb');
		$this->breadcrumb->add('Home', base_url().'main/index');
		$this->breadcrumb->add('Contact', base_url().'main/contact');
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);			
	}
	public function load_data(){
		
		$data = array(
		 'user' => $this->input->post('user'),
		);
		$this->load->view('admin_panel/contact',$data);
	}

	
}
