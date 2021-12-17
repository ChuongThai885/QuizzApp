package QuizzApp.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QuizzApp.Model.Exam;
import QuizzApp.Model.User_Infor;
import QuizzApp.Service.QuizzService;

public class AddQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddQuizController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("AddQuiz.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String quiz = request.getParameter("txtQuiz");
		String topic = request.getParameter("txtTopic");
		if (quiz.equals("") || topic.equals("")) {
			response.sendRedirect("AddQuiz.jsp");
		} else {
			
			User_Infor user = (User_Infor) request.getSession().getAttribute("user");
			int iduser = (int) user.getID();
			System.out.println(iduser);
			QuizzService quizservice = new QuizzService();
			Exam e = new Exam(quiz, topic, iduser);
			boolean b = quizservice.Add_New_Quizz(iduser, e);
			System.out.println(b);
			ArrayList<Exam> ql = quizservice.Get_All_Exam(user.getEmail());
			Exam q = ql.get(ql.size()-1);
			request.setAttribute("quiz", q);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddQuestion.jsp");
			rd.forward(request, response);
		}
	}

}
