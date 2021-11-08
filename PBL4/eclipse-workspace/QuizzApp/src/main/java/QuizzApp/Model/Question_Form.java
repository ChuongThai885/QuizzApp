package QuizzApp.Model;

import java.util.List;

public class Question_Form {
	private Question ques;
	private List<Answer> ans;
	public Question getQues() {
		return ques;
	}
	public void setQues(Question ques) {
		this.ques = ques;
	}
	public List<Answer> getAns() {
		return ans;
	}
	public void setAns(List<Answer> ans) {
		this.ans = ans;
	}
}
