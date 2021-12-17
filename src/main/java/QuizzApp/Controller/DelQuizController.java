package QuizzApp.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QuizzApp.Service.QuizzService;

public class DelQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DelQuizController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idquiz = request.getParameter("id");
		int ID_Exam = Integer.parseInt(idquiz);
		QuizzService quizservice = new QuizzService();
		quizservice.Remove_Exam(ID_Exam);
		response.sendRedirect("Welcome.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
