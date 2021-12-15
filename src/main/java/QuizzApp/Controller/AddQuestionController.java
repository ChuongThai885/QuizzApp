package QuizzApp.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QuizzApp.Model.Answer;
import QuizzApp.Model.Exam;
import QuizzApp.Model.Question;
import QuizzApp.Model.QuestionForm;
import QuizzApp.Service.QuizzService;

public class AddQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddQuestionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String question = request.getParameter("txtQues");
		String time = request.getParameter("selectTime");
		String trueans = request.getParameter("trueAns");
		String ans1 = request.getParameter("txtAns1");
		String ans2 = request.getParameter("txtAns2");
		String ans3 = request.getParameter("txtAns3");
		String ans4 = request.getParameter("txtAns4");
		String id = request.getParameter("idquiz");
		int ID_Ex = Integer.parseInt(id);
		System.out.println(question+", "+time+", "+trueans+", "+ans1+", "+ans2+", "+ans3+", "+ans4);
		String la1[] = {ans1, ans2, ans3, ans4};
		QuestionForm form = new QuestionForm();
		Question ques = new Question();
		QuizzService quizservice = new QuizzService();
		ArrayList<Answer> la = new ArrayList<Answer>();
		
		if (question.equals("") || time.equals("") || trueans.equals("") || 
				ans1.equals("") || ans2.equals("") || ans3.equals("") || ans4.equals("")) {
			response.sendRedirect("AddQues.jsp");
		}
		else {
			
			ques.setQues(question);
			ques.setCountdown_Time(Integer.parseInt(time));
			ques.setID_Ex(ID_Ex);
			ques.setIs_Multi(false);
			form.setQues(ques);
			for (int i = 0; i<4; i++) {
				Answer a = new Answer();
				a.setAns(la1[i]);
				if (Integer.parseInt(trueans)==i) a.setSelected(true);
				else a.setSelected(false);
				System.out.println(a.toString());				
				la.add(a);
			}
			form.setAns(la);
			System.out.println(form.getQues().getQues() + ", " + form.getAns()); 
			quizservice.add_New_Question(ID_Ex, form);
			ArrayList<QuestionForm> ql = quizservice.get_List_QuestionForm_by_ID_Exam(ID_Ex);
			Exam e = quizservice.Get_Exam_by_ID(ID_Ex);
			request.setAttribute("quiz", e);
			request.setAttribute("queslist", ql);			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddQuestion.jsp");
			rd.forward(request, response);
		}
	}

}
