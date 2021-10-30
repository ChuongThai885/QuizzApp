package QuizzApp.Model;

import java.awt.Image;

public class Question {
	private int ID;
	private String Question;
	private Image Img;
	private boolean Is_Multy;
	private int cowndown;
	private int ID_Ex;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public Image getImg() {
		return Img;
	}
	public void setImg(Image img) {
		Img = img;
	}
	public boolean isIs_Multy() {
		return Is_Multy;
	}
	public void setIs_Multy(boolean is_Multy) {
		Is_Multy = is_Multy;
	}
	public int getCowndown() {
		return cowndown;
	}
	public void setCowndown(int cowndown) {
		this.cowndown = cowndown;
	}
	public int getID_Ex() {
		return ID_Ex;
	}
	public void setID_Ex(int iD_Ex) {
		ID_Ex = iD_Ex;
	}
	
}
