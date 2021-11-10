package QuizzApp.Model;

public class Answer {
	
	private long ID;
	private boolean selected;
	private String Ans;
	private long ID_Question;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getAns() {
		return Ans;
	}
	public void setAns(String ans) {
		Ans = ans;
	}
	public long getID_Question() {
		return ID_Question;
	}
	public void setID_Question(long iD_Question) {
		ID_Question = iD_Question;
	}
	
}
