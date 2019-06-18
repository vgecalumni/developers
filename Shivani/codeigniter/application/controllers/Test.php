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
	
}
?>