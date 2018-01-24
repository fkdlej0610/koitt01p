package service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IReviewDao;
@Service
public class ReviewService {
	
	@Autowired
	private IReviewDao rDao;
	
	public List<HashMap<String, String>> topSelectFestival(){
		return rDao.topSelectFestival();
	}
	public List<HashMap<String, String>> topSelectCountry(){
		return rDao.topSelectCountry();
	}
	public List<HashMap<String, String>> topSelectRestaurant(){
		return rDao.topSelectRestaurant();
	}
	
}
