package QuizzApp.DAL;

public class DAL {

	//select update delete create
	public boolean CheckAccount(String username, String pass)
	{
		return new DBHelper().CheckAccount(username, pass);
	}
	public boolean Create(String query)
	{
		return false;
	}
	public boolean Update(String query)
	{
		return false;
	}
	public boolean Delete(String query)
	{
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}