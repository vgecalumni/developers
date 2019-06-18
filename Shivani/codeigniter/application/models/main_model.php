<?php

class Main_model extends CI_Model {

	
	public function test()
	{
		echo "this is model";
	}
	public function fetch_data()
	{
		$query = $this->db->get("admin_users");
		return $query;
	}
	public function insert_image($data)
	{
		$this->db->insert("event_photos",$data);
	}
	public function fetch_image()
	{
		$output = '';
		$this->db->select("e_images");
		$this->db->from("event_photos");
		$this->db->order_by("id","DESC");
		$query = $this->db->get();
		foreach($query->result() as $row)
		{
			$output .= '
				<img src="'.base_url().'upload/'.$row->e_images.'"/>
			';
		}
		return $output;
	}
}	
