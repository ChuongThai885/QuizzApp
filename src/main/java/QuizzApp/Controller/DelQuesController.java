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

public class DelQuesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DelQuesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idques = request.getParameter("id");
		String idquiz = request.getParameter("quiz");
		int ID_Ques = Integer.parseInt(idques);
		int ID_Exam = Integer.parseInt(idquiz);
		QuizzService quizservice = new QuizzService();
		quizservice.Remove_Question(ID_Ques);
		User_Infor user = (User_Infor) request.getSession().getAttribute("user");
		ArrayList<QuestionForm> ql = quizservice.get_List_QuestionForm_by_ID_Exam(ID_Exam);
		Exam e = quizservice.Get_Exam_by_ID(ID_Exam);
		request.setAttribute("quiz", e);
		request.setAttribute("queslist", ql);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddQuestion.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
