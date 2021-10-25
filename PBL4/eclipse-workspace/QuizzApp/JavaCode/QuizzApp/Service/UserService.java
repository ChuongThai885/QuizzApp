package QuizzApp.Service;

import QuizzApp.DAL.*;

public class UserService {
	public boolean CheckAccount(String username, String pass)
	{
		DAL dal = new DAL();
		return dal.CheckAccount(username,pass);
	}
}
