package QuizzApp.Service;

import java.sql.ResultSet;

import QuizzApp.Model.*;

public class UserService {
	public boolean CheckAccount(String email, String pass)
	{
		try
		{
			ResultSet res = new DataService().GetUserInfor(email);
			if(res.next())
			{
				String password = "";
				password = new EncodeService().decodeString(res.getString("Pass"));
				System.out.println(password+","+ pass);
				if(password.compareTo(pass) == 0) return true;
				else return false;
			}
			return false;
		}
		catch (Exception e) {
			System.out.println("Check Account error: " + e.getMessage());
			return false;
		}
	}

	public User_Infor GetUserInfor(String Email)
	{
		try
		{
			ResultSet res = new DataService().GetUserInfor(Email);
			if(res.next())
			{
				return new User_Infor(res.getString("User_Name"),res.getString("Email"),res.getTimestamp("SignUp_Date"));
			}
			else return new User_Infor();
		}
		catch (Exception e) {
			return new User_Infor();
		}
	}
	public boolean CreateAccount(String name,String email,String pass)
	{
		try
		{
			String encodepass = "";
			encodepass = new EncodeService().encodeString(pass);
			if(encodepass != "")
				pass = encodepass;
		}catch (Exception e) {
			System.out.println("Create account error: " +e.getMessage());
		}
		return new DataService().CreateAccount(name, email, pass);
	}
	
}
