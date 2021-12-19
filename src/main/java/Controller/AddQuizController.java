package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BO.*;
import Model.Bean.*;


public class AddQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddQuizController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddQuiz.jsp");
			rd.forward(request, response);
		}catch (Exception e) {
			response.sendRedirect("/QuizzApp/");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			String quiz = request.getParameter("txtQuiz");
			String topic = request.getParameter("txtTopic");
			if (quiz.equals("") || topic.equals("")) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddQuiz.jsp");
				rd.forward(request, response);
			} else {
				HttpSession session = request.getSession();
				User_Infor user = (User_Infor) session.getAttribute("user");
				int iduser = (int) user.getID();
				QuizzService quizservice = new QuizzService();
				Exam e = new Exam(quiz, topic, iduser);
				boolean b = quizservice.Add_New_Quizz(iduser, e);
				
				ArrayList<Exam> ql = quizservice.Get_All_Exam(user.getEmail());
				Exam q = ql.get(ql.size()-1);
				session.setAttribute("quiz", q);
				
				response.sendRedirect("/QuizzApp/AddQuestion");
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("/QuizzApp/");
		}
	}

}
