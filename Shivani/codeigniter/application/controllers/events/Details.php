<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Details extends CI_Controller {

	
	public function index($event_id)
	{
		$data["title"] = "Event Details | VGEC ALUMNI";
		$data["request"] = "event_details";
		$data["designation"]="Admin";
		$data["user"]="Prahar Pandya";
		$data["event_id"]=$event_id;
		$this->load->library('breadcrumb');
		$this->breadcrumb->add('Home', base_url().'main/index');
		$this->breadcrumb->add('Events', base_url().'main/events/get/event_details');
		$this->breadcrumb->add($event_id." Details", base_url().'main/events/'.$event_id.'/event_details');
		
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);			
	}
	public function load_data(){
		
		$data = array(
		 'user' => $this->input->post('user'),
		 'event_id' => $this->input->post('event_id'),
		);
		$this->load->view('admin_panel/event_details',$data);
	}

	
}
