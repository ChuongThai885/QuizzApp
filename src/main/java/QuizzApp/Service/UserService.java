package QuizzApp.Service;

import QuizzApp.Model.*;

public class UserService {
	
	public boolean Is_Exist_User(String email, String pass)
	{
		try
		{
			User_Infor user = new DataService().Get_User(email);
			if(user != null)
			{
				String check_pass = user.getPass();
				String password = "";
				password = new EncodeService().decodeString(check_pass);
				System.out.println(password+","+ pass);
				if(password.compareTo(pass) == 0) return true;
				else return false;
			}
			return false;
		}
		catch (Exception e) {
			System.out.println("Check Account error: " + e);
			return false;
		}
	}

	public User_Infor Get_User(String Email)
	{
		return new DataService().Get_User(Email);
	}
	
	public boolean Create_User(String name,String email,String pass)
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
		return new DataService().Create_User(name, email, pass);
	}
	
	public boolean Remove_User(String email, String pass)
	{
		try
		{
			DataService service = new DataService();
			User_Infor user = service.Get_User(email);
			String encodepass = "";
			encodepass = new EncodeService().encodeString(pass);
			if(encodepass != user.getPass()) return false;
			else return new DataService().Remove_User(email);
		}catch (Exception e1) {
			System.out.println("Error encode: " +e1.getMessage());
		}
		return false;
	}
	
	public boolean Update_User(String name, String email, String pass)
	{
		try
		{
			DataService service = new DataService();
			User_Infor user = service.Get_User(email);
			String encodepass = "";
			encodepass = new EncodeService().encodeString(pass);
			if(encodepass != user.getPass()) return false;
			else return new DataService().Update_User(name, email, encodepass);
		}
		catch (Exception e) {
			System.out.println("Error update: " +e.getMessage());
		}
		return false;
	}
	
	public boolean Refresh_PassWord(String email, String newpass)
	{
		try
		{
			try
			{
				String encodepass = "";
				encodepass = new EncodeService().encodeString(newpass);
				if(encodepass != "")
					newpass = encodepass;
			}catch (Exception e1) {
				System.out.println("Error encode: " +e1.getMessage());
			}
			DataService service = new DataService();
			User_Infor user = service.Get_User(email);
			return service.Update_User(user.getName(), email, newpass);
		}
		catch (Exception e) {
			System.out.println("Error refresh pass: " +e.getMessage());
		}
		return false;
	}
}
