package model;

public class MyThread extends Thread{
	String to,msg,subject,toContact,fromContact,SMSMsge;
	public void run(){
		MailFinal.send(this.to, this.subject, this.msg);
	}
	public void StartThread(String to, String msg, String subject){
		this.msg = msg ;
		this.to = to ;
		this.subject = subject ;
		this.start();
	}
	
	
}
