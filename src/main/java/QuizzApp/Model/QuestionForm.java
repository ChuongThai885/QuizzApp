package QuizzApp.Model;

import java.util.ArrayList;

public class QuestionForm {
	private Question ques;
	private ArrayList<Answer> ans;
	public Question getQues() {
		return ques;
	}
	public void setQues(Question ques) {
		this.ques = ques;
	}
	public ArrayList<Answer> getAns() {
		return ans;
	}
	public void setAns(ArrayList<Answer> ans) {
		this.ans = ans;
	}
}
