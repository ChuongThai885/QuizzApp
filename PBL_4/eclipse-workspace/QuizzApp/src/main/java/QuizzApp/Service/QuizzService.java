package QuizzApp.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import QuizzApp.Model.*;

public class QuizzService {
	
	DataService service = new DataService();
	
	public boolean Add_New_Quizz(int ID_User,Exam ex)
	{
		String query = "insert into quizzappdata.exercise (Topic, Ex_Name, ID_User) values (?,?,?)";
		try {
			PreparedStatement ps = CreateConnect().prepareStatement(query);
			ps.setString(1, ex.getTopic());
			ps.setString(2, ex.getName());
			ps.setInt(3, ID_User);
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("DB Ser: "+ e.getMessage());
			return false;
		}		
	}
	
	public ArrayList<Exam> Get_AllQuizzes(String email)
	{
		return service.Get_List_Exam_by_Email_User(email);
	}
	
	public boolean Update_Quizz(int ID_User,Exam ex)
	{
		return false;
	}
	
	public boolean Remove_Quizz(int ID_Quiz)
	{
		return false;
	}
	
	public boolean add_NewQuestion(int ID_Ex, Question ques, ArrayList<Answer> ans)
	{
		return false;
	}
	public ArrayList<Question> get_All_Questions(int ID_Quizz)
	{
		//
		return null;
	}
	public QuestionForm get_Question_Forms(int ID_Ques)
	{
		//
		return null;
	}
	public ArrayList<QuestionForm> get_List_QuestionForm_by_ID_Exam(int ID_Exam)
	{
		try
		{
			ArrayList<QuestionForm> arrayListQuestionForm = new ArrayList<QuestionForm>();
			ArrayList<Question> arrayListQuestion = service.Get_List_Quesion_by_ID_Exam(ID_Exam);
			for(int i = 0; i < arrayListQuestion.size(); i++)
			{
				QuestionForm questionForm = new QuestionForm();
				questionForm.setQues(arrayListQuestion.get(i));
				questionForm.setAns(service.Get_List_Answer_by_ID_Question(arrayListQuestion.get(i).getID()));
			    arrayListQuestionForm.add(questionForm);
			}
			return arrayListQuestionForm;
		}
		catch (Exception e) {
			System.out.println("Error get list question form: " + e.getMessage()); 
		}
		return null;
	}
	private Connection CreateConnect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:6033/quizzappdata";
			String name = "root";
			String pass = "matkhausql1@";
			return DriverManager.getConnection(url,name,pass);
		}catch (Exception e) {
			System.out.println("Error: " +e);
		}
		return null;
	}
}
