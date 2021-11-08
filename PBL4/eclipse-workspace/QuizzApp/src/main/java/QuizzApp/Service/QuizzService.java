package QuizzApp.Service;


import java.util.List;

import QuizzApp.Model.Answer;
import QuizzApp.Model.Exercise;
import QuizzApp.Model.Question;
import QuizzApp.Model.Question_Form;

public class QuizzService {
	
	public boolean Add_New_Quizz(long ID_User,Exercise ex)
	{
		return false;
	}
	
	public List<Exercise> Get_AllQuizzes(long ID_User)
	{
		//
		return null;
	}
	
	public boolean Update_Quizz(int ID_User,Exercise ex)
	{
		return false;
	}
	
	public boolean Remove_Quizz(int ID_Quiz)
	{
		return false;
	}
	
	public boolean add_NewQuestion(int ID_Ex, Question ques, List<Answer> ans)
	{
		return false;
	}
	public List<Question> get_All_Questions(int ID_Quizz)
	{
		//
		return null;
	}
	public Question_Form get_Question_Forms(int ID_Ques)
	{
		//
		return null;
	}
}
