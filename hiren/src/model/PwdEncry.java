package model;
import org.mindrot.jbcrypt.BCrypt;

public class PwdEncry {

	public static String getEncryPwd(String Pwd)
	 {
		  	String Hashpwd=BCrypt.hashpw(Pwd, BCrypt.gensalt(12));
	        return Hashpwd;
	 }
	
	public static boolean checkEncryPwd(String Pwd,String Hashpwd)
	{
		boolean i;
		i=BCrypt.checkpw(Pwd, Hashpwd);
		return i;
	}
	
}
