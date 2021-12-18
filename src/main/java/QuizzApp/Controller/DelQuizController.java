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
		User_Infor user = (User_Infor) request.getSession().getAttribute("user");
		if (user != null) {
			ArrayList<Exam> el = new QuizzService().Get_All_Exam(user.getEmail());
			request.setAttribute("listquizz", el);
			int numberQuiz = el.size();
			request.setAttribute("numberQuiz", numberQuiz);
			ArrayList<Integer> numberQues = new ArrayList<Integer>();			
			for (Exam e : el) {
				int n;
				n = new QuizzService().Get_Number_Of_Question(e.getID());
				numberQues.add(n);
			}
			request.setAttribute("numberQues", numberQues);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Welcome.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
