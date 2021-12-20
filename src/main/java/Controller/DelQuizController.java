package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BO.QuizzService;

public class DelQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DelQuizController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			int ID_Exam = Integer.parseInt( request.getParameter("id") );
			QuizzService quizservice = new QuizzService();
			quizservice.Remove_Exam(ID_Exam);
			response.sendRedirect("/QuizzApp/Home");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("/QuizzApp/");
		}
	}
}
