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
	public function can_login($username, $password)
	{
		$this->db->where('user_name',$username);
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

	public function forgot_pass_retrive($username)
   {

		$this->db->select('password');
		$this->db->from('admin_users');
		$this->db->where('user_name',$username);
		$query=$this->db->get();
		    if ($query->num_rows() == 1)
		    {
		    $val = $query->row();
			return $val->password;     
		    }
		    else
		    {
		      return FALSE;
   			}
   	}
   	public function user_email($username)
   {

		$this->db->select('email');
		$this->db->from('admin_users');
		$this->db->where('user_name',$username);
		$query=$this->db->get();
		    if ($query->num_rows() == 1)
		    {
		    $val = $query->row();
			return $val->email;     
		    }
		    else
		    {
		      return FALSE;
   			}
   	}
}	
