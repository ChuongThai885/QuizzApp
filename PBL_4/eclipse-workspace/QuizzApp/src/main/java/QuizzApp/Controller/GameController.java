package QuizzApp.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import QuizzApp.Model.*;
import QuizzApp.Service.QuizzService;

/**
 * Servlet implementation class GameController
 */
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println(id);
		ArrayList<QuestionForm> q = new QuizzService().get_List_QuestionForm_by_ID_Exam(Integer.parseInt(id));
		ArrayList<ques> que = new ArrayList<ques>();
		int i = 1;
		try
		{
			for(QuestionForm a : q)
			{
				ques qs = new ques();
				qs.num = i;
				qs.Countdown_Time = a.getQues().getCountdown_Time();
				qs.question = a.getQues().getQues();
				int j = 0;
				for(Answer ans : a.getAns())
				{
					qs.option.add(ans.getAns());
					if(ans.isSelected()) qs.answer = ans.getAns();
				}
				que.add(qs);
				i++;
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		String json = new Gson().toJson(que);
		System.out.println(json);
		HttpSession ses = request.getSession();
		ses.setAttribute("quiz", json);
//		RequestDispatcher rq = request.getRequestDispatcher("Lobby.jsp");
//		rq.forward(request, response);
		response.sendRedirect("Lobby.jsp");
	}

}
