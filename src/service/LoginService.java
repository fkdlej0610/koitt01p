package service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ILoginDao;

@Service
public class LoginService {
    @Autowired
    private ILoginDao lDao;
    
    
    	public boolean login(String userID, String password) {
    		HashMap<String, String> result = lDao.login(userID);
      		if(result == null) {
    			return false;
    		} else {
    			String oPwd = (String)result.get("password");
    			if(oPwd == null) 
    				return false;
    			else {
    				if(oPwd.equals(password)) {
    					return true;
    				} else
    					return false;
    			}
    		}
    	}
     
    
    
    public void createUser(HashMap<String, String> params) {
    	if(params.get("password").equals(params.get("pw_CHECK"))) {
    	lDao.createUser(params);
    	}
    }
}
