<?php

class Main_model extends CI_Model {

	
	public function test()
	{
		echo "this is model";
	}
	public function can_login($email, $password)
	{
		$this->db->where('email',$email);
		$this->db->where('password',$password);
		$query = $this->db->get('admin_users');

		if($query->num_rows()>0)
		{
			return true;
		}
		else
		{
			return false;
		}
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
	public function insert_image($data)
	{
		$this->db->insert("event_photos",$data);
	}
}
