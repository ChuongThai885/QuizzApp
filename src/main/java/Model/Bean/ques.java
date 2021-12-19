package Model.Bean;

import java.util.ArrayList;

// this class for convert to object JSON for parse to nodejs server
public class ques {
	public int num;
	public int Countdown_Time;
	public String question;
	public ArrayList<String> option = new ArrayList<String>();
	public String answer;
}
