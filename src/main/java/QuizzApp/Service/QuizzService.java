package QuizzApp.Service;

import java.util.ArrayList;

import QuizzApp.Model.*;

public class QuizzService {
	
	DataService service = new DataService();
	
	public boolean Add_New_Quizz(int ID_User,Exam ex)
	{
		return false;
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
}
