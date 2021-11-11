package QuizzApp.Service;

import java.util.ArrayList;

import QuizzApp.Model.*;

public class QuizzService {
	public boolean Add_New_Quizz(int ID_User,Exam ex)
	{
		return false;
	}
	
	public ArrayList<Exam> Get_AllQuizzes(int ID_User)
	{
		//
		return null;
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
			DataService service = new DataService();
			ArrayList<QuestionForm> lqf = new ArrayList<QuestionForm>();
			ArrayList<Question> lq = service.Get_List_Quesion_by_ID_Exam(ID_Exam);
			QuestionForm qf = new QuestionForm();
			for(Question i : lq)
			{
				qf.setQues(i);
				qf.setAns(service.Get_List_Answer_by_ID_Question(i.getID()));
				lqf.add(qf);
			}
			return lqf;
		}
		catch (Exception e) {
			System.out.println("Error get list question form: " + e.getMessage()); 
		}
		return null;
	}
}
