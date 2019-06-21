<?php
class Export_event_model extends CI_Model
{

	function fetch_all()
	{
		$this->load->database();
		$this->db->select("id,	e_id,	event,	firstName	,lastName,	company	,designation,	passingYear,	branch	,programme,	mobile,	email,	award,	cultural,	project	,status,	refcode	,refname,	refcount,	photo,	food,	hostelier,	gender,	enrollNo	,roomNo,	foodoption,	amount");
	
		$query = $this->db->get("event_reg");
		return $query;
	}

}
?>