package QuizzApp.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBHelper {
	public boolean CheckAccount(String username, String pass)
	{
		try {
			String str = "Select * from quizzappdata.users where Email= ? and Pass = ?";
			Connection con = CreateConnect();
			PreparedStatement st = con.prepareStatement(str);
			st.setString(1, username);
			st.setString(2, pass);
			ResultSet rs = st.executeQuery();
			if(rs.next())
				return true;
			else 
				return false;
		}catch (Exception e) {
			return false;
		}
	}
	public ResultSet GetData(String query)
	{
		return null;
	}
	
	public boolean ExecuteQuery(String query)
	{
		Connection con = CreateConnect();
		return false;
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
