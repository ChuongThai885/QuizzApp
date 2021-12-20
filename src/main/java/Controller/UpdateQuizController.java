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
import Model.Bean.Exam;
import Model.Bean.QuestionForm;

public class UpdateQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateQuizController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try
		{
			String idexam = request.getParameter("id");
			int ID_Exam = Integer.parseInt(idexam);
			
			QuizzService quizservice = new QuizzService();
			HttpSession session = request.getSession();
			
			ArrayList<QuestionForm> ql = quizservice.get_List_QuestionForm_by_ID_Exam(ID_Exam);
			Exam e = quizservice.Get_Exam_by_ID(ID_Exam);
			
		    session.setAttribute("quiz", e);
			session.setAttribute("queslist", ql);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddQuestion.jsp");
			rd.forward(request, response);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("/QuizzApp/");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			String idexam = request.getParameter("idexam");
			int ID_Exam = Integer.parseInt(idexam);
			String quiz = request.getParameter("txtQuiz");
			String topic = request.getParameter("txtTopic");
			
			Exam e = new Exam();
			e.setID(ID_Exam);
			e.setName(quiz);
			e.setTopic(topic);
			QuizzService quizservice = new QuizzService();
			HttpSession session = request.getSession();
			quizservice.Update_Exam(e);
			
			Exam q = quizservice.Get_Exam_by_ID(ID_Exam);
			session.setAttribute("quiz", q);
			ArrayList<QuestionForm> ql = quizservice.get_List_QuestionForm_by_ID_Exam(ID_Exam);
			session.setAttribute("queslist", ql);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddQuestion.jsp");
			rd.forward(request, response);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("/QuizzApp/");
		}
	}
}
