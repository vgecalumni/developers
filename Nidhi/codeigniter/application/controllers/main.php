<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Main extends CI_Controller {
	public function index()
	{
		$this->load->model('main_model');
		$this->load->view("main_view");
		//echo $this->main_model->test_main();
	}
	public function form_validation()
	{
		$this->load->library('form_validation');
		$this->form_validation->set_rules("email","email",'required');
		$this->form_validation->set_rules("passward","passward",'required');
	}

}?>
