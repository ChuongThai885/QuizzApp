package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BO.QuizzService;
import Model.Bean.*;

public class DelQuesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DelQuesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			int ID_Ques = Integer.parseInt( request.getParameter("id") );
			int ID_Exam = Integer.parseInt( request.getParameter("quiz") );
			
			QuizzService quizservice = new QuizzService();
			HttpSession session = request.getSession();
			
			quizservice.Remove_Question(ID_Ques);
			ArrayList<QuestionForm> ql = quizservice.get_List_QuestionForm_by_ID_Exam(ID_Exam);
			Exam e = quizservice.Get_Exam_by_ID(ID_Exam);
			session.setAttribute("quiz", e);
		    session.setAttribute("queslist", ql);
			
			response.sendRedirect("/QuizzApp/AddQuestion");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("/QuizzApp/");
		}
	}
}
