package QuizzApp.Model;

import java.util.Date;

public class User_Infor {
	
	private long ID;
	private String Name;
	private String Email;
	private String pass;
	private Date SignUp_Date;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Date getSignUp_Date() {
		return SignUp_Date;
	}
	public void setSignUp_Date(Date signUp_Date) {
		SignUp_Date = signUp_Date;
	}
	@Override
	public String toString() {
		return "User_Infor [ID=" + ID + ", Name=" + Name + ", Email=" + Email + ", pass=" + pass + ", SignUp_Date="
				+ SignUp_Date + "]";
	}
	
}
