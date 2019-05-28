<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Coupons extends CI_Controller {

	
	public function index($event_id)
	{
		$data["title"] = "Event Coupons | VGEC ALUMNI";
		$data["request"] = "event_coupons";
		$data["designation"]="Admin";
		$data["user"]="Prahar Pandya";
		$data["event_id"]=$event_id;
		$this->load->library('breadcrumb');
		$this->breadcrumb->add('Home', base_url().'main/index');
		$this->breadcrumb->add('Events', base_url().'main/events/get/event_coupons');
		$this->breadcrumb->add($event_id." Coupons", base_url().'main/events/'.$event_id.'/event_coupons');
		
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);	
	}
	public function load_data(){
		
		$data = array(
		 'user' => $this->input->post('user'),
		 'event_id' => $this->input->post('event_id'),
		);
		$this->load->view('admin_panel/event_coupons',$data);
	}

	
}
