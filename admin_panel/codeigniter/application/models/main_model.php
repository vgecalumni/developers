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
}
