<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Events extends CI_Controller {

	
	public function index($event_id=null,$event_op=null)
	{

  		if($event_id == "get"){

			$data["title"] = "Events | VGEC ALUMNI";
			$data["request"] = "events";
			$data["designation"]="Admin";
			$data["user"]="Prahar Pandya";
			$data["event_op"]=$event_op;
			$this->load->library('breadcrumb');
			$this->breadcrumb->add('Home', base_url().'main/index');
			$this->breadcrumb->add('Events', base_url().'main/events');
			$this->load->view('layout/admin_panel/admin_panel_layout',$data);	

		}elseif ($event_op == "event_details") {
			require('Details.php');
  			$obj = new Details();
  			$obj->index($event_id);
		}elseif ($event_op == "event_proposals") {
			require('Proposals.php');
	  		$obj = new Proposals();
	  		$obj->index($event_id);
		}elseif ($event_op == "event_reports") {
			require('Reports.php');
	  		$obj = new Reports();
	  		$obj->index($event_id);
		}elseif ($event_op == "event_photos") {
			require('Photos.php');
	  		$obj = new Photos();
	  		$obj->index($event_id);
		}elseif ($event_op == "event_registrations") {
			require('Event_registrations.php');
	  		$obj = new Event_registrations();
	  		$obj->index($event_id);
		}elseif ($event_op == "event_coupons") {
			require('Coupons.php');
	  		$obj = new Coupons();
	  		$obj->index($event_id);
		}
	}
	public function load_data(){
		
		$data = array(
		 'user' => $this->input->post('user'),
		 'event_op' => $this->input->post('event_op'),
		);
		$this->load->view('admin_panel/events',$data);
	}

	
}
