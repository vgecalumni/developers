<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Second extends CI_Controller {

	
	public function index()
	{
		$data["title"] = "Dashboard | VGEC ALUMNI";
		$data["request"] = "dashboard";
		$data["designation"]="Admin";
		$data["user"]="Prahar Pandya";
		$this->load->library('breadcrumb');
		$this->breadcrumb->add('Home', base_url().'main/index');
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);
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
		
	}
	public function login()
	{
		$data['title'] = 'codeigniter simple login form with sessions';
		$this->load->view("login",$data);
	}

	public function login_validation()
	{
		$this->load->library('form_validation');
		$this->load->helper('form');
		$this->form_validation->set_rules('username','Username','required');
		$this->form_validation->set_rules('password','Password','required');
		if($this->form_validation->run())
		{
			//true
			$username = $this->input->post('username');
			$password = $this->input->post('password');
			//model function
			$this->load->model('main_model');
			if($this->main_model->can_login($username,$password))
			{
				$session_data = array(
					'username' => $username
				);
				$this->session->set_userdata($session_data);
				redirect('http://127.0.0.1/admin_panel/codeigniter/main/enter');
			}
			else
			{
				$this->session->set_flashdata('error','Invalid Username and Password');	
				redirect('http://127.0.0.1/admin_panel/codeigniter/main/login');
			}
		}
		else
		{
			//false
			$this->login();
		}
	}

	public function enter()
	{
		if($this->session->userdata('username') != '')
		{
			// redirect('http://127.0.0.1/admin_panel/codeigniter/main/enter');
			echo '<h2>Welcome '.$this->session->userdata('username').'</h2>';
			echo '<a href="http://127.0.0.1/admin_panel/codeigniter/main/logout">Logout</a>';
		}
		else
		{
			redirect('http://127.0.0.1/admin_panel/codeigniter/main/login');
		}
	}

	public function logout()
	{
		$this->session->unset_userdata('username');
		redirect('http://127.0.0.1/admin_panel/codeigniter/main/login');
	}
}
