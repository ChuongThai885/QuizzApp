package QuizzApp.Model;

import java.util.Date;

public class Quizz_History {
	private int ID;
	private int Number_Of_Player;
	private Date Quizz_Day;
	private float Average_Score;
	private int Highest_Score;
	private int Lowest_Score;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getNumber_Of_Player() {
		return Number_Of_Player;
	}
	public void setNumber_Of_Player(int number_Of_Player) {
		Number_Of_Player = number_Of_Player;
	}
	public Date getQuizz_Day() {
		return Quizz_Day;
	}
	public void setQuizz_Day(Date quizz_Day) {
		Quizz_Day = quizz_Day;
	}
	public float getAverage_Score() {
		return Average_Score;
	}
	public void setAverage_Score(float average_Score) {
		Average_Score = average_Score;
	}
	public int getHighest_Score() {
		return Highest_Score;
	}
	public void setHighest_Score(int highest_Score) {
		Highest_Score = highest_Score;
	}
	public int getLowest_Score() {
		return Lowest_Score;
	}
	public void setLowest_Score(int lowest_Score) {
		Lowest_Score = lowest_Score;
	}
}
