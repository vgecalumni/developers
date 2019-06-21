<?php
class Forms extends CI_Controller
{
	public function __construct()
	{
		/*call CodeIgniter's default Constructor*/
		parent::__construct();
		/*load database libray manually*/
		$this->load->database();
		$this->load->library('session');
		/*load Model*/
		$this->load->helper('url');
		$this->load->model('Hello_model');
	}

   public function forgot_pass()
	{
		if($this->input->post('forgot_pass'))
		{
			$email=$this->input->post('email');
			$que=$this->db->query("select password,email from admin_users where email='$email'");
			$row=$que->row();
			$user_email=$row->email;
			if((!strcmp($email, $user_email))){
			$pass=$row->password;
				/*Mail Code*/
				$to = $user_email;
				$subject = "Password";
				$txt = "Your password is $pass .";
				$headers = "From: password@example.com" . "\r\n" .
				"CC: ifany@example.com";

				mail($to,$subject,$txt,$headers);
				}
			else{
			$data['error']="
Invalid Email ID !
";
			}

	}
	   $this->load->view('forgot_pass',@$data);
   }



}
?>
