package QuizzApp.Service;

import java.sql.*;

public class DataService {
	public ResultSet GetUserInfor(String Email)
	{
		String query ="Select * from quizzappdata.users where Email= ?";
		try (PreparedStatement st = CreateConnect().prepareStatement(query);)
		{
			st.setString(1, Email);
			return st.executeQuery();
		}
		catch (Exception e) {
			return null;
		}
	}
	public boolean CreateAccount(String name,String email,String pass)
	{
		String str = "insert into quizzappdata.users (User_Name,Pass,Email,SignUp_Date) values (?, ?, ?,current_timestamp())";
		try (PreparedStatement st = CreateConnect().prepareStatement(str))
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
			Class.forName("com.mysql.jdbc.Driver");
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
