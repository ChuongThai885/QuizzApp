package QuizzApp.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import QuizzApp.Service.UserService;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("user");
		String pass = request.getParameter("pass");
		if(new UserService().CheckAccount(email, pass))
		{
			HttpSession ses = request.getSession();
			ses.setAttribute("user", email);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome.jsp");
//			dispatcher.forward(request, response);
			response.sendRedirect("Welcome.jsp");
		}
		else 
		{
			HttpSession ses = request.getSession();
			ses.setAttribute("error", "An error has occur, please try later !");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//			dispatcher.forward(request, response);
			response.sendRedirect("Login.jsp");
		}
	}

}
