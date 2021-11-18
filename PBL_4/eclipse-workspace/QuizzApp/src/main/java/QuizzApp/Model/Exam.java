package QuizzApp.Model;

public class Exam {
	
	private int ID;
	private String Name;
	private String Topic;
	private int ID_User;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTopic() {
		return Topic;
	}
	public void setTopic(String topic) {
		Topic = topic;
	}
	public int getID_User() {
		return ID_User;
	}
	public void setID_User(int iD_User) {
		ID_User = iD_User;
	}
	@Override
	public String toString() {
		return "Exam [ID=" + ID + ", Name=" + Name + ", Topic=" + Topic + ", ID_User=" + ID_User + "]";
	}
}
