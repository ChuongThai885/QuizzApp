package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BO.QuizzService;
import Model.Bean.Answer;
import Model.Bean.Exam;
import Model.Bean.Question;
import Model.Bean.QuestionForm;
import Model.Bean.User_Infor;

public class AddQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddQuestionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddQuestion.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			
			HttpSession session = request.getSession();
			User_Infor user = (User_Infor) session.getAttribute("user");
			String question = request.getParameter("txtQues");
			String time = request.getParameter("selectTime");
			String trueans = request.getParameter("trueAns");
			String ans1 = request.getParameter("txtAns1");
			String ans2 = request.getParameter("txtAns2");
			String ans3 = request.getParameter("txtAns3");
			String ans4 = request.getParameter("txtAns4");
			int ID_Ex = Integer.parseInt( request.getParameter("idquiz") );
			
			String la1[] = { ans1, ans2, ans3, ans4 };
			QuestionForm form = new QuestionForm();
			Question ques = new Question();
			
			QuizzService quizservice = new QuizzService();
			ArrayList<Answer> la = new ArrayList<Answer>();

			ques.setQues(question);
			ques.setCountdown_Time(Integer.parseInt(time));
			ques.setID_Ex(ID_Ex);
			ques.setIs_Multi(false);
			form.setQues(ques);
			for (int i = 0; i < 4; i++) {
				if (!la1[i].equals("")) {
					Answer a = new Answer();
					a.setAns(la1[i]);
					if (Integer.parseInt(trueans) == (i + 1))
						a.setSelected(true);
					else
						a.setSelected(false);
					la.add(a);
				}
			}
			form.setAns(la);
			quizservice.add_New_Question(ID_Ex, form);
			ArrayList<QuestionForm> ql = quizservice.get_List_QuestionForm_by_ID_Exam(ID_Ex);
			Exam e = quizservice.Get_Exam_by_ID(ID_Ex);
			session.setAttribute("quiz", e);
			session.setAttribute("queslist", ql);

			response.sendRedirect("/QuizzApp/AddQuestion");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("/QuizzApp/");
		}
	}
}
