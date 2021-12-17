package QuizzApp.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QuizzApp.Model.Exam;
import QuizzApp.Model.QuestionForm;
import QuizzApp.Model.User_Infor;
import QuizzApp.Service.QuizzService;

public class UpdateQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateQuizController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idexam = request.getParameter("id");
		int ID_Exam = Integer.parseInt(idexam);
		QuizzService quizservice = new QuizzService();
		ArrayList<QuestionForm> ql = quizservice.get_List_QuestionForm_by_ID_Exam(ID_Exam);
		Exam e = quizservice.Get_Exam_by_ID(ID_Exam);
		User_Infor user = (User_Infor) request.getSession().getAttribute("user");
		int iduser = (int) user.getID();
		System.out.println(iduser);
		request.setAttribute("quiz", e);
		request.setAttribute("queslist", ql);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddQuestion.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String idexam = request.getParameter("idexam");
		// String edit = request.getParameter("edit");
		int ID_Exam = Integer.parseInt(idexam);
		String quiz = request.getParameter("txtQuiz");
		String topic = request.getParameter("txtTopic");
		Exam e = new Exam();
		e.setID(ID_Exam);
		e.setName(quiz);
		e.setTopic(topic);
		QuizzService quizservice = new QuizzService();
		quizservice.Update_Exam(e);
		Exam q = quizservice.Get_Exam_by_ID(ID_Exam);
		request.setAttribute("quiz", q);
		ArrayList<QuestionForm> ql = quizservice.get_List_QuestionForm_by_ID_Exam(ID_Exam);
		request.setAttribute("queslist", ql);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddQuestion.jsp");
		rd.forward(request, response);
		// }
	}
}
