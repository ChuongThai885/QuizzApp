package QuizzApp.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		String name = request.getParameter("user");
		String pass = request.getParameter("pass");
		if(new UserService().CheckAccount(name, pass))
		{
			HttpSession ses = request.getSession();
			ses.setAttribute("user", name);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome.jsp");
//			dispatcher.forward(request, response);
			response.sendRedirect("Welcome.jsp");
		}
		else 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

}
