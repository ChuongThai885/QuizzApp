package QuizzApp.Model;

public class Answer {
	private int ID;
	private boolean Selected;
	private String Answer;
	public int ID_Ques;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public boolean isSelected() {
		return Selected;
	}
	public void setSelected(boolean selected) {
		Selected = selected;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String answer) {
		Answer = answer;
	}
	public int getID_Ques() {
		return ID_Ques;
	}
	public void setID_Ques(int iD_Ques) {
		ID_Ques = iD_Ques;
	}
}
