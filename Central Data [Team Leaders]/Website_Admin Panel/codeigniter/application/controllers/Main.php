<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Main extends CI_Controller {

	
	public function index()
	{
		// $this->load->model('main_model');
		//echo $this->main_model->test();
		// $data["designation"]="Admin";
		// $data["user"]="Prahar Pandya";
		
		// $this->load->library('breadcrumb');
		// $this->breadcrumb->add('Home', base_url());
  		// $this->breadcrumb->add('Tutorials', base_url().'tutorials');

		$this->dashboard();
	}
	//main/events/farewell/event_details
	public function events($event_id=null,$event_op=null){
		require('events/Events.php');
  		$obj = new Events();
  		$obj->index($event_id,$event_op);
	}
	public function alumni_registrations(){
		require('alumni/Alumni_registrations.php');
  		$obj = new Alumni_registrations();
  		$obj->index();
	}
	public function messages(){
		require('advanced/Messages.php');
  		$obj = new Messages();
  		$obj->index();
	}
	public function activity(){
		require('advanced/Activity.php');
  		$obj = new Activity();
  		$obj->index();
	}
	public function contact(){
		require('core/Contact.php');
  		$obj = new Contact();
  		$obj->index();
	}
	public function user_mgmt(){
		require('core/User_mgmt.php');
  		$obj = new User_mgmt();
  		$obj->index();
	}
	public function dashboard()
	{

		require('core/Dashboard.php');
  		$obj = new Dashboard();
  		$obj->index();
		// $data = array(
		// 'user' => $this->input->post('user'),
		// );
		// $this->load->view('admin_panel/dashboard',$data);
		//Either you can print value or you can send value to database
		// echo json_encode($data);
	}
}
