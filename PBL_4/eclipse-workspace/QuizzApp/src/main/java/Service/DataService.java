package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import Model.*;

public class DataService {
	public boolean Create_User(String name,String email,String pass)
	{
		String query = "insert into quizzappdata.users (User_Name,Pass,Email,SignUp_Date) values (?, ?, ?,current_timestamp())";
		try (PreparedStatement st = CreateConnect().prepareStatement(query))
		{
			st.setString(1, name);
			st.setString(2, pass);
			st.setString(3, email);
		    int res = st.executeUpdate();
			if(res >0) return true;
				return false;
		}catch (Exception e) {
			System.out.println("DB Ser: "+ e.getMessage());
			return false;
		}
	}
	
	public User_Infor Get_User(String Email)
	{
		String query ="Select * from quizzappdata.users where Email= ?";
		try (PreparedStatement st = CreateConnect().prepareStatement(query))
		{
			User_Infor user = new User_Infor();
			st.setString(1, Email);
			ResultSet res = st.executeQuery();
			while(res.next())
			{
				user.setID(res.getLong("ID"));
				user.setName(res.getString("User_Name"));
				user.setEmail(res.getString("Email"));
				user.setPass(res.getString("Pass"));
				user.setSignUp_Date(res.getTimestamp("SignUp_Date"));
				return user;
			}
			return user;
		}
		catch (Exception e) {
			System.out.println("DB Ser: "+ e.getMessage());
			return null;
		}
	}
	
	
	public boolean Update_User(String name, String email, String pass)
	{
		String query = "update quizzappdata.users set Pass = ?, User_Name = ? where Email= ?";
		try (PreparedStatement st = CreateConnect().prepareStatement(query))
		{
			st.setString(1, pass);
			st.setString(2, name);
			st.setString(3, email);
			int res = st.executeUpdate();
			if(res >0) return true;
				return false;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean Remove_User(String email)
	{
		String query = "delete quizzappdata.users where Email= ?";
		try (PreparedStatement st = CreateConnect().prepareStatement(query))
		{
			st.setString(1, email);
			int res = st.executeUpdate();
			if(res >0) return true;
				return false;
		}
		catch (Exception e) {
			System.out.println("Error remove: " +e.getMessage());
		}
		return false;
	}
	public boolean Add_New_Exam()
	{
		return false;
	}
	public List<Answer> Get_Ans_by_QuesID(int ID_Question)
	{
		return null;
	}
	
	public ResultSet GetData(String query)
	{
		try (Statement st = CreateConnect().createStatement())
		{
			return st.executeQuery(query);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public boolean ExecuteUpdate(String query)
	{
		try ( Statement st = CreateConnect().createStatement() )
		{
			int n = st.executeUpdate(query);
			if(n>0) return true;
			else return false;
		}catch (Exception e) {
			return false;
		}
	}
	private Connection CreateConnect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:6033/quizzappdata";
			String name = "root";
			String pass = "@chuongthai1357901";
			return DriverManager.getConnection(url,name,pass);
		}catch (Exception e) {
			System.out.println("Error: " +e);
		}
		return null;
	}
	
}
