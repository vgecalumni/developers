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
		if($event_id == "get"){

			$data["title"] = "Events | VGEC ALUMNI";
			$data["request"] = "events";
			$data["email"]=$this->session->userdata('email');
			$this->db->select('*');
			$this->db->from('admin_users');

	    	$this->db->where('email', $data["email"]);
	    	$query = $this->db->get();

			$data["designation"]=$query->row()->role;
	    	$data["pic"] = $query->row()->profile_pic;
	    	$data["user_name"] = $query->row()->user_name;
				
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
			// require('Photos.php');
	  		// $obj = new Photos();
	  		// $obj->index($event_id);
	  		$data["title"] = "Event Photos | VGEC ALUMNI";
			$data["request"] = "event_photos";
			$data["email"]=$this->session->userdata('email');
			$this->db->select('*');
			$this->db->from('admin_users');

	    	$this->db->where('email', $data["email"]);
	    	$query = $this->db->get();

			$data["designation"]=$query->row()->role;
	    	$data["pic"] = $query->row()->profile_pic;
	    	$data["user_name"] = $query->row()->user_name;
	    	$data["event_id"]=$event_id;
	    	

			
			
			$this->load->library('breadcrumb');
			$this->breadcrumb->add('Home', base_url().'main/index');
			$this->breadcrumb->add('Events', base_url().'main/events/get/event_photos');
			$this->breadcrumb->add($event_id." Photos", base_url().'main/events/'.$event_id.'/event_photos');
			
			$this->load->view('layout/admin_panel/admin_panel_layout',$data);			
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
	public function alumni_registrations(){
		$data["title"] = "Alumni Registrations | VGEC ALUMNI";
		$data["request"] = "alumni_registrations";
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
		$this->breadcrumb->add('Alumni Registrations', base_url().'main/alumni_registrations');
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);	
	}
	public function messages(){
		$data["title"] = "Messages | VGEC ALUMNI";
		$data["request"] = "messages";
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
		$this->breadcrumb->add('Messages', base_url().'main/messages');
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);			
	}
	public function activity(){
		$data["title"] = "Activity Tracker | VGEC ALUMNI";
		$data["request"] = "activity";
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
		$this->breadcrumb->add('Activity', base_url().'main/activity');
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);	
	}
	public function contact(){
		$data["title"] = "Contact | VGEC ALUMNI";
		$data["request"] = "contact";
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
		$this->breadcrumb->add('Contact', base_url().'main/contact');
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);
	}
	public function user_mgmt(){
		$data["title"] = "User Management | VGEC ALUMNI";
		$data["request"] = "user_mgmt";
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
		$this->breadcrumb->add('User Management', base_url().'main/user_mgmt');
		$this->load->view('layout/admin_panel/admin_panel_layout',$data);	
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
