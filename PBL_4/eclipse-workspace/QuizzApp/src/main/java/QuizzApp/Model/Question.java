package QuizzApp.Model;

public class Question {
	
	private long ID;
	private String Ques;
	private boolean Is_Multi;
	private int Countdown_Time;
	private int ID_Quizz;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getQues() {
		return Ques;
	}
	public void setQues(String ques) {
		Ques = ques;
	}
	public boolean isIs_Multi() {
		return Is_Multi;
	}
	public void setIs_Multi(boolean is_Multi) {
		Is_Multi = is_Multi;
	}
	public int getCountdown_Time() {
		return Countdown_Time;
	}
	public void setCountdown_Time(int countdown_Time) {
		Countdown_Time = countdown_Time;
	}
	public int getID_Quizz() {
		return ID_Quizz;
	}
	public void setID_Quizz(int iD_Quizz) {
		ID_Quizz = iD_Quizz;
	}
}
