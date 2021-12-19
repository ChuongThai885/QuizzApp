package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import Model.BO.*;
import Model.Bean.*;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect("Home");
		}
		else 
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("user");
		String pass = request.getParameter("pass");
		UserService service = new UserService();
		
		if(service.Is_Exist_User(email, pass))
		{
			try
			{
				email = new EncodeService().encodeString(email);
			}
			catch (Exception e) {
				System.out.println("Error encode email:" + e.getMessage());
			}
			Cookie cookie = new Cookie("name", email);
			response.addCookie(cookie);
			
			response.sendRedirect("/QuizzApp/Home");
		}
		else 
		{
			response.sendRedirect("/QuizzApp/Login");
		}
	}

}
