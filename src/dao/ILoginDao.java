package dao;

import java.util.HashMap;

public interface ILoginDao {
	
	public HashMap<String, String> login(String userID);
	
	public void createUser(HashMap<String, String> params);

}
