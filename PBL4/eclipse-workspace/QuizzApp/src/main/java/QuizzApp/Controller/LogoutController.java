package QuizzApp.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutController
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		session.removeAttribute("user");
//		session.invalidate();
		Cookie cookie = new Cookie("val", "");
		cookie.setMaxAge(0); 
		response.addCookie(cookie);
		
		response.sendRedirect("Login.jsp");
	}

}
