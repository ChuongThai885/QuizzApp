package QuizzApp.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import QuizzApp.Model.Exam;
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
		UserService service = new UserService();
		if(service.Is_Exist_User(email, pass))
		{
			HttpSession ses = request.getSession();
			ses.setAttribute("user", service.Get_User(email));
			//ArrayList<Exam> el = new QuizzService().Get_All_Exam(email);
			//ses.setAttribute("listquizz", el);
			try
			{
				email = new EncodeService().encodeString(email);
				System.out.println(new EncodeService().encodeString("test"));
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
