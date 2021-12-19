package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BO.EncodeService;
import Model.BO.QuizzService;
import Model.BO.UserService;
import Model.Bean.Exam;
import Model.Bean.User_Infor;

/**
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			boolean check = false;
			String uri = request.getRequestURI();
			if(uri.compareTo("/QuizzApp/Home")==0)
			{
				Cookie [] cookies = request.getCookies();
				HttpSession session = request.getSession();
				for (Cookie i : cookies)
				{
					if( (i.getName()).compareTo("name") == 0 )
					{
						User_Infor user_Infor = new User_Infor();
						if(session.getAttribute("user") == null)
						{
							String email = new EncodeService().decodeString(i.getValue());
							user_Infor = new UserService().Get_User(email);
							session.setAttribute("user", user_Infor);
						}
						else
						{
							user_Infor = (User_Infor) session.getAttribute("user");
						}
						ArrayList<Exam> el = new QuizzService().Get_All_Exam(user_Infor.getEmail());
						session.setAttribute("listquizz", el);
						int numberQuiz = el.size();
						session.setAttribute("numberQuiz", numberQuiz);
						ArrayList<Integer> numberQues = new ArrayList<Integer>();			
						for (Exam e : el) {
							int n;
							n = new QuizzService().Get_Number_Of_Question(e.getID());
							numberQues.add(n);
						}
						session.setAttribute("numberQues", numberQues);
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/HomePage.jsp");
						rd.forward(request, response);
						check = true;
					}
				}
				if(!check)
				{
					response.sendRedirect("/QuizzApp/");
				}
			}
			else 
			{
				Cookie [] cookies = request.getCookies();
				HttpSession session = request.getSession();
				for (Cookie i : cookies)
				{
					if((i.getName()).compareTo("name") == 0)
					{
						response.sendRedirect("/QuizzApp/Home");
						check = true;
						break;
					}
				}
				if(!check)
				{
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Welcome.jsp");
					rd.forward(request, response);
				}
			}
		}catch (Exception e) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Welcome.jsp");
			rd.forward(request, response);
		}
	}

}
