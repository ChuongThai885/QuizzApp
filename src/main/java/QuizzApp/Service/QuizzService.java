package QuizzApp.Service;

import java.util.ArrayList;

import QuizzApp.Model.*;

public class QuizzService {
	
	DataService service = new DataService();
	
	public boolean Add_New_Quizz(int ID_User,Exam ex)
	{
		return false;
	}
	
	public ArrayList<Exam> Get_All_Exam(String email)
	{
		return service.Get_List_Exam_by_Email_User(email);
	}

	public int Get_Number_Of_Question(int ID_Exam) 
	{
		ArrayList<Question> questions = service.Get_List_Quesion_by_ID_Exam(ID_Exam);
		int count = questions.size();
		return count;
	}
	
	public boolean Update_Exam(Exam ex)
	{
		return service.Update_Exam(ex);
	}
	
	public boolean Remove_Exam(int ID_Ex)
	{
		return service.Remove_Object("exercise",ID_Ex);
	}
	
	public boolean add_New_Question(int ID_Ex, QuestionForm form)
	{
		return service.add_New_Question(ID_Ex, form);
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
	
	public boolean Remove_Question(long ID_Question)
	{
		return service.Remove_Object("question", ID_Question);
	}
	
	public boolean Remove_Answer(long ID_Answer)
	{
		return service.Remove_Object("question", ID_Answer);
	}
}
