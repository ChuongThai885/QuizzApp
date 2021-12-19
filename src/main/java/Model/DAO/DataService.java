package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import Model.Bean.Answer;
import Model.Bean.Exam;
import Model.Bean.Question;
import Model.Bean.QuestionForm;
import Model.Bean.User_Infor;

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
	
	public boolean Add_New_Exam(int ID_User, Exam ex)// đoá, để v đi cho đẹp :3
	{
		String query = "insert into quizzappdata.exercise (Topic, Ex_Name, ID_User) values (?,?,?)";
		try (PreparedStatement ps = CreateConnect().prepareStatement(query))
		{
			ps.setString(1, ex.getTopic());
			ps.setString(2, ex.getName());
			ps.setInt(3, ID_User);
			ps.execute();
			return true;
		} catch (Exception e) {
			System.out.println("DB Ser: "+ e.getMessage());
			return false;
		}		
	}
	
	public ArrayList<Exam> Get_List_Exam_by_Email_User(String email)
	{
		String query = "SELECT * FROM quizzappdata.exercise where ID_User = (Select ID from quizzappdata.users where Email = ?)";
		try (PreparedStatement st = CreateConnect().prepareStatement(query))
		{
			st.setString(1, email);
			ArrayList<Exam> le = new ArrayList<Exam>();			
			ResultSet res = st.executeQuery();
			while(res.next())
			{
				Exam ex = new Exam();
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
	
	public boolean Update_Exam(Exam ex)
	{
		String query = "update quizzappdata.exercise set Topic = ?, Ex_Name=? where ID =?";
		try (PreparedStatement statement = CreateConnect().prepareStatement(query))
		{
			statement.setString(1, ex.getTopic());
			statement.setString(2, ex.getName());
			statement.setString(3, "" + ex.getID());
			int result = statement.executeUpdate();
			if(result > 0)
				return true;
		}
		catch (Exception e) {
			System.out.println("Update error: " + e);
		}
		return false;
	}
	
	public boolean add_New_Question(int ID_Ex, QuestionForm form)
	{
		String query = "insert into quizzappdata.question (Ques,Is_Multi,Countdown_Time,ID_Ex) values (?,?,?,?);";
		// add question first
		try (PreparedStatement statement = CreateConnect().prepareStatement(query))
		{
			statement.setString(1, form.getQues().getQues());
			if (form.getQues().isIs_Multi() == false) statement.setInt(2, 0);
			statement.setString(3, "" + form.getQues().getCountdown_Time());
			statement.setString(4, "" + ID_Ex);
			int result = statement.executeUpdate();
			if(result < 1)
				return false;
		}
		catch (Exception e) {
			System.out.println("add new ques : "+ e);
			return false;
		}
		
		try
		{
			for(Answer i : form.getAns() )
			{
				// check if answer is exist
				query = "SELECT * FROM quizzappdata.answers where ID_Ques = (SELECT ID FROM quizzappdata.question where Ques = ? ) and Answer = ? ";
				try (PreparedStatement statement = CreateConnect().prepareStatement(query))
				{
					statement.setString(1, form.getQues().getQues());
					statement.setString(2, i.getAns());
					ResultSet resultSet = statement.executeQuery();
					if(resultSet.next())
						return false;
				}
				catch (Exception e1) {
					System.out.println("check ans : " + e1);
					return false;
				}
				//if not exist
				query = "insert into quizzappdata.answers(Answer,Selected,ID_Ques) values ( ?, ?, (SELECT ID FROM quizzappdata.question where Ques = ?) );";
				try (PreparedStatement statement = CreateConnect().prepareStatement(query))
				{
					statement.setString(1, i.getAns());
					if (i.isSelected() ==  false) {
						statement.setInt(2, 0);
					}
					else {
						statement.setInt(2, 1);
					}
					statement.setString(3, form.getQues().getQues());
					int result = statement.executeUpdate();
					if(result < 1)
						return false;
				}
				catch (Exception e1) {
					System.out.println("Add ans : " + e1);
					return false;
				}
			}
		}
		catch (Exception e) {
			System.out.println("add answers :" + e);
		}
		return false;
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
	
	public boolean Remove_Object(String name_object,long ID)
	{
		String query = "delete from quizzappdata."+name_object+" where ID=" + ID;
		System.out.println(query);
		try (Statement statement = CreateConnect().createStatement())
		{
			int res = statement.executeUpdate(query);
			if(res >0) return true;
				return false;
		}
		catch (Exception e) {
			System.out.println("Error remove object: " +e.getMessage());
		}
		return false;
	}

	public Exam Get_Exam_By_IDExam(int iD_Exam) {
		String query = "SELECT * FROM quizzappdata.exercise where ID=?";
		try (PreparedStatement st = CreateConnect().prepareStatement(query))
		{
			st.setInt(1, iD_Exam);
			Exam ex = new Exam();
			ResultSet res = st.executeQuery();
			while(res.next())
			{
				ex.setID(res.getInt("ID"));
				ex.setTopic(res.getString("Topic"));
				ex.setName(res.getString("Ex_Name"));
				ex.setID_User(res.getInt("ID_User"));
				System.out.println(ex.toString());				
			}
			return ex;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public boolean Update_Question(int iD_Ques, QuestionForm form) {
		String query = "update quizzappdata.question set Ques=?, Countdown_Time=? where ID=?";
		try (PreparedStatement st = CreateConnect().prepareStatement(query)){
			st.setString(1, form.getQues().getQues());
			st.setInt(2, form.getQues().getCountdown_Time());
			st.setInt(3, iD_Ques);
			int result = st.executeUpdate();
			if(result < 1)
				return false;
		} catch (Exception e) {
			System.out.println("update question: " + e.getMessage());
			return false;
		}
		
		try {
			for (Answer a : form.getAns()) {
				query = "update quizzappdata.answers set Answer=?, Selected=? where ID=?";
				try (PreparedStatement st = CreateConnect().prepareStatement(query)){
					st.setString(1, a.getAns());
					if (a.isSelected()==false) st.setInt(2, 0);
					else st.setInt(2, 1);
					st.setLong(3, a.getID());
					int result = st.executeUpdate();
					if(result < 1)
						return false;
				} catch (Exception e) {
					System.out.println("Update answer: " + e.getMessage());
					return false;
				}
			}						
		} catch (Exception e) {
			System.out.println("update answer: " + e.getMessage());
			return false;
		}
		return false;
	}

	public boolean Add_New_Anwser(int iD_Ques, Answer a) {
		String query = "insert into quizzappdata.answers(Answer,Selected,ID_Ques) values (?, ?, ?);";
		try (PreparedStatement statement = CreateConnect().prepareStatement(query))
		{
			statement.setString(1, a.getAns());
			if (a.isSelected() ==  false) {
				statement.setInt(2, 0);
			}
			else {
				statement.setInt(2, 1);
			}
			statement.setInt(3, iD_Ques);
			int result = statement.executeUpdate();
			if(result < 1)
				return false;
		} catch (Exception e) {
			System.out.println("Add answer: " + e.getMessage());
			return false;
		}
		return false;
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
