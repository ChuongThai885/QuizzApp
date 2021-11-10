package QuizzApp.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import QuizzApp.Service.*;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("user");
		String pass = request.getParameter("pass");
		if(new UserService().Is_Exist_User(email, pass))
		{
//			HttpSession ses = request.getSession();
//			ses.setAttribute("user", email);
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
			HttpSession ses = request.getSession();
			ses.setAttribute("error", "An error has occur, please try later !");
			response.sendRedirect("Login.jsp");
		}
	}

}
