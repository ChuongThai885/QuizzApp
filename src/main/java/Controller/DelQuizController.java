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
import Model.Bean.User_Infor;

public class DelQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DelQuizController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			String idquiz = request.getParameter("id");
			int ID_Exam = Integer.parseInt(idquiz);
			
			QuizzService quizservice = new QuizzService();
			HttpSession session = request.getSession();
			
			quizservice.Remove_Exam(ID_Exam);
			
			response.sendRedirect("/QuizzApp/Home");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("/QuizzApp/");
		}
	}
}
