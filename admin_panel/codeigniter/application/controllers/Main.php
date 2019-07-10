<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Main extends CI_Controller {

	
	public function index()
	{
		$data["title"] = "Dashboard | VGEC ALUMNI";
		$data["request"] = "dashboard";
		
		
		$data["email"]=$this->session->userdata('email');
		$this->db->select('*');
		$this->db->from('admin_users');

    	$this->db->where('email', $data["email"]);
    	$query = $this->db->get();

    	$data["designation"]=$query->row()->role;
    	$data["pic"] = $query->row()->profile_pic;
    	$data["user_name"] = $query->row()->user_name;
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
		$this->form_validation->set_rules('email','Email','required');
		$this->form_validation->set_rules('password','Password','required');
		if($this->form_validation->run())
		{
			//true
			$email = $this->input->post('email');
			$password = $this->input->post('password');
			//model function
			$this->load->model('main_model');
			if($this->main_model->can_login($email,$password))
			{
				$session_data = array(
					'email' => $email
				);
				$this->session->set_userdata($session_data);
				redirect(base_url().'main/enter');
			}
			else
			{
				$this->session->set_flashdata('error','Invalid Email and Password');	
				redirect(base_url().'main/login');
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
		if($this->session->userdata('email') != '')
		{
			redirect(base_url().'main/index');
			// echo '<h2>Welcome '.$this->session->userdata('username').'</h2>';
			// echo '<a href="http://127.0.0.1/admin_panel/codeigniter/main/logout">Logout</a>';
		}
		else
		{
			redirect(base_url().'main/login');
		}
	}

	public function ForgetPassword()
	{
		$data['title'] = 'FORGOT YOUR PASSWORD?';
		$this->load->view('ForgetPassword',$data);
	}

	public function resetlink()
	{
		$from_email = "shivanibalwani3@gmail.com";
		$email = $this->input->post('email');
		

		
		 $this->db->select("password");
		 $this->db->from("admin_users");
		 $this->db->where('email',$email);
		 $query=$this->db->get();
		 foreach ($query->result() as $row)
		 {
		 	$val = $row->password;
		 }
		 $this->load->library('email'); 
         
   
         $this->email->from($from_email,'Shivani'); 
         $this->email->to($email);
         $this->email->subject('Email Test'); 
         $this->email->message($val); 
   
         //Send mail 
         if($this->email->send()) 
         {
         	//$this->session->set_flashdata("email_sent","Email sent successfully."); 
         	echo "email has been sent";
         }
         else 
         {
         	 show_error($this->email->print_debugger());
         }
         
	}

	public function logout()
	{
		$this->session->unset_userdata('username');
		redirect(base_url().'main/login');
	}
}
