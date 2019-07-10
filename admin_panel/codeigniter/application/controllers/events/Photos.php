<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Photos extends CI_Controller {

	
	public function index($event_id)
	{
		$data["title"] = "Event Photos | VGEC ALUMNI";
		$data["request"] = "event_photos";
		$data["designation"]="Admin";
		$data["user"]="Prahar Pandya";
		$data["event_id"]=$event_id;
		$this->load->library('breadcrumb');
		$this->breadcrumb->add('Home', base_url().'main/index');
		$this->breadcrumb->add('Events', base_url().'main/events/get/event_photos');
		$this->breadcrumb->add($event_id." Photos", base_url().'main/events/'.$event_id.'/event_photos');
		
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);			
	}
	public function load_data(){
		
		$data = array(
		 'user' => $this->input->post('user'),
		 'event_id' => $this->input->post('event_id'),
		);
		$this->load->view('admin_panel/event_photos',$data);
	}

	
}
