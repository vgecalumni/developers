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
		// $this->load->view('admin_panel/event_photos',$data);
		$data['title']= "Upload Image using Ajax Jquery in CodeIgniter";
		$this->load->model("main_model");
		$data["image_data"] = $this->main_model->fetch_image();
		$this->load->view('admin_panel/event_photos',$data);
	}

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
