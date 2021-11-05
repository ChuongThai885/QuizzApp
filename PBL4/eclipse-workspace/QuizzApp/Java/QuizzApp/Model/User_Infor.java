package QuizzApp.Model;

import java.util.Date;

public class User_Infor {
	private int ID;
	private String Name;
	private String Email;
	private String pass;
	private Date SignUp_Date;
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
	public Date getSignUp_Date() {
		return SignUp_Date;
	}
	public void setSignUp_Date(Date signUp_Date) {
		SignUp_Date = signUp_Date;
	}
	public User_Infor(String name, String email,String pass, Date signupdate)
	{
		this.Name = name;
		this.Email = email;
		this.pass = pass;
		this.SignUp_Date = signupdate;
	}
	public User_Infor() {
		this.Name = "";
		this.Email = "";
		this.SignUp_Date = new Date();
	}
	@Override
	public String toString() {
		return "User_Infor [Name=" + Name + ", Email=" + Email + ", SignUp_Date=" + SignUp_Date + "]";
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
}
