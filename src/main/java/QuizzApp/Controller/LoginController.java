package QuizzApp.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import QuizzApp.Model.Exam;
import QuizzApp.Model.User_Infor;
import QuizzApp.Service.*;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		else response.sendRedirect("Login.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("user");
		String pass = request.getParameter("pass");
		UserService service = new UserService();
		
		if(service.Is_Exist_User(email, pass))
		{
			HttpSession ses = request.getSession();
			User_Infor user = new UserService().Get_User(email);
			ses.setAttribute("user", user);			
			ArrayList<Exam> el = new QuizzService().Get_All_Exam(email);
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
			try
			{
				email = new EncodeService().encodeString(email);
			}
			catch (Exception e) {
				System.out.println("Error encode email:" + e.getMessage());
			}
			Cookie cookie = new Cookie("name", email);
			response.addCookie(cookie);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Welcome.jsp");
			rd.forward(request, response);
			//response.sendRedirect("Welcome.jsp");
		}
		else 
		{
			HttpSession ses = request.getSession();
			ses.setAttribute("error", "An error has occur, please try later !");
			response.sendRedirect("Login.jsp");
		}
	}

}
