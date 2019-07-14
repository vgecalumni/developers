<?php
class Csv_import_model extends CI_Model
{
 function select()
 {
  $this->db->order_by('id', 'DESC');
  $query = $this->db->get('vaa_prof');
  return $query;
 }

 

 function insert($data)
 {
  $this->db->insert_batch('vaa_prof', $data);
 }

    
}

