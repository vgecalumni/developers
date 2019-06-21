<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Test extends CI_Controller {

	
	// public function index()
	// {
	// 	// $this->load->model('main_model');
	// 	//echo $this->main_model->test();
	// 	// $data["designation"]="Admin";
	// 	// $data["user"]="Prahar Pandya";
		
	// 	// $this->load->library('breadcrumb');
	// 	// $this->breadcrumb->add('Home', base_url());
 //  		// $this->breadcrumb->add('Tutorials', base_url().'tutorials');

	// 	$this->load->model("main_model");
	// 	$data["fetch_data"] = $this->main_model->fetch_data();
	// 	// $this->load->view("admin_panel/fetch",$data);
	// 	// $this->dashboard();
	// 	$data["TITLE"] = "me";
	// 	$data["title1"] = "him";
	// 	$this->load->view("fetch.php",$data);
	// }
	public function image_upload()
	{
		$data['title']= "Upload Image using Ajax Jquery in CodeIgniter";
		$this->load->model("main_model");
		$data["image_data"] = $this->main_model->fetch_image();
		$this->load->view('fetch.php',$data);
	}
	//main/events/farewell/event_details
	public function ajax_upload()
	{
		if(isset($_FILES["image_file"]["name"]))
		{
			$config['upload_path'] = './upload/';
			$config['allowed_types'] = 'jpg|jpeg|png|gif';
			$this->load->library('upload',$config);
			if(!$this->upload->do_upload('image_file'))
			{
				echo $this->upload->display_errors();
			}
			else
			{
				$data = $this->upload->data();
				$config['image_library'] = 'gd2';
				$config['source_image'] = './upload/'.$data["file_name"];
				$config['create_thumb'] = FALSE;
				$config['maintain_ratio'] = FALSE;
				$config['quality'] = '60%';
				$config['width'] = 400;
				$config['height'] = 200;
				$config['new_image'] = './upload/'.$data["file_name"];
				$this->load->library('image_lib',$config);
				$this->image_lib->resize();
				$this->load->model('main_model');
				$image_data = array(
					'e_images' => $data["file_name"]
				);
				$this->main_model->insert_image($image_data);
				echo $this->main_model->fetch_image();

				// echo '<img src="'.base_url().'upload/'.$data["file_name"].'">';
			}
		}
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
				redirect('http://127.0.0.1/codeigniter/test/enter');
			}
			else
			{
				$this->session->set_flashdata('error','Invalid Username and Password');	
				redirect('http://127.0.0.1/codeigniter/test/login');
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
			echo '<h2>Welcome '.$this->session->userdata('username').'</h2>';
			echo '<a href="http://127.0.0.1/codeigniter/test/logout">Logout</a>';
		}
		else
		{
			redirect('http://127.0.0.1/codeigniter/test/login');
		}
	}

	public function logout()
	{
		$this->session->unset_userdata('username');
		redirect('http://127.0.0.1/codeigniter/test/login');
	}

	public function ForgetPassword()
	{
		$data['title'] = 'FORGOT YOUR PASSWORD?';
		$this->load->view('ForgetPassword',$data);
	}

	public function resetlink()
	{
		$username = $this->input->post('username');
		// $this->db->select("email");
		// $this->db->from("admin_users");
		// $this->db->where('user_name',$username);
		//$query = $this->db->get();
		//$query = $this->db->select('email')->from('admin_users')->where('user_name',$username)->get();

		// if($query->num_rows()>0)
		// {
		
		//  $row = $query->row_array();	
		 $from_email = "shivanibalwani3@gmail.com"; 
		 // $this->load->model('main_model');
   //       $pass = $this->main_model->forgot_pass_retrive($username);
         $this->load->model('main_model');
         $email = $this->main_model->user_email($username);


		      
         $this->load->library('email'); 
         
   
         $this->email->from($from_email, 'Shivani'); 
         $this->email->to($email);
         $this->email->subject('Email Test'); 
         $this->email->message("xcb"); 
   
         //Send mail 
         if($this->email->send()) 
         //$this->session->set_flashdata("email_sent","Email sent successfully."); 
         	echo "email has been sent";
         else 
         {
         	 show_error($this->email->print_debugger());
         }
         //$this->session->set_flashdata("email_sent","Error in sending Email."); 
         //$this->load->view('email_form'); 
		
	// }
	// 	else
	// 	{
	// 		redirect('http://127.0.0.1/codeigniter/test/ForgetPassword');
	// 	}
	}
}
?>