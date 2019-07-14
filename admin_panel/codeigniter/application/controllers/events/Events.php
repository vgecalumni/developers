<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Events extends CI_Controller {

	
	
	public function load_data(){
		
		$data = array(
		 'user' => $this->input->post('user'),
		 'event_op' => $this->input->post('event_op'),
		);
		$this->load->view('admin_panel/events',$data);
	}
// 			$this->db->select('e_name');
// 			$this->db->from('event_main');
// 			$query = $this->db->get();
// 			foreach($query->result() as $row)
// 			{
// 			$data["e_name"] = $row->e_name;
// 			}

	
}
