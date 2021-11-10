package QuizzApp.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import QuizzApp.Service.*;

/**
 * Servlet implementation class SignUpController
 */
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("user");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		UserService ser = new UserService();
		if(ser.Is_Exist_User(email, pass))
		{
			HttpSession session = request.getSession();
			session.setAttribute("error", "Email already in use");
			response.sendRedirect("SignUp.jsp");
		}
		else
		{
			if(ser.Create_User(name,email,pass))
			{
//				HttpSession session = request.getSession();
//				session.setAttribute("user", email);
				try
				{
					email = new EncodeService().encodeString(email);
				}
				catch (Exception e) {
					System.out.println("Error encode email:" + e.getMessage());
				}
				Cookie cookie = new Cookie("name", email);
				response.addCookie(cookie);
				response.sendRedirect("Welcome.jsp");
			}
			else
			{
				HttpSession session = request.getSession();
				session.setAttribute("error", "An error has occur, please try later !");
				response.sendRedirect("SignUp.jsp");
			}
		}
	}

}
