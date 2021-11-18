package QuizzApp.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import QuizzApp.Model.*;

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
				user.setID(res.getInt("ID"));
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
	
	public ArrayList<Exam> Get_List_Exam_by_Email_User(String email)
	{
		String query = "SELECT * FROM quizzappdata.exercise where (Select ID from quizzappdata.users where Email = ?)";
		try (PreparedStatement st = CreateConnect().prepareStatement(query))
		{
			st.setString(1, email);
			ArrayList<Exam> le = new ArrayList<Exam>();
			Exam ex = new Exam();
			ResultSet res = st.executeQuery();
			while(res.next())
			{
				ex.setID(res.getInt("ID"));
				ex.setTopic(res.getString("Topic"));
				ex.setName(res.getString("Ex_Name"));
				ex.setID_User(res.getInt("ID_User"));
				System.out.println(ex.toString());
				le.add(ex);
			}
			return le;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public ArrayList<Question> Get_List_Quesion_by_ID_Exam(int ID_Exam)
	{
		try (Statement st = CreateConnect().createStatement())
		{
			String query = "SELECT * FROM quizzappdata.question where ID_Ex =" + ID_Exam;
			ResultSet res = st.executeQuery(query);
			ArrayList<Question> arrayList = new ArrayList<Question>();
			while(res.next())
			{
				Question question = new Question();
				question.setID(res.getLong("ID"));
				question.setQues(res.getString("Ques"));
				question.setIs_Multi(res.getBoolean("Is_Multi"));
				question.setCountdown_Time(res.getInt("Countdown_Time"));
				question.setID_Ex(res.getInt("ID_Ex"));
				arrayList.add(question);
			}
//			for(Question as : arrayList)
//			{
//				System.out.println(as.toString());
//			}
			return arrayList;
		}
		catch (Exception e) {
			System.out.println("Error get list question: "+e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Answer> Get_List_Answer_by_ID_Question(long ID_Question)
	{
		try (Statement st = CreateConnect().createStatement())
		{
			ResultSet res = st.executeQuery("SELECT * FROM quizzappdata.answers where ID_Ques =" + ID_Question);
			ArrayList<Answer> arrayList = new ArrayList<Answer>();
			while(res.next())
			{
				Answer answer = new Answer();
				answer.setID(res.getLong("ID"));
				answer.setSelected(res.getBoolean("Selected"));
				answer.setAns(res.getString("Answer"));
				answer.setID_Question(res.getLong("ID_Ques"));
				arrayList.add(answer);
			}
//			for(Answer as : arrayList)
//			{
//				System.out.println(as.toString());
//			}
			return arrayList;
		}
		catch (Exception e) {
			System.out.println("Error get list ans :" + e.getMessage());
		}
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
