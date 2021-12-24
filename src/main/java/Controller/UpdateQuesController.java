package Controller;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BO.QuizzService;
import Model.Bean.*;

public class UpdateQuesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateQuesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			
			String id = request.getParameter("id");
			int ID_Ques = Integer.parseInt(id);
			int ID_Quiz = Integer.parseInt(request.getParameter("idQuiz"));
			String question = request.getParameter("txtQues");
			String time = request.getParameter("selectTime");
			String trueans = request.getParameter("trueAns");
			//get cac cau tra loi
			String ans1 = request.getParameter("txtAns1");
			String ans2 = request.getParameter("txtAns2");
			String ans3 = request.getParameter("txtAns3");
			String ans4 = request.getParameter("txtAns4");
			String la1[] = {ans1, ans2, ans3, ans4};
			if(la1[Integer.parseInt(trueans)-1] == "")
			{
				throw new Exception();
			}
			
			//get id cac cau tra loi
			String idans1 = request.getParameter("txtIDAns1");
			String idans2 = request.getParameter("txtIDAns2");
			String idans3 = request.getParameter("txtIDAns3");
			String idans4 = request.getParameter("txtIDAns4");
			String lid1[] = {idans1, idans2, idans3, idans4};
			
			QuizzService quizservice = new QuizzService();
			QuestionForm form = new QuestionForm();
			Question ques = new Question();
			ArrayList<Answer> la = new ArrayList<Answer>();
					
			for (int i=0; i<4; i++) {
				//neu ans va id deu khac rong thi update
				if (!la1[i].equals("") && !lid1[i].equals("")) {
					Answer a = new Answer();
					a.setAns(la1[i]);
					if (Integer.parseInt(trueans)==(i+1)) a.setSelected(true);
					else a.setSelected(false);
					a.setID(Integer.parseInt(lid1[i]));
					la.add(a);
				}//neu co ans ma khong co id thi them dap an moi
				else if (!la1[i].equals("") && lid1[i].equals("")) {
					Answer a = new Answer();
					a.setAns(la1[i]);
					if (Integer.parseInt(trueans)==(i+1)) a.setSelected(true);
					else a.setSelected(false);
					a.setID_Question(ID_Ques);
					quizservice.Add_New_Anwser(ID_Ques, a);
				}//neu co id ma khong co ans thi xoa dap an
				else if (la1[i].equals("") && !lid1[i].equals("")) {
					boolean b = quizservice.Remove_Answer(Integer.parseInt(lid1[i]));
				}
			}
			
			form.setAns(la);
			ques.setID(ID_Ques);
			ques.setQues(question);
			ques.setCountdown_Time(Integer.parseInt(time));
			form.setQues(ques);
			quizservice.Update_Question(ID_Ques, form);
			Exam q = quizservice.Get_Exam_by_ID(ID_Quiz);
			session.setAttribute("quiz", q);
			ArrayList<QuestionForm> ql = quizservice.get_List_QuestionForm_by_ID_Exam(ID_Quiz);
			session.setAttribute("queslist", ql);
			
			response.sendRedirect("/QuizzApp/AddQuestion");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("/QuizzApp/Home");
		}
	}

}
