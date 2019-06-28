<?php
class Export_csv_model extends CI_Model
{

	function fetch_all()
	{
		$this->load->database();
		$this->db->select("id,	enrollNo,	firstName,	middleName,	lastName,	gender,	dob,	mobile,	emailID	,address	,city	,district	,state	,postalcode	,branch,	passingYear,	pic	,registered	,introduction	,account");
	
		$query = $this->db->get("vaa_prof");
		return $query;
	}

}
?>